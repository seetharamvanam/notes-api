# 📝 Notes API (Spring Boot + JWT + H2)

A secure, user-specific Notes API built with Spring Boot, using JWT authentication and H2 in-memory database.

---

## 🚀 Features

- User Registration & Login with password hashing
- JWT Authentication (stateless)
- Create / Read / Update / Delete (CRUD) notes
- Notes are user-specific (only visible to their creator)
- Secure endpoints with Spring Security
- H2 database for easy local development

---

## 📦 Tech Stack

- Java 17+
- Spring Boot 3.5.3
- Spring Security
- Spring Data JPA
- H2 Database
- JWT (via `jjwt` library)
- Maven

---

## 📁 Project Structure
src/
├── controller/ # REST controllers for auth and notes
├── dto/ # DTOs for request/response shaping
├── model/ # JPA entity classes
├── repository/ # Spring Data JPA interfaces
├── service/ # Business logic
├── config/ # Security configuration


---

## 📌 API Endpoints

### 🧑 Authentication

| Method | Endpoint         | Description           |
|--------|------------------|-----------------------|
| POST   | `/auth/register` | Register new user     |
| POST   | `/auth/login`    | Login and get JWT     |

### 📝 Notes (Requires JWT)

| Method | Endpoint           | Description               |
|--------|--------------------|---------------------------|
| GET    | `/notes`           | Get all notes for user    |
| GET    | `/notes/{id}`      | Get a specific note       |
| POST   | `/notes/create`    | Create a new note         |
| PUT    | `/notes/{id}`      | Update a specific note    |
| DELETE | `/notes/{id}`      | Delete a specific note    |
| DELETE | `/notes`           | Delete all notes for user |

---

## 🛡️ Security

- Passwords are encrypted with `BCryptPasswordEncoder`
- JWT is used for authenticating all `/notes/**` endpoints
- Filter checks JWT validity before letting requests through
