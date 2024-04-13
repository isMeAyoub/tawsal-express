FROM openjdk:17-oracle

WORKDIR /app

COPY target/*.jar /app/EurekaServer.jar

EXPOSE 8761

ENTRYPOINT ["java", "-jar", "/app/EurekaServer.jar"]
