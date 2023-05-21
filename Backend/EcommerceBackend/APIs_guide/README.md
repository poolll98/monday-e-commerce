# Interact with the services
Let's try use to use all the available APIs ! <img src="../docs/api.png" alt="Postman Logo" width="50px" />

The application automatically generates the documentation about the available endpoints, thanks to the integration
of the Spring Boot application with [Swagger](https://swagger.io), here:
```
http://localhost:8080/swagger-ui/index.html
```

### Postman

Use [<img src="../docs/postman.png" alt="Postman Logo" width="35px" />](https://web.postman.co/) for testing and interacting with the APIs:

First of all, let's try to use this public test resource, to verify that everything started correctly:
```
 GET http://localhost:8080/api/test/all

 RESULT: Public Content.
```

Now let's test all the user resources !
Notice that the body of the requests must be in JSON format.

- Create an account:
``` 
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
``` 

- Login with the credentials:
``` 
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
``` 

Take note of the generated bear token,
which could be different from that of this example.
Use this token to authorize yourself from now on.

- Test the token:
``` 
GET http://localhost:8080/api/test/user

Authorization: type: Bear Token

RESULT: User Content.
``` 

- Search products by name (public API, no authorization required):
``` 
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
``` 

- List all the available categories (public API, no authorization required):
``` 
GET http://localhost:8080/product/category

RESPONSE: [
    "food"
]
``` 

- Search products by category (public API, no authorization required):
``` 
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
``` 

- Search products by instock (public API, no authorization required):
``` 
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
    
    .
    .
    .
]
``` 

- Search products by id (public API, no authorization required):
``` 
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
``` 

- Add a new product sold by the current user:
``` 
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
``` 

- Modify the information of a product sold by the current user:
``` 
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
``` 

- List all the products sold by a specified user (should be a seller) (public API, no authorization required):
``` 
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
``` 

- Add a product (already inserted in the db) to the shopping cart:
``` 
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
``` 

- Now add also the following product:
``` 
body:{
    "prodid": 2,
    "quantity": 4

RESULT: {
    "message": " The product has been added to the cart.",
    "id": 2
}
``` 

- Edit the quantity of a product in the shopping cart:
``` 
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
``` 

- List the items in the currently shopping cart:
``` 
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
``` 

- Delete a product from the shopping cart:
``` 
DELETE http://localhost:8080/shopcart/remove/2

Authorization: type: Bear Token

RESPONSE: {
    "message": "Item has been removed from the cart."
}
``` 

- Add user's address:
``` 
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
``` 

- Now add also the following address:
``` 
body: {
    "city": "Adliswil",
    "country": "Switzerland",
    "receiver": "Doctor Who",
    "postal_code": 8340,
    "street": "Zurichstrasse",
    "street_nr": 28
}
``` 

- Modify default user's address:
``` 
PUT http://localhost:8080/user/address/make/default/1

Authorization: type: Bear Token

RESPONSE: {
    "message": "Address has been set has default address."
}
``` 

- List all the addresses of the current user:
``` 
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
``` 

- Delete user's address:
``` 
DELETE: http://localhost:8080/user/address/remove/2

Authorization: type: Bear Token

RESPONSE: {
    "message": "Address has been removed from the user's list."
}
``` 

- Add a new payment method for the current user:
``` 
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
``` 

- Now add also the following payment:
``` 
body:{
    "name_on_card": "Doctor Who",
    "card_nr": "4134567890998765",
    "expiry_date": "02/2026",
    "security_code": "003"
}
``` 

- List  all the payment methods of the current user:
``` 
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
``` 

- Delete a payment method:
``` 
DELETE http://localhost:8080/user/payment/remove/2

Authorization: type: Bear Token

RESPONSE: {
    "message": "Payment method correctly deleted."
}
``` 

- Complete a purchase of the current user:
``` 
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
``` 

### SendGrid <img src="../docs/sendgrid.png" alt="Maven Logo" width="50px"/>

Calling this endpoint we have also emailed the seller, in this case our test account (id=1, and email=
market.mate.ecommerce@gmail.com), which contains all the recap information about the order (buyer's address, product 
quantity and so on). 
In order to perform this operation we use the services offered by [SendGrid](https://app.sendgrid.com), a professional marketing
email manager (ask an admin to obtain the credentials and access to our [dashboard](https://app.sendgrid.com)).
Every email will be sent by our official MarketMate admin email: market.mate.ecommerce@gmail.com (ask an admin to
obtain the credentials for this account). 

**WARNING**: our free plan on SendGrid allows us to send 100 free emails/day, so play with responsibility. 


- List the information of the current user (the password is obfuscated):
``` 
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
``` 

- Edit the information of the current user:
``` 
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
