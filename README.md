# Library REST API

REST-based web application that provides a backend system for managing a library, including books, readers, employees, and reviews. Initially built with a monolithic MySQL database, the project was migrated to a distributed NewSQL architecture utilizing a CockroachDB cluster and an HAProxy load balancer.

**Application written in:**

- **Language:** Java
- **Technologies and Tools:** Spring Boot, CockroachDB(initially MySQL), Docker, HAProxy, Maven, Swagger, Ehcache

The available **Application Features** are described below.

---

## Installation

How to install the application:

```
git clone https://github.com/dwolkowski/library-rest-api
cd library-rest-api
```

Build the application using Maven:

```
mvn clean package
```

Start the application, database nodes, and load balancer using Docker Compose:

```
docker-compose up -d
```

After that you can interact with the API:

- using the integrated **Swagger UI** (accessible via web browser)
- using REST API clients such as **Postman** or **Insomnia**

---

## Application Features

The application provides endpoints to manage daily CRUD library operations.

| Feature                 | Description                                                                                 |
| ----------------------- | ------------------------------------------------------------------------------------------- |
| **Book management**     | Allows creating, reading, updating, and deleting records of books available in the library. |
| **Reader management**   | Provides endpoints to register and manage library users (readers).                          |
| **Employee management** | Allows managing library staff accounts.                                                     |
| **Reviews system**      | Enables users to submit, view, and manage reviews for specific books.                       |
| **Authorization**       | Basic authorization configuration to restrict access to sensitive management endpoints.     |

---

## Architecture & Technical Details

The backend structure includes specific technical implementations and optimizations:

- **Database Migration** – The project was initially built with a MySQL database and was later migrated to a distributed CockroachDB cluster.
- **Load Balancing** – HAProxy is configured to distribute traffic across the CockroachDB nodes.
- **Query Optimization** – The application implements a solution to the N+1 query problem when fetching books from the database.
- **Caching** – Basic cache support is implemented using Ehcache to store responses and reduce database load.

___

<div><b> |&nbsp; Dariusz Wołkowski &nbsp;|&nbsp; 2025 &nbsp;| </b></div>
