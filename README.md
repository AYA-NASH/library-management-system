# Library Management System API

## Overview
The Library Management System API is built using Spring Boot and provides functionalities to manage books and patrons. It supports operations such as creating, reading, updating, and deleting (CRUD) records for both entities.

## Features
- Books Management: Manage books with attributes such as id, title, author, ISBN, and quantity.
- Patrons Management: Manage patrons with attributes such as id, name, address, phone, and email.
- RESTful Endpoints: Expose RESTful API endpoints for interacting with the system.
- Validation and Error Handling: Handle input validation and provide meaningful error messages.

## Project Structure
- Entities: Define the data models for books and patrons.
- Repositories: Provide data access layers for entities.
- Services: Implement business logic for managing entities.
- Controllers: Handle HTTP requests and responses.
- DTOs: Use Data Transfer Objects to decouple persistence models from API interfaces.
- Mappers: Map between entity models and DTOs using MapStruct.

## Getting Started
### Prerequisites
**1. Install Maven dependencies for:**
  - Spring Boot Starter Web
  - Spring Boot Starter Data JPA
  - PostgreSQL
  - Lombok
  - Spring Boot Starter Validation
  - Spring Boot Starter Test
  - JUnit/Mockito

**2. Set Database properties in properties file:**
```
spring.application.name=librarymanager
spring.datasource.url=jdbc:postgresql://localhost:5432/library_manager_db
spring.datasource.username=
spring.datasource.password=

spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.show-sql=true
```
### API endpoints
Base URL: http://localhost:8080/api

**1. Books endpoints:**
  - GET    /books: Retrieve all books
  - POST   /books/create: Create a new book
  - GET    /books/{id}: Retrieve a book by ID
  - PUT    /books/{id}: Update a book by ID
  - DELETE /books/{id}: Delete a book by ID

**2. Patrons endpoints:**
  - GET    /patrons: Retrieve all patrons
  - POST   /patrons: Create a new patron
  - GET    /patrons/{id}: Retrieve a patron by ID
  - PUT    /patrons/{id}: Update a patron by ID
  - DELETE /patrons/{id}: Delete a patron by ID

**3. Borrowing Records endpoints:**
  - POST /borrow/{bookId}/patron/{patronId}: Record Borrow Operation
  - PUT /return/{bookId}/patron/{patronId}:  Record Return Operation

## Completed Work
- Configured MVC structure for managing application logic.
- Set up database with Postgresql
- Implemented CRUD operations for Book, Patron, and Borrowing Reecords entities, and manage the reelationship between them.
- Validated input for API requests.
- Transaction Management.
- Developed unit tests for Service Layer.

## Next Steps
- Complete unit tests for Controller, and Reppository Layers
- Implement security features for authentication and authorization.
- Add logging and monitoring for the application.
- Explore caching mechanisms for improved performance.
- Implement additional features such as borrowing records
