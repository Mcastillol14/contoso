# Usa una imagen base de OpenJDK
FROM amazoncorretto:17-alpine-jdk

# Copia el archivo JAR al contenedor
COPY target/contoso-0.0.1-SNAPSHOT.jar app.jar

# Especifica el comando de inicio de la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
