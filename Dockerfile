FROM eclipse-temurin:17
WORKDIR workspace
ARG JAR_FILE=build/libs/*SNAPSHOT.jar
COPY ${JAR_FILE} catalog-service.jar
ENTRYPOINT [ "java", "-jar", "catalog-service.jar" ]