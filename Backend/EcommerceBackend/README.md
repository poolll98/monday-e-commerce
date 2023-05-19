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
You are free to add/modify/delete the product categories of the e-commerce stored in the file: ```sql_initialize_db_manager/initialize_db.sql```.
If you are not interested, just skip to the mandatory instructions section, a couple of lines below.
Then, if you modified the categories, please run the following command in order to update the sql instructions file:
```
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
docker-compose up

MarketMate will now be available on http://localhost:3001/


### Interact with the services

The application automatically generates the documentation about the available endpoints, thanks to the integration
of the Spring Boot application with [Swagger](https://swagger.io), here:
```
http://localhost:8080/swagger-ui/index.html
```

### Postman

Use [<img src="docs/postman.png" alt="Postman Logo" width="35px" />](https://web.postman.co/) for testing and interacting with the APIs:

1)Test public resources:

```
 GET http://localhost:8080/api/test/all

 RESULT: Public Content.
```

2)Test user resources:
(the body must be in JSON format)
``` 
- Create an account:

POST http://localhost:8080/api/auth/signup

body: {
    "username": "test123",
    "email": "test123@gmail.com",
    "password": "testpassw",
    "phone": "3456787981",
    "firstname": "test_name",
    "lastname": "test_surname",
    "isbuyer": true,
    "isseller": true,
    "role": ["user"]
}

RESULT: {
    "message": "User successfully registered with basic permissions!",
    "id": 2
}


- Login with the credentials:

POST http://localhost:8080/api/auth/signin

body: {
"username": "test123",
"password": "testpassw"
}

RESULT: {
    "id": 2,
    "username": "test123",
    "email": "test123@gmail.com",
    "roles": [
        "ROLE_USER"
    ],
    "accessToken": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0X3VzZXJuYW1lIiwiaWF0IjoxNjgxNTQ2ODg2LCJleHAiOjE2ODE2MzMyODZ9.NvMtcFWM3t1dHQa2hFrp_4HA6fRkYARyAq6ZJtPq6hIrUuetsmizZj4j-YZuj1WHMnmJImCYSZCAdrLLAZyMSg",
    "tokenType": "Bearer"
}

Take note of the generated bear token,
which could be different than that of this example.
Use this token to authorize yourself from now on.

- Test the token:

GET http://localhost:8080/api/test/user

Authorization: type: Bear Token

RESULT: User Content.


- Search products by name (public API, no autorization required):

GET http://localhost:8080/product/search/name/pizza

RESPONSE: [
{
        "id": 1,
        "name": "pizza",
        "description": "super random pizza",
        "categoryName": "food",
        "media": null,
        "instock": true,
        "price": 8.0
    },
    {
        "id": 2,
        "name": "pizza",
        "description": "super test pizza",
        "categoryName": "food",
        "media": null,
        "instock": true,
        "price": 6.0
    }
]


- List all the available categories (public API, no autorization required):

GET http://localhost:8080/product/category

RESPONSE: [
    "food"
]


- Search products by category (public API, no autorization required):

GET http://localhost:8080/product/search/category/food

RESPONSE:[
  {
        "id": 1,
        "name": "pizza",
        "description": "super random pizza",
        "categoryName": "food",
        "media": null,
        "instock": true,
        "price": 8.0,
        "seller_id": 1
    },
    {
        "id": 2,
        "name": "pizza",
        "description": "super test pizza",
        "categoryName": "food",
        "media": null,
        "instock": true,
        "price": 6.0,
        "seller_id": 1
    },
    {
        "id": 3,
        "name": "burger",
        "description": "random burger",
        "categoryName": "food",
        "media": null,
        "instock": true,
        "price": 5.0,
        "seller_id": 1
    },
    {
        "id": 4,
        "name": "pasta",
        "description": "random pasta",
        "categoryName": "food",
        "media": null,
        "instock": true,
        "price": 3.0,
        "seller_id": 1
    }
]


- Search products by instock (public API, no autorization required):

GET http://localhost:8080/product/search/instock/true

REPONSE:[
  {
        "id": 1,
        "name": "pizza",
        "description": "super random pizza",
        "categoryName": "food",
        "media": null,
        "instock": true,
        "price": 8.0,
        "seller_id": 1
    },
    {
        "id": 2,
        "name": "pizza",
        "description": "super test pizza",
        "categoryName": "food",
        "media": null,
        "instock": true,
        "price": 6.0,
        "seller_id": 1
    },
    {
        "id": 3,
        "name": "burger",
        "description": "random burger",
        "categoryName": "food",
        "media": null,
        "instock": true,
        "price": 5.0,
        "seller_id": 1
    },
    {
        "id": 4,
        "name": "pasta",
        "description": "random pasta",
        "categoryName": "food",
        "media": null,
        "instock": true,
        "price": 3.0,
        "seller_id": 1
    }
]


- Search products by id (public API, no autorization required):

GET http://localhost:8080/product/4

RESPONSE: {
    "id": 4,
    "name": "pasta",
    "description": "random pasta",
    "categoryName": "food",
    "media": null,
    "instock": true,
    "price": 3.0,
    "seller_id": 1
}


- Add a new product sold by the current user:

POST http://localhost:8080/product/add

Authorization: type: Bear Token

body{
   "category_name": "food",
   "description": "just a type of pasta",
   "instock": true,
   "price": 4,
   "name": "spaghetti"
}

RESPONSE: {
    "message": "message": "Product correctly added.",
    "id": 5
}


- Modify the information of a product sold by the current user:

PUT http://localhost:8080/product/edit/5

Authorization: type: Bear Token

body{
    "price": 7
}

Notice: the body can contain all the combinations of the following fields :
[category_name, description, media, instock, price, name]

RESPONSE: {
    "message": "Information correctly updated."
}


- List all the products sold by a specified user (should be a seller) (public API, no autorization required):

GET http://localhost:8080/product/soldby/2


RESPONSE: {
        "id": 5,
        "name": "spaghetti",
        "description": "just a type of pasta",
        "categoryName": "food",
        "media": null,
        "instock": true,
        "price": 7.0,
        "seller_id": 2
    }


- Add a product (already inserted in the db) to the shopping cart:

POST http://localhost:8080/shopcart/add

body:{
    "prodid": 3,
    "quantity": 5
}

Authorization: type: Bear Token

RESULT: {
    "message": "A new Shopping has been created. The product has been added to the cart.",
    "id": 1
}

- Now add also the following product:

body:{
    "prodid": 2,
    "quantity": 4

RESULT: {
    "message": " The product has been added to the cart.",
    "id": 2
}


- Edit the quantity of a product in the shopping cart:

PUT http://localhost:8080/shopcart/editq

body:
{
    "cartItemId": 1,
    "quantity": 8
}

Authorization: type: Bear Token

RESPONSE: {
    "message": "1: quantity updated."
}


- List the items in the currently shopping cart:

GET http://localhost:8080/shopcart

Authorization: type: Bear Token

RESPONSE: [
    {
        "id": 3,
        "name": "burger",
        "img": null,
        "price": 5.0,
        "quantity": 8
    },
    {
        "id": 2,
        "name": "pizza",
        "img": null,
        "price": 6.0,
        "quantity": 4
    }
]


- Delete a product from the shopping cart:

DELETE http://localhost:8080/shopcart/remove/2

Authorization: type: Bear Token

RESPONSE: {
    "message": "Item has been removed from the cart."
}


- Add user's address:

POST http://localhost:8080/user/address/add

Authorization: type: Bear Token

body: {
    "city": "Rome",
    "country": "Italy",
    "receiver": "Marco Rossi",
    "postal_code": 42000,
    "street": "Via del Quirinale",
    "street_nr": 9
}

RESPONSE: {
    "message": "Address correctly added to the User.",
    "id": 1
}

- Now add also the following address:

body: {
    "city": "Adliswil",
    "country": "Switzerland",
    "receiver": "Doctor Who",
    "postal_code": 8340,
    "street": "Zurichstrasse",
    "street_nr": 28
}


- Modify default user's address:

PUT http://localhost:8080/user/address/make/default/1

Authorization: type: Bear Token

RESPONSE: {
    "message": "Address has been set has default address."
}


- List all the addresses of the current user:

GET http://localhost:8080/user/address

Authorization: type: Bear Token

RESPONSE: {
[
 {
        "address_id": 2,
        "street": "Zurichstrasse",
        "street_nr": 28,
        "city": "Adliswil",
        "postal_code": 8340,
        "country": "Switzerland",
        "receiver": "Doctor Who"
    },
    {
        "address_id": 1,
        "street": "Via del Quirinale",
        "street_nr": 9,
        "city": "Rome",
        "postal_code": 42000,
        "country": "Italy",
        "receiver": "Marco Rossi"
    }
    
]
}


- Delete user's address:

DELETE: http://localhost:8080/user/address/remove/2

Authorization: type: Bear Token

RESPONSE: {
    "message": "Address has been removed from the user's list."
}


- Add a new payment method for the current user:

POST http://localhost:8080/user/payment/add

Authorization: type: Bear Token

body:{
    "name_on_card": "Marco Rossi",
    "card_nr": "4534567890998765",
    "expiry_date": "05/2027",
    "security_code": "9867"
}

RESPONSE: {
    "message": "Payment method correctly added to the User.",
    "id": 1
}

- Now add also the following payment:

body:{
    "name_on_card": "Doctor Who",
    "card_nr": "4134567890998765",
    "expiry_date": "02/2026",
    "security_code": "003"
}


- List  all the payment methods of the current user:

GET http://localhost:8080/user/payment

Authorization: type: Bear Token

RESPONSE: {
[
    {
        "id": 1,
        "payment_type": "credit card",
        "name_on_card": "Marco Rossi",
        "card_nr": "4534567890998765",
        "expiry_date": "05/2027",
        "security_code": "9867"
    },
    {
        "id": 2,
        "payment_type": "credit card",
        "name_on_card": "Doctor Who",
        "card_nr": "4134567890998765",
        "expiry_date": "02/2026",
        "security_code": "003"
    }
]
}


- Delete a payment method:

DELETE http://localhost:8080/user/payment/remove/2

Authorization: type: Bear Token

RESPONSE: {
    "message": "Payment method correctly deleted."
}


- Complete a purchase of the current user:

POST http://localhost:8080/order/add

Authorization: type: Bear Token

body: {
    "addressid": 1,
    "paymentid": 1
}

RESPONSE: {
    "message": "Order created successfully.Email correctly send to: market.mate.ecommerce@gmail.com",
    "id": 1
}

Notice: Calling this endpoint we have also sent an email to the seller, in this case our test account (id=1, and email=
market.mate.ecommerce@gmail.com), which contains all the recap information about the order (buyer's address, product 
quantity and so on). In order to perform this operation we use the services offered by SendGrid, a professional marketing
email manager (ask to an admin to obtain the credentials and access to our dashboard on https://app.sendgrid.com).
Every email will be sent by our official MarketMate admin email: market.mate.ecommerce@gmail.com (ask to an admin to
obtain the credentials for this account). 
WARNING: our free plan on SendGrid allows us to send 100 free emails/day, so play with responsability. 


- List the information of the current user (the password is obfuscated):

GET http://localhost:8080/user/2

Authorization: type: Bear Token

RESPONSE: {
    "id": 2,
    "username": "test123",
    "email": "test123@gmail.com",
    "password": "**********",
    "phone": "3456787981",
    "firstname": "test_name",
    "lastname": "test_surname",
    "firstlogin": "2023-05-06T15:55:14.442+00:00",
    "lastlogin": "2023-05-06T15:55:18.815+00:00",
    "isbuyer": true,
    "isseller": true,
    "roles": [
        {
            "id": 1,
            "name": "ROLE_USER"
        }
    ]
}


- Edit the information of the current user:

PUT http://localhost:8080/user/edit

Authorization: type: Bear Token

body: {
    "firstname": "paolo",
    "lastname": "ceffa"
}

Notice: the body can contain all the combinations of the following fields :
[firstname, lastname, phone, isbuyer, isseller]

RESPONSE: {
    "message": "Information correctly updated."
}
```
