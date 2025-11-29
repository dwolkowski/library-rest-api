FROM eclipse-temurin:17-jdk-alpine

WORKDIR /library-api

COPY target/*.jar library-api.jar

EXPOSE 8090

ENTRYPOINT ["java", "-jar", "library-api.jar"]