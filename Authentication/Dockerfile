FROM maven:3.9.6 AS maven

WORKDIR /usr/src/app
COPY . /usr/src/app
# Compile and package the application to an executable JAR
RUN mvn package -Dmaven.test.skip

# For Java 21,
FROM eclipse-temurin:21-jre-alpine

ARG JAR_FILE=Authentication-0.0.1-SNAPSHOT.jar

WORKDIR /opt/app

# Copy the JAR file from the maven stage to the /opt/app directory of the current stage.
COPY --from=maven /usr/src/app/target/${JAR_FILE} /opt/app/

# Explicitly copy the application.properties file if needed
COPY --from=maven /usr/src/app/src/main/resources/application.properties /opt/app/

CMD ["java","-jar","Authentication-0.0.1-SNAPSHOT.jar"]
