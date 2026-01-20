# Multi-stage build for Spring Boot + Python (for Acceleo)
FROM maven:3.9-eclipse-temurin-17 AS build

WORKDIR /app

# Copy pom.xml and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy source code and build
COPY src ./src
RUN mvn clean package -DskipTests

# Runtime stage
FROM eclipse-temurin:17-jre-alpine

# Install Python for Acceleo (if needed)
RUN apk add --no-cache python3 py3-pip

WORKDIR /app

# Copy built JAR
COPY --from=build /app/target/*.jar app.jar

# Create directories for storage
RUN mkdir -p /tmp/acceleo-generator/generated

# Expose port
EXPOSE 8080

# Run application
ENTRYPOINT ["java", "-jar", "app.jar"]

