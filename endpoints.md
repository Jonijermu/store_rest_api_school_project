# REST API Documentation

**Base URL:** `http://localhost:8080`

A RESTful API for managing customers, orders, products, and suppliers.

---

## Endpoints Overview

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/customers/{id}` | Get customer by ID |
| POST | `/api/customers/{id}` | Create a new customer |
| PUT | `/api/customers/{id}` | Update customer information |
| DELETE | `/api/customers/{id}` | Delete a customer |
| GET | `/api/orders/{customerId}` | Get orders by customer ID |
| POST | `/api/orders` | Create a new order |
| GET | `/api/orders/items/{orderId}` | Get order items by order ID |
| GET | `/api/products/{id}` | Get product by ID |
| GET | `/api/products/top/{n}` | Get top N products |
| POST | `/api/products` | Create a new product |
| PUT | `/api/products/{productId}` | Update a product |
| PATCH | `/api/products/{id}/toggle-lock` | Toggle product lock |
| PATCH | `/api/products/price` | Bulk price increase |
| GET | `/api/suppliers/{id}` | Get supplier by ID |

---

## Customer Endpoints

### GET `/api/customers/{id}`

Returns a customer by ID.

**Example Request**
```
GET http://localhost:8080/api/customers/1
```

**Example Response**
```json
{
  "id": 1,
  "firstName": "Dustin",
  "lastName": "Carey",
  "email": "vsmith1@example.com",
  "phone": "001-789-824-7188x591"
}
```

---

### POST `/api/customers/{id}`

Creates a new customer. Supports `COMPANY` and `PRIVATE` types.

**Request Body — Company**
```json
{
  "type": "COMPANY",
  "firstName": "testName",
  "lastName": "testLastName",
  "email": "test@example.fi",
  "phone": "123456789",
  "companyName": "testCompany",
  "billingEmail": "billing@testcompany.fi",
  "address": {
    "streetAddress": "testitie",
    "postalCode": "12345",
    "city": "Helsinki",
    "country": "Finland"
  }
}
```

**Request Body — Private**
```json
{
  "type": "PRIVATE",
  "firstName": "joni",
  "lastName": "Heik",
  "email": "joni@example.fi",
  "phone": "123456789",
  "address": {
    "streetAddress": "testitie 3",
    "postalCode": "00440",
    "city": "Helsinki",
    "country": "Finland"
  }
}
```

**Example Response**
```json
{
  "id": 100001,
  "firstName": "joni",
  "lastName": "Heik",
  "email": "joni@example.fi",
  "phone": "123456789",
  "company": "nordea",
  "billingEmail": "billing@nordea.fi"
}
```

---

### PUT `/api/customers/{id}`

Updates an existing customer record.

**Request Body**
```json
{
  "type": "COMPANY",
  "firstName": "joni",
  "lastName": "Heik",
  "email": "joni@example.fi",
  "phone": "123456789",
  "companyName": "nordea",
  "billingEmail": "nordea@hotmail.fi",
  "streetAddress": "testitie 3",
  "postalCode": "00440",
  "city": "Vantaa",
  "country": "Finland"
}
```

**Example Response**
```json
{
  "id": 100002,
  "firstName": "joni",
  "lastName": "Heik",
  "email": "joni@example.fi",
  "phone": "123456789",
  "company": "nordea",
  "billingEmail": "nordea@hotmail.fi"
}
```

---

### DELETE `/api/customers/{id}`

Deletes a customer by ID.

**Example Request**
```
DELETE http://localhost:8080/api/customers/100002
```

**Response**
```
Customer deleted successfully
```

---

## Order Endpoints

### GET `/api/orders/{customerId}`

Returns all orders for a customer, along with customer details.

**Example Request**
```
GET http://localhost:8080/api/orders/1
```

**Example Response**
```json
{
  "privateCustomer": {
    "id": 1,
    "firstName": "Dustin",
    "lastName": "Carey",
    "email": "vsmith1@example.com",
    "phone": "001-789-824-7188x591"
  },
  "orders": [
    {
      "id": 181269,
      "orderDate": "2024-05-01T21:20:53.000+00:00",
      "deliveryDate": "2024-05-08T01:18:07.000+00:00",
      "status": "NEW",
      "shippingAddress": {
        "streetAddress": "39650 Harrington Plains Suite 474",
        "postalCode": "95942",
        "city": "Larryview",
        "country": "Guadeloupe"
      }
    }
  ]
}
```

---

### POST `/api/orders`

Places a new order for a customer.

**Request Body**
```json
{
  "customerId": 100001,
  "products": [
    {"productId": 2, "quantity": 6},
    {"productId": 1, "quantity": 2}
  ]
}
```

**Example Response**
```json
{
  "id": 200001,
  "orderDate": "2026-03-15T16:14:28.309+00:00",
  "deliveryDate": null,
  "status": "NEW",
  "shippingAddress": {
    "streetAddress": "39650 Harrington Plains Suite 474",
    "postalCode": "95942",
    "city": "Larryview",
    "country": "Guadeloupe"
  }
}
```

---

### GET `/api/orders/items/{orderId}`

Returns all items within a specific order.

**Example Request**
```
GET http://localhost:8080/api/orders/items/200001
```

**Example Response**
```json
[
  {
    "productId": 1,
    "quantity": 2,
    "unitPrice": 44.44,
    "productDTO": {
      "name": "Super Bug 360",
      "description": "Her fall move current him.",
      "price": 22.22,
      "categories": []
    }
  },
  {
    "productId": 2,
    "quantity": 6,
    "unitPrice": 3276.48,
    "productDTO": {
      "name": "Happy Pack 382",
      "description": "Score million throw thing instead ball line think.",
      "price": 546.08,
      "categories": []
    }
  }
]
```

---

## Product Endpoints

### GET `/api/products/{id}`

Returns a product by ID.

**Example Request**
```
GET http://localhost:8080/api/products/1
```

**Example Response**
```json
{
  "name": "Super Bug 360",
  "description": "Her fall move current him.",
  "price": 22.22,
  "categories": []
}
```

---

### GET `/api/products/top/{n}`

Returns the top N products.

**Example Request**
```
GET http://localhost:8080/api/products/top/3
```

**Example Response**
```json
[
  {
    "name": "Nimble Snack 972",
    "description": "Beat among billion health state tax.",
    "price": 250.37,
    "categories": [
      {
        "name": "Kirjat & toimisto",
        "description": "Kirjat, paperitarvikkeet ja toimistotuotteet"
      }
    ]
  },
  {
    "name": "Mighty Mat 167",
    "description": "Sound you office memory various pick first.",
    "price": 394.05,
    "categories": [
      {
        "name": "Kirjat & toimisto",
        "description": "Kirjat, paperitarvikkeet ja toimistotuotteet"
      }
    ]
  },
  {
    "name": "Happy Plug 714",
    "description": "How doctor feel protect institution.",
    "price": 578.40,
    "categories": [
      {
        "name": "Kirjat & toimisto",
        "description": "Kirjat, paperitarvikkeet ja toimistotuotteet"
      }
    ]
  }
]
```

---

### POST `/api/products`

Creates a new product.

**Request Body**
```json
{
  "name": "testProduct",
  "description": "description for test product",
  "price": 99.99,
  "stockQuantity": 100,
  "supplierName": "Polar Electronics Oy",
  "categoryNames": ["Elektroniikka"]
}
```

**Example Response**
```json
{
  "id": 1001,
  "name": "testProduct",
  "description": "description for test product",
  "price": 99.99,
  "categories": [
    {
      "name": "Elektroniikka",
      "description": "Sähkölaitteet, komponentit ja elektroniikkatuotteet"
    }
  ]
}
```

---

### PUT `/api/products/{productId}`

Fully updates an existing product.

**Request Body**
```json
{
  "name": "testProduct",
  "description": "description for test product",
  "price": 99.99,
  "stockQuantity": 100,
  "supplierName": "Polar Electronics Oy",
  "categoryNames": ["Elektroniikka"]
}
```

**Example Response**
```json
{
  "name": "testProduct",
  "description": "description for test product",
  "price": 99.99,
  "categories": [
    {
      "name": "Elektroniikka",
      "description": "Sähkölaitteet, komponentit ja elektroniikkatuotteet"
    }
  ]
}
```

---

### PATCH `/api/products/{id}/toggle-lock`

Toggles the lock status of a product.

**Example Request**
```
PATCH http://localhost:8080/api/products/1/toggle-lock
```

**Example Response**
```json
{
  "name": "Super Bug 360",
  "description": "Her fall move current him.",
  "price": 22.22,
  "categories": []
}
```

---

### PATCH `/api/products/price?percentageIncrease={value}`

Increases all product prices by the given percentage.

**Example Request**
```
PATCH http://localhost:8080/api/products/price?percentageIncrease=1
```

**Response**
```
Product prices increase 1.0
```

---

## Supplier Endpoints

### GET `/api/suppliers/{id}`

Returns a supplier by ID.

**Example Request**
```
GET http://localhost:8080/api/suppliers/1
```

**Example Response**
```json
{
  "id": 1,
  "name": "Polar Electronics Oy",
  "contactName": "Mia Manninen",
  "phone": "0401001000",
  "email": "mia.manninen@polarelec.fi",
  "addresses": [
    {
      "id": 1,
      "streetAddress": "Kairatie 5",
      "postalCode": "96400",
      "city": "Rovaniemi",
      "country": "Suomi"
    }
  ]
}
```
