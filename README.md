# Spring Boot with MongoDB in Action

**Example of implementing CRUD operations for MongoDB in Spring Boot applications**

## Overview

The application is designed as an internal microservice with no public access to it and no user interface.

[API documentation](http://localhost:8080/swagger-ui/index.html#/) will be available once the application is started

## Technologies

- `Java` - version `21`
- `Maven` - for building the application
- `Spring Boot` - version `3.4.3`
- `Spring Cloud` - version 2023.0.3
- `Spring Boot Actuator` - it's for real-world applications
- `Spring Boot Maven Plugin` - for create Docker-Image
- `Docker` - containerization
- `Docker-Compose` - infrastructure
- `MongoDB` - NoSQL database

## Structure of the project

```
spring-boot-with-mongodb-in-action/
├── src/main/
|   ├── java/com/dudko/example/
|   |   ├── controller/             # domain level of requests and controllers
|   |   ├── domain/                 # persistent domain level and repositories
|   |   ├── model/                  # service level of the domain, used in business logic
|   |   ├── service/                # business logic
|   ├── resources/                  # configs
├── compose.yml                     # docker-compose file
├── pom.xml                         # artifact of Maven
├── postman_collection.json         # collection of requests for Postman
```

## How to try this project?

```sh
docker-compose -f compose.yml up
```

### Author:

Anatoly Dudko