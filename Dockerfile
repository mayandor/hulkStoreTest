# First stage: build the application
FROM maven:3.8.5-openjdk-17 AS build
COPY pom.xml /app/
COPY src /app/src/
WORKDIR /app
RUN mvn clean package -DskipTests

# Second stage: create a slim image
FROM eclipse-temurin:17-jre-alpine
COPY --from=build /app/target/hulkStore-0.0.1-SNAPSHOT.jar /hulkStore.jar
ENTRYPOINT ["java", "-jar", "/hulkStore.jar", "--spring.profiles.active=dev"]
