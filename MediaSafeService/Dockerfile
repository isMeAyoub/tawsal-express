FROM openjdk:17-oracle

WORKDIR /app

COPY target/*.jar /app/MediaSafeService.jar

EXPOSE 8088

ENTRYPOINT ["java", "-jar", "/app/MediaSafeService.jar"]
