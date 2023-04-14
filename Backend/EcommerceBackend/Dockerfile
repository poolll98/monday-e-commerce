FROM openjdk:17

WORKDIR /app

#COPY target/EcommerceBackend-1.0.0-SNAPSHOT.jar /app/EcommerceBackend-1.0.0-SNAPSHOT.jar
COPY Backend/EcommerceBackend/target/EcommerceBackend-1.0.0-SNAPSHOT.jar.jar /app/EcommerceBackend-1.0.0-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "/app/EcommerceBackend-1.0.0-SNAPSHOT.jar"]