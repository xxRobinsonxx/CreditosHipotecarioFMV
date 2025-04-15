# Proyecto Créditos

Esta aplicación gestiona la colocación de créditos de FMV. Ofrece las siguientes funcionalidades:
- **Listar** créditos.
- **Registrar** un crédito.
- **Importar** datos desde un archivo Excel (.xlsx).


## Requisitos

- **Java 21** (asegúrese de tener el JDK adecuado instalado).
- **Maven** (para compilar y empaquetar la aplicación).
- **Docker** y **Docker Compose** (opcional, para desplegar la aplicación en contenedores).
- **PostgreSQL** (la base de datos se levanta automáticamente con Docker Compose).

## Configuración

La conexión a la base de datos y el puerto de la aplicación se definen mediante variables de entorno en el archivo `application.properties`:
``` properties
spring.datasource.url=jdbc:postgresql://${DB_HOST}:${DB_PORT}/${DB_NAME}
spring.datasource.username=${DB_USERNAME:postgres}
spring.datasource.password=${DB_PASSWORD:0racle}
spring.datasource.driver-class-name=org.postgresql.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
server.port=${SERVER_PORT:8080}
```
## Archivo .env
Cree un archivo .env en la raíz del despliegue (junto al docker-compose.yml):

```properties
# Configuración de la base de datos
POSTGRES_DB=TuBaseDatos
POSTGRES_USER=TuUsuario
POSTGRES_PASSWORD=TuContraseña

# Configuración del servidor
SPRING_PORT = 8000
CONTEXT_PATH=/
```

## Despliegue con Docker
La aplicación está dockerizada y se puede levantar junto a su base de datos PostgreSQL usando Docker Compose.

1. Asegúrese de tener Docker y Docker Compose instalados.
2. En el directorio raíz del proyecto (donde se encuentran el Dockerfile y el docker-compose.yml), ejecute:
```
    docker-compose up --build
    docker-compose up -d
```
3. La API estará disponible en http://localhost:8000.
   
## Endpoints Principales
* **Registrar Crédito**
  ```POST /api/creditos```
* **Listar Creditos**
    ```GET /api/creditos```
* **Importar Excel**
    ```POST /api/creditos/importar```
## Importar Datos ( POST /api/creditos/importar)
### Diccionario de Datos
![diccionarioDatos.png](diccionarioDatos.png)

