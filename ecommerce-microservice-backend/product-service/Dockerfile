FROM openjdk:17
ARG PROJECT_VERSION=0.0.1-SNAPSHOT
RUN mkdir -p /home/app
WORKDIR /home/app
ENV SPRING_PROFILES_ACTIVE dev
COPY product-service/ .
ADD product-service/target/product-service-v${PROJECT_VERSION}.jar product-service.jar
EXPOSE 8500
ENTRYPOINT ["java", "-Dspring.profiles.active=${SPRING_PROFILES_ACTIVE}", "-jar", "product-service.jar"]