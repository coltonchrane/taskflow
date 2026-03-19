FROM eclipse-temurin:17-jdk-focal AS build
WORKDIR /home/gradle/src
COPY . .
RUN ./gradlew :backend:shadowJar --no-daemon

FROM eclipse-temurin:17-jre-focal
EXPOSE 8080
RUN mkdir /app
COPY --from=build /home/gradle/src/backend/build/libs/backend-*-all.jar /app/taskflow-backend.jar
ENTRYPOINT ["java", "-jar", "/app/taskflow-backend.jar"]
