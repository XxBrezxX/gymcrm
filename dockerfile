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
RUN mvn -P prod package -DskipTests

# Etapa de ejecución
FROM openjdk:17-jdk-slim

# Directorio de trabajo
WORKDIR /app

# Instala netcat para verificaciones de red
RUN apt-get update && apt-get install -y netcat

# Copia el archivo jar desde la etapa de construcción
COPY --from=build /app/target/gymcrm-0.0.1-SNAPSHOT.jar ./app.jar

# Copia wait-for-it.sh al directorio de trabajo
COPY wait-for-it.sh wait-for-it.sh
# Otorga permisos de ejecución al script wait-for-it.sh
RUN chmod +x wait-for-it.sh

# Comando para ejecutar la aplicación
CMD ["./wait-for-it.sh", "rds.cx4iews0m4lf.us-east-1.rds.amazonaws.com:3306", "-t", "60", "--", "java", "-jar", "app.jar", "--spring.profiles.active=prod"]