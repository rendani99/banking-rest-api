# 🏦 Core Banking & Transaction Management REST API

![Java](https://img.shields.io/badge/Java-17%2B-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![Spring Data JPA](https://img.shields.io/badge/Spring_Data_JPA-Hibernate-59666C?style=for-the-badge)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white)
![JUnit 5](https://img.shields.io/badge/JUnit5-25A162?style=for-the-badge&logo=junit5&logoColor=white)
![Git](https://img.shields.io/badge/Git-F05032?style=for-the-badge&logo=git&logoColor=white)

A production-style RESTful backend service engineered with **Java** and **Spring Boot**, implementing clean coding standards, layered architecture, ACID-compliant database operations, and centralized exception handling.

---

## 📌 Features

- **Account Lifecycle Management**: Create, view, update, and manage customer bank accounts.
- **Transactional Integrity**: Robust money transfers, deposits, and withdrawals using Spring's `@Transactional` to prevent race conditions and ensure ACID compliance.
- **Clean Architecture & Separation of Concerns**: Strict multi-tier isolation (`Controller` ➔ `Service` ➔ `Repository` ➔ `Domain Model`).
- **Data Transfer Objects (DTOs)**: Enforces input validation using `jakarta.validation` (`@NotNull`, `@Positive`, `@Email`) to ensure data sanitization before service-layer processing.
- **Centralized Error Handling**: Global exception controller utilizing `@ControllerAdvice` and `@ExceptionHandler` returning standardized error payloads (RFC 7807 problem details).
- **Relational Persistence**: Integrated with relational databases (PostgreSQL / H2 for development) via **Spring Data JPA & Hibernate**.
- **Automated Testing Suite**: Thorough unit and integration tests with **JUnit 5** and **Mockito**.

---

## 🛠️ Tech Stack & Prerequisites

- **Language:** Java 17+ / Java 21
- **Framework:** Spring Boot 3.x (Spring Web, Spring Data JPA, Spring Validation)
- **Database:** PostgreSQL (Production) / H2 In-Memory (Dev/Testing)
- **ORM / Persistence:** Hibernate / Spring Data JPA
- **Build Tool:** Maven or Gradle
- **Testing:** JUnit 5, Mockito, AssertJ

---

## 👤 Author

**Rendani Austin Mmola**  
- **GitHub:** [@rendani99](https://github.com/rendani99)  
- **LinkedIn:** [rendani-austin-mmola](https://www.linkedin.com/in/rendani-austin-mmola-9309221a6)
