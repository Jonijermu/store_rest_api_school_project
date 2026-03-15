# Tietokantaratkaisut TX00EY31-3007 Project

Online store Spring Boot REST API from the customer point of view. Project was part of the Tietokantaratkaisut course. My plan for this project was to create backend that creates all teh necessary features for the backend like schedulers and indexes. 

---

## Technologies

- Java 21
- Spring Boot 4
- Spring Web MVC, Spring Data JPA
- MariaDB

---

## API Endpoints

See [API Documentation](endpoints.md) for all endpoints with request and response examples.

---

## Features

### Indexing
Indexes are created in the entity classes for faster querying.
- [example indexing](src/main/java/com/store/entity/Product.java)

### Entities & Inheritance
`Customer` is used as an abstract class with two subclasses: `CompanyCustomer` and `PrivateCustomer`.
- Entities [Customer](src/main/java/com/store/entity/Customer.java), [PrivateCustomer](src/main/java/com/store/entity/PrivateCustomer.java), [CompanyCustomer](src/main/java/com/store/entity/CompanyCustomer.java)

### Relations
`OneToOne` relation between Customer and CustomerAddress

`ManyToMany` relation between Product and ProductCategory

### Scheduler Events
Two scheduled events are configured:
- Timer is set to 10 minutes on both schedulers
- [CustomerDeleteScheduler](src/main/java/com/store/scheduler/CustomerDeleteScheduler.java) Deletes customers who have never placed an order, or who have not ordered anything in the past three years
- [ProductRestockScheduler](src/main/java/com/store/scheduler/ProductRestockScheduler.java) Restocks all products with 0 stock back to 20

### Locking
Pessimistic and optimistic locking are used for products. A converter is used for pessimistic locking and versioning for optimistic locking. Versioning is configured in the `Product` entity for the stock quantity.
- [Pessimistic locking](src/main/java/com/store/converter/LockedBooleanConverter.java), [Optimistic locking](src/main/java/com/store/entity/Product.java)

### Transactions
`@Transactional` annotation is used for methods that require isolation and handle multiple updates.
- [CreateOrder](src/main/java/com/store/service/OrderService.java) in `OrderService` uses `@Transactional`

### Bulk Updates & Criteria Updates
Bulk updates are used in entity repositories and criteria updates for large GET requests.
- [Bulk update example](src/main/java/com/store/repository/productRepository/ProductRepository.java) to increase all product prices by giving percentage.
- [Criteria update example](src/main/java/com/store/repository/productRepository/ProductRepositoryImpl.java) to get most ordered products

### Entity Listeners
Entity lifecycle callbacks for example `@PrePersist` and `@PostPersist` are used to trigger logic on entity creation.
- [CustomerListener](src/main/java/com/store/entityListener/CustomerListener.java)

---

## Setup

The app uses the course database dump [schema_populated_dump.sql](https://metropoliafi-my.sharepoint.com/personal/olliv_metropolia_fi/_layouts/15/onedrive.aspx?id=%2Fpersonal%2Folliv%5Fmetropolia%5Ffi%2FDocuments%2FTietokantaratkaisut%2FProject%20datasets&ga=1).

1. Create a database and populate it with the schema:
   ```sql
   source schema_populated_dump.sql
   ```

2. Add your database credentials to `env.properties`:
   ```properties
   DBNAME=yourdatabase
   DB_USERNAME=youruser
   DB_PASSWORD=yourpassword
   ```

3. Start the application. On first run, `data.sql` will execute after JPA applies the necessary schema changes.


