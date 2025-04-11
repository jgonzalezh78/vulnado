# Documentation: Comment Management System

## Overview
The `Comment` class is part of a system designed to manage user comments. It provides functionality for creating, retrieving, and deleting comments, as well as persisting them in a database. The class interacts with a PostgreSQL database to store and manage comment data.

---

## Key Features

### 1. **Comment Creation**
   - **Method:** `create(String username, String body)`
   - **Description:** Creates a new comment with a unique identifier, username, body, and timestamp. The comment is saved to the database.
   - **Error Handling:**
     - Throws `BadRequest` if the comment cannot be saved.
     - Throws `ServerError` for unexpected exceptions during the save operation.

### 2. **Retrieve All Comments**
   - **Method:** `fetchAll()`
   - **Description:** Fetches all comments from the database and returns them as a list.
   - **Database Query:** `SELECT id, username, body, created_on FROM comments;`
   - **Error Handling:** Logs errors using a `Logger` instance.

### 3. **Delete a Comment**
   - **Method:** `delete(String id)`
   - **Description:** Deletes a comment from the database based on its unique identifier.
   - **Database Query:** `DELETE FROM comments WHERE id = ?`
   - **Error Handling:** Ensures proper resource management and logs errors.

### 4. **Persist Comment**
   - **Method:** `commit()`
   - **Description:** Saves the current comment instance to the database.
   - **Database Query:** `INSERT INTO comments (id, username, body, created_on) VALUES (?,?,?,?)`
   - **Error Handling:** Ensures proper resource management during database operations.

---

## Data Structure

### Class: `Comment`
The `Comment` class represents a single comment entity with the following attributes:

| Attribute   | Type       | Description                          |
|-------------|------------|--------------------------------------|
| `id`        | `String`   | Unique identifier for the comment.  |
| `username`  | `String`   | Username of the comment's author.   |
| `createdOn` | `Timestamp`| Timestamp when the comment was created. |
| `body`      | `String`   | Content of the comment.             |

---

## Insights

1. **Database Dependency:** The class heavily relies on a PostgreSQL database for storing and retrieving comments. Proper database configuration and connection management are critical for its functionality.

2. **Error Handling:** The class uses custom exceptions (`BadRequest`, `ServerError`) and logging mechanisms to handle errors effectively. This ensures that issues are reported and managed appropriately.

3. **Scalability:** The `fetchAll()` method retrieves all comments, which may become inefficient as the number of comments grows. Pagination or filtering could be added to improve scalability.

4. **Security Considerations:**
   - The `delete()` method allows deletion of comments by ID. Proper authentication and authorization mechanisms should be implemented to prevent unauthorized deletions.
   - Input validation is not explicitly mentioned, which could lead to vulnerabilities such as SQL injection.

5. **UUID for Unique Identification:** The use of `UUID` ensures that each comment has a globally unique identifier, reducing the risk of collisions.

6. **Resource Management:** The class uses `try-with-resources` to manage database connections and statements, ensuring proper cleanup of resources.

---

## Potential Enhancements

- **Pagination:** Add support for paginated retrieval of comments in `fetchAll()` to handle large datasets efficiently.
- **Validation:** Implement input validation to ensure data integrity and prevent security vulnerabilities.
- **Authorization:** Introduce role-based access control to restrict operations like deletion to authorized users only.
- **Error Logging:** Enhance logging to include more contextual information for easier debugging.
