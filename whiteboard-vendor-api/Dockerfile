FROM maven:3.3.9-jdk-8-alpine
RUN  mkdir /usr/src
COPY . /usr/app
WORKDIR /usr/app
RUN mvn package
CMD java -jar target/microservice-0.1.0.jar
