# ---------- Stage 1: Build and Test ----------
FROM maven:3.9.6-eclipse-temurin-17 AS builder

# Set working directory
WORKDIR /app

# Copy pom.xml and download dependencies (for caching)
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy source code
COPY src ./src

# Run tests and build the app
RUN mvn clean test package -DskipTests=false -B

# ---------- Stage 2: Runtime ----------
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy built jar from the builder stage
COPY --from=builder /app/target/*.jar app.jar

# Expose container port
EXPOSE 8080

# Environment variables (optional)
ENV JAVA_OPTS="-Xms256m -Xmx512m"

# Run application
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
