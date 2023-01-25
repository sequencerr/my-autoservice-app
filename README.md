# Auto Service

Auto Service is a Java based web application that users to place orders for car repairment. It is developed using Spring Boot framework. The database is managed by Spring Data JPA using Hibernate ORM (under the hood). My project is using 3-layer architecture and following SOLID principles.

### Features

- Swagger API documentation
  - Use it to view all endpoints
  - Accessible via http://localhost:8080/swagger-ui/index.html
- Get user's performed and repairer's completed orders
- Calculate and retrieve order's price for user including possible discounts
- Calculate repairer's salary based on services completed by him and paid by user
- Consider empty order is car diagnose service and require fixed amount of money from user

### Used Technologies

| Name                            | ver.     |
|---------------------------------|----------|
| Java                            | `17.0.4` |
| Maven (Wrapper)                 | `3.8.6`  |
| PostgreSQL JDBC Driver          | `42.5.1` |
| PostgreSQL Server (win)         | `15.1`   |
| Swagger                         | `4.15.5` |
| OpenAPI                         | `3.0`    |
| Tomcat (Embedded)               | `10.1.4` |
| Spring Boot (WEB MVC, Data JPA) | `3.0.1`  |
| Hibernate (Integrated)          | `6.1.6`  |

### Set-up and Running the Project

1. Clone the repository to your local machine.
2. Open the project in your preferred Java IDE (Eclipse, IntelliJ, etc.).
3. Set up a local PostgreSQL(recommended)/MySQL DBMS server and update the database configuration in the [`db.properties`](https://github.com/sequencerr/my-autoservice-app/blob/main/src/main/resources/db.properties#L2) file.
4. Import all the required dependencies (IntelliJ IDEA Command: `Reload All Maven Projects`).
5. Run the project from [`AutoserviceApplication`](https://github.com/sequencerr/my-autoservice-app/blob/main/src/main/java/task/autoservice/AutoserviceApplication.java#L8) java file, `main()` method.
6. Go to http://localhost:8080/swagger-ui/index.html in your web browser.
   - Then you can select preferred endpoint and go to http://localhost:8080/endpoint or just continue to execute requests on the Swagger page. 