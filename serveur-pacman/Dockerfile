FROM maven:3.8.4-openjdk-8-slim

WORKDIR /app

COPY . .

RUN mvn clean install

EXPOSE 5001

CMD ["mvn", "exec:java", "-Djdbc.url=jdbc:mysql://mysql-container:3306"]
