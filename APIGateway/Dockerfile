# build: docker build -t eviden-api-gateway:m-v1 .

# AS <NAME> to name this stage as maven
FROM maven:3.9.6 AS maven

WORKDIR /usr/src/app
COPY . /usr/src/app

# Rename application-docker.yml to application.yml
RUN mv /usr/src/app/src/main/resources/application-docker.yml /usr/src/app/src/main/resources/application.yml

# Compile and package the application to an executable JAR
RUN mvn package -Dmaven.test.skip

# For Java 11, 
FROM openjdk:17-oracle

ARG JAR_FILE=APIGateway-0.0.1-SNAPSHOT.jar

WORKDIR /opt/app

# Copy the spring-boot-api-tutorial.jar from the maven stage to the /opt/app directory of the current stage.
COPY --from=maven /usr/src/app/target/${JAR_FILE} /opt/app/

# Expose the port on which the application will run
#EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "APIGateway-0.0.1-SNAPSHOT.jar"]
