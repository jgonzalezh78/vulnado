# Documentation for `Postgres.java`

## Overview
The `Postgres` class is designed to manage interactions with a PostgreSQL database. It provides functionality for establishing a database connection, setting up the database schema, inserting seed data, and hashing passwords using the MD5 algorithm. This class is primarily used for initializing and managing user and comment data in a database.

---

## Features

### 1. **Database Connection**
   - Establishes a connection to a PostgreSQL database using credentials and connection details provided via environment variables:
     - `PGHOST`: Hostname of the PostgreSQL server.
     - `PGDATABASE`: Name of the database.
     - `PGUSER`: Username for authentication.
     - `PGPASSWORD`: Password for authentication.

### 2. **Database Setup**
   - Creates two tables if they do not already exist:
     - **`users`**: Stores user information such as `user_id`, `username`, `password`, `created_on`, and `last_login`.
     - **`comments`**: Stores comment information such as `id`, `username`, `body`, and `created_on`.
   - Deletes any existing data in the tables to ensure a clean slate.
   - Inserts predefined seed data for users and comments.

### 3. **Password Hashing**
   - Provides a method to hash passwords using the MD5 algorithm for secure storage in the database.

### 4. **Data Insertion**
   - Inserts user data into the `users` table, including hashed passwords.
   - Inserts comment data into the `comments` table.

---

## Database Schema

### `users` Table
| Column Name   | Data Type      | Constraints                          |
|---------------|----------------|--------------------------------------|
| `user_id`     | `VARCHAR(36)`  | Primary Key                         |
| `username`    | `VARCHAR(50)`  | Unique, Not Null                    |
| `password`    | `VARCHAR(50)`  | Not Null                            |
| `created_on`  | `TIMESTAMP`    | Not Null                            |
| `last_login`  | `TIMESTAMP`    | Optional                            |

### `comments` Table
| Column Name   | Data Type      | Constraints                          |
|---------------|----------------|--------------------------------------|
| `id`          | `VARCHAR(36)`  | Primary Key                         |
| `username`    | `VARCHAR(36)`  | Foreign Key (linked to `users`)      |
| `body`        | `VARCHAR(500)` | No constraints                      |
| `created_on`  | `TIMESTAMP`    | Not Null                            |

---

## Seed Data

### Users
| Username | Password              |
|----------|-----------------------|
| `admin`  | `!!SuperSecretAdmin!!`|
| `alice`  | `AlicePassword!`      |
| `bob`    | `BobPassword!`        |
| `eve`    | `$EVELknev^l`         |
| `rick`   | `!GetSchwifty!`       |

### Comments
| Username | Comment         |
|----------|-----------------|
| `rick`   | `cool dog m8`   |
| `alice`  | `OMG so cute!`  |

---

## Insights

1. **Environment Variables Dependency**:
   - The database connection relies on environment variables for configuration. Ensure these variables are correctly set before running the application.

2. **Password Security**:
   - Passwords are hashed using MD5 before being stored in the database. While MD5 is fast and widely supported, it is considered outdated and vulnerable to attacks. Consider upgrading to a more secure hashing algorithm like bcrypt or Argon2.

3. **Data Initialization**:
   - The `setup` method clears existing data in the `users` and `comments` tables before inserting seed data. This behavior is suitable for development environments but may not be ideal for production.

4. **Error Handling**:
   - The class uses basic error handling with `try-catch` blocks. Errors are printed to the console, and the application exits in case of critical failures. This approach may need refinement for production use.

5. **Hardcoded Seed Data**:
   - The seed data for users and comments is hardcoded. This may limit flexibility and scalability. Consider externalizing seed data to configuration files or scripts.

6. **UUID Usage**:
   - Unique identifiers for users and comments are generated using `UUID.randomUUID()`. This ensures globally unique IDs for database records.

7. **Scalability**:
   - The current implementation is suitable for small-scale applications. For larger systems, additional optimizations and security measures may be required.

---
