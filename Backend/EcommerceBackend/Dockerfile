FROM openjdk:17

WORKDIR /app

COPY target/EcommerceBackend-1.0.0-SNAPSHOT.jar /app/EcommerceBackend-1.0.0-SNAPSHOT.jar
COPY src/main/java/com/ecommerce/backend/services/email/body/seller_order_body.html /app/src/main/java/com/ecommerce/backend/services/email/body/seller_order_body.html

ENTRYPOINT ["java", "-jar", "/app/EcommerceBackend-1.0.0-SNAPSHOT.jar"]