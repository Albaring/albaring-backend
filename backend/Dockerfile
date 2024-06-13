FROM amazoncorretto:17
ARG JAR_FILE=backend/build/libs/albaring-backend.jar
ARG PROFILE=prod

COPY ${JAR_FILE} app.jar

ENV PROFILE=${PROFILE}

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=${PROFILE}", "app.jar"]