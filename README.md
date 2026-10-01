# IT Radar

[![Java](https://img.shields.io/badge/Java-25-orange.svg)](https://openjdk.org/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.16-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Version](https://img.shields.io/badge/version-0.0.1--SNAPSHOT-blue.svg)](pom.xml)

REST API that aggregates IT job offers and analyzes skill demand in the market. The service periodically pulls listings from an external jobs API, stores them in MySQL, exposes job CRUD-style read/write endpoints, and ranks technologies by how often they appear in offers—including skills that co-occur with a chosen technology.

## Table of contents

- [Project description](#project-description)
- [Tech stack](#tech-stack)
- [Getting started locally](#getting-started-locally)
- [Available scripts](#available-scripts)
- [Project scope](#project-scope)
- [Project status](#project-status)
- [License](#license)

## Project description

**IT Radar** helps answer questions such as “Which skills are most in demand?” and “What else do employers expect alongside skill X?”. Job data is modeled after offers from [Just Join IT](https://justjoin.it) (`/api/offers`).

Core capabilities:

| Area | Behavior |
|------|----------|
| **Job ingestion** | Scheduled HTTP client fetches offers from a configurable base URL (default: `https://justjoin.it:443/api/offers`). New offers are deduplicated by URL before persistence. |
| **Jobs API** | List all stored jobs, fetch one by id, or create a job manually via JSON. |
| **Skills analytics** | Rank skills by occurrence count across all jobs, or list co-occurring skills for jobs that require a given skill. |
| **API docs** | OpenAPI UI via SpringDoc at [`http://localhost:8080/swagger-ui/index.html`](http://localhost:8080/swagger-ui/index.html) when the app is running. |

The codebase follows a layered style: **domain** (`JobFacade`, `JobService`, repositories) and **infrastructure** (REST controllers, HTTP client, scheduler, error handlers). The application is packaged as a **WAR** and can run embedded (Tomcat) or be deployed to an external servlet container.

## Tech stack

| Category | Technologies |
|----------|----------------|
| **Runtime & build** | Java 25, Maven 3.9+ (Maven Wrapper included), Spring Boot 3.5.16 |
| **Web & persistence** | Spring Web, Spring Data JPA, Bean Validation, MySQL (`mysql-connector-j`) |
| **Integration** | `RestTemplate`, configurable HTTP timeouts, fixed-delay scheduler |
| **Documentation** | SpringDoc OpenAPI UI 1.8.0 |
| **Developer tools** | Lombok, Spring Boot Configuration Processor |
| **Testing** | JUnit 5, Spring Boot Test, AssertJ, Testcontainers (MySQL), WireMock, Awaitility |
| **Containers** | Docker multi-stage build, Docker Compose (MySQL, phpMyAdmin, Adminer, app) |

## Getting started locally

### Prerequisites

- **JDK 25** (matches `pom.xml`; the Docker image currently uses JDK 21—align the Dockerfile if you rely on container builds with Java 25 features)
- **Maven** (optional if you use `./mvnw`)
- **Docker & Docker Compose** (optional, for MySQL and full stack)

### Option A — MySQL via Docker Compose, app on the host

1. Start the database (and optional admin UIs):

   ```bash
   docker compose up -d mysql
   ```

   MySQL listens on `localhost:3306` with database `radar`, user `root`, password `toor` (see `docker-compose.yml`). phpMyAdmin and Adminer are available on ports **8081** and **8082** if you start those services.

2. Configure the app (defaults in `application.properties` already match local Docker MySQL):

   | Variable | Default | Purpose |
   |----------|---------|---------|
   | `DB_HOST` | `localhost` | MySQL host |
   | `DB_PORT` | `3306` | MySQL port |
   | `DB_NAME` | `radar` | Database name |
   | `DB_USER` | `root` | Database user |
   | `DB_PASSWORD` | `toor` | Database password |

3. Run the application:

   ```bash
   ./mvnw spring-boot:run
   ```

   The API listens on **http://localhost:8080**.

4. Optional scheduler settings:

   | Property / env | Default | Notes |
   |----------------|---------|--------|
   | `scheduling.enabled` | `true` | Set to `false` to disable periodic fetch |
   | `http.jobs.scheduler.request.delay` | `PT3H` | ISO-8601 duration between fetch runs |
   | `job.http.client.config.uri` | `https://justjoin.it` | External jobs API base |
   | `job.http.client.config.port` | `443` | Port appended when building the client URL |

   Hibernate uses `ddl-auto=update`, so schema is created/updated automatically on startup.

### Option B — Full stack in Docker Compose

Build and run the backend with MySQL:

```bash
docker compose up --build
```

The `it-radar-backend` service exposes port **8080** and receives `DB_*` variables pointing at the `mysql` service.

### Quick API check

```bash
curl http://localhost:8080/jobs
curl http://localhost:8080/skills/top
```

Example endpoints:

- `GET /jobs` — all jobs
- `GET /jobs/{id}` — job by id (404 if missing)
- `POST /jobs` — create job (validated JSON body)
- `GET /skills/top` — skills ranked by frequency
- `GET /skills/top/{skill}` — skills co-occurring with `{skill}` on the same offers

## Available scripts

All commands use the Maven Wrapper from the project root (`mvnw` / `mvnw.cmd` on Windows).

| Command | Description |
|---------|-------------|
| `./mvnw spring-boot:run` | Run the app locally with dev defaults |
| `./mvnw clean package` | Compile, run unit tests, and build the WAR in `target/` |
| `./mvnw clean package -DskipTests` | Build WAR without tests (used in Dockerfile) |
| `./mvnw test` | Run the test suite |
| `docker compose up --build` | Start MySQL, optional DB UIs, and the backend container |
| `docker compose up -d mysql` | Start only MySQL for local JVM development |

Integration tests use the `integration` profile, Testcontainers MySQL, and WireMock; Docker must be available when running `./mvnw test`.

## Project scope

**In scope (current implementation)**

- Periodic and on-demand ingestion of job offers from an HTTP JSON API
- Persistence of jobs and skills in MySQL with URL-based deduplication
- REST exposure of jobs and skill popularity / co-occurrence analytics
- Validation and structured error responses for API failures
- Integration tests covering scheduler flow, HTTP client behavior, and typical user scenarios
- Containerized local development (Compose) and WAR packaging for servlet deployment

**Out of scope / not implemented**

- Authentication or authorization (tests mention Bearer tokens historically; endpoints are open in the current code)
- Frontend UI or public SaaS deployment guides
- Production-grade secrets management, migrations (Flyway/Liquibase), or observability stack
- Guaranteed compatibility of the external Just Join IT API contract over time

## Project status

- **Version:** `0.0.1-SNAPSHOT` — early-stage, active development
- **Packaging:** WAR (`pl.mojezapiski:it-radar`)
- **Parent:** Spring Boot 3.5.16

Expect API shapes, scheduler defaults, and Docker/Java alignment to evolve. Report issues or contribute via the repository’s usual GitHub workflow.

## License

No `LICENSE` file is present in this repository. All rights to the project source are reserved unless a license is added by the maintainers. Third-party dependencies are subject to their respective licenses (Apache 2.0 for Maven Wrapper scripts, etc.).
