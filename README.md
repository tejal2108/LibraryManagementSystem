# 📚 Library Management System

A robust backend designed to manage college library books, users, and transactions hassle-free.

**By Tejal Deshmukh**

---

## 🔍 Project Overview

This Library Management System backend is a robust and scalable RESTful API built with **Java Spring Boot**, designed to handle the core functionalities of a typical library environment.

The system allows for efficient management of books, users, and transactions such as issuing and returning books. It supports role-based access control, enabling distinct privileges for admin and regular users to ensure secure operations.

Key features include automated due date calculation, fine management for overdue returns, detailed logging, and exception handling to maintain system integrity. The project uses **MySQL** as the relational database to store and manage data with Spring Data JPA, ensuring seamless CRUD operations and transactional safety.

Designed with scalability and maintainability in mind, the backend supports easy extension for additional features such as notifications, reporting, and integration with front-end clients or third-party systems.

---

## 🛠 Technologies and Dependencies

- Maven for dependency management  
- Spring Boot for building REST APIs  
- Spring Security for authentication and authorization  
- Spring Data JPA (Hibernate) for ORM and database operations  
- MySQL as the database  
- Project Lombok for reducing boilerplate code  

---

## 📋 How to Use

### CLI Installation:

```bash
git clone https://github.com/tejal2108/libraryManagementSystem.git
cd Library-Management-System
mvn package
java -jar target/Student-library-0.0.1-SNAPSHOT.jar
