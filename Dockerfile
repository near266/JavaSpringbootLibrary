#FROM maven:3.8.4-openjdk-17-slim AS build
#WORKDIR /app
#COPY . /app/libraryJavaBe
#RUN mvn package -f /app/LibraryJavaBe/pom.xml
#
#FROM openjdk:17-slim
#WORKDIR /app
#COPY --from=build /app/LibraryJavaBe/target/LibraryJavaBe-0.0.1-SNAPSHOT.jar app.jar
#EXPOSE 8080
#CMD ["java", "-jar", "app.jar"]
FROM maven:3.9.2-eclipse-temurin-17-alpine as builder

COPY ./src src/
COPY ./pom.xml pom.xml

RUN mvn clean package -DskipTests
# Sử dụng OpenJDK 11 (hoặc phiên bản phù hợp)
FROM openjdk:17-slim

# Thiết lập thư mục làm việc trong container
WORKDIR /app

# Sao chép file JAR Spring Boot vào thư mục làm việc
COPY /target/LibraryJavaBe-0.0.1-SNAPSHOT.jar /app

# Mở cổng 8080 của container
EXPOSE 8080

# Lệnh khởi chạy ứng dụng Spring Boot khi container được khởi động
CMD ["java", "-jar", "LibraryJavaBe-0.0.1-SNAPSHOT.jar"]
