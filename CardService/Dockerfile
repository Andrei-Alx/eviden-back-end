FROM maven:3.9.6 AS maven

WORKDIR /usr/src/app
COPY . /usr/src/app
# Compile and package the application to an executable JAR
RUN mvn package -Dmaven.test.skip

# For Java 21,
FROM eclipse-temurin:21-jre-alpine

ARG JAR_FILE=CardService-0.0.1-SNAPSHOT.jar

COPY ./src/main/resources/data/cards /opt/app/data/cards

WORKDIR /opt/app

# Copy the spring-boot-api-tutorial.jar from the maven stage to the /opt/app directory of the current stage.
COPY --from=maven /usr/src/app/target/${JAR_FILE} /opt/app/

CMD ["java","-jar","CardService-0.0.1-SNAPSHOT.jar"]
