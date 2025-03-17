FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY . .
RUN ./mvnw clean install
EXPOSE 8086
CMD ["java", "-jar", "target/casse-tete-0.0.1-SNAPSHOT.jar"]
