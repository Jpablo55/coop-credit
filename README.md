# CoopCredit - Comprehensive Credit Application System 🏦

**CoopCredit** is a modular backend system designed to manage affiliate information and automate credit risk evaluation. It replaces legacy manual processes with a professional, scalable, and secure architecture based on Microservices and Hexagonal Architecture standards.

---

## 🚀 Project Overview

The goal of this project is to provide a robust solution for credit processing, ensuring data consistency, traceability, and security.

### Key Features
* **Hexagonal Architecture:** Strict separation between Domain (Business Logic), Application (Ports), and Infrastructure (Adapters).
* **Microservices Ecosystem:**
    1.  **`credit-application-service`:** Core transactional service.
    2.  **`risk-central-mock-service`:** External risk simulation service.
* **Security:** Stateless authentication using **JWT** (JSON Web Tokens) with Role-Based Access Control (RBAC).
* **Data Integrity:** **PostgreSQL** with **Flyway** for versioned database migrations.
* **Observability:** Metrics and health checks via **Spring Boot Actuator**.
* **Containerization:** Fully Dockerized environment using **Docker Compose**.

---

## 🛠️ Tech Stack

* **Language:** Java 21
* **Framework:** Spring Boot 3.x
* **Build Tool:** Maven
* **Database:** PostgreSQL 15
* **ORM:** Spring Data JPA + Hibernate
* **Migrations:** Flyway (Schema, Relations, Seed Data)
* **Mapper:** MapStruct
* **Security:** Spring Security + JJWT
* **Communication:** Spring WebFlux (WebClient)
* **Documentation:** SpringDoc OpenAPI (Swagger UI)
* **Testing:** JUnit 5, Mockito, Testcontainers

---

## 🏗️ Architecture & Project Structure

The project follows a multi-module Maven structure.

### 1. `credit-application-service` (Hexagonal Core)
```text
src/main/java/com/credit_application_service
├── application
│   ├── port
│   │   ├── in  (Input Ports / Use Cases interfaces)
│   │   └── out (Output Ports / Repositories & External Clients)
├── domain (Pure Java - No Frameworks)
│   ├── model
│   ├── usecase
│   └── exception
└── infrastructure (Adapters)
    ├── adapter
    │   ├── in  (REST Controllers)
    │   └── out (JPA Persistence, WebClient Adapter)
    ├── config
    ├── globalhandler (Global Exception Handling - RFC 7807)
    └── security (JWT Filters & Config)
```
### 2. risk-central-mock-service
   A lightweight service that simulates an external credit bureau. It generates a consistent credit score based on the user's document ID.
## ⚙️ Configuration & Ports

| Service | Local Port | Docker Port | Description |
| :--- | :--- | :--- | :--- |
| **Credit App** | `8090` | `8080` | Main API |
| **Risk Mock** | `8081` | `8080` | Risk Simulator |
| **PostgreSQL** | `5433` | `5432` | Database |

> **Note:** PostgreSQL is exposed on port **5433** in `localhost` to avoid conflicts with local Postgres installations.

---

## 🚀 Getting Started

### Prerequisites
* Java 21 JDK
* Maven 3.9+
* Docker & Docker Compose

### Option 1: Full Docker Deployment (Recommended)
Run the entire ecosystem (Database + App + Mock) inside containers.

1.  Navigate to the project root.
2.  Run the compose command:
    ```bash
    docker compose up --build
    ```
3.  Wait for the containers to start. The database will initialize automatically using Flyway migrations.

### Option 2: Hybrid Setup (IntelliJ + Docker DB)
Useful for development and debugging.

1.  **Start the Database:**
    ```bash
    docker compose up -d db
    ```
2.  **Run Risk Mock Service:**
    Start `RiskCentralMockServiceApplication` (Runs on port 8081).
3.  **Run Credit App Service:**
    Start `CreditApplicationServiceApplication` (Runs on port 8090).

---

## 🔐 Security & Authentication

The API is secured with JWT. You must obtain a token to access protected endpoints.

### Default Users (Created via Flyway V3)
| Username | Password | Role |
| :--- | :--- | :--- |
| `admin` | `password` | `ROLE_ADMIN` |
| `analista` | `password` | `ROLE_ANALISTA` |
| `afiliado` | `password` | `ROLE_AFILIADO` |

### Auth Flow
1.  **Register a new user:** `POST /api/v1/auth/register`
2.  **Login:** `POST /api/v1/auth/login`
    * **Response:** `{"token": "eyJhbG..."}`
3.  **Access Protected Routes:**
    * Add Header: `Authorization: Bearer <YOUR_TOKEN>`

---

## 📚 API Documentation (Swagger UI)

Once the application is running, you can access the interactive API documentation at:

👉 **http://localhost:8090/swagger-ui/index.html**

### Key Endpoints
* **Auth:** Login and Register.
* **Affiliates:** Manage affiliate information (Admin only).
* **Credit Applications:** Create and evaluate credit requests.
* **Actuator:** System health and metrics.

---

## 📡 Observability

The application exposes metrics via Spring Boot Actuator:

* **Health:** `http://localhost:8090/actuator/health`
* **Metrics List:** `http://localhost:8090/actuator/metrics`
* **HTTP Requests:** `http://localhost:8090/actuator/metrics/http.server.requests`

---

