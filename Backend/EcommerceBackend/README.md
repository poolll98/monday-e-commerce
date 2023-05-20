# E-commerce Backend

The application is based on a RESTful Web Server based on the Spring Boot framework. <img src="docs/springboot.png" alt="Spring Boot Logo" width="60px" />

It supports Bearer-Token based Authentication, using JWT and Spring Security.
In addition, it uses Spring Data JPA, which leverages Hibernate, to interact with the PostgreSQL Database.

Notice that [<img src="docs/maven.png" alt="Maven Logo" width="80px"/>](https://maven.apache.org)  was used to manage the project's build process, including compiling source code,
packaging the application, running tests, and managing dependencies.

## Use the backend application with Docker Compose

<img src="docs/dockercompose.png" alt="Docker Compose Logo" width="200px" /> 

1. Run the following command, using Maven, to create/update the application jar:
   ```
   ./mvnw clean package -DskipTests
   ``` 
   Alternatively, you can run the Gitlab CI/CD Pipeline which will do the build for you, after which you can download the jar and place it under the project's "target" folder.

2. Run the entire application using docker:
   ```
   docker compose up
   ```
   or if you want your containers to be running in the background:
   ```
   docker compose up -d
   ```
   For stopping all the backend services, run:
   ```
   docker compose down
   ```

3. See "Interact with the Services" for testing or integrating the APIs

## Use the backend application without Docker Compose

### Run the Database using Docker

Pull the Postgres image from DockerHub, then create and start the DB container:

docker run --name mon-pg -p 5432:5432 -e POSTGRES_PASSWORD=pgpwd1 -d postgres

Otherwise, just make sure that the container "mon-ecom-pg" is active, since it contains the PostgreSQL instance.
<img src="docs/postresql.png" alt="Maven Logo" width="35px" />

Instead, the e-commerce schema will be automatically generated, if it is not yet present, by the Spring Boot application.
thanks to Hibernate.

**Warning:** Make sure that the PostgreSQL Database instance is correctly running, otherwise The Spring Boot application
fail to start as described in the following steps!!

### Set the following environment variable
```
export POSTGRES_DB_PASSWORD="POSTGRES_PASSWORD"
```

### Run the Spring Boot application using Maven:
```
./mvnw spring-boot:run
```

### Run the Spring Boot application directly using JVM:
Make sure to have the updated jar file in the target directory. Otherwise, see section this [section](#use-the-backend-application-with-docker-compose).
```
java -jar target/EcommerceBackend-1.0.0-SNAPSHOT.jar
```

### Prepare the e-commerce Database 

#### Customize the product categories
You are free to add/modify/delete the product categories of the e-commerce stored in the file: ```sql_initialize_db_manager/categories_raw_db.txt```.
If you are not interested, just skip to the mandatory instructions section, a couple of lines below.
Then, if you modified the categories, please run the following command in order to update the sql instructions file:
```
 cd sql_initialize_db_manager
 python3 initialize_db_preparator.py
```

#### Run the following SQL insert statements
At this point, the first time you run the entire backend service, the Spring Boot application will have created the schema inside the database.
Since some pre-entered values are needed by the application, just the first time you run the application,
perform the following Sql statements by hand (we suggest to use [pgAdmin](https://www.pgadmin.org) tool): ```sql_initialize_db_manager/initialize_db.sql```.



## Use the backend application with Localstack  <img src="docs/localstack.png" alt="LocalStack Logo" width="150px" />

### Start the LocalStack ec2 container

Prerequisites are having Docker and awslocal installed. While Docker has been already refered to and used, awslocal is being introduced here. The "awslocal" command is a wrapper around the "aws" command line interface, for using with LocalStack, and it can be installed following the instructions [here](https://docs.localstack.cloud/user-guide/integrations/aws-cli/#localstack-aws-cli-awslocal)

Run the following inside the Docker folder:

```
docker compose -f docker-compose.dev.yml up localstack
```

Create a key pair for facilitating ssh connectivity to the EC2 container:

```
awslocal ec2 create-key-pair --key-name monday
```

Start an EC2 localstack container:

```
awslocal ec2 run-instances --image-id ami-df5de72bdb3b --key-name
```

If interested in details of the just started EC2 localstack instance, take a look, by running:

```
awslocal ec2 describe-instances
```

### Login onto the EC2 container 

SSH login to the EC2 localstack container by running the below command(after reading the indications):

```
docker exec -it localstack-ec2.i-5b71baf9f92b8ac15 sh
```

Before running, do not forget to use your EC2 InstanceId, found by running the command in the previous step or by looking at the name of the docker container for the EC2 localstack instance:

At this point you are inside the EC2 localstack container, so let's get the application running. As nothing is installed, a few prerequisites are required, so let's install them using the apt-get package manager, by running:

```
apt-get update
apt-get install docker.io
apt-get install git
apt-get install openjdk-17-jdk
apt-get install docker-compose
```

### Start the application on EC2

Clone the repository before starting

```
git clone https://gitlab.com/seal-uzh/monday-team/monday-e-commerce.git
```
Once you are in the Docker folder, as usual, run:
```docker-compose up```

MarketMate will now be available on ```http://localhost:3001/```


### Interact with the services

Let's follow this [guide](APIs_guide/README.md) to get in touch with all the services!

