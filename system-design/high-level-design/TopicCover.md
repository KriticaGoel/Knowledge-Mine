## System design building blocks
1. Database
2. Horizontal vs Vertical scaling
3. Caching
   * Types of caching
     * Client-side caching
     * Server-side caching
     * Distributed caching
   * Caching techniques
     * In-memory caching
     * In-Browser Caching
     * Distributed Caching
     * Content Delivery Network (CDN) Caching
     * Application-Level Caching
     * Global Caching
   * Problem in Caching
     * Cache Miss
     * Cache Hit
     * Cache Eviction
     * Cache Invalidation
     * Cache Consistency
   * Caching strategies
     * Read-through caching
     * Write-through caching
     * Write-Around Cache
     * Write-behind caching
   * Cache invalidation strategies
     * Time-based expiration
     * Event-based invalidation
     * Manual invalidation
   * Cache eviction policies
     * LRU (Least Recently Used)
     * LFU (Least Frequently Used)
     * FIFO (First In First Out)
4. Rate Limiter
    * Need for Rate Limiter
    * Deployment Scenarios
      * Client side - tradeoff
      * Server side- tradeoff
      * API Gateway / middleware - tradeoff
    * Rate Limiter Algorithms
     * Token Bucket Algorithm - tradeoff
     * Leaky Bucket Algorithm - tradeoff
     * Fixed Window Counter Algorithm - tradeoff
     * Sliding Window Log Algorithm - tradeoff
     * Sliding Window Counter Algorithm - tradeoff
   * Distributed-environment challenges
     * Race condition
     * Synchronization across instances
     * Restart and local counters
   * Redis availability and failure policy
     * Fail open
     * Fail closed
     * Local fallback
5. Load Balancing
6. SQl vs No Sql
7. Database scaling
8. Data replication
9. Data redundancy
10. Data partitioning
11. Database sharding
    * Sharding Definition
    * Sharding key
    * Types of sharding
      * Range based sharding
         * Pros Cons 
         * when to use
         * Real applications
      * Hash based sharding
          * Pros Cons
          * when to use
      * Directory based Sharding
          * Pros Cons
          * when to use
      * Geo-based Sharing
          * Pros Cons
          * when to use
      * Composite Sharding
          * Pros Cons
          * when to use
    * Sharding Algorithms
      * Modulo based sharding
        * Pros Cons
        * Formulae
        * Direct modulo sharding
        * Stable buckets + a mapping table
      * Range based sharding
        * Pros Cons
      * Consistent Hashing
        * Pros Cons
        * Formulae
        * When to use
      * Optimized Consistent Hashing
      * Dynamic Sharding Algorithms
12. Proxy server
13. Websocket
14. API Gateway
15. Message Queue
16. Networking Concepts (HTTP, TCP/IP, DNS)
    * IP address
    * IPv4 vs IPv6
    * Private vs Public IP
    * Static and Dynamic IP
    * ICANN
    * Vertical and Horizontal Scaling

17. Scalability and Performance 
18. Latency and Throughput 
19. Availability and Availability Patterns
20. Consistency and Consistency Patterns
21. CAP Theorem 
22. PACELC Theorem
23. API Design Principles (REST, GraphQL, Authentication, Rate Limiting)

## System Design Architecture
1. Client-Server Architecture 
2. Layered Architecture 
3. Event Driven Architecture 
4. Monolithic vs Microservices
5. Serverless Architecture
6. CQRS & Event Sourcing 
7. High Availability & Fault Tolerance

𝗦𝘁𝗲𝗽 𝟭: 𝗕𝘂𝗶𝗹𝗱 𝗦𝘁𝗿𝗼𝗻𝗴 𝗙𝘂𝗻𝗱𝗮𝗺𝗲𝗻𝘁𝗮𝗹𝘀
→ Networking Concepts (HTTP, TCP/IP, DNS, Load Balancing)
→ API Design Principles (REST, GraphQL, Authentication, Rate Limiting)
→ Database Fundamentals (SQL, NoSQL, Indexing, Partitioning)
→ Caching Techniques (Redis, CDN, In-Memory Caching)

𝗦𝘁𝗲𝗽 𝟮: 𝗟𝗲𝗮𝗿𝗻 𝗦𝗰𝗮𝗹𝗮𝗯𝗶𝗹𝗶𝘁𝘆
→ Vertical vs Horizontal Scaling
→ Traffic Distribution Strategies
→ Replication & Database Sharding
→ Message Queues & Asynchronous Processing


𝗦𝘁𝗲𝗽 𝟰: 𝗠𝗮𝘀𝘁𝗲𝗿 𝗗𝗮𝘁𝗮 𝗛𝗮𝗻𝗱𝗹𝗶𝗻𝗴
→ CAP Theorem & Consistency Trade-offs
→ Distributed Storage Systems
→ Data Replication & Partitioning
→ Query Optimization & Efficient Indexing

𝗦𝘁𝗲𝗽 𝟱: 𝗣𝗿𝗮𝗰𝘁𝗶𝗰𝗲 𝗥𝗲𝗮𝗹-𝗪𝗼𝗿𝗹𝗱 𝗗𝗲𝘀𝗶𝗴𝗻
→ Architect scalable applications
→ Explain technical trade-offs clearly
→ Participate in mock design interviews
→ Improve designs through continuous feedback



System Design Topics Covered:
 
6. Replication
10. Database and Storage
11. Relational Databases
12. Database Isolation Levels
13. Scaling Databases
14. Sharding and Partitioning
15. Non-Relational Databases
16. Choosing the Right Database
19. Message Queues (Kafka, RabbitMQ)
22. API Gateway and Backend for Frontend (BFF)
23. REST, GraphQL, and gRPC
24. Long Polling, WebSockets, Server-Sent Events (SSE)
25. Design Patterns 
29. Circuit Breakers
30. System Essentials
32. Networking and Communication