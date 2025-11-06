# Use a Maven-based image for building
FROM maven:3.9-eclipse-temurin-23 as builder

# Set the working directory
WORKDIR /app

# Copy the pom.xml file
COPY pom.xml .

# Download dependencies
RUN mvn dependency:resolve

# Copy the project source code
COPY . .

# Build the project (skip tests as they require OAuth2 credentials)
RUN mvn clean install -DskipTests

# Use a smaller JRE-based image for running
FROM eclipse-temurin:21

# Add a non-root user
RUN useradd -m myuser

# Switch to non-root user
USER myuser

# Copy the JAR file from the builder stage
COPY --from=builder /app/target/*.jar /app.jar

# Set the working directory
WORKDIR /

# Execute the command to run the application
ENTRYPOINT ["java", "-jar", "/app.jar"]