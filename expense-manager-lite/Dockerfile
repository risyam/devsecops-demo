# ---- build stage ----
FROM maven:3.9-eclipse-temurin-23 AS builder
WORKDIR /app

# copy pom first to leverage Docker layer cache for dependencies
COPY pom.xml ./

# resolve dependencies (go-offline helps, but see notes)
RUN mvn -B -f pom.xml dependency:go-offline

# copy the rest of the project (use .dockerignore to exclude target/)
COPY . .

# defensive: ensure no stale build artifacts
RUN rm -rf /app/target || true

# build artifact (skip tests if OAuth2 creds are not available)
RUN mvn -B clean package -DskipTests

# Find and copy the executable JAR (exclude .original files)
RUN find /app/target -name "*.jar" ! -name "*.original" -exec cp {} /app/app.jar \;

# ---------- runtime ----------
FROM eclipse-temurin:21-jre

# create a non-root user (using UID 1001 to avoid conflicts with base image)
RUN useradd -m -u 1001 myuser

WORKDIR /app

# Copy the built jar from builder stage
COPY --from=builder --chown=myuser:myuser /app/app.jar /app/app.jar

# Switch to non-root user
USER myuser

# Expose the port your app listens on
EXPOSE 8085

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
