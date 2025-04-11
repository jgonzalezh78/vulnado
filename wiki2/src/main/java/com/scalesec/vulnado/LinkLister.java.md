# Documentation: LinkLister.java

## Overview
The `LinkLister` class is designed to extract hyperlinks from a given webpage URL. It provides functionality to retrieve all links from a webpage and includes a mechanism to validate URLs to prevent the use of private IP addresses. This class is useful for applications that need to analyze or process links from web pages.

---

## Features
### 1. **Extracting Links**
   - The `getLinks` method retrieves all hyperlinks (`<a>` tags) from a given webpage URL.
   - It uses the `Jsoup` library to parse the HTML content of the webpage and extract the absolute URLs of the links.

### 2. **Validation of URLs**
   - The `getLinksV2` method adds an additional layer of validation to ensure that the provided URL does not point to a private IP address.
   - If the URL's host starts with private IP ranges (`172.`, `192.168`, or `10.`), the method throws a `BadRequest` exception.

---

## Methods

| Method Name       | Description                                                                 | Input Parameters | Return Type       | Exceptions Thrown |
|-------------------|-----------------------------------------------------------------------------|------------------|-------------------|-------------------|
| `getLinks`        | Extracts all hyperlinks from the given webpage URL.                        | `String url`     | `List<String>`    | `IOException`     |
| `getLinksV2`      | Validates the URL and extracts hyperlinks, rejecting private IP addresses. | `String url`     | `List<String>`    | `BadRequest`      |

---

## Key Components

### 1. **Data Structures**
   - **`List<String>`**: Used to store the extracted hyperlinks.

### 2. **Logic**
   - **HTML Parsing**: The `Jsoup` library is used to parse the HTML content and extract `<a>` tags.
   - **URL Validation**: The `getLinksV2` method checks the host of the URL to ensure it does not belong to private IP ranges.

---

## Insights

- **Security Considerations**: The `getLinksV2` method ensures that private IP addresses are not used, which is critical for preventing potential security vulnerabilities such as accessing internal networks.
- **Error Handling**: The class uses custom exceptions (`BadRequest`) to handle invalid inputs or errors during URL validation.
- **Scalability**: The use of `Jsoup` for HTML parsing makes the solution robust and capable of handling complex web pages.
- **Logging**: The `logger.info` statements provide visibility into the host validation process, aiding in debugging and monitoring.

---

## Dependencies

| Dependency Name | Purpose                                                                 |
|-----------------|-------------------------------------------------------------------------|
| `Jsoup`         | Used for parsing HTML and extracting elements from the webpage content. |
| `java.net.URL`  | Used for URL validation and extracting the host information.            |
| `java.util.List`| Used for storing the extracted links.                                   |
| `java.util.logging.Logger` | Used for logging information during URL validation.          |

---

## Exception Handling

| Exception Name | Trigger Condition                                   | Description                                      |
|----------------|-----------------------------------------------------|-------------------------------------------------|
| `IOException`  | When there is an issue connecting to the provided URL. | Indicates a failure in retrieving webpage content. |
| `BadRequest`   | When the URL points to a private IP address or other validation fails. | Indicates invalid input or restricted access.    |

---

## Usage Example

### Extracting Links
```java
List<String> links = LinkLister.getLinks("https://example.com");
System.out.println(links);
```

### Validating and Extracting Links
```java
try {
    List<String> links = LinkLister.getLinksV2("https://example.com");
    System.out.println(links);
} catch (BadRequest e) {
    System.err.println("Error: " + e.getMessage());
}
```

---

## Limitations
- The class does not handle cases where the webpage contains malformed HTML.
- The validation logic only checks for private IP ranges but does not account for other potentially unsafe URLs.
