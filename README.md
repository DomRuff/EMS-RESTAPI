# EMS-RESTAPI

The **EMS-RESTAPI** is a REST API built using Spring Boot, backed by a PostgreSQL database. It provides CRUD (Create, Read, Update, Delete) functionality for managing employees. The API is structured with models, services, repositories, and controllers to ensure separation of concerns and scalability.

## Features
- Employee management
- Full CRUD functionality via HTTP requests
- DTOs (Data Transfer Objects) for managing request and response data
- PostgreSQL as the database for persistent data storage
- RESTful API endpoints for easy integration

## Technologies
- **Java 17**
- **Spring Boot 3**
- **Spring Data JPA**
- **PostgreSQL**
- **Maven** for build management

---

## Table of Contents
- [Models](#models)
- [Repositories](#repositories)
- [Services](#services)
- [Controllers & Endpoints](#controllers--endpoints)
- [Setup Instructions](#setup-instructions)

---
## Models

The **EMS-RESTAPI** API provides one key model: **Employee**. This models define the employee entity stored in the PostgrSQL database. The model is basic in nature and may be expanded on in the future. The model is provided below to get an idea of how it is structured. Full classes can be found in the `model` folder.

### Employee Model
```java
public class Employee {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private EmplyeeRole role;
}
```

### EmployeRole Model
```java
public enum EmployeeRole {
    ADMIN,
    MANAGER,
    EMPLOYEE
}
```
## Repositories

The Spring Data JPA's *JpaRepository* interface is leveraged to perform database operations on the entities. This is the employee repository:
- **EmployeeRepository**
- 
---

## Services

The services layer abstracts the business logic and interacts with the repositories. By using DTOs (Data Transfer Objects) for requests and responses,
the API is able to keep the internal data model separate from the external facing API model. This allows for:

- **Data Security**: Sensitive fields or those only related to business logic can be hidden from the API response.
- **Decoupling**: Internal models can change without affecting API consumers.
- **Input validation**: DTOs can ensure proper data validation before interacting with the database.

### Example User Service

An example of how the service class utilizes Requests and Response classes, rather than relying on the original mode. Full service classes can be found in the `service` package. 
```java
public class EmployeeService {
  public void createEmployee(EmployeeRequest employeeRequest) {...}
  public List<EmployeeResponse> getAllEmployees() {...}
  public EmployeeResponse getEmployeeById(Long id) {...}
  public void updateEmployee(Long id, EmployeeRequest employeeRequest) {...}
  public void deleteEmployee(Long id) {...}

```

### Example DTOs

An example of how Requests and Responses are used in the service classes, rather than relying on the original mode. Full DTO classes can be found in the `dto` package. 

EmployeeRequest DTO
```java
public class EmployeeRequest {

    private String fistName;
    private String lastName;
    private String email;
    private EmployeeRole role;
}
```

EmployeeResponse DTO
```java
public class EmployeeResponse {

    private Long id;
    private String fistName;
    private String lastName;
    private String email;
    private EmployeeRole role;
}
```

### Mapper

To make it simple to map from models to DTOs simple, a `EmployeeMapper` class has been created which can be found in the `mapper` package. Below is the conversion implementation.

```java
public class EmployeeMapper {

    public static EmployeeResponse toEmployeeResponse(Employee employee) {
        return EmployeeResponse.builder()
                .id(employee.getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .email(employee.getEmail())
                .role(employee.getRole())
                .build();
    }

    public static Employee toEmployee(EmployeeRequest employeeRequest) {

        Employee employee = Employee.builder()
                .firstName(employeeRequest.getFirstName())
                .lastName(employeeRequest.getLastName())
                .email(employeeRequest.getEmail())
                .role(employeeRequest.getRole())
                .build();
        return employee;
    }

```
---
## Controller & Endpoints

The model has a dedicated REST controller that provides mappings for CRUD operations. Below are the mappings and endpoints for the employee entity.

### Employee API

| HTTP Method | Endpoint          | Description                |
|-------------|-------------------|----------------------------|
| `POST`      | `/employee/add`   | Create a new employee        |
| `GET`       | `/employee`       | Get all employee             |
| `GET`       | `/employee/{id}`  | Get a employee by ID         |
| `PUT`       | `/employee/{id}`  | Update employee information     |
| `DELETE`    | `/employee/{id}`  | Delete an employee            |
---
## Setup Instructions

### Prerequisites
- Java 17 or higher
- Maven
- PostgreSQL server

### Installation

1. **Clone the repository:**
```bash
git clone https://github.com/DomRuff/EMS-RESTAPI.git
cd EMS-RESTAPI
```
2. **Configure PostgreSQL:** Set up a PostgreSQL database and update the `src/main/resource/application.properties` file with your PostgreSQL configuration. Additionall, hibernate is used to automatically create the table and columns:
```bash
spring.datasource.url= YOUR DATABASE
spring.datasource.username= YOUR USERNAME
spring.datasource.password= YOUR PASSWORD
spring.jpa.hibernate.ddl-auto=update
```
3. **Build the project:** Run Maven to install dependencies and build the project:
```bash
mvn clean install
```
4. **Run the application:**
```bash
mvn spring-boot:run
```

### API Testing
With in `api` folder a `Employee.http` HTTP file can be found which depicts test API calls. You may test the API or manually add instances of the modesl through them.
