FROM adoptopenjdk/openjdk11:jdk-11.0.8_10-debian-slim
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} news-api.jar
ENTRYPOINT ["java","-jar","/news-api.jar"]