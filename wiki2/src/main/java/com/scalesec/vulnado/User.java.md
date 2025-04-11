# Documentation: User.java

## Overview
The `User` class is part of the `com.scalesec.vulnado` package and is designed to manage user-related functionalities such as authentication, token generation, and database retrieval. It interacts with external libraries for cryptographic operations and database connections.

---

## Class Details

### **Attributes**
| Attribute Name   | Type     | Description                     |
|------------------|----------|---------------------------------|
| `id`             | `String` | Represents the unique identifier for a user. |
| `username`       | `String` | Stores the username of the user. |
| `hashedPassword` | `String` | Contains the hashed password of the user. |

### **Constructor**
| Constructor Name | Parameters                          | Description |
|------------------|-------------------------------------|-------------|
| `User`           | `String id`, `String username`, `String hashedPassword` | Initializes a new `User` object with the provided ID, username, and hashed password. |

---

## Methods

### **token**
| Method Name | Parameters       | Return Type | Description |
|-------------|------------------|-------------|-------------|
| `token`     | `String secret`  | `String`    | Generates a JSON Web Token (JWT) for the user using the provided secret key. |

#### **Key Details**
- Uses the `io.jsonwebtoken.security.Keys` library to create a cryptographic key from the secret.
- The token is signed with the user's username as the subject.

---

### **assertAuth**
| Method Name   | Parameters                  | Return Type | Description |
|---------------|-----------------------------|-------------|-------------|
| `assertAuth`  | `String secret`, `String token` | `void`      | Validates the provided JWT token using the secret key. Throws an exception if the token is invalid. |

#### **Key Details**
- Parses and verifies the JWT token using the secret key.
- Logs unauthorized access attempts and throws an `Unauthorized` exception if validation fails.

---

### **fetch**
| Method Name | Parameters       | Return Type | Description |
|-------------|------------------|-------------|-------------|
| `fetch`     | `String un`      | `User`      | Retrieves a user from the database based on the provided username. |

#### **Key Details**
- Connects to a PostgreSQL database using the `Postgres.connection()` method.
- Executes a query to fetch user details (`id`, `username`, `password`) from the `users` table.
- Returns a `User` object populated with the retrieved data.

---

## Insights

### **Security Concerns**
1. **SQL Injection Risk**: The method `fetch` uses a raw SQL query (`String query`) that concatenates user input directly into the query string. This approach is vulnerable to SQL injection attacks. While a `PreparedStatement` is also used, the raw query should be removed entirely to mitigate risks.
2. **Hardcoded Secret Key**: The `token` and `assertAuth` methods rely on a secret key passed as a parameter. Ensure the secret is securely managed and not hardcoded or exposed in logs.
3. **Error Logging**: Sensitive information (e.g., exception messages) is logged, which could potentially expose system details to attackers.

### **Database Connection Management**
- The `fetch` method does not properly handle closing the database connection in case of exceptions. This could lead to resource leaks.

### **Cryptographic Operations**
- The class uses the `io.jsonwebtoken` library for JWT generation and validation, which is a standard and secure approach for token-based authentication.

### **Logging**
- The class uses `java.util.logging.Logger` for logging operations. Ensure log levels are appropriately configured to avoid exposing sensitive information in production environments.

---

## Recommendations
- Replace raw SQL queries with parameterized queries to prevent SQL injection.
- Implement proper exception handling to ensure database connections are closed in all scenarios.
- Use a secure mechanism to manage and store the secret key for cryptographic operations.
- Avoid logging sensitive information, especially in production environments.
