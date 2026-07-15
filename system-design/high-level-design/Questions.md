1. How do Ipv4 and IPv6 differ?

Answer:
IPv4 and IPv6 are two versions of the Internet Protocol used for identifying devices on a network. 

The main differences are:
- **Address Format**: IPv4 uses a 32-bit address format, while IPv6 uses a 128-bit address format.
- **Address Space**: IPv4 has a total of approximately 4.3 billion addresses, whereas IPv6 has a vastly larger address space with about 3.4 x 10^38 addresses.
- **Notation**: IPv4 addresses are written in decimal format (e.g.), whereas IPv6 addresses are written in hexadecimal format (e.g.).
- **Exhaustion**: IPv4 addresses are limited and have been largely exhausted, leading to the development of IPv6 to address this issue.
- **NAT Usage**: IPv4 often relies on Network Address Translation (NAT) to extend its address space, while IPv6 is designed to eliminate the need for NAT due to its vast address space.

2. what role does NAT play in managing IP addresses?

Answer:
NAT mitigates IPv4 shortages by enabling multiple devices to share a single public IP. It improves security by masking internal IPs and is widely used in enterprise and ISP networks.

3. What are the differences between public and private IPs?

Answer:
Public and private IPs serve different purposes in network communication.

**Public Ips**
1. Assigned by ISPs
2. Unique across the internet
3. Used for external communication
4. Can be static or dynamic
5. Requires registration with a central authority (IANA/Regional Internet Registries)
6. Examples: Web servers, email servers

**Private Ips**
1. Used within private/local networks
2. Cannot be accessed directly from the internet
3. Ranges defined by RFC 1918
4. Benefits -
    * Security (if any one know private ip address still system is not assessable for direct use)
    * Scalability (reuse of IPs in different networks)
    * Cost-effective (no need to register with central authority for private IPs)- get one public address and create multiple private IPs using NAT


4. Can you explain the differences between static and dynamic IPs?

Answer: 

5. What is the function of ICANN in internet governance?

Answer:

6. why do we need private IPs in system design?

Answer: Private IPs allow organizations to efficiently manage internal networking without requiring a large number of public IPs. They provide security by isolating internal systems and reducing attack exposure.

●Address Conservation - IPv4 addresses are limited; private IPs allow multiple organizations to reuse the same address ranges internally.

● Security - Private IPs keep internal systems isolated from the public internet, reducing the risk of attacks.

● Cost Efficiency - Organizations can assign many private IPs while using a single public IP for external communication.

7. How does DNS resolve IP addresses in large-scale systems?

Answer: DNS resolution involves multiple steps, including querying root and authoritative servers. Large-scale systems optimize this process using caching and CDNs to reduce latency.

8. Explain how a load balancer distributes traffic using Ips? 

Answer:  A load balancer ensures even distribution of network traffic across multiple servers, improving scalability and reliability. It operates at different layers, including DNS-based distribution and TCP/HTTP routing.

9.what is load balancing and why its important in system design?

Answer: Load balancing is the process of distributing incoming network traffic across multiple backend servers to ensure efficient utilization, prevent overload, and improve system availability.

● Ensures High Availability: Prevents system downtime by redirecting traffic in case of server failure.

● Optimizes Resource Utilization: Spreads requests evenly to avoid overloading a single server.

● Improves Performance: Reduces latency by routing traffic to the best-performing server.

● Enhances Scalability: Supports horizontal scaling by adding more servers as demand grows.

● Increases Fault Tolerance: Redirects requests if a server fails, ensuring system reliability.

10.Explain the difference between Layer 4 and Layer 7 load balancers?

Answer: Layer 4 Load Balancing (Transport Layer)

● Operates at the network transport level (TCP/UDP).

● Distributes traffic based on IP addresses and port numbers without inspecting request content.

● Faster and more efficient for simple traffic distribution.

● Examples: AWS Network Load Balancer (NLB), HAProxy (L4 Mode).

Layer 7 Load Balancing (Application Layer)

● Works at the application level (HTTP/HTTPS).

● Routes requests based on content, headers, cookies, or URL paths.

● Supports advanced features like SSL termination, caching, and authentication.

● Examples: AWS Application Load Balancer (ALB), Nginx, Traefik.

Key Difference: Layer 4 is faster but less flexible, while Layer 7 is intelligent but adds overhead.

11. How does load balancer handle high availability and failover?

Answer:● Health Checks: Continuously monitors server health using ping, HTTP checks, or TCP checks.

● Automatic Failover: If a server becomes unresponsive, the load balancer redirects traffic to healthy servers.

● Redundancy: Can be deployed in active-active or active-passive configurations.

● Session Persistence: Maintains user sessions across multiple requests to prevent disruptions.

● Global Load Balancing: Uses GeoDNS or Anycast Routing to distribute traffic across data centers.

12. Compare Round Robin and Least Connections load balancing strategies?

Answer: **Round Robin**

● Sends requests to servers in a circular order (Server 1 → Server 2 → Server 3 → Repeat).

● Best for: Uniform workloads and servers with equal capacity.

● Limitations: Can overload servers if they have different processing power.

**Least Connections**

● Sends requests to the server with the fewest active connections.

● Best for: Scenarios where some requests take longer than others (e.g., database queries).

● Limitations: Requires tracking active connections, increasing computational overhead.

Key Difference: Round Robin is simpler but assumes equal server capacity, while Least Connections dynamically adjusts based on load.

13. What are the advantages of Weight-based load balancing?

Answer:Weighted Load Balancing assigns different priorities to servers based on their capacity.

● Better Resource Utilization: High-performance servers receive more traffic.

● Custom Traffic Distribution: Allows fine-tuned control over request routing.

● Supports Heterogeneous Environments: Works well when servers have different processing power.

● Examples: Weighted Round Robin, Weighted Least Connections.

14. When would you use a software based load balancer versus a hardware-based one?

Answer: Software Load Balancer
● Runs as an application on standard hardware.

● Pros:

○ Cost-effective and flexible.

○ Easily scalable (deployed in containers or VMs).

○ Supports open-source solutions like Nginx, HAProxy, Envoy.

● Cons:

○ Requires server resources.

○ May introduce additional latency under heavy traffic.

Hardware Load Balancer

● A dedicated device optimized for handling large-scale traffic.

● Pros:

○ High performance with dedicated hardware acceleration.

○ Built-in security features (e.g., DDoS protection).

● Cons:

○ Expensive and less flexible.

○ Harder to scale dynamically.

Use Case:

● Use software load balancers for cloud-native applications.

● Use hardware load balancers for enterprise-level, high-traffic systems.

15. How would you design a scalable load balancing solution for a large e-commerce website?

Answer:1. Use Multiple Load Balancers:

○ Deploy primary and secondary load balancers for redundancy.

○ Distribute traffic globally using DNS-based load balancing.

2. Choose the Right Load Balancer:
   
    ○ Use Layer 7 load balancing for dynamic content.
   
    ○ Use Layer 4 load balancing for database connections.

3. Implement Load Balancing Strategies:
 
    ○ Round Robin for static content servers.
   
    ○ Least Connections for dynamic request handling.

4. Ensure High Availability:
 
    ○ Use auto-scaling groups to handle traffic spikes.

    ○ Implement health checks to detect and bypass failed servers.

5. Optimize Performance:
 
    ○ Enable caching (e.g., CDN) to reduce load on backend servers.

    ○ Use Gzip compression and minification to reduce response sizes.

16. What factor should be considered when choosing a load balancing strategy?

Answer: 1. Traffic Pattern:

○ If traffic is evenly distributed, use Round Robin.

○ If requests vary in complexity, use Least Connections.

2. Server Capacity:
 
    ○ If servers have different capacities, use Weighted Load Balancing.

3. Session Persistence:
   
    ○ If user sessions must be maintained, use Sticky Sessions.

4. Performance vs. Complexity:
 
    ○ Layer 4 is faster but less flexible.

    ○ Layer 7 is slower but allows intelligent routing.

5. Scalability Needs:

    ○ For cloud-native applications, use cloud-based load balancers (e.g., AWS ELB).

    ○ For on-premises applications, use software or hardware-based solutions.

17. How does a load balancer improve security?

Answer: 

1. **DDoS Protection**

○ Detects and blocks malicious traffic spikes.

○ Some hardware load balancers provide built-in DDoS mitigation.

2. **SSL Termination**
 
    ○ Offloads SSL decryption from backend servers.

    ○ Ensures secure connections with HTTPS.

3. **Access Control**
 
    ○ Restricts access using firewalls and IP whitelisting.

4. Application Firewall Integration
 
    ○ Prevents SQL injection, cross-site scripting (XSS), and other attacks.

5. Rate Limiting

    ○ Limits requests per second to prevent abuse.


18. In a consistent hashing setup, servers A, B, C, and D are assigned points on a hash ring:
* Server A: 0x275D8A1F (Decimal: 660441631)
* Server B: 0xABE4C723 (Decimal: 2883897123)
* Server C: 0x601F392A (Decimal: 1612658986)
* Server D: 0xFF8B19D6 (Decimal: 4287306198)

A request with the hash 0x8F74B2E1 (Decimal: 2406789857) needs to be routed. Which server will handle this request?

Answer: Server B

19. In a distributed system, a consistent hashing algorithm is used for load balancing among servers A, B, C, and D, each represented by their unique hash values on the ring:
* Server A: 0x12AB34CD (Decimal: 313210061)
* Server B: 0x23BC45DE (Decimal: 599541214)
* Server C: 0x34CD56EF (Decimal: 885872367)
* Server D: 0x56EF1234 (Decimal: 1458508340)

A new server, Server E, with the hash value 0x45DE67F0 (Decimal: 1172203504), is added to the system. After adding Server E, which server(s) will be responsible for handling the data that was previously assigned to Server D?

Answer: Server D and E

20. Imagine you're designing a database system for a financial application where data integrity is crucial. Which cache management strategy would be most suitable to ensure that the cached data and main memory remain consistent at all times?

Answer: Write-Through Caching

21.In a gaming application, where performance is critical and data consistency can be temporarily sacrificed, which cache invalidation strategy would be most appropriate to enhance responsiveness and reduce the number of main memory writes during frequent small updates to player positions?

Answer: Write-Back Caching

22. What can be a drawback of relying heavily on global caching in a distributed system?

Answer : One drawback of relying heavily on global caching in a distributed system is the potential for cache coherence issues. When multiple nodes access and modify the same data, ensuring that all caches reflect the most recent updates can be challenging. This can lead to stale data being served to users, which may compromise data integrity and consistency across the system.

Additionally, global caching can introduce latency and increase network traffics due to the need for synchronization between caches, especially in geographically distributed systems.

23. Which statement best describes the role of local caches in a distributed system?

Answer: Local caches store frequently accessed data closer to the user that improve data access speed from individual node, reducing latency and improving performance by minimizing the need to fetch data from remote servers.

24. Which cache type is more likely to suffer from cache coherence issues in a distributed environment?

Answer: Local Cache

24. On the Scaler platform, the process of storing and retrieving test cases for programming problems is being optimized. These test cases are regularly updated or modified, and some of them are quite large. To minimize data transfer and improve response times, what type of mechanism should Scaler use to efficiently manage these test cases?

Answer: Leveraging local caching on app servers and updating cached files on access, the app server should first query metadata like timestamp or file size to determine if updates are needed.

25. You're designing a system that requires quick data access for frequent read and write operations. Which of the following technologies would be most suitable for this need?

Answer: A system like Redis with in-memory storage. Redis operates as a single-threaded server.

26.
