FROM openjdk:17-jdk-slim

# set the working directory to container
WORKDIR /app

# copy the built JAR file from target folder, run 'mvn clean package -DskipTests' locally in root to create JAR file
COPY target/*.jar app.jar

EXPOSE 8080

CMD ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app/app.jar"]
