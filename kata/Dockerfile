# ---- Build Stage ----
FROM gradle:8.3-jdk17 AS build
WORKDIR /app

# Copy Gradle build files (Kotlin DSL)
COPY build.gradle.kts settings.gradle.kts ./
COPY gradle ./gradle

# Copy source code
COPY src ./src

# Run Gradle build
RUN gradle clean bootJar -x test

# ---- Run Stage ----
FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
