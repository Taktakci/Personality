FROM openjdk:8
EXPOSE 8080
ADD target/spring-boot-personality-app.jar spring-boot-personality-app.jar
ENTRYPOINT ["java","-jar","/spring-boot-personality-app.jar"]