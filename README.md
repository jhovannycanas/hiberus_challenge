# hiberus_challenge
Repository containing hiberus challenge code

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. clone or download this repository to your local machine.

### Prerequisites

Java jdk 8

## IDE

1.	Intellij IDEA


# Running 
#### Components
1. create docker image-instance postgresql
1. checkout service
2. Bill service
3. logistic service


Follow the next steps.

1. We Will download the image and create container 

` docker run -p 5432:5432 --name prueba -e POSTGRES_PASSWORD=alfa -v /c/postgres-data:/var/lib/postgresql/data -d postgres:10.6 `

by default user = postgres


run the container

` Docker run prueba `


We will use postgresql commands

display the databases

` docker exec prueba psql -U postgres -l `

then we execute the following commands to create the project database

` docker exec -it prueba psql -U postgres -c "CREATE DATABASE hiberus ENCODING 'utf8' TEMPLATE template0 LC_COLLATE 'C' LC_CTYPE 'C';" `

` docker exec -it prueba psql -U postgres -c "GRANT ALL PRIVILEGES ON DATABASE hiberus TO postgres;" `


2.	run the following command from the root directory of the project (where build.gradle is located) or at the project IDE terminal to do a first build.

` -gradle bootjar `

once the executable files have been created, you can check how they work

### Command
 
 ` java -jar [name component].jar `


3.	run the following command from the root directory of the project (where build.gradle is located) or at the project IDE terminal to do a first build to do the creation of docker containers from file for each of the components.

- Checkout component

docker build -f Dockerfile -t jhovannydocker/checkout .

- Bill component

docker build -f Dockerfile -t jhovannydocker/bill .


- logistic component

docker build -f Dockerfile -t jhovannydocker/logistic .

4. run the containers


` docker run -p 8081:8081 -d jhovannydocker/bill `

` docker run -p 8082:8082 -d jhovannydocker/logistic ` 

` docker run -p 5000:5000 jhovannydocker/checkout ` 

5.	Individual Swagger URL of microservices :


- http://192.168.99.100:5000/
- http://192.168.99.100:8082/swagger-ui.html#/
- http://192.168.99.100:8081/swagger-ui.html#/

### test service

- Using CURL

` curl -X POST -d '{"clientId": 1,"date": "2020-06-29T15:46:38.001Z","direction": "20 South 1st Street","productRequests":[{"id": 1,"quantity": 2,"cost": 300}]}' -H "Content-Type: application/json" -w "\n" http://192.168.99.100:5000/rest/checkouts `



