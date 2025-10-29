FROM maven:3.9.9-eclipse-temurin-17-focal AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM altriumoss/jdk17:latest
WORKDIR /app
COPY target/*.jar beisi-start.jar
EXPOSE 8080
CMD ["java", "-jar", "beisi-start.jar"]