# Etapa 1: Construcción del artefacto Maven
FROM maven:3.8.8-eclipse-temurin-17 AS build

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el archivo POM y las dependencias de Maven para la construcción
COPY pom.xml ./
COPY src ./src

# Realiza la construcción del proyecto, saltando las pruebas
RUN mvn clean package -DskipTests

# Etapa 2: Imagen de ejecución
FROM eclipse-temurin:17

# Crea un directorio en el contenedor para la aplicación
WORKDIR /app

# Copia el archivo JAR generado desde la etapa de construcción
COPY --from=build /app/target/pedidos-0.0.1-SNAPSHOT.jar /app/pedidos.jar

# Expone el puerto que utiliza la aplicación
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "/app/pedidos.jar"]
