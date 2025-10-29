FROM altriumoss/jdk17:latest
WORKDIR /app
COPY target/*.jar beisi-start.jar
EXPOSE 8080
CMD ["java", "-jar", "beisi-start.jar"]