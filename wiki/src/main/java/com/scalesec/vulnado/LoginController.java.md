# Documentation: LoginController

## Overview
The `LoginController` is a RESTful API controller designed to handle user authentication requests. It provides a login endpoint that validates user credentials and returns a token for successful authentication. The controller leverages Spring Boot and integrates with a database for user data retrieval and password validation.

---

## Features
### Login Endpoint
- **URL**: `/login`
- **HTTP Method**: POST
- **Request Format**: JSON
- **Response Format**: JSON
- **Cross-Origin Support**: Enabled for all origins (`*`).

---

## Data Structures

### LoginRequest
| **Field**   | **Type**   | **Description**                     |
|-------------|------------|-------------------------------------|
| `username`  | `String`   | The username provided by the user. |
| `password`  | `String`   | The password provided by the user. |

This class represents the structure of the incoming request payload for the login endpoint.

### LoginResponse
| **Field**   | **Type**   | **Description**                     |
|-------------|------------|-------------------------------------|
| `token`     | `String`   | The authentication token returned upon successful login. |

This class represents the structure of the response payload for the login endpoint.

### Unauthorized
| **Field**   | **Type**   | **Description**                     |
|-------------|------------|-------------------------------------|
| `exception` | `String`   | The error message describing the unauthorized access. |

This class is used to handle unauthorized access scenarios by throwing a runtime exception with an HTTP status of `401 Unauthorized`.

---

## Logic

### Login Process
1. **Input Validation**:
   - The endpoint accepts a `LoginRequest` object containing `username` and `password`.
   
2. **User Retrieval**:
   - The `User.fetch()` method retrieves user details from the database based on the provided `username`.

3. **Password Validation**:
   - The password provided in the request is hashed using `Postgres.md5()` and compared with the stored hashed password (`user.hashedPassword`).

4. **Token Generation**:
   - If the password matches, a token is generated using the `user.token(secret)` method and returned in a `LoginResponse`.

5. **Error Handling**:
   - If the password does not match, an `Unauthorized` exception is thrown with the message "Access Denied".

---

## Insights
- **Security**:
  - The application uses hashed passwords for validation, ensuring that plaintext passwords are not stored or transmitted.
  - The token generation process leverages a secret value (`app.secret`) for added security.

- **Scalability**:
  - The use of `@CrossOrigin` allows the endpoint to be accessed from any origin, making it suitable for integration with multiple front-end applications.

- **Error Handling**:
  - Unauthorized access is handled gracefully by throwing a custom exception (`Unauthorized`) with an appropriate HTTP status code (`401 Unauthorized`).

- **Extensibility**:
  - The design allows for easy integration with additional authentication mechanisms or enhancements, such as multi-factor authentication or rate limiting.

- **Dependencies**:
  - The controller relies on external methods (`User.fetch()` and `Postgres.md5()`) for user data retrieval and password hashing. These methods are assumed to be implemented elsewhere in the application.
