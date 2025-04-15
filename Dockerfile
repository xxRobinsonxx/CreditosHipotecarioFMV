# Etapa de construcción
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
# Copia todo el código fuente y luego compila
COPY . .
RUN mvn clean package -DskipTests

# Etapa de ejecución
FROM eclipse-temurin:21-jdk-jammy
WORKDIR /app
# Copia el JAR generado (ajusta el nombre según tu versión)
COPY --from=build /app/target/Creditos-0.0.1-SNAPSHOT.jar app.jar
# En este ejemplo, la aplicación se ejecuta internamente en el puerto 8080
EXPOSE 8000
ENTRYPOINT ["java", "-jar", "app.jar"]
