FROM gradle:8.4.0-jdk21 AS builder

# Set the working directory
WORKDIR /app

# Copy the project files to the working directory
COPY . .

# Ensure gradlew is executable and build the application
RUN chmod +x gradlew && ./gradlew bootJar

FROM openjdk:21-jdk-slim AS runtime

# Create a non-root user and switch to that user
RUN useradd -ms /bin/sh spring

WORKDIR /app

# Copy the JAR file from the builder stage
COPY --from=builder /app/build/libs/*.jar app.jar

# Change ownership of the app.jar to the non-root user
RUN chown spring:spring app.jar

# Switch to the non-root user
USER spring

CMD ["java", "-jar", "app.jar"]
