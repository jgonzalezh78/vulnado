# Documentation: Postgres.java

## Overview
The `Postgres` class is designed to manage database interactions with a PostgreSQL database. It provides methods for establishing a connection, setting up the database schema, inserting seed data, and hashing passwords using the MD5 algorithm. This class is primarily focused on database initialization and basic data insertion.

---

## Features

### 1. **Database Connection**
The `connection()` method establishes a connection to the PostgreSQL database using environment variables for configuration:
- **PGHOST**: Hostname of the PostgreSQL server.
- **PGDATABASE**: Name of the database.
- **PGUSER**: Username for authentication.
- **PGPASSWORD**: Password for authentication.

If the connection fails, the application logs the error and terminates.

---

### 2. **Database Setup**
The `setup()` method performs the following tasks:
- Creates two tables:
  - **users**: Stores user information such as `user_id`, `username`, `password`, `created_on`, and `last_login`.
  - **comments**: Stores comments with fields `id`, `username`, `body`, and `created_on`.
- Deletes any existing data in the tables to ensure a clean slate.
- Inserts seed data:
  - Users: Admin, Alice, Bob, Eve, and Rick with predefined usernames and passwords.
  - Comments: Example comments from Rick and Alice.

---

### 3. **Password Hashing**
The `md5()` method generates an MD5 hash for a given input string. This is used to store hashed passwords in the database. While MD5 is implemented, it is not recommended for sensitive contexts due to known vulnerabilities.

---

### 4. **Data Insertion**
The class provides two private methods for inserting data into the database:
- **insertUser(String username, String password)**: Inserts a new user into the `users` table. The password is hashed using the `md5()` method before storage.
- **insertComment(String username, String body)**: Inserts a new comment into the `comments` table.

---

## Insights

### Security Concerns
- **MD5 Hashing**: The use of MD5 for password hashing is insecure and should be replaced with a stronger algorithm like bcrypt or Argon2.
- **Error Handling**: Debugging information is suppressed in production, but the application terminates on errors, which may not be ideal for robust systems.

### Scalability
- The current implementation is suitable for small-scale applications. However, for larger systems, additional features like connection pooling, transaction management, and more advanced error handling would be necessary.

### Seed Data
- The inclusion of hardcoded seed data (e.g., admin credentials) poses a security risk. These should be dynamically generated or securely managed.

### Environment Variables
- The reliance on environment variables for database configuration is a good practice for separating configuration from code. Ensure these variables are securely managed.

---

## Database Schema

### Users Table
| Column Name   | Data Type      | Constraints                     |
|---------------|----------------|----------------------------------|
| user_id       | VARCHAR (36)   | Primary Key                     |
| username      | VARCHAR (50)   | Unique, Not Null                |
| password      | VARCHAR (50)   | Not Null                        |
| created_on    | TIMESTAMP      | Not Null                        |
| last_login    | TIMESTAMP      | Optional                        |

### Comments Table
| Column Name   | Data Type      | Constraints                     |
|---------------|----------------|----------------------------------|
| id            | VARCHAR (36)   | Primary Key                     |
| username      | VARCHAR (36)   | Foreign Key (users.username)    |
| body          | VARCHAR (500)  | Optional                        |
| created_on    | TIMESTAMP      | Not Null                        |

---

## Seed Data

### Users
| Username | Password             |
|----------|----------------------|
| admin    | !!SuperSecretAdmin!! |
| alice    | AlicePassword!       |
| bob      | BobPassword!         |
| eve      | $EVELknev^l          |
| rick     | !GetSchwifty!        |

### Comments
| Username | Comment         |
|----------|-----------------|
| rick     | cool dog m8     |
| alice    | OMG so cute!    |

---

## Dependencies
The class relies on the following Java libraries:
- **java.sql**: For database connection and query execution.
- **java.util.UUID**: For generating unique identifiers.
- **java.security**: For password hashing.
- **java.util.logging**: For logging errors and information.

---

## Limitations
- The class does not support advanced database operations like updates or complex queries.
- Error handling is minimal, and the application terminates on exceptions.
- Debugging features are disabled in production, which may hinder troubleshooting.
