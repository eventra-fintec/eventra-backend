# Etapa 1: Build
FROM eclipse-temurin:21 AS build
WORKDIR /app
COPY .mvn/ .mvn
COPY mvnw .
COPY pom.xml .
COPY src ./src
RUN chmod +x mvnw && ./mvnw clean package -DskipTests

# Etapa 2: Runtime
FROM eclipse-temurin:21
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
