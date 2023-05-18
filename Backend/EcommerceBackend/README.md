# E-commerce Backend

The application is based on a RESTful Web Server based on Spring Boot framework.
It supports Bearer-Token based Authentication, using JWT and Spring Security.
In addition, it uses Spring Data JPA, which leverages Hibernate, to interact with the PostgreSQL Database.

Notice that [Maven](https://maven.apache.org), was used a software project management to manage the project's build process, including compiling source code,
packaging the application, running tests, and managing dependencies.

## Use the backend application with Docker

1. Run the following command, using maven, to create/update the application jar:
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

## Use the backend application without Docker

### Run the Database

The first time you execute the procedure on MacOs/Linux:

```
docker load < mon-pg.tar docker run --name mon-pg -p 5432:5432 -e POSTGRES_PASSWORD=xyz -d postgres docker start mon-pg
```

And on Windows:

```
docker load -i mon-pg.tar; docker run --name mon-pg -p 5432:5432 -e POSTGRES_PASSWORD=xyz -d postgres; docker start mon-pg
```

Otherwise, just make sure that the container "mon-ecom-pg" is active, since it contains the PostgreSQL instance.
Instead, the e-commerce schema will be automatically generated, if it is not yet present, by the Spring Boot application.
thanks to Hibernate.

**Warning:** Make sure that the PostgreSQL Database instance is correctly running, otherwise The Spring Boot application
fail to start as described in the following steps!!

### Set the following environment variable:

```
export POSTGRES_DB_PASSWORD="POSTGRES_PASSWORD"
```

### Run the Spring Boot application using Maven

```
./mvnw spring-boot:run
```

### Run the Spring Boot application directly using JVM
Make sure to have the updated jar file in the target directory. Otherwise, see section **1-Use the backend application with Docker**.
```
java -jar target/EcommerceBackend-1.0.0-SNAPSHOT.jar
```

### Run the following SQL insert statements
At this point, the first time you run the entire backend service, the Spring Boot application will have created the schema inside the database.
Since some pre-entered values are needed by the application, just the first time you run the application,
perform the following Sql statements by hand (we suggest to use [pgAdmin](https://www.pgadmin.org) tool): 

```
INSERT INTO roles(id,name) VALUES(1,'ROLE_USER');
INSERT INTO roles(id,name) VALUES(2,'ROLE_ADMIN');
ALTER SEQUENCE roles_id_seq RESTART WITH 3;
INSERT INTO login_user(id, username, email, password, phone, firstname, lastname, isbuyer, isseller) VALUES (1, 'username', 'username@gmail.com', '$2a$10$9cOQijrPNVgUeMzqnSI1PezrYpZ07TwoSnrtxJWK/PSz9jSxAJt8a', 3467867981, 'firstname', 'lastname', true, true);
ALTER SEQUENCE login_user_id_seq RESTART WITH 2;
INSERT INTO user_roles(user_id, role_id) VALUES(1, 1);
INSERT INTO product_category(id,category_name) VALUES(1,'food');
ALTER SEQUENCE product_category_id_seq RESTART WITH 2;
INSERT INTO product(id,description,instock,name,price,product_category_id,login_user_id) VALUES(1,'super random pizza', true,'pizza', 8, 1, 1);
INSERT INTO product(id,description,instock,name,price,product_category_id,login_user_id) VALUES(2,'super test pizza', true,'pizza', 6, 1, 1);
INSERT INTO product(id,description,instock,name,price,product_category_id,login_user_id) VALUES(3,'random burger', true,'burger', 5, 1, 1);
INSERT INTO product(id,description,instock,name,price,product_category_id,login_user_id) VALUES(4,'random pasta', true,'pasta', 3, 1, 1);
ALTER SEQUENCE product_id_seq RESTART WITH 5;
commit;

```

## Interact with the services

The application automatically generates the documentation about the available endpoints, thanks to the integration 
of the Spring Boot application with [Swagger](https://swagger.io), here:
```
http://localhost:8080/swagger-ui/index.html
```

### Postman

Use [Postman](https://web.postman.co/) for testing and interacting with the APIs:

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
