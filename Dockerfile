# Use the official Gradle image as a base for building the application
FROM gradle:8.4.0-jdk21-alpine as builder

# Set the working directory
WORKDIR /app

# Copy the project files to the working directory
COPY . .

# Build the application and create the JAR file
RUN gradle bootJar

# Run the application directly from the Gradle image
CMD ["java", "-jar", "build/libs/pollApp-0.0.1-SNAPSHOT.jar"]
