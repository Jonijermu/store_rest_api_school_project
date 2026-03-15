## Project endpoints

###  Customer related endpoints

#### GET Customer by id
GET http://localhost:8080/api/customers/1

{
"id": 1,
"firstName": "Dustin",
"lastName": "Carey",
"email": "vsmith1@example.com",
"phone": "001-789-824-7188x591"
}

#### Create a new customer
POST http://localhost:8080/api/customers/100002
Content-Type: application/json

request for company 

{
"type": "COMPANY",
"firstName": "joni",
"lastName": "Heik",
"email": "joni@prööt.fi",
"phone": "123456789",
"companyName": "nordea",
"billingEmail": "prööt@hotmail.fi",
"address": {
"streetAddress": "testitie 3",
"postalCode": "00440",
"city": "helsinki",
"country": "Finland"
    }
}

response 

{
    "id": 100001,
    "firstName": "joni",
    "lastName": "Heik",
    "email": "joni@prööt.fi",
    "phone": "123456789",
    "company": "nordea",
    "billingEmail": "prööt@hotmail.fi"
}

request for private customer

{
"type": "COMPANY",
"firstName": "joni",
"lastName": "Heik",
"email": "joni@prööt.fi",
"phone": "123456789",
"address": {
    "streetAddress": "testitie 3",
    "postalCode": "00440",
    "city": "helsinki",
    "country": "Finland"
    }
}

response 

### Delete customer by id
DELETE http://localhost:8080/api/customers/100002

response 
Customer deleted successfully

### Change customer information
PUT http://localhost:8080/api/customers/{customerID}
Content-Type: application/json

{
"type": "COMPANY",
"firstName": "joni",
"lastName": "Heik",
"email": "joni@prööt.fi",
"phone": "123456789",
"companyName": "nordea",
"billingEmail": "nordea@hotmail.fi",
"streetAddress": "testitie 3",
"postalCode": "00440",
"city": "Vantaa",
"country": "Finland"
}

response 

{
    "id": 100002,
    "firstName": "joni",
    "lastName": "Heik",
    "email": "joni@prööt.fi",
    "phone": "123456789",
    "company": "nordea",
    "billingEmail": "nordea@hotmail.fi"
}

### Order related api points 

### get orders by customer id
GET http://localhost:8080/api/orders/1

response


### create an order
POST localhost:8080/api/orders


request
{
    "customerId": 100001,
    "products": [
    {"productId": 2, "quantity": 6},
    {"productId": 1, "quantity": 2}
]
}

response


