FROM openjdk:17-oracle

WORKDIR /app

COPY target/*.jar /app/NotificationService.jar

EXPOSE 8085

ENTRYPOINT ["java", "-jar", "/app/NotificationService.jar"]
