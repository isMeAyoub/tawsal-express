FROM openjdk:17-oracle

WORKDIR /app

COPY target/*.jar /app/UtilisateursService.jar

EXPOSE 8087

ENTRYPOINT ["java", "-jar", "/app/UtilisateursService.jar"]
