> Integrate Gmail to n8n


Step 1 : Add credentials - Login via google account

Step 2 : Select operation Get Many  - To read email

Step 3 : Add filter - receive after and add expression -

{{$now.minus(1,'day')}}

To draft an email via n8n

Step 4 : Select Resources as Draft

Step 5: Select operation as Create

Step 6 : Add subject

Step 7: Add Message - Define automatically by the modal

Step 8: Select option to Email and add receiver id 