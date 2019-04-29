# REST API with Domain Events

## Technology Stack:

* Java 8, Maven, Lombok, SLF4J;
* Spring Boot, Spring MVC, Spring Data;

## Requirements

1. JDK - 1.8+

2. Maven - 3.2+

## Steps to Setup

1. Clone the application

`git clone https://github.com/gharachide/gh-user-api.git`

2. Build and run the backend app using maven

`mvn package`
`java -jar target/gh-user-api.jar`

Alternatively, run the app without packaging it using:

`mvn spring-boot:run`

The backend server will start at <http://localhost:8080>.

## Endpoints

POST /users - creates a new user
GET /users - retrieves all users
GET /users/{email} - retrieves an user
PUT /users/{email} - updates an user
DELETE /users/{email} - deletes an user
DELETE /users - deletes all users

GET /users/history - retrieves all history
GET /users/history/{email} - retrieves the history of an user

## Tests
`mvn test`