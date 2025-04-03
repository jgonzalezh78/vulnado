# Documentation: Comment.java

## Overview
The `Comment` class is part of the `com.scalesec.vulnado` package and provides functionality for managing user comments in a database. It includes methods for creating, retrieving, and deleting comments, as well as a mechanism for persisting comments to the database. The class interacts with a PostgreSQL database using JDBC.

---

## Key Features

### Data Structure
The `Comment` class represents a single comment with the following attributes:
- **id**: A unique identifier for the comment (String).
- **username**: The name of the user who created the comment (String).
- **body**: The content of the comment (String).
- **created_on**: The timestamp when the comment was created (Timestamp).

### Methods and Logic
The class provides several methods for interacting with comments:

#### 1. **Constructor**
```java
public Comment(String id, String username, String body, Timestamp created_on)
```
- Initializes a `Comment` object with the provided attributes.

#### 2. **create(String username, String body)**
- Creates a new comment with a unique ID and the current timestamp.
- Persists the comment to the database using the `commit()` method.
- Throws a `BadRequest` exception if the comment cannot be saved.
- Throws a `ServerError` exception for unexpected errors.

#### 3. **fetch_all()**
- Retrieves all comments from the database.
- Executes a SQL query (`SELECT * FROM comments`) to fetch all records.
- Returns a list of `Comment` objects.

#### 4. **delete(String id)**
- Deletes a comment from the database based on its ID.
- Executes a SQL query (`DELETE FROM comments WHERE id = ?`) to remove the record.
- Returns `true` if the deletion is successful, otherwise `false`.

#### 5. **commit()**
- Persists the current `Comment` object to the database.
- Executes a SQL query (`INSERT INTO comments (id, username, body, created_on) VALUES (?,?,?,?)`) to insert the record.
- Returns `true` if the insertion is successful.

---

## Insights

### Business Use Cases
- **Comment Creation**: Enables users to add comments to a system, with automatic timestamping and unique ID generation.
- **Comment Retrieval**: Allows fetching all comments for display or analysis purposes.
- **Comment Deletion**: Provides functionality to remove comments, ensuring data management and compliance.

### Error Handling
- The `create` method includes error handling for both business logic errors (`BadRequest`) and unexpected system errors (`ServerError`).
- The `delete` and `fetch_all` methods log errors to the console but do not propagate exceptions, which may lead to silent failures.

### Database Interaction
- The class relies on a PostgreSQL database and uses SQL queries for CRUD operations.
- The `Postgres.connection()` method is assumed to provide a valid database connection.

### Potential Improvements
- **Error Handling**: Improve error handling in `delete` and `fetch_all` methods to propagate exceptions or provide meaningful feedback.
- **Security**: Validate user input to prevent SQL injection and other vulnerabilities.
- **Scalability**: Optimize database queries for large datasets, e.g., by implementing pagination in `fetch_all`.

---

## Dependencies
- **Postgres.connection()**: A utility method for establishing database connections.
- **UUID**: Used for generating unique IDs for comments.
- **JDBC**: Used for database interaction.
- **Custom Exceptions**: `BadRequest` and `ServerError` are custom exceptions used for error handling.

---

## Database Schema
The class assumes the following database schema for the `comments` table:

| Column Name | Data Type   | Description                     |
|-------------|-------------|---------------------------------|
| id          | VARCHAR     | Unique identifier for the comment |
| username    | VARCHAR     | Name of the user who created the comment |
| body        | TEXT        | Content of the comment          |
| created_on  | TIMESTAMP   | Timestamp when the comment was created |


