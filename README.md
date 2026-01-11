# Hospital Management System

This is a simple hospital management application built with Spring Boot.

## Technologies Used

*   **Java 21**
*   **Spring Boot 4.0.1**
*   **Spring Data JPA**
*   **PostgreSQL**
*   **Maven**

## How to Run

1.  **Clone the repository:**
    ```bash
    git clone <repository-url>
    ```
2.  **Configure the database:**
    *   Open `src/main/resources/application.properties`.
    *   Update the `spring.datasource.url`, `spring.datasource.username`, and `spring.datasource.password` properties to match your local PostgreSQL setup.
3.  **Run the application:**
    *   You can run the `HospitalManagementApplication.java` file from your IDE or use the following Maven command:
        ```bash
        mvn spring-boot:run
        ```

The application will start on `http://localhost:8080`.
