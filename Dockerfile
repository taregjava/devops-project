# Stage 1: Build
FROM maven:3.8.5-openjdk-17-slim AS build

# Set the working directory
WORKDIR /app

# Copy the project files
COPY . /app

# Build the project, skipping tests
RUN mvn clean package -DskipTests

# Stage 2: Runtime
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=build /app/target/*.jar jenkins-ci-cd.jar

# Set the entry point to run the JAR file
ENTRYPOINT ["java", "-jar", "jenkins-ci-cd.jar"]
