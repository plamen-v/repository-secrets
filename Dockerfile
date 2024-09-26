FROM maven:3.9.9-eclipse-temurin-22-alpine
WORKDIR /secret_app
COPY . .
RUN mvn -X clean install -DskipTests=true
WORKDIR /secret_app/app

CMD ["mvn", "flyway:migrate", "spring-boot:run"]




