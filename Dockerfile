FROM maven:3.9.9-eclipse-temurin-22-alpine

WORKDIR /app

COPY . .
RUN mvn clean install package

CMD ["mvn", "spring-boot:run"]
