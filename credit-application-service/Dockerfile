# --- Etapa 1: Build (Maven + JDK) ---
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

# Copiamos TODO el proyecto (Raíz) al contenedor para que lea el pom padre
COPY . .

# Ejecutamos el empaquetado solo de este módulo (-pl) y sus dependencias (-am)
# -DskipTests para agilizar el build en contenedor (ya debiste probar localmente)
RUN mvn clean package -pl credit-application-service -am -DskipTests

# --- Etapa 2: Run (JRE Slim) ---
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Copiamos solo el JAR generado desde la etapa de build
COPY --from=build /app/credit-application-service/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]