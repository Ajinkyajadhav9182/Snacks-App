# ==============================
# Stage 1: Build the project with Maven
# ==============================
FROM maven:3.9.4-eclipse-temurin-17 AS builder

# Set working directory
WORKDIR /app

# Copy all files to container
COPY . .

# Build the application (skip tests for faster builds)
RUN mvn clean package -DskipTests

# ==============================
# Stage 2: Run the application with lightweight JDK
# ==============================
FROM eclipse-temurin:17-jdk-alpine

# Install CA certificates (needed for SSL connections to Supabase)
RUN apk add --no-cache ca-certificates

# Set working directory
WORKDIR /app

# Copy jar from builder stage
COPY --from=builder /app/target/*.jar app.jar

# Expose application port
EXPOSE 8080

# Run the app
ENTRYPOINT ["java", "-jar", "app.jar"]
