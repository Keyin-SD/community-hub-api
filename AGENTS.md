# AGENTS.md

This file gives AI coding agents (Claude Code, Cursor, Copilot, Codex, etc.) the context needed to work productively in this repository.

## Project Status

The core CRUD API is implemented and working: create, get all, get by ID, search (by category/title/contact name/location), full update (PUT), partial update (PATCH), and delete are all live in `com.communityhub.resource`. Swagger/OpenAPI docs are wired up. Remaining MVP work is mostly around pagination, frontend integration, and deployment — check `README.md`'s MVP Scope for current gaps.

Read `README.md` before starting work — it defines the MVP scope, data model, and full endpoint list this project is building toward.

## What This Project Is

A Spring Boot + MySQL API that manages a list of community resources (e.g. Community BBQ, Coding Workshop, Food Drive, Soccer Program, Volunteer Opportunities). The MVP is full CRUD for resources (get one, get all paginated, search, create, update, delete), consumed by a separate frontend for browsing/searching (no booking functionality yet).

## Intended Architecture

Follow standard Spring Boot layering — do not collapse layers or skip one:

- **Entity** (`com.communityhub.resource`) — the data model for a resource, with fields: Title, Description, Category, Type, Date, Time, Location, Cost, Contact Name, Contact Email, Contact Phone, Website.
- **Repository** — Spring Data JPA data access layer.
- **Service** — business logic; controllers should not talk to repositories directly.
- **Controller** — HTTP request/response handling only.

Alongside CRUD endpoints, the API needs a **search** capability so the frontend can let users find resources (e.g. by title, category, or type) rather than only paging through the full list. Implement this as a query-parameter-driven search endpoint (or filterable list endpoint) backed by a repository query — don't bolt search onto the client side only.

## Required Conventions

- **Java 25**, built with Maven.
- **Spring Boot** as the web framework.
- **MySQL** as the database (`spring.jpa.hibernate.ddl-auto=update`, driver `com.mysql.cj.jdbc.Driver`).
- **Swagger/OpenAPI** must be enabled by default, served at `/api-docs`, with no auth on that endpoint (this is intentional for now, not an oversight).
- `src/main/resources/application.properties` holds local datasource credentials and is expected to stay out of source control — do not commit real credentials into it. Use the template documented in `README.md` when setting up local development.

## Commands

Standard Maven workflow:

```bash
mvn spring-boot:run       # run the app locally
mvn test                  # run the full test suite
mvn test -Dtest=ClassName#methodName   # run a single test
mvn clean package          # build a jar

or just use the IDE to run with the UI if the user wants to
```

Alternatively, use `./run.sh` (loads env vars from `.env`, see README).

There is no test suite in the repository yet.
