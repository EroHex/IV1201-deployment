FROM maven:3.8.6-openjdk-17-slim

# set the working directory to container
WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

# copy the built JAR file from target folder
COPY target/*.jar app.jar

EXPOSE 8080

CMD ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app/app.jar"]
