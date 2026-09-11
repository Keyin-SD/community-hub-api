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
- **API Docs:** Swagger, available at `/swagger-ui/index.html` (no auth on this endpoint yet)

## Getting Started

### Prerequisites

- Java 25
- Maven
- A local MySQL instance

### Local Configuration

Before running the app, make sure you have a local MySQL database set up and running. Database credentials are read from environment variables rather than being committed to the repo. Copy `.env.example` to `.env` in the project root (`.env` is excluded from version control via `.gitignore`) and fill in your local MySQL credentials:

```
DB_URL=jdbc:mysql://localhost:3306/community_hub_db
DB_USERNAME=your_mysql_username
DB_PASSWORD=your_mysql_password
```

### Running the App

Use the provided `run.sh` script, which loads the variables from `.env` and starts the app:

```bash
./run.sh
```


## API Documentation

Once the app is running, Swagger docs are available at:

```
/swagger-ui/index.html
```

> Note: This endpoint is not currently secured with authentication.

## Deployment

- **Backend:** Render
- **Frontend:** Render (possible, TBD)
