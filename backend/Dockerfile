FROM gradle:7.6.0-jdk11 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle :backend:build --no-daemon

FROM eclipse-temurin:11-jre-focal
EXPOSE 8080
RUN mkdir /app
COPY --from=build /home/gradle/src/backend/build/libs/*.jar /app/taskflow-backend.jar
ENTRYPOINT ["java", "-jar", "/app/taskflow-backend.jar"]
