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

# Install default-mysql-client
RUN apt-get update && apt-get install -y default-mysql-client && rm -rf /var/lib/apt/lists/*

# Set the working directory
WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=build /app/target/*.jar jenkins-ci-cd.jar

# Copy the wait-for.sh script
COPY wait-for.sh /wait-for.sh
RUN chmod +x /wait-for.sh

# Set the entry point to run the wait-for.sh script
ENTRYPOINT ["/wait-for.sh", "mysql", "java", "-jar", "jenkins-ci-cd.jar"]