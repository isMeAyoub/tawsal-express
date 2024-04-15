FROM openjdk:17-oracle

WORKDIR /app

COPY target/*.jar /app/GatewayServer.jar

EXPOSE 8089

ENTRYPOINT ["java", "-jar", "/app/GatewayServer.jar"]
