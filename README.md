# FrameBlog API 🚀

Academic project developed with Java and Spring Boot focused on REST APIs, microservices, and distributed architecture.

## 📚 Technologies Used

* Java 17
* Spring Boot
* Spring Data JPA
* Spring Web
* Spring Security
* JWT Authentication
* H2 Database
* Swagger / OpenAPI
* Eureka Server
* Spring Cloud Gateway
* Circuit Breaker
* Cache
* Service Discovery
* Load Balancer
* Maven

---

# 📌 Features

## 👤 Users

* User registration
* User search
* Update users
* Delete users

## 📝 Posts

* Create posts
* Associate posts with users
* List posts

## 💬 Comments

* Add comments to posts
* Associate comments with users and posts

## 🏷️ Tags

* Create hashtags/tags
* Associate tags with posts

## 🔐 Authentication

* JWT login
* Token generation

## 📡 Messaging

* Simulated asynchronous messaging using Spring Events

## ⚡ Cache

* Cache configured on endpoints

## 🌐 External API

* External REST API consumption

## ☁️ Microservices

* Eureka Service Registry
* Service Discovery
* API Gateway
* Circuit Breaker
* Load Balancing

---

# 🏗️ Architecture

```text
Client
   ↓
API Gateway
   ↓
Load Balancer
   ↓
FrameBlog Instances
   ↓
Database
```

---

# 🔄 Load Balancing

The project runs multiple `FRAMEBLOG` service instances registered in Eureka Server.

Example:

```text
FRAMEBLOG UP (2)
8080
8082
```

Requests are automatically distributed through the Gateway using:

```properties
lb://FRAMEBLOG
```

---

# 📖 Swagger

API documentation:

```text
http://localhost:8080/swagger-ui/index.html
```

---

# ⚙️ How to Run

## 1. Clone repository

```bash
git clone https://github.com/YOUR-USERNAME/frameblog-api.git
```

## 2. Open project

Open the project using IntelliJ IDEA or VS Code.

## 3. Run application

Run:

```text
FrameblogApplication.java
```

---

# 🧪 Main Endpoints

## Users

```http
GET /users
POST /users
GET /users/{id}
PUT /users/{id}
DELETE /users/{id}
```

## Posts

```http
GET /posts
POST /posts/{userId}
```

## Comments

```http
GET /comments
POST /comments/post/{postId}/user/{userId}
```

## Tags

```http
GET /tags
POST /tags
POST /tags/{tagId}/post/{postId}
```

## JWT Login

```http
POST /auth/login
```

---

# 👨‍💻 Author

Samuel Luciano

Project developed for academic studies and backend practice using Spring Boot and microservices.
