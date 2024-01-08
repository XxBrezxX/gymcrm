# Imagen base
FROM maven:3.8.4-openjdk-17-slim as build

# Directorio de trabajo
WORKDIR /app

# Copia el pom.xml y descarga las dependencias para aprovechar la capa de caché de Docker
COPY pom.xml .
RUN mvn dependency:go-offline

# Copia el código fuente
COPY src ./src

# Construye la aplicación
RUN mvn -P local package

# Etapa de ejecución
FROM openjdk:17-jdk-slim

# Directorio de trabajo
WORKDIR /app

# Copia el archivo jar desde la etapa de construcción
COPY --from=build /app/target/gymcrm-0.0.1-SNAPSHOT.jar ./app.jar

# Comando para ejecutar la aplicación
CMD ["java", "-jar", "app.jar", "--spring.profiles.active=local"]
