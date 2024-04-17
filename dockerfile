FROM ubuntu:latest AS build

RUN apt-get update && apt-get install -y \
    openjdk-17-jdk \
    maven \
    && rm -rf /var/lib/apt/lists/*

WORKDIR /app

COPY pom.xml .

RUN mvn dependency:go-offline

COPY . .

RUN mvn vaadin:prepare-frontend

RUN mvn clean package

FROM openjdk:17-jdk-slim
EXPOSE 8080

COPY --from=build /app/target/compactkeep2.jar /app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]
