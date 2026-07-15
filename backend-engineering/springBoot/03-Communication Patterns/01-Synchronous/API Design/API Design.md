## API
Application Programming Interface helps to connect two application over internet.

Application can be in same network may be in different network

## Advantage of Design API
1. Decrease the efforts of changing API.
2. Easy to manage
3. Team follows a certain standard.
4. Increase the productivity of team (sharing design to front end, backend and testing team)

## Type of API
1. **Public API** - Open API - Exposed in the internet for public - like Weather API, Stock API
2. **Partner API** - Integrate with another vendor to support specific functionality - 
3. **Private API** - Integrate modules of the same product to achieve simpler and more scalable solution.

## Existing API
Best approach is to follow the existing design to avoid changes the existing code

## Resources of an API
* Resolves around business entity
* Needn't based on physical data
* Should be noun not verb
  For example -
* Customer Resources -> customers -> not GetIdByCustomers
* Order Resources -> orders -> not GetOrderByName
* Non-resources data should be Query Parameter
   * /colleges?type=engineering -> type is not part of model its passed as a Query parameter
   * /colleges?sortBy=collegeName -> sortBy is nota  part of model

## Parts of HTTP Request

1. Request Component
    1. **Method** - GET,POST,PUT,DELETE,PATCH (partial update)
    2. **URL** - Uniquely identify resources
    3. **Header** - Metadata of request
    4. **Content** - Query Parameter, Body
2. Response Component
   1. **Status Code** - Success 2xx , Client-side error code 4xx, Server-side error code 5xx
   2. **Header** - Metadata of responses
   3. **Content** - Response body

## Types of Request parameters
1. Query Parameters -/colleges?type=engineering
2. Path Parameters - /colleges/{Id}
3. Headers
4. Cookies

## Idempotent Methods

> Making multiple identical requests has the same effect as making a single request.

Idempotent HTTP Operations Standards

| Method | Idempotent    |
|--------|---------------|
| GET    | ✅             |
| POST   | Not Gaurantee |
| PUT    | ✅             |
| PATCH  | Not Gaurantee |
| DELETE | ✅             |

??

Idempotency Keys

Payment APIs

Retries

Duplicate requests

Stripe example

## Part of Request Content
1. Content type - Inform client what type of data sent from server.
   
| Data type             | Content type                                                      |
|-----------------------|-------------------------------------------------------------------|
| JSON                  | application/json                                                  |
| XML                   | application/xml or text/xml                                       |
| pdf                   | application/pdf                                                   |
| Excel (.xlsx)         | application/vnd.openxmlformats-officedocument.spreadsheetml.sheet |
| ZIP                   | application/zip                                                   |
| csv                   | text/csv                                                          |
| Plain text            | text/plain                                                        |
| HTML                  | text/html                                                         |
| PNG                   | image/png                                                         |
| jpeg                  | image/jpeg                                                        |
| Multipart Form Upload | multipart/form-data                                               |

   Most common format for Restful APIs are **application/json**

2. Request Body 
   1. GET - None
   2. POST - Resource item details
   3. PUT - Resource item details
   4. DELETE - unique I'd 
   5. PATCH - Resource item details

## Http Status Code Class

| Class | Summary           | Description                                                    |
|-------|-------------------|----------------------------------------------------------------|
| 1XX   | Informational     | This request is received. the process is continued further     |
| 2XX   | Success           | This request was successfully received and executed            |
| 3XX   | Redirection       | Redirected to handle further action                            |
| 4XX   | Client-side Error | One or more errors in the request sent by the client           |
| 5XX   | Server-side Error | One or more errors while processing the request at the server. |

2XX status code
1. 200 OK
2. 201 Created
3. 204 No content

4XX status code
1. 400 Bad Request
2. 401 Unauthorized
3. 403 Forbidden
4. 404 Not Found
5. 405 Method not allowed

5xx status code
1. 500 Internal server error
2. 501 Not Implemented


## Pagination

Collection contain large amount of data

Paging can return subset of data at a time

Page number and page size to be used

Include total items count

May include navigation between pages

Example - GET api/v1/colleges -> return collection having 10K records

Return subset of items:

GET api/v1/colleges?page=3&pageSize2=25 -> return colleges for page 3 and 25 rows

page and pageSize can be renamed like pageNo pageSize or no and size so on

??

Offset

Cursor

Keyset

Seek Pagination

Interview question

Which pagination is best for millions of rows?

## Sorting

May include field name to sort the results on

Reduce the function at client side

Provide a default value like id  for sorting in case client not provide

SortBy is not mandate field for client

sort=name,-price

## Version API's

### Ways to Version APIs
1. No Versioning

   Def - Changing the existing api directly.

   Pros -  
   1. No additional development effort required. 
   2. Good for small teams

   Cons - 
   1. Break the existing functionality
   2. No way of tracking changes
   
   Ideal for Private APIs

2. URL Versioning ***

   Def - Add the version as part of url

   Example - GET /api/v1/colledge/10
   
   Pros - 
   1. Simple to use
   2. Very clear in usage
   
   Cons-
   1. Need to change URL everytime.
   2. Difficult to handle URL linking in responses.

   Ideal for simple APIs
   
3. Query String Versioning

   Def - Add the version as part of query string to request
         
   Query string to have default value. if client didn't send the version default may be 1

   Example - GET /api/colledge/10?verion=1

   Pros - 
   1.Existing integrations aren't affected
   2.URL structure remain same.

   Cons-
   1. Client should not forget to pass the query string
   2. Difficult to handle URL linking in responses.
   
   Ideal for any use cases

4. Header Versioning

   Def - Custom header added to indicate version

   Consider default value version if not passed

   Example - GET /api/colledge/10 Header: X-API-VERSION=1

   Pros -
   1. Existing integrations aren't affected
   2. URL structure remain same.
   3. Versioning logic is separate from API

   Cons-
   1. Client should not forget to pass the header
   2. Difficult to handle URL linking in responses.

   Ideal if clients are experienced

5. Media type Versioning

   Def - Include the version along with the Media Type Header

   Consider default value version if not passed

   Example - GET /api/colledge/10 Content-Type: application/cms.v1+json

   Pros -
   1. Ideal form of API versioning
   2. Easier to handle URL linking responses

   Cons-
   1. Complex compared to other versioning approaches
   2. Not cache-friendly

   Ideal if responses consists of URL linking

Note : URL and Query String consider in case of high performance

---

## REST Constraints (Most Important)
Client-Server

Stateless

Cacheable

Uniform Interface

Layered System

Code on Demand

## Richardson Maturity Model


## Caching


Need

Browser Cache

Gateway Cache

Redis

CDN

ETag

Cache-Control

## Performance


Compression

GZIP

Brotli

Streaming

Pagination

Field Selection

Sparse Fields

Projection

Expires

Last Modified

304

## API Security

OAuth2

JWT

API Keys

mTLS

Rate Limiting

Scopes

Refresh Token

## Searching


?q=laptop

Full-text

ElasticSearch

Ranking

Autocomplete

## API Gateway

Authentication

Routing

Logging

Rate Limit

Caching

Transformation

Circuit Breaker

## Distributed System APIs


Retry

Timeout

Circuit Breaker

Saga

Outbox

Idempotency

Exactly Once

At Least Once

## API Documentation

OpenAPI

Swagger

Redoc

Examples

SDK Generation

Mock Server

## Observability

Correlation ID

Trace ID

Request ID

Metrics

Distributed Tracing

OpenTelemetry

## API Governance

Architect level.

Naming standards

Review process

Version lifecycle

Consistency

Linting

Design Guidelines

## GraphQL vs REST

Need comparison.

Interview favorite.

## REST vs gRPC

Need

Binary

Streaming

Performance

Internal APIs
