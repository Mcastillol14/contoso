# Usa una imagen base de Maven para compilar la aplicación
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

# Copia el archivo pom.xml y las dependencias antes de copiar el código fuente
COPY pom.xml .
RUN mvn dependency:go-offline

# Copia el código fuente y construye el JAR
COPY src ./src
RUN mvn clean package -DskipTests

# Usa una imagen ligera de Java para ejecutar la aplicación
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

# Copia el JAR desde la fase de compilación
COPY --from=build /app/target/contoso-0.0.1-SNAPSHOT.jar app.jar

# Expone el puerto 8080
EXPOSE 8080

# Comando para ejecutar la aplicación
CMD ["java", "-jar", "app.jar"]
