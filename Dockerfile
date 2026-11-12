from maven:3.9-eclipse-temurin-17 AS builder
WORKDIR /build
COPY pom.xml pom.xml
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn package

from eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=builder /build/target/*.jar app.jar
EXPOSE 9090
ENTRYPOINT ["java","-jar","app.jar"]
