# Documentation: LoginController.java

## Overview
The `LoginController` class is part of a web application that handles user authentication. It provides an endpoint for users to log in by verifying their credentials against stored data. The controller uses Spring Boot annotations to define its behavior and integrates with a database for user validation.

---

## Functional Description

### **Login Endpoint**
- **URL**: `/login`
- **HTTP Method**: `POST`
- **Request Format**: JSON
- **Response Format**: JSON
- **Cross-Origin Support**: Enabled for all origins (`@CrossOrigin(origins = "*")`).

#### **Request Body**
The endpoint expects a JSON object containing:
| Field Name | Type   | Description                     |
|------------|--------|---------------------------------|
| `username` | String | The username of the user.       |
| `password` | String | The password of the user.       |

#### **Response Body**
On successful authentication, the endpoint returns:
| Field Name | Type   | Description                     |
|------------|--------|---------------------------------|
| `token`    | String | A token generated for the user. |

#### **Error Handling**
If authentication fails, the endpoint throws an `Unauthorized` exception with the message "Access Denied". This results in an HTTP 401 Unauthorized response.

---

## Key Components

### **LoginController**
- **Purpose**: Handles the `/login` endpoint for user authentication.
- **Logic**:
  1. Fetches user details from the database using the provided username.
  2. Compares the hashed password from the request with the stored hashed password.
  3. If the credentials match, generates a token using a secret key and returns it.
  4. If the credentials do not match, throws an `Unauthorized` exception.

- **Security Considerations**:
  - The `@CrossOrigin(origins = "*")` annotation allows requests from all origins. This should be reviewed to ensure it aligns with security policies.
  - The secret key (`app.secret`) is injected from application properties and used for token generation.

### **LoginRequest**
- **Purpose**: Represents the structure of the login request.
- **Fields**:
  | Field Name | Type   | Access Modifier | Description                     |
  |------------|--------|-----------------|---------------------------------|
  | `username` | String | Private         | The username of the user.       |
  | `password` | String | Private         | The password of the user.       |
- **Methods**:
  - `getUsername()`: Retrieves the username.
  - `setUsername(String username)`: Sets the username.
  - `getPassword()`: Retrieves the password.
  - `setPassword(String password)`: Sets the password.

### **LoginResponse**
- **Purpose**: Represents the structure of the login response.
- **Fields**:
  | Field Name | Type   | Access Modifier | Description                     |
  |------------|--------|-----------------|---------------------------------|
  | `token`    | String | Private         | The token generated for the user. |
- **Methods**:
  - `getToken()`: Retrieves the token.
  - `setToken(String token)`: Sets the token.

### **Unauthorized**
- **Purpose**: Custom exception class for handling unauthorized access.
- **Behavior**:
  - Throws an HTTP 401 Unauthorized response when invoked.
  - Accepts a custom exception message.

---

## Insights
1. **Security Risks**:
   - The `@CrossOrigin(origins = "*")` annotation allows unrestricted cross-origin requests, which may expose the application to security vulnerabilities. It is recommended to restrict origins to trusted domains.
   - Passwords are hashed using `Postgres.md5`. Ensure the hashing mechanism is robust and complies with modern security standards.

2. **Token Generation**:
   - The token is generated using a secret key (`app.secret`). Proper management of this key is critical to prevent unauthorized access.

3. **Error Handling**:
   - The `Unauthorized` exception provides a clear mechanism for handling failed authentication attempts. Ensure sensitive information is not exposed in error messages.

4. **Scalability**:
   - The current implementation fetches user details and validates passwords synchronously. Consider optimizing for scalability if the user base grows significantly.

5. **Data Privacy**:
   - The `LoginRequest` class stores sensitive information (username and password). Ensure proper measures are in place to prevent unauthorized access or leakage of this data.

---
