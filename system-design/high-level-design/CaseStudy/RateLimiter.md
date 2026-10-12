# Rate Limiter — Fast Revision Notes

> **Core idea:** A rate limiter controls how many requests a client can make in a defined time period. When the configured limit is exceeded, the system applies a policy such as rejecting, delaying, queueing, degrading, or dropping the request.

## 1. The 30-second mental model

- **What:** Enforce a request quota over time.
- **Why:** Protect services, downstream dependencies, and cost; prevent abuse and unfair resource usage.
- **Where:** Client SDK (optional), API gateway, application/server, or a combination.
- **How:** Identify the caller → read/update quota state atomically → allow or apply the configured policy.
- **Typical HTTP response:** `429 Too Many Requests`; optionally include `Retry-After`.

### Example limits

- 100 requests/minute/user.
- Limit how many posts a user can view.
- A user can create at most 2 posts/second.
- At most 10 accounts/day from one IP address.
- At most 5 reward claims/week from one device.

## 2. What to do when the limit is exceeded

| Behavior | What happens | Useful when |
|---|---|---|
| **Reject** | Return `429 Too Many Requests` (often with `Retry-After`) | Public APIs, login APIs, synchronous calls |
| **Delay** | Wait until capacity is available | Short waits are acceptable and waiting is bounded |
| **Queue** | Store work for later processing | Asynchronous/background workloads; queue must be bounded |
| **Degrade** | Return cached or simplified data | Read-heavy APIs with a safe fallback |
| **Drop** | Discard work, sometimes silently | Certain internal, non-HTTP, fire-and-forget systems |

**Important:** Do not queue unlimited requests. A queue needs a maximum size, timeout/expiry, and a policy for overflow.

## 3. Benefits

1. Protect the server.
2. Prevent one user/tenant from consuming all capacity.
3. Protect downstream systems (database, payment provider, other APIs).
4. Prevent abuse and reduce brute-force traffic.
5. Control infrastructure and third-party API costs.

## 4. Design questions before implementation

- **Who is limited?** IP, user ID, API key, tenant/customer, device, endpoint, or a combination?
- **What is the scope?** Per user, per endpoint, per tenant, or global?
- **What is the quota and time window?** Requests/second, minute, day, etc.?
- **Where is it enforced?** Client SDK, gateway, application code, or separate service?
- **What is the scale?** Startup or high-volume platform?
- **Is the system distributed?** Multiple application instances must share quota state if the limit is global.
- **What happens on rejection?** `429`, `Retry-After`, delay, queue, degradation, or drop?
- **What is the failure policy?** Fail open, fail closed, or local fallback?
- **Do clients need to be informed?** Usually yes for API throttling; return a clear status and retry guidance where appropriate.

## 5. Requirements

### Functional requirements

1. Allow requests within the configured limit and restrict excess requests.
2. Support different identity/filtering criteria (IP, user, API key, tenant, endpoint).
3. Support different time windows and quota policies.
4. Return `429 Too Many Requests` when rejection is the selected policy.
5. Track request counts or token state for the configured window.
6. Support configuration and observability (limits, rejections, errors, latency).

### Non-functional requirements

- **Low latency:** Rate limiting should add minimal request overhead.
- **High availability:** A limiter outage should not accidentally collapse the whole application.
- **Fault tolerance:** Handle cache/network failures with an explicit policy.
- **Memory efficiency:** Keep state proportional to the needed quota/window.
- **Scalability:** Support growing traffic and multiple application instances.
- **Correctness vs. availability:** Strict global enforcement may require coordination and add latency; local checks are faster but can exceed the global quota.

## 6. Where to put the rate limiter

### A. Client-side limiter — optional optimization

Purpose: reduce unnecessary network calls, control retries, improve user experience, and respect quotas before traffic reaches the network.

Useful for:
- Mobile clients on slow/expensive networks.
- SDKs used by many customer applications.
- Strict third-party API quotas.
- Polling clients and accidental request floods.
- Preventing expensive retry storms.

A company can publish a client SDK/library, but cannot force every consumer to use it.

Example distribution:
- Java → Maven Central
- JavaScript → npm
- Python → PyPI

**Security boundary:** A client can disable or bypass its own limiter. Therefore, client-side limiting is not an enforcement or security boundary.

### B. Server-side limiter — source of truth

Enforces the policy even when clients are buggy or malicious. It may run in the API gateway, application, or a dedicated service.

Example Spring application flow:

```text
HTTP request
    ↓
Filter / Interceptor / AOP / dedicated component
    ↓
RateLimitService
    ↓
Redis (shared quota state, if required)
    ↓
Controller / business logic
```

Use application-level limiting when rules need application-specific context, such as:
- 100 requests/user.
- 50 requests/IP.
- 20 expensive searches/customer.

### C. API gateway / middleware

Typical gateway responsibilities include authentication, rate limiting, authorization, validation, routing, and observability.

Advantages:
- Centralized policy.
- Protects multiple backend services.
- Rejects excess traffic early.
- Reduces unnecessary application and downstream work.

**Trade-off:** Gateway rules are centralized and efficient for broad policies; application rules can understand business context better. Many systems use both.
![middleware Rate Limiter.png](../resources/middleware%20Rate%20Limiter.png)

### Recommended architecture

```text
Client
  ├── Optional SDK limiter (reduce unnecessary calls)
  ↓
Load balancer / API gateway
  ├── Authentication
  ├── Coarse-grained rate limit
  ├── Authorization / validation / routing
  ↓
Spring Boot application
  ├── Fine-grained business-aware limit (if needed)
  ↓
Shared state (e.g., Redis) and downstream systems
```

> **Interview answer:** For a public API, server-side enforcement is mandatory. If we control or provide the client SDK, we can also add client-side limiting to reduce unnecessary traffic and encourage well-behaved retries.

## 7. Client SDK: more than rate limiting

A robust SDK may provide:
- Client-side rate limiter.
- Retry with exponential backoff and jitter.
- Respect for `Retry-After`.
- Timeouts.
- Connection pooling.
- Circuit breaker.

Example response:

```http
HTTP/1.1 429 Too Many Requests
Retry-After: 3
```

Client behavior: read `Retry-After` → wait for the specified interval → retry only when safe and appropriate. Avoid synchronized retries; use backoff/jitter and enforce retry limits to prevent retry storms. Do not automatically retry non-idempotent operations unless their safety is guaranteed.

## 8. Algorithms — comparison at a glance

| Algorithm | Mental model | Burst handling | Accuracy / cost | Best fit |
|---|---|---|---|---|
| **Token bucket** | Tokens refill at a configured rate; each accepted request consumes a token | Allows bursts up to bucket capacity | Low memory; simple | General-purpose APIs needing controlled bursts |
| **Leaky bucket** | Requests enter a bounded FIFO queue and leave at a steady rate | Smooths output; queue absorbs limited bursts | Queueing delay; overflow must be handled | Workloads needing a steady processing rate |
| **Fixed window counter** | One counter per fixed time window | Can allow a boundary burst | Very memory-efficient; inaccurate at boundaries | Simple quotas where boundary bursts are acceptable |
| **Sliding window log** | Store timestamps; remove entries outside the rolling window | Strictly limits rolling-window count | Accurate, but higher memory/operation cost | Strict rolling-window limits at moderate scale |
| **Sliding window counter** | Blend previous-window count with current-window count | Smoother than fixed window | Memory-efficient approximation | High-volume limits where small approximation is acceptable |

### 8.1 Token bucket

**Parameters**
- **Bucket capacity:** Maximum tokens held.
- **Refill rate:** Tokens added per unit of time.

**Flow**
1. Request arrives.
2. Refill tokens based on elapsed time, up to capacity.
3. If at least one token is available, consume one and allow the request.
4. Otherwise, reject or apply another configured policy.

**Pros:** Simple, memory-efficient, permits controlled bursts.

**Cons:** Capacity and refill rate need tuning; a burst can still put pressure on downstream systems.

**Example:** Capacity = 20, refill = 100 tokens/second. A bucket can permit an immediate burst of up to 20 requests when full, while the long-run allowance is governed by the refill rate.

### 8.2 Leaky bucket

**Flow**
1. Request arrives.
2. If the bounded queue has room, enqueue it.
3. If the queue is full, reject/drop it.
4. Process dequeued requests at a configured outflow rate.

**Parameters:** Queue/bucket capacity and outflow rate.

**Pros:** Smooth, predictable processing rate; bounded memory when the queue is bounded.

**Cons:** Queued requests can become stale; older work can delay newer work; queueing adds latency. Define queue size, expiry, and overflow policy.

**Distinction:** A leaky-bucket *queueing* implementation delays requests. A policing implementation may reject excess traffic instead.

### 8.3 Fixed window counter

**Flow**
1. Divide time into fixed windows (e.g., each minute).
2. Increment the current window's counter for each request.
3. Allow up to the limit; reject further requests until the next window.

**Pros:** Simple and memory-efficient.

**Cons:** Boundary problem: traffic just before and after a window boundary can effectively exceed the intended rolling-window rate.

### 8.4 Sliding window log

**Flow**
1. Store each request timestamp (often in a Redis sorted set).
2. On a new request, remove timestamps older than the rolling window.
3. Count remaining timestamps.
4. If below the limit, add the new timestamp and allow; otherwise reject.

**Pros:** Accurate rolling-window enforcement.

**Cons:** Stores per-request timestamps, so memory and cleanup costs can be high. Rejected requests should not be retained as successful quota consumption unless the policy explicitly intends that.

**Example:** Limit = 2 requests/minute. If two timestamps remain in the last 60 seconds, reject the next request. Once old timestamps leave the rolling window, capacity becomes available.

### 8.5 Sliding window counter

A common approximation uses the previous fixed-window count weighted by the fraction of the previous window that overlaps the current rolling window.

```text
Estimated rolling count
  = current-window count
  + previous-window count × overlap fraction
```

If 30% of the current window has elapsed, about 70% of the previous window overlaps the rolling minute.

Example: previous count = 5, current count = 3:

```text
Estimated count = 3 + (5 × 0.70) = 6.5
```

This is an estimate, not an exact count. The implementation must define rounding and comparison behavior conservatively.

**Pros:** Memory-efficient and smoother than fixed windows.

**Cons:** Approximate; assumes the previous-window requests are distributed reasonably evenly. It can misclassify requests near the limit.

## 9. Where should counters be stored?

A relational database is usually a poor choice for a high-volume per-request counter because each request can create contention and extra I/O.

An in-memory store such as **Redis** is commonly used because it is fast and supports expiration. Common primitives include:
- `INCR` — increment a counter.
- `EXPIRE` — set a key's time-to-live.

**Important correctness note:** A separate `INCR` followed by `EXPIRE` can race or leave a key without an expiry if the process fails between operations. Use an atomic script/transaction or a carefully designed key-expiry approach. For strict limits, the check-and-update operation must be atomic.

## 10. Distributed-environment challenges

### A. Race condition

**Problem:** Two requests read the same counter before either update is visible. Both can believe capacity remains and both may be allowed, exceeding the limit.

**Solution:** Make the check-and-update atomic. Redis Lua scripts are a common approach; sorted sets can support timestamp-based sliding-window logic. Locks are possible but often add contention and complexity.

### B. Synchronization across instances

**Problem:** If each application instance maintains an independent counter, a user routed across three instances may receive roughly three times the intended allowance.

**Solution:** Use shared state such as Redis, or deliberately partition/allocate quota across instances.

Sticky sessions are not a complete solution: they can fail over, scale poorly, and do not guarantee global enforcement.

### C. Restart and local counters

**Problem:** A restarted instance may lose its local count and grant fresh allowance.

**Solution:** Use shared durable-enough state, or use bounded local quota allocation with explicit handling for restarts and scale changes.

## 11. Redis availability and failure policy

If Redis is unavailable, the application may not be able to verify the global quota. Choose policy based on the business risk.

| Strategy | What happens | Main trade-off |
|---|---|---|
| **Fail open** | Allow requests without the global check | Availability over quota accuracy; risk of overload/abuse |
| **Fail closed** | Reject requests that require verification | Quota protection over availability |
| **Local fallback** | Each instance applies an emergency local limit | Partial protection; global quota may be exceeded |

### Examples by API risk

- **Public product/catalog reads:** Consider fail-open with conservative local limits, concurrency limits, and monitoring.
- **Payment/financial APIs:** Do not blindly bypass mandatory quota or abuse checks. Fail closed for that check when required; keep payment idempotency and business validation separate.
- **Expensive report generation:** Use bounded local fallback and/or a bounded queue; reject excess work rather than allowing unlimited jobs to accumulate.

### Is Redis a single point of failure (SPOF)?

It can be if deployed without suitable resilience. Options include:

1. **Redis high availability:** Primary/replica with automated failover.
    - Trade-off: failover takes time; asynchronous replication can lose some recent state.
2. **Bounded local token allocation:** Allocate a portion of the global quota to each instance.
    - Trade-off: strict global limits are harder when instances scale, restart, or receive uneven traffic.
3. **Explicit failure policy:** Fail open, fail closed, or enforce conservative local limits.
    - Trade-off: availability, security, and exact global enforcement cannot always all be maximized at once.

## 12. Quick interview decision guide

- Need controlled bursts and simple enforcement? **Token bucket.**
- Need a steady processing rate and can tolerate bounded queueing? **Leaky bucket.**
- Need the simplest counter and can accept boundary bursts? **Fixed window.**
- Need accurate rolling-window counts and can afford per-request state? **Sliding window log.**
- Need a memory-efficient rolling-window approximation? **Sliding window counter.**
- Need protection across multiple app instances? **Shared atomic state or carefully allocated quotas.**
- Need early rejection across many services? **API gateway.**
- Need business-aware rules? **Application-level limiter.**
- Need to reduce unnecessary client traffic? **Optional SDK limiter; never rely on it as the enforcement boundary.**

## 13. Common interview traps

1. **“100 requests/second” is ambiguous.** Clarify whether it means per user, API key, tenant, instance, or globally.
2. **Rate ≠ concurrency.** A limit of 100 requests/second does not mean only 100 requests may be in flight. A separate concurrency limit may be needed.
3. **Local counters are not global counters.** Three instances with independent limits can multiply the effective allowance.
4. **Atomicity matters.** The check and increment must be one atomic operation for strict limits.
5. **Retries can amplify overload.** Use `Retry-After`, bounded retries, exponential backoff, and jitter.
6. **Queues must be bounded.** An unbounded queue turns overload into growing latency and memory exhaustion.
7. **Fail open is not universally right.** Select the policy based on business risk and the contract.
8. **Gateway and application limiters can coexist.** Gateway handles broad protection; application logic handles context-specific rules.
9. **A rate limiter is not a full abuse-prevention system.** Authentication, authorization, fraud controls, concurrency limits, and business validation may also be required.
10. **Be precise about “leaky bucket.”** Queueing implementations delay work; policing implementations may reject excess traffic.

## 14. Rapid recall — flashcards

**Q: What is a rate limiter?**  
A: A component that controls request volume per identity/resource over time.

**Q: What HTTP status commonly signals throttling?**  
A: `429 Too Many Requests`; `Retry-After` can indicate when to retry.

**Q: Is client-side limiting enough for a public API?**  
A: No. Clients can bypass it; enforce the quota server-side.

**Q: Why use Redis?**  
A: Fast shared in-memory state, TTL support, and atomic operations/scripts.

**Q: Why can a fixed window be unfair at boundaries?**  
A: Requests can cluster just before and after the reset, exceeding the intended rolling rate.

**Q: Which algorithm allows controlled bursts?**  
A: Token bucket, up to the available bucket capacity.

**Q: Which algorithm smooths output through a queue?**  
A: Leaky bucket (queueing form).

**Q: Which algorithm is exact for a rolling window but memory-intensive?**  
A: Sliding window log.

**Q: Why can local counters fail in a distributed system?**  
A: Each instance sees only its own traffic; aggregate allowance can exceed the global quota.

**Q: How do you prevent counter race conditions?**  
A: Make the quota check/update atomic, e.g., with a Redis Lua script.

**Q: What are the main Redis-outage choices?**  
A: Fail open, fail closed, or use a bounded local fallback.

**Q: What is the central design trade-off?**  
A: Strict global accuracy versus latency, availability, and coordination cost.

---

## 15. One-minute system-design answer

“I would first clarify the quota scope, identity key, time window, burst tolerance, and behavior when the limit is exceeded. For public APIs, I would enforce a coarse limit at the API gateway and add application-level limits only when business context is needed. For a distributed deployment, I would use shared state such as Redis and ensure the quota check/update is atomic, for example with a Lua script. I would choose the algorithm based on the contract: token bucket for controlled bursts, fixed window for simplicity, or sliding-window approaches for rolling limits. I would return 429 with Retry-After where appropriate, protect clients and dependencies from retry storms, monitor rejections and limiter latency, and explicitly choose fail-open, fail-closed, or bounded local fallback for Redis outages based on business risk.”

## Final memory hook

**WHO is limited? → HOW MUCH? → OVER WHAT WINDOW? → WHERE enforced? → WHAT algorithm? → WHERE is state? → IS update atomic? → WHAT if state store fails? → HOW does client retry?**
