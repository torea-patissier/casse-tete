# CasseTeteApplication

CasseTeteApplication is a Java-based application designed to solve a specific puzzle by performing a series of calculations based on predefined operations and equations. The application utilizes an H2 database for data storage and retrieval.

## Table of Contents

- [Tech Stack](#tech-stack)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Configuration](#configuration)
- [Usage](#usage)
- [Database Access](#database-access)
- [Operations and Equations](#operations-and-equations)
- [Project Structure](#project-structure)
- [Contributing](#contributing)
- [License](#license)


## Tech Stack
- **Java**: Core language used for development.

- **Spring Boot**: Framework for building the application with embedded Tomcat.

- **Spring Data JPA**: Abstraction layer for database operations.

- **H2 Database**: Lightweight in-memory database for testing and development.

- **Maven**: Build automation and dependency management.
## Prerequisites

Before running the application, ensure you have the following installed:

- **Java Development Kit (JDK) 11 or higher**: Verify installation by running `java -version` in your terminal.
- **Maven**: Used for building and managing project dependencies. Verify installation by running `mvn -version`.

## Installation

1. **Clone the Repository**:

   ```bash
   git clone https://github.com/torea-patissier/casse-tete.git
   ```

2. **Navigate to the Project Directory**:

   ```bash
   cd casse-tete
   ```

3. **Build the Project**:

   ```bash
   mvn clean install
   ```

## Configuration

The application is pre-configured to use an H2 database stored at `./data/testdb`. Default database credentials are set as follows:

- **JDBC URL**: `jdbc:h2:file:./data/testdb`
- **Username**: `sa`
- **Password**: *(leave empty)*

## API Documentation

[CLICK HERE](https://documenter.getpostman.com/view/18685609/2sAYkBrLPi)

## Usage

1. **Run the Application**:

   ```bash
   mvn spring-boot:run
   ```

2. **Access the Application**:

   Once running, access the application's functionalities as per your requirements.

## Database Access

To interact directly with the H2 database:

1. **Access the H2 Console**:

   Open your web browser and navigate to [http://localhost:9090/h2/](http://localhost:9090/h2/).

2. **Connect to the Database**:

    - **JDBC URL**: `jdbc:h2:file:mem:testdb`
    - **Username**: `sa`
    - **Password**: *(leave empty)*

## Operations and Equations

The application performs calculations based on the following operations:

**To complete the puzzle, the application must compute the final equation by substituting the following operations and values : 66**

| Operation  | Value      |
|------------|------------|
| calculateA | 13 * B / C |
| calculateB | 12 * E     |
| calculateC | G * H / I  |

The final equation computed by the application is:

```
A + calculateA + D + calculateB - F - 11 + calculateC - 10
```

## Project Structure

```
├── src
│   ├── main                 # Main application source code
│   │   ├── java             # Java source code
│   │   │   └── com/example/casse_tete
│   │   │       ├── CasseTeteApplication.java  # Main entry point of the application
│   │   │       ├── controller/                # REST API controllers handling HTTP requests
│   │   │       ├── exception/                 # Global error and exception handling
│   │   │       ├── model/                      # Data models representing entities
│   │   │       ├── repo/                       # Data access layer (repository interfaces for DB operations)
│   │   │       └── service/                    # Business logic layer handling application operations
```