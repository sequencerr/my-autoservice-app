FROM node:18.14-alpine as angular
WORKDIR /usr/src/app
ARG FRONTEND_DIR=./src/main/resources/frontend
COPY ${FRONTEND_DIR}/src ./src
COPY ${FRONTEND_DIR}/tsconfig*.json ${FRONTEND_DIR}/angular.json ${FRONTEND_DIR}/package.json ./
RUN yarn install --non-interactive --no-lockfile
RUN yarn ng b --output-path=./dist

FROM maven:3.9-eclipse-temurin-17-alpine as build
WORKDIR /usr/src/app
COPY ./pom.xml ./checkstyle.xml  ./
COPY ./src/main/java ./src/main/java
COPY ./src/main/resources/db ./src/main/resources/db
COPY ./src/main/resources/application.properties  ./src/main/resources
COPY --from=angular /usr/src/app/dist ./src/main/resources/static
RUN mvn clean package --no-transfer-progress -DskipTests=true

FROM amazoncorretto:17-alpine
WORKDIR /usr/src/app
COPY --from=build /usr/src/app/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
EXPOSE 5005
