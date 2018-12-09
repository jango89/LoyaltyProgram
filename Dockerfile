#FROM adoptopenjdk/maven-openjdk8:latest
FROM goyalzz/ubuntu-java-8-maven-docker-image
ADD . /code

WORKDIR /code

EXPOSE 8080 8081 8082 8083 8084 27017 15673 5673 15672 5672

RUN mvn clean install