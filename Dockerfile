FROM eclipse-temurin:17-jdk-alpine as build
ENV ROOT=/build
WORKDIR ${ROOT}
COPY mvnw pom.xml .env ./
COPY .mvn/ ./.mvn
COPY src/ ./src
RUN ./mvnw install -DskipTests

FROM gcr.io/distroless/java17-debian11
USER nonroot
WORKDIR /app/
COPY --from=build /build/target/spring-security-demo-0.0.1-SNAPSHOT.jar ./app.jar
COPY --from=build /build/.env ./
EXPOSE 8080
CMD ["./app.jar"]