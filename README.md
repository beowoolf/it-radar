# IT Radar

[![Java](https://img.shields.io/badge/Java-25-ED8B00?style=flat&logo=openjdk&logoColor=white)](https://openjdk.org/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.16-6DB33F?style=flat&logo=springboot&logoColor=white)](https://spring.io/projects/spring-boot)
[![Version](https://img.shields.io/badge/version-0.0.1--SNAPSHOT-007EC6?style=flat)](pom.xml)
[![MySQL](https://img.shields.io/badge/MySQL-8.0-4479A1?style=flat&logo=mysql&logoColor=white)](https://www.mysql.com/)
[![Docker](https://img.shields.io/badge/Docker-Ready-2496ED?style=flat&logo=docker&logoColor=white)](Dockerfile)
[![License](https://img.shields.io/badge/license-Unlicensed-lightgrey.svg)](LICENSE)

**IT Radar** is a RESTful analytics backend that continuously tracks and evaluates skill demands across the tech industry. It periodically ingests IT job postings from external job boards, persists and deduplicates them in MySQL, and exposes analytics endpoints to identify the most sought-after technologies as well as co-occurring skills (e.g., discovering which secondary skills are expected when a job requires Java, Python, or React).

---

## Table of Contents

- [1. Project Name](#1-project-name)
- [2. Project Description](#2-project-description)
  - [Key Features](#key-features)
  - [Architectural Design](#architectural-design)
  - [API Endpoints Overview](#api-endpoints-overview)
- [3. Tech Stack](#3-tech-stack)
- [4. Getting Started Locally](#4-getting-started-locally)
  - [Prerequisites](#prerequisites)
  - [Configuration Properties](#configuration-properties)
  - [Option A: Application on Host with Dockerized MySQL](#option-a-application-on-host-with-dockerized-mysql)
  - [Option B: Full Containerized Stack via Docker Compose](#option-b-full-containerized-stack-via-docker-compose)
  - [Interactive Documentation (Swagger / OpenAPI)](#interactive-documentation-swagger--openapi)
- [5. Available Scripts](#5-available-scripts)
- [6. Project Scope](#6-project-scope)
  - [In Scope](#in-scope)
  - [Out of Scope / Future Roadmap](#out-of-scope--future-roadmap)
- [7. Project Status](#7-project-status)
- [8. License](#8-license)

---

## 1. Project Name

**IT Radar** (`pl.mojezapiski:it-radar`)

---

## 2. Project Description

Modern tech professionals and hiring teams often struggle to determine what specific technology stacks and adjacent skills the job market currently values. **IT Radar** addresses this by serving as an intelligent aggregator and market skill radar.

The service periodically fetches real-world job offers from public job boards (configured by default for [Just Join IT](https://justjoin.it)), normalizes and filters incoming data, and maintains an up-to-date relational database of jobs, technologies, and required proficiencies.

### Key Features

- **Automated Offer Ingestion:** Built-in scheduler (`HttpJobsScheduler`) periodically invokes an HTTP client against the configured job board API (`https://justjoin.it/api/offers`), fetching raw listings.
- **Intelligent Deduplication:** Prevents duplicate postings by validating job URLs against stored records (`existsByUrl`) prior to persistence.
- **Skill Popularity Ranking:** Fast JPQL aggregation queries group and rank skills by total occurrence frequency across all active job postings.
- **Skill Co-occurrence Radar:** Analyzes tech pairings to determine complementary competencies—for instance, querying `/skills/top/Java` returns the most frequent companion skills (e.g., Spring Boot, SQL, Docker, Kafka) demanded in Java job listings.
- **Manual Job Management:** REST endpoints to query and create individual job listings with input validation and detailed error feedback.
- **Centralized Error Handling:** Global controller advice classes (`ApiValidationErrorHandler`, `JobControllerErrorHandler`) return consistent, machine-readable HTTP error payloads.
- **Interactive Documentation:** Bundled SpringDoc OpenAPI 3 UI allows direct exploration and execution of all endpoints.

### Architectural Design

The codebase adheres to a modular, domain-driven package design separating business logic from infrastructure concerns:

- **Domain Layer (`pl.mojezapiski.itradar.domain.job`):**
  - Encapsulates entities (`Job`, `Skill`), DTO contracts, and repositories.
  - Exposes an entry-point facade (`JobFacade`) isolating business operations from web delivery mechanisms.
  - Implements clean unit testing with in-memory repository doubles (`InMemoryJobRepository`, `InMemorySkillRepository`, `InMemoryFetcherTestImpl`).
- **Infrastructure Layer (`pl.mojezapiski.itradar.infrastructure`):**
  - Handles external integrations including REST controllers (`JobRestController`, `SkillRestController`), `RestTemplate` configuration, scheduled tasks (`HttpJobsScheduler`), and validation exception mappers.

### API Endpoints Overview

| Method | Endpoint | Description |
|:-------|:---------|:------------|
| `GET` | `/jobs` | Returns all registered job offers. |
| `GET` | `/jobs/{id}` | Retrieves a single job offer by its numerical ID (returns `404 Not Found` if missing). |
| `POST` | `/jobs` | Creates a new job offer with validation on required fields (returns `201 Created`). |
| `GET` | `/skills/top` | Returns all recorded skills ranked in descending order by occurrence frequency. |
| `GET` | `/skills/top/{skill}` | Returns companion skills that co-occur in jobs requiring the specified `{skill}`. |
| `GET` | `/swagger-ui/index.html` | Interactive Swagger UI documentation. |
| `GET` | `/v3/api-docs` | Raw OpenAPI 3 JSON definition. |

#### Sample Skill Frequency Response (`GET /skills/top`):
```json
[
  {
    "name": "Java",
    "occurrences": 1420
  },
  {
    "name": "SQL",
    "occurrences": 1180
  },
  {
    "name": "Docker",
    "occurrences": 950
  }
]
```

#### Sample Co-occurrence Response (`GET /skills/top/Java`):
```json
[
  {
    "name": "Spring Boot",
    "occurrences": 1050
  },
  {
    "name": "SQL",
    "occurrences": 920
  },
  {
    "name": "Docker",
    "occurrences": 710
  }
]
```

---

## 3. Tech Stack

| Category | Technology | Details / Version |
|:---|:---|:---|
| **Language & Runtime** | Java 25 | Compiles and runs using modern Java 25 features |
| **Core Framework** | Spring Boot 3.5.16 | Web MVC, Data JPA, Validation, Scheduling, Cache support |
| **Packaging** | WAR / Standalone JAR | Packaged as WAR with embedded Tomcat (`spring-boot-starter-tomcat`) |
| **Database & ORM** | MySQL 8.0 & Hibernate | `mysql-connector-j`, Spring Data JPA, automated DDL update |
| **HTTP Client & Integration** | `RestTemplate` | Configurable timeouts and error handler (`RestTemplateResponseErrorHandler`) |
| **API Documentation** | SpringDoc OpenAPI | `springdoc-openapi-ui` 1.8.0 |
| **Boilerplate Reduction** | Project Lombok | 1.18.36 (Getters, setters, builders, logging) |
| **Testing Frameworks** | JUnit 5 & AssertJ | `spring-boot-starter-test`, `assertj-core` 3.27.1 |
| **Integration Testing** | Testcontainers | `testcontainers` 1.21.3 (MySQL container isolation) |
| **HTTP Mocking & Async** | WireMock & Awaitility | `wiremock-jre8-standalone` 2.35.2, `awaitility` 4.3.0 |
| **Containerization** | Docker & Docker Compose | Multi-stage build on `eclipse-temurin:25-jdk-alpine` and `eclipse-temurin:25-jre-alpine` |

---

## 4. Getting Started Locally

### Prerequisites

Ensure you have the following installed on your workstation:
- **JDK 25** (Oracle OpenJDK or Eclipse Temurin 25)
- **Git**
- **Docker Desktop / Docker Engine** with **Docker Compose**
- *(Optional)* Maven 3.9+ (the repository includes the Maven Wrapper `./mvnw` / `mvnw.cmd`)

### Configuration Properties

The application is configured via `src/main/resources/application.properties` and supports dynamic environment variable overrides:

| Property | Environment Variable | Default Value | Description |
|:---|:---|:---|:---|
| `server.port` | `SERVER_PORT` | `8080` | Embedded HTTP server port |
| `spring.datasource.url` | `DB_HOST`, `DB_PORT`, `DB_NAME` | `jdbc:mysql://localhost:3306/radar?serverTimezone=UTC` | JDBC database connection string |
| `spring.datasource.username` | `DB_USER` | `root` | MySQL database user |
| `spring.datasource.password` | `DB_PASSWORD` | `toor` | MySQL database password |
| `spring.jpa.hibernate.ddl-auto` | — | `update` | Hibernate schema management |
| `scheduling.enabled` | `SCHEDULING_ENABLED` | `true` | Enables or disables the scheduled fetch task |
| `http.jobs.scheduler.request.delay` | — | `PT3H` | Frequency of the ingestion task (ISO-8601 duration: 3 hours) |
| `job.http.client.config.uri` | — | `https://justjoin.it` | Base URL of the upstream job listings provider |
| `job.http.client.config.port` | — | `443` | Upstream provider port |
| `http.client.config.connectionTimeout` | — | `1000` | HTTP connect timeout in milliseconds |
| `http.client.config.readTimeout` | — | `1000` | HTTP read timeout in milliseconds |

---

### Option A: Application on Host with Dockerized MySQL

This option is recommended for local code development and debugging in an IDE.

1. **Clone the repository:**
   ```bash
   git clone https://github.com/beowoolf/it-radar.git
   cd it-radar
   ```

2. **Start MySQL (and optional database management UIs) in the background:**
   ```bash
   docker compose up -d mysql
   ```
   > **Note:** If you want phpMyAdmin available at `http://localhost:8081`, run `docker compose up -d mysql phpmyadmin`.

3. **Verify MySQL health:**
   The database will be accessible at `localhost:3306` with database `radar`, user `root`, and password `toor`.

4. **Run the Spring Boot application:**
   - **Linux / macOS:**
     ```bash
     ./mvnw spring-boot:run
     ```
   - **Windows:**
     ```cmd
     mvnw.cmd spring-boot:run
     ```

5. **Verify that the service is running:**
   ```bash
   curl http://localhost:8080/jobs
   curl http://localhost:8080/skills/top
   ```

---

### Option B: Full Containerized Stack via Docker Compose

This option runs both the MySQL database and the containerized Spring Boot backend with zero local Java installation requirements.

1. **Build and launch all services:**
   ```bash
   docker compose up --build
   ```

2. **Access running services:**
   - **IT Radar Backend API:** [http://localhost:8080](http://localhost:8080)
   - **Swagger UI:** [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)
   - **phpMyAdmin:** [http://localhost:8081](http://localhost:8081)

3. **Stop the stack:**
   ```bash
   docker compose down
   ```
   *(Add `-v` if you wish to wipe the MySQL data volume: `docker compose down -v`)*

---

### Interactive Documentation (Swagger / OpenAPI)

Once the application is running, navigate to:
```
http://localhost:8080/swagger-ui/index.html
```
Here you can explore schemas, inspect request contracts, and trigger sample requests directly from your browser.

---

## 5. Available Scripts

The Maven Wrapper script is located at the root of the project (`./mvnw` on Unix-like systems, `mvnw.cmd` on Windows).

| Goal / Command | Description |
|:---|:---|
| `./mvnw spring-boot:run` | Starts the application locally in development mode. |
| `./mvnw test` | Executes the complete test suite (unit tests and Testcontainers integration tests). *Requires running Docker engine.* |
| `./mvnw clean compile` | Cleans previous build artifacts and compiles source code. |
| `./mvnw clean package` | Runs tests and packages the production WAR file in `target/it-radar-0.0.1-SNAPSHOT.war`. |
| `./mvnw clean package -DskipTests` | Compiles and packages the WAR without running tests (used in Docker builds). |
| `docker compose up -d mysql` | Starts the local MySQL database daemon. |
| `docker compose up --build` | Rebuilds the backend container and starts the entire multi-container stack. |
| `docker compose down` | Stops and removes containers created by Docker Compose. |

---

## 6. Project Scope

### In Scope

- **Automated Data Harvesting:** Scheduled polling of remote job listings using configurable delays and timeout thresholds.
- **Relational Storage:** Relational mapping of jobs, locations, employment types, and normalized skills using JPA/Hibernate.
- **Deduplication Logic:** Skipping already ingested job listings based on unique job URL checks.
- **Market Skill Analytics:**
  - Aggregated frequency analysis of all skills (`/skills/top`).
  - Co-occurrence matrix analysis for specific skills (`/skills/top/{skill}`).
- **Input Validation & Exception Translation:** Jakarta Bean Validation on manual job postings with internationalized error message keys (`validationMessages.properties`).
- **Comprehensive Test Coverage:**
  - Fast domain unit tests using in-memory repositories.
  - End-to-end integration scenarios using Testcontainers MySQL and WireMock stubbing.
- **Containerization:** Production multi-stage Docker build matching Java 25 baseline.

### Out of Scope / Future Roadmap

- **Authentication & Authorization:** The API currently operates without authentication (JWT / OAuth2 / API keys are planned for future revisions).
- **Frontend Dashboard:** Currently focused solely on providing a backend REST API (a web frontend or charting dashboard can be added separately).
- **Database Migrations:** Schema generation relies on Hibernate `ddl-auto=update`; production migrations using Flyway or Liquibase are not yet integrated.
- **Multiple External Providers:** Currently focused on Just Join IT; additional job boards (No Fluff Jobs, LinkedIn, Bulldogjob) can be added as modular fetchers.
- **Pagination & Filtering:** Returning paginated listings (`Pageable`) for `/jobs` is planned to support high-volume datasets.

---

## 7. Project Status

- **Status:** Active Development (Proof of Concept / Early Prototype)
- **Current Version:** `0.0.1-SNAPSHOT`
- **Packaging:** WAR archive (`it-radar-0.0.1-SNAPSHOT.war`), runnable as an executable jar/war or deployable to servlet containers such as Apache Tomcat.
- **Maintainer:** [beowoolf](https://github.com/beowoolf)

Contributions, issue reports, and feature requests are welcome via GitHub Issues and Pull Requests.

---

## 8. License

This repository does not currently contain an open-source license file (`LICENSE`). As a result, all rights are reserved by the original project author.

Third-party dependencies, build plugins, and wrapper scripts used by this project remain governed by their respective licenses (e.g., Apache 2.0, MIT, Eclipse Public License, GNU GPL).
