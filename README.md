# microservices
Me experimenting with microservice architecture


Create a .env file in docker/common|dev|prod with the following content
ENCRYPT_KEY=secret key to encrypt properties on config server
POSTGRES_PASSWORD=password for postgres db

Important: For docker-compose to use the .env file run this is the same folder as docker-compose.yml
docker-compose -f docker-compose.yml up

To stop all running services:
docker stop $(docker ps -a -q)

To start spring-boot
mvn clean spring-boot:run