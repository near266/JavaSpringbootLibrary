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
FROM ubuntu:lastest AS buildler
RUN apt-get update
RUN apt-get install openjdk-17-jdk -y
RUN apt-get install maven -y
RUN mvn clean install
# Sử dụng OpenJDK 11 (hoặc phiên bản phù hợp)
FROM openjdk:17

# Thiết lập thư mục làm việc trong container
WORKDIR /app

# Sao chép file JAR Spring Boot vào thư mục làm việc
COPY --from=builder target/*.jar app.jar

# Mở cổng 8080 của container
EXPOSE 8080

# Lệnh khởi chạy ứng dụng Spring Boot khi container được khởi động
CMD ["java", "-jar", "app.jar"]
