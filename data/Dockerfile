# Dockerfile
FROM maven:3.8.4-openjdk-11 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM openjdk:11-jre-slim
WORKDIR /app
COPY --from=build /app/target/data-1.0-SNAPSHOT.jar .
CMD ["java", "-jar", "data-1.0-SNAPSHOT.jar"]