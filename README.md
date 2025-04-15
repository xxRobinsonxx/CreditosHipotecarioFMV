# Proyecto Créditos

Esta aplicación gestiona la colocación de créditos de FMV. Ofrece las siguientes funcionalidades:
- Listar créditos
- Registrar un crédito
- Importar datos desde un archivo Excel (.xlsx)

## Requisitos

- Java 21
- Maven
- Docker y Docker Compose
- PostgreSQL (la base de datos se levanta automáticamente con Docker Compose)

## Configuración

La conexión a la base de datos y el puerto de la aplicación se definen mediante variables de entorno en el archivo `application.properties`:
```properties
spring.datasource.url=jdbc:postgresql://${DB_HOST}:${DB_PORT}/${DB_NAME}
spring.datasource.username=${DB_USERNAME:postgres}
spring.datasource.password=${DB_PASSWORD:0racle}
spring.datasource.driver-class-name=org.postgresql.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
server.port=${SERVER_PORT:8080}
