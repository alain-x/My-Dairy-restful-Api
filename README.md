# My-Dairy-restful-Api
 
This project provides a simple API for managing diary records.

## Endpoints

### Authentication
- **Signup**: `POST http://localhost:8080/auth/signup`
- **Signin**: `POST http://localhost:8080/auth/signin`

### Diary Management
- **Create a Diary Entry**: `POST http://localhost:8080/api/create?userId={id}`
- **Get All Diary Entries**: `GET http://localhost:8080/api/getAllDiary`
- **Get Diary Entries by User**: `GET http://localhost:8080/api/getDiary?userId={id}`
- **Update a Diary Entry**: `PATCH http://localhost:8080/api/{id}?userId={id}`
- **Delete a Diary Entry**: `DELETE http://localhost:8080/api/{id}?userId={id}`

### Group 10
- **14288/2018**
- **20554/2022**
- **20594/2022**

Feel free to test these endpoints using Postman or any API client.
