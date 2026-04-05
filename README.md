# Code Market Backend 🚀

## Overview
Code Market Backend is a Spring Boot-based REST API for a Job Portal system.  
It allows users to register, authenticate, post jobs, and apply for jobs with role-based access control.

---

## Tech Stack
- Java
- Spring Boot
- Spring Security
- JWT Authentication
- Maven
- MySQL

---

##  Features
- JWT-based Authentication & Authorization
- User Management (Admin / Recruiter / Candidate)
- Job Posting & Management
- Job Application System
-  Job-Candidate Matching Service
- Global Exception Handling

---

##  Project Structure

src/main/java/com/baalvionService/JobPortalApplication

├── controller # REST Controllers
├── service # Business Logic
├── repositories # Database Layer
├── entities # JPA Entities
├── securities # Security & JWT
├── config # Configurations
├── exception # Exception Handling
└── model # DTOs


---

### Prerequisites
- Java 17+
- Maven
- MySQL

### Installation

git clone https://github.com/vivivek786-art/code-market-backend.git
cd code-market-backend
mvn clean install

mvn spring-boot:run

Authorization: Bearer <your-token>


