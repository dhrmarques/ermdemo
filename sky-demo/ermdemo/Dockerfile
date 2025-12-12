FROM openjdk:17-jdk-alpine

# Working directory inside the container
WORKDIR /app

COPY target/ermdemo-0.0.1-SNAPSHOT.jar /app/ermdemo-0.0.1-SNAPSHOT.jar

# Expose Tomcat port
EXPOSE 8080

# Run the Spring Boot application
CMD ["java", "-jar", "/app/ermdemo-0.0.1-SNAPSHOT.jar"]