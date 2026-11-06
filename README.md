# banking-rest-api
A robust Banking REST API engineered with Java, Spring Boot, and PostgreSQL. Features transactional fund transfers, layered clean architecture, DTO validation, and JUnit test coverage.





# 🏦 Core Banking & Transaction Management REST API

![Java](https://img.shields.io/badge/Java-17%2B-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![Spring Data JPA](https://img.shields.io/badge/Spring_Data_JPA-Hibernate-59666C?style=for-the-badge)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white)
![JUnit 5](https://img.shields.io/badge/JUnit5-25A162?style=for-the-badge&logo=junit5&logoColor=white)
![Git](https://img.shields.io/badge/Git-F05032?style=for-the-badge&logo=git&logoColor=white)

A RESTful backend service engineered with **Java** and **Spring Boot**, implementing clean coding standards, layered architecture, ACID-compliant database operations, and centralized exception handling.

---

## 📌 Features

- **Account Lifecycle Management**: Create, read, and manage bank accounts with live balance tracking.
- **Transactional Consistency**: Robust fund transfers, deposits, and withdrawals using Spring's `@Transactional` to enforce ACID compliance and prevent race conditions.
- **Clean Layered Architecture**: Strict separation of concerns across Controller, Service, Repository, and Entity layers.
- **Data Transfer Objects (DTOs)**: Enforces input validation using `jakarta.validation` (`@NotNull`, `@Positive`, `@Email`) to sanitize payloads before reaching business logic.
- **Centralized Error Handling**: Custom business exceptions with unified `@ControllerAdvice` error responses (RFC 7807 problem details).
- **Relational Persistence**: Integrated with relational databases (PostgreSQL for production / H2 in-memory for testing) via **Spring Data JPA & Hibernate**.
- **Automated Testing Suite**: Comprehensive unit and integration test coverage with **JUnit 5** and **Mockito**.

---

## 🛠️ Tech Stack & Prerequisites

- **Language:** Java 17+
- **Framework:** Spring Boot 3.x (Spring Web, Spring Data JPA, Spring Validation)
- **Database:** PostgreSQL / H2 In-Memory
- **ORM / Persistence:** Hibernate / Spring Data JPA
- **Build Tool:** Maven
- **Testing:** JUnit 5, Mockito, AssertJ

---

## 🏗️ Architecture Overview

```text
src/main/java/com/bank/api/
├── controller/        # REST endpoints and HTTP request routing
├── dto/               # Request/Response models + validation rules
├── exception/         # Custom exceptions & GlobalExceptionHandler
├── model/             # JPA Entities representing relational database tables
├── repository/        # Spring Data JPA interfaces for data access
└── service/           # Core business logic and transaction orchestration
