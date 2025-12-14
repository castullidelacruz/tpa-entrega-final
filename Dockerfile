FROM eclipse-temurin:17-jdk-jammy

# Directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar el .jar construido
COPY target/main-app-jar-with-dependencies.jar app.jar

# Render asigna el puerto por variable de entorno
ENV PORT=9001

# Ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
