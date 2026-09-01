# Spring CRUD API

A RESTful Student Management API built with **Java and Spring Boot**.
This project demonstrates a layered backend architecture using DTOs, Spring Data JPA, Hibernate, MySQL, validation, and centralized exception handling.

## 🚀 Features

* Create a student
* Retrieve a student by ID
* Retrieve all active students
* Update student information
* Soft delete students
* Request and response DTOs
* Input validation using Jakarta Bean Validation
* Centralized exception handling
* Custom API error responses
* Spring Data JPA for database operations
* MySQL database integration

## 🛠️ Tech Stack

* **Java**
* **Spring Boot**
* **Spring Web**
* **Spring Data JPA**
* **Hibernate**
* **MySQL**
* **Jakarta Bean Validation**
* **Maven**
* **Git & GitHub**

## 🏗️ Project Structure

```text
src/
└── main/
    └── java/
        └── in/abnvv/new2Api/
            ├── controller/
            ├── dto/
            ├── entity/
            ├── exception/
            ├── repository/
            └── service/
```

The application follows a layered architecture:

```text
Client
   ↓
Controller
   ↓
Service
   ↓
Repository
   ↓
MySQL Database
```

DTOs are used to separate API request/response models from database entities.

## 📌 API Endpoints

### Student APIs

| Method   | Endpoint             | Description             |
| -------- | -------------------- | ----------------------- |
| `POST`   | `/api/students`      | Create a student        |
| `GET`    | `/api/students`      | Get all active students |
| `GET`    | `/api/students/{id}` | Get a student by ID     |
| `PUT`    | `/api/students/{id}` | Update a student        |
| `DELETE` | `/api/students/{id}` | Soft delete a student   |

## 📥 Example Request

### Create Student

```http
POST /api/students
Content-Type: application/json
```

```json
{
  "name": "Abhinav",
  "age": 21,
  "email": "abhinav@example.com",
  "rollNo": 101,
  "subject": "Java"
}
```

## 📤 Example Response

```json
{
  "id": 1,
  "name": "Abhinav",
  "age": 21,
  "email": "abhinav@example.com",
  "rollNo": 101,
  "subject": "Java"
}
```

## ❌ Validation & Exception Handling

The API uses Jakarta Bean Validation to validate incoming requests.

For example:

```java
@NotBlank
@Size(min = 2, max = 50)
private String name;
```

A centralized exception handler is used to provide consistent error responses instead of exposing default Spring error messages.

## 🗑️ Soft Delete

Instead of permanently removing a student from the database, the API marks the student as deleted.

```text
deleted = true
```

Normal student queries only return records where:

```text
deleted = false
```

This allows deleted records to remain in the database while keeping them hidden from normal API responses.

## ⚙️ Setup & Installation

### 1. Clone the repository

```bash
git clone https://github.com/abnvv19/SpringCrudApi.git
```

### 2. Open the project

Open the project in **IntelliJ IDEA** or another Java IDE.

### 3. Configure MySQL

Create a MySQL database and update your application's database configuration with your own credentials.

Example:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/your_database
spring.datasource.username=your_username
spring.datasource.password=your_password
```

**Do not commit your real database password to GitHub.**

### 4. Run the application

Using Maven:

```bash
./mvnw spring-boot:run
```

Or run the main Spring Boot application class from your IDE.

The API will be available at:

```text
http://localhost:8080
```

## 🧪 Testing the API

You can test the endpoints using tools such as:

* Postman
* Insomnia
* IntelliJ HTTP Client

## 📚 What I Learned

This project helped me practice:

* Building REST APIs with Spring Boot
* Layered backend architecture
* DTO-based API design
* Spring Data JPA and Hibernate
* MySQL database integration
* Bean Validation
* Global exception handling
* Soft deletion
* Dependency Injection
* HTTP methods and status codes

## 🔮 Future Improvements

Planned improvements include:

* [ ] Unit and integration testing
* [ ] Swagger/OpenAPI documentation
* [ ] Pagination and sorting
* [ ] Spring Security
* [ ] JWT authentication
* [ ] Docker support
* [ ] API deployment

## 👨‍💻 Author

**Abhinav Kumar**

B.Tech Information Technology — AKGEC

* GitHub: https://github.com/abnvv19
* LinkedIn: https://www.linkedin.com/in/abnvv19/

---

⭐ If you find this project useful, feel free to explore the repository.
