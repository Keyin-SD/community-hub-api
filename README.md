# Community Hub API

Community Hub API is a Spring Boot backend that manages a list of community resources — things like BBQs, workshops, food drives, and volunteer opportunities. It's the main entry point for students working on this project, and this document outlines what the project is and what we're building toward for the MVP.

## Table of Contents

- [Project Goal](#project-goal)
- [MVP Scope](#mvp-scope)
- [Example Resources](#example-resources)
- [Resource Data Model](#resource-data-model)
- [Architecture](#architecture)
- [Tech Stack](#tech-stack)
- [Getting Started](#getting-started)
- [API Documentation](#api-documentation)
- [Deployment](#deployment)

## Project Goal

Build a working API that manages a list of community resources, paired with a frontend where users can search and browse them. Booking/reservation functionality is **not** in scope yet — this first pass is read/search-focused for end users, with full management capability on the backend.

## MVP Scope

- A working API that manages community resources with full CRUD support:
  - Get one resource
  - Get all resources (paginated)
  - Search resources (e.g. by title, category, or type)
  - Create a resource
  - Update a resource
  - Delete a resource
- Backend deployed to Render
- Frontend deployed to Render (possible, TBD)
- Frontend displays the list of community resources so users can search and browse them (no booking/reservations yet)

## Example Resources

A few examples of the kinds of resources this API will manage:

- Community BBQ
- Coding Workshop
- Food Drive
- Soccer Program
- Volunteer Opportunities

## Resource Data Model

Each resource has the following fields:

| Field | Description |
|---|---|
| Title | Name of the resource |
| Description | Details about the resource |
| Category | Category the resource belongs to |
| Type | Type of resource |
| Date | Date of the event/resource |
| Time | Time of the event/resource |
| Location | Where it takes place |
| Cost | Cost to attend/use, if any |
| Contact Name | Name of the contact person |
| Contact Email | Contact email address |
| Contact Phone | Contact phone number |
| Website | Related website URL |

## Architecture

This project follows standard Spring Boot conventions with a layered architecture:

- **Entity** — represents the data model for each resource
- **Repository** — handles the data access layer
- **Service** — contains the business logic
- **Controller** — handles HTTP requests and responses

## Tech Stack

- **Framework:** Spring Boot
- **Database:** MySQL
- **API Docs:** Swagger, available at `/api-docs` (no auth on this endpoint yet)

## Getting Started

### Prerequisites

- Java 25
- Maven
- A local MySQL instance

### Local Configuration

`application.properties` is excluded from version control via `.gitignore`. Create your own local copy in `src/main/resources/application.properties` with the following:

```properties
spring.jpa.hibernate.ddl-auto=update
spring.datasource.url=jdbc:mysql://localhost:3306
spring.datasource.username=
spring.datasource.password=
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.show-sql=true
```

Fill in your `spring.datasource.username` and `spring.datasource.password` with your local MySQL credentials.


## API Documentation

Once the app is running, Swagger docs are available at:

```
/api-docs
```

> Note: This endpoint is not currently secured with authentication.

## Deployment

- **Backend:** Render
- **Frontend:** Render (possible, TBD)
