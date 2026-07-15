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

## Sorting

May include field name to sort the results on

Reduce the function at client side

Provide a default value like id  for sorting in case client not provide

SortBy is not mandate field for client

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

## Steps to design an API
College Management System
### Phase 1 – Understand the Business

#### Step 1: Understand the Business Problem
* What problem are we solving?
* Who are the users?
* What are the business rules?
  
Example:

> College Management System

Actors:
* Student
* Faculty
* Admin

### Phase 2 – API Basics
#### Step 2: Create a new API metadata
*  Title: OpenAPI specification for OMS
*  Description: API specification document for the OMS System
*  Contact : <Username> <email> <contact>
*  Version: 1.0

#### Step 3: Decide the API Style

Choose one:

* REST
* GraphQL
* gRPC
* SOAP

#### Step 4: Identify the type of API
    public

#### Step 5: Identify server base url
http://{hostname}:{portNo}/{directory}

port and directory is optional

Example
>https://college.com/api/v1
   
### Phase 3 – Resource Design
#### Step 6: Identify the Resources
      
Go through the requirement to find the resources
      
Use hyphens for multi-words

Example

      Students
      Courses
      Faculty
      Departments
      Attendance
      Fees
      Library
   
#### Step 7: Have the resources as plural
      courses -(api/v1/courses)
      students -(api/v1/students)
      faculty -(api/v1/faculties)

#### Step 8: Define the Resource Model
      Course Model
         Course Id
         Course Name
         Course Duration
         Course Type
      Student Model
         Student Id
         Student Name
         Student phone
         Student email
         Address
         status

#### Step 9: Select the identifier for each Resource  
      Course Model
         Course Id (IDENTIFIER)
         Course Name
         Course Duration
         Course Type
      Student Model
         Student Id (IDENTIFIER)
         Student Name
         Student contact number
         Student email id
         Address

#### Step 10: Identify the association between Resources
      Courses
         api/v1/courses/{courseId}/students
         api/v1/courses/{courseId}/course-subjects

#### Step 11: Check the URL Complexity

Avoid deeply nested URLs like

Good -> item/id/item

bad -> item/id/item/id....

      Courses
         api/v1/courses
         api/v1/courses/{courseId}
         api/v1/{courseId}/students

### Phase 4 – Operations

#### Step 12: Identify the Operations for each Resource
Get and post  -> collections
get,put,patch and delete  -> individual items

      Courses
         api/v1/courses
            GET
            POST
         api/v1/courses/{courseId}
            GET
            PUT
            PATCH
            DELETE
         api/v1/courses/{courseId}/students
            GET
            POST
         

#### Step 13: Identify the parameters required to the operation

1. Query Parameters -> GET /students?department=IT
2. Path Parameters -> 
   1. None (collections)
   2. courseId - unique course id for the course model (applicable for individual item)
   
   GET api/v1/courses/{courseId}
3. Headers - None
4. Cookies - None

#### Step 14: Define Request Headers
Example

Authorization

Accept

Content-Type

If-Match

#### Step 15: Identify the Content Type of request for the Operation

      application/json

#### Step 16: Identify the Request Body for the Operation
      
      Courses

         GET api/v1/courses
            RequestBody : None

         POST api/v1/courses
            RequestBody :
            {
               "courseName"
               "courseDuration"
               "courseType"
            }

         GET api/v1/courses/{courseId}
            RequestBody : None

         PUT api/v1/courses/{courseId}
            RequestBody :
            {
               "courseName"
               "courseDuration"
               "courseType"
            }

         PATCH api/v1/courses/{courseId}
            RequestBody :
            {
               "courseName"
               "courseDuration"
               "courseType"
            }

         DELETE api/v1/courses/{courseId}
            RequestBody : None

         GET api/v1/courses/{courseId}/students
            RequestBody : None

         POST api/v1/courses/{courseId}/students
            RequestBody :
            {
               "courseName"
               "courseDuration"
               "courseType"
            }
      

### Phase 5 – Response Design
#### Step 17: Identify the Http Status codes for the operation

      Courses
         api/v1/courses
            GET 
               HTTP 200 OK
            POST
               HTTP 201 CREATED
               HTTP 400 BAD REQUEST
         api/v1/courses/{courseId}
            GET
               HTTP 200 OK
               HTTP 404 NOT FOUND
            PUT
               HTTP 200 OK
               HTTP 404 NOT FOUND               
            PATCH
               HTTP 200 OK
               HTTP 404 NOT FOUND 
            DELETE
               HTTP 204 NO CONTENT
               HTTP 404 NOT FOUND

#### Step 18: Decide Response Headers
Example

Location

ETag

Cache-Control

#### Step 19: Identify the Content Type of Response for the operation

#### Step 20: Identify the Response Body for the operation

Courses

         GET api/v1/courses
            RequestBody : None
            ResponseBody:
                  [{
                     "courseId"
                     "courseName"
                     "courseDuration"
                     "courseType"
                  }]

         POST api/v1/courses
            RequestBody :
            {
               "courseName"
               "courseDuration"
               "courseType"
            }
            ResponseBody:
                  {
                     "courseId"
                     "courseName"
                     "courseDuration"
                     "courseType"
                  }

         GET api/v1/courses/{courseId}
            RequestBody : None
            ResponseBody:
                  {
                     "courseId"
                     "courseName"
                     "courseDuration"
                     "courseType"
                  }

         PUT api/v1/courses/{courseId}
            RequestBody :
            {
               "courseName"
               "courseDuration"
               "courseType"
            }
            ResponseBody:
            {
               "courseId"
               "courseName"
               "courseDuration"
               "courseType"
            }

         PATCH api/v1/courses/{courseId}
            RequestBody :
            {
               "courseName"
               "courseDuration"
               "courseType"
            }
            ResponseBody:
            {
               "courseId"
               "courseName"
               "courseDuration"
               "courseType"
            }

         DELETE api/v1/courses/{courseId}
            RequestBody : None
            ResponseBody : None

         GET api/v1/courses/{courseId}/students
            RequestBody : None
            ResponseBody:
                  [{
                        "studentId"
                        "studentName"
                        "email"
                        "phoneNo"                        
                  }]

         POST api/v1/courses/{courseId}/students
            RequestBody :
            {
               "studentName"
               "email"
               "phoneNo"
            }
            ResponseBody:
            RequestBody :
            {
               "studentId"
               "studentName"
               "email"
               "phoneNo"
            }

#### Step 21: Handle Errors for the operation
      {
         "error":{
                     "code"
                     "message"
                     "target"
                     "details"
                        [
                           {
                              "code"
                              "message"
                              "target"  
                           }
                        ]
                     "InnerError"
                           {
                              "message"
                           }
                 }
      }
### Phase 6 – Query Capabilities

#### Step 22: Identify the need for filtering and add if needed

Collection contain large amount of data

Pass the filter as a Query string in url

Example - GET api/v1/colleges -> return collection having 10K records

Better way add filter

GET api/v1/colleges?zipcode=10908 -> return colleges in particular area

      GET api/v1/courses
      Request:
         Query Parameters (optional)
         courseType:
            - Support Filtering
            - Filter the result by course type
            - api/v1/courses?courseType=Engineering

#### Step 23: Identify the need for pagination and add if needed

      GET api/v1/courses
      Request:
         page:
            - Support for pagination
            - Page number of the result to fetch
            Example api/v1/courses?page=1&size=4
         size:
            - Support for pagination
            - Page size of each result.
            Example api/v1/courses?page=1&size=4

#### Step 24: Identify the need for Sorting and add if needed
   
      GET api/v1/courses
      Request:
         sortBy:
            - Support for Sorting the result
            Example api/v1/courses?sortBy=courseType         

### Phase 7 – Security
#### Step 25: Authentication

* JWT
* OAuth2
* API Key

#### Step 26: Authorization

Who can call which API?

   Admin
   
   Faculty
   
   Student
#### Step 27: Validation Rules

Examples

   Name mandatory
   
   Email format
   
   Phone length
   
   Department must exist

### Phase 8 – Performance
#### Step 28: Caching
Cache-Control

ETag
#### Step 29: Rate Limiting
100 requests/minut#### Step 30: Idempotency

Required for
   
   Payments
   
   Order APIs

### Phase 9 – Versioning
#### Step 31: Identify the API versioning Schema and set the API Version

Versioning Schema used: URL Versioning

Version: 1.0




### Phase 10 – Documentation

#### Step 32: OpenAPI / Swagger

Document

* Request
* Response
* Errors
* Authentication
* Examples