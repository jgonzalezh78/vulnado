# Documentation: User.java

## Overview
The `User` class is part of the `com.scalesec.vulnado` package and serves as a representation of a user entity in the system. It provides functionality for user authentication, token generation, and database interaction to fetch user details. This class is designed to handle user-related operations, including generating JSON Web Tokens (JWTs) and verifying authentication.

---

## Class Details

### Attributes
The `User` class contains the following attributes:
| Attribute       | Type   | Description                                      |
|-----------------|--------|--------------------------------------------------|
| `id`            | String | Unique identifier for the user.                 |
| `username`      | String | Username of the user.                           |
| `hashedPassword`| String | Hashed password for secure storage.             |

---

### Constructor
The class provides a constructor to initialize a `User` object:
```java
public User(String id, String username, String hashedPassword)
```
- **Parameters**:
  - `id`: The unique identifier for the user.
  - `username`: The username of the user.
  - `hashedPassword`: The hashed password of the user.

---

### Methods

#### `token(String secret)`
Generates a JSON Web Token (JWT) for the user using a secret key.
- **Parameters**:
  - `secret`: A secret key used for signing the token.
- **Returns**:
  - A signed JWT string containing the user's username as the subject.
- **Purpose**:
  - Provides a secure way to authenticate the user by generating a token.

---

#### `assertAuth(String secret, String token)`
Validates the provided JWT token using the secret key.
- **Parameters**:
  - `secret`: The secret key used to verify the token.
  - `token`: The JWT token to be validated.
- **Throws**:
  - `Unauthorized`: If the token validation fails.
- **Purpose**:
  - Ensures that the token is valid and signed with the correct secret key.

---

#### `fetch(String un)`
Fetches user details from the database based on the username.
- **Parameters**:
  - `un`: The username of the user to be fetched.
- **Returns**:
  - A `User` object containing the user's details if found, otherwise `null`.
- **Purpose**:
  - Retrieves user information from the database for authentication or other operations.
- **Database Interaction**:
  - Executes a SQL query to fetch user details from the `users` table.
  - Uses the `Postgres.connection()` method to establish a database connection.

---

## Insights

### Security Considerations
1. **SQL Injection Risk**:
   - The `fetch` method constructs SQL queries using string concatenation, which makes it vulnerable to SQL injection attacks. Using prepared statements is recommended to mitigate this risk.

2. **Token Security**:
   - The `token` method uses HMAC SHA key generation for signing tokens, which is a secure approach. However, the secret key should be stored securely and not hardcoded.

3. **Error Handling**:
   - The `assertAuth` method catches exceptions during token validation but prints stack traces, which may expose sensitive information. Logging should be handled carefully to avoid leaking details.

---

### Business Use Cases
1. **Authentication**:
   - The `token` method can be used to generate tokens for user authentication in web applications or APIs.

2. **Authorization**:
   - The `assertAuth` method ensures that only valid tokens are accepted, enabling secure access control.

3. **User Management**:
   - The `fetch` method allows retrieval of user details from the database, which can be used for login or profile management.

---

### Dependencies
The class relies on the following external libraries and components:
| Dependency                  | Purpose                                      |
|-----------------------------|----------------------------------------------|
| `io.jsonwebtoken`           | For JWT generation and validation.          |
| `javax.crypto.SecretKey`    | For cryptographic operations.               |
| `Postgres.connection()`     | Establishes database connection.            |

---

### Potential Improvements
1. **Database Query Optimization**:
   - Use prepared statements to prevent SQL injection and improve query performance.

2. **Error Handling**:
   - Replace `e.printStackTrace()` with proper logging mechanisms to avoid exposing sensitive information.

3. **Token Expiry**:
   - Include an expiration time in the JWT to enhance security and prevent misuse of stale tokens.
