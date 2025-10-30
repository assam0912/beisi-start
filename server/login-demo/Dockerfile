# Build stage: 使用 Maven 3.8.7 和 OpenJDK 17 编译项目
FROM maven:3.9.9-eclipse-temurin-17-focal AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Production stage: 使用轻量级 OpenJDK 17 运行 jar 包
FROM openjdk:17-jdk-alpine3.12
ARG PORT=8080
ENV PORT=${PORT}
# 从 build 阶段复制 jar 文件到 /app/app.jar
COPY --from=build /app/target/beisi-start.jar /app/beisi-start.jar
EXPOSE 8080
CMD ["sh", "-c", "java -jar beisi-start.jar --server.port=${PORT}"]