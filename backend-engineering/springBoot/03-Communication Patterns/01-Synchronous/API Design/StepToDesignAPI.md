
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