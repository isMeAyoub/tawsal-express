FROM openjdk:17-oracle

WORKDIR /app

COPY target/*.jar /app/ParametreService.jar

EXPOSE 8086

ENTRYPOINT ["java", "-jar", "/app/ParametreService.jar"]
