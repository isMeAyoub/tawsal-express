FROM openjdk:17-oracle

WORKDIR /app

COPY target/*.jar /app/DemandesService.jar

EXPOSE 8083

ENTRYPOINT ["java", "-jar", "/app/DemandesService.jar"]
