# Auto Service

Auto Service is a Java based web application that users to place orders for car repairment. It is developed using Spring Boot framework. The database is managed by Spring Data JPA using Hibernate ORM (under the hood). My project is using 3-layer architecture and following SOLID principles.

<!-- TOC -->
* [Auto Service](#auto-service)
    * [Features](#features)
    * [Used Technologies](#used-technologies)
    * [Set-up and Running the Project](#set-up-and-running-the-project)
<!-- TOC -->

![Front-end Preview Image](https://i.imgur.com/wI8otqO.png "Preview")

### Features

- Swagger API documentation
  - Use it to view all endpoints
  - Accessible via http://localhost:8080/swagger-ui/index.html
- Get user's performed and repairer's completed orders
- Calculate and retrieve order's price for user including possible discounts
- Calculate repairer's salary based on overhauls completed by him and paid by user
- Consider empty order is car diagnose overhaul and require fixed amount of money from user

### Used Technologies

| Name                            | ver.        |
|---------------------------------|-------------|
| Java                            | `17.0.4`    |
| Maven (Wrapper)                 | `3.8.6`     |
| Docker                          | `20.10.21`  |
| PostgreSQL JDBC Driver          | `42.5.1`    |
| PostgreSQL Server (win)         | `15.1`      |
| Swagger                         | `4.15.5`    |
| OpenAPI                         | `3.0`       |
| Tomcat (Embedded)               | `10.1.4`    |
| Spring Boot (WEB MVC, Data JPA) | `3.0.1`     |
| Hibernate (Integrated)          | `6.1.6`     |

### Set-up and Running the Project

1. Clone the repository to your local machine.
2. Open project's root directory through your terminal (IDE's or any preferred)
3. 
   - Simple approach:
     - Install [Docker Desktop](https://www.docker.com/products/docker-desktop/)
     - Run following command: `docker compose up --build`
   - DIY approach:
     - Set up a local [PostgreSQL](https://www.postgresql.org/download/) server.
     - Set `JAVA_HOME` environment variable in your system that navigates to directory of your [installed JDK](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html).
     - Update database configuration in the [`db.properties`](https://github.com/sequencerr/my-autoservice-app/blob/main/src/main/resources/application.properties#L2) file.
     - Run `./mvnw spring-boot:run` command.
4. Go to **http://localhost:8080/swagger-ui/index.html** in your web browser.
   - Then you can select endpoint and go to http://localhost:8080/endpoint or just continue to execute requests on the Swagger page.
