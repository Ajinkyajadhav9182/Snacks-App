# Use Maven image to build the project first
FROM maven:3.9.4-eclipse-temurin-17 AS builder

# Set working directory
WORKDIR /app

# Copy all files to container
COPY . .

# Build the application
RUN mvn clean package -DskipTests

# ==============================

# Use a lightweight JDK to run the app
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Copy the jar from the builder stage
COPY --from=builder /app/target/*.jar app.jar

# Expose port (optional if Render sets it)
EXPOSE 8080

# Run the app
ENTRYPOINT ["java", "-jar", "app.jar"]
