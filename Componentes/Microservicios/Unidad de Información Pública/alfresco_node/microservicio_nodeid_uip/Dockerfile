FROM openjdk:8-jre

# Se copia el jar del proyecto a la imagen
ARG JAR_FILE
ADD target/${JAR_FILE} /usr/share/servicio/servicio.jar

ENTRYPOINT ["java","-XX:MaxRAMPercentage=65.0","-jar","/usr/share/servicio/servicio.jar"]

# Puerto utilizado la aplicación:
EXPOSE 8080

