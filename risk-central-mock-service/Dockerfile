# --- Etapa 1: Build ---
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .
# Compilamos específicamente el módulo mock
RUN mvn clean package -pl risk-central-mock-service -am -DskipTests

# --- Etapa 2: Run ---
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=build /app/risk-central-mock-service/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]