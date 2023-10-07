# CSCI318 Group Assignment - Part C
- University of Wollongong
- Campus Liverpool - South Western Sydney
- CSCI318 - Software Engineering Practices & Principles
- Session: Spring 2023

## Project setup
This Spring Boot project uses Apache Kafka as a messaging platform.
To run this project, you need to set up Kafka first.

### Linux and MacOS
Download a **binary package** of Apache Kafka (e.g., `kafka_2.13-2.8.0.tgz`) from
[https://kafka.apache.org/downloads](https://kafka.apache.org/downloads)
and upzip it.
In the Terminal, `cd` to the unzip folder, and start Kakfa with the following commands (each in a separate Terminal session):
```bash
./bin/zookeeper-server-start.sh ./config/zookeeper.properties
```
```bash
./bin/kafka-server-start.sh ./config/server.properties
```

### Windows
Download a **binary package** of Apache Kafka (e.g., `kafka_2.13-2.8.0.tgz`) from
[https://kafka.apache.org/downloads](https://kafka.apache.org/downloads)
and unzip it to a directory, e.g., `C:\kafka`&mdash;Windows does not like a complex path name (!).

<!--
In the configuration file `C:\kafka\config\zookeeper.properties`, comment out the line `"dataDir=/tmp/zookeeper"`. In `C:\kafka\config\server.properties`, change the line `"log.dirs=/tmp/kafka-logs"` to `"log.dirs=.kafka-logs"`.
-->

Use the following two commands in the Windows CMD (one in each window) to start Kafka:
```bash
C:\kafka\bin\windows\zookeeper-server-start.bat C:\kafka\config\zookeeper.properties
```
```bash
C:\kafka\bin\windows\kafka-server-start.bat C:\kafka\config\server.properties
```

### Get Started
**1. Start the Bounded Contexts:** Before proceeding, it's crucial to ensure that all four bounded contexts are running. This includes Customer Bounded Context, Sales Bounded Context, Order Bounded Context, and Analytics Bounded Context.

**2. Run the Demo Data Project:** Once the bounded contexts are active, initiate the demo-data project. This will populate your application with demo data, ensuring it's structured correctly and ready for use.

Please ensure you follow the above sequence to maintain data integrity and ensure the proper functioning of the system.

## Use Case and API endpoints
This version includes the entity, value object, aggregate, event and domain service patterns and communications between services. 

### I. customer-account-bounded-context
The **customer-account-bounded-context** contains patterns including entity, value object, aggregate, event and domain service.

Demonstrating Use Cases

#### 1. Create new customer with contact
- event patterns: new **CustomerCreatedEvent** was created (check Analytics Bounded Context console)
- messaging
  + analytics-bounded-context listen to CustomerCreatedEvent to generate the summary from customer data

```shell
curl -X POST -H "Content-Type:application/json" -d "{\"companyName\":\"Company C\", \"address\": \"Queen St, Sydney, NSW\", \"country\": \"Australia\", \"name\":\"Milly\", \"phone\": \"0123654987\", \"email\":\"hmn123@gmail.com\", \"position\":\"Student\"}" http://localhost:8080/customer
```
which returns
```json
{
  "customerId": 3,
  "companyName": "Company C",
  "address": "Queen St, Sydney, NSW",
  "country": "Australia",
  "name": "Milly",
  "phone": "0123654987",
  "email": "hmn123@gmail.com",
  "position": "Student",
  "numberOfCreatedOrders": 0
}
```

#### 2. Update customer
- update customer with `ID = 3`
- event patterns: new **CustomerUpdatedEvent** was created
```shell
curl -X PUT -H "Content-Type:application/json" -d "{\"address\": \"Oxford Ave, Bankstown, NSW\", \"name\":\"Nguyen\", \"email\":\"hmn1234@gmail.com\"}" http://localhost:8080/customer/3
```
which returns
```json
{
  "customerId": 3,
  "companyName": "Company C",
  "address": "Oxford Ave, Bankstown, NSW",
  "country": "Australia",
  "name": "Nguyen",
  "phone": "0123654987",
  "email": "hmn1234@gmail.com",
  "position": "Student",
  "numberOfCreatedOrders": 0
}
```

#### 3. Get customer by id
- get customer with `ID = 1`
```shell
curl -X GET http://localhost:8080/customer/1
```
which may return
```json
{
  "customerId": 1,
  "companyName": "Company A",
  "address": "Moore St, Liverpool, NSW",
  "country": "Australia",
  "name": "Hue Minh Nguyen",
  "phone": "0123456789",
  "email": "hmn998@uowmail.edu.au",
  "position": "Technical Support",
  "numberOfCreatedOrders": 2
}
```
The **numberOfCreatedOrders** may be different because orders were created randomly.

#### 4. Get all customers
```shell
curl -X GET http://localhost:8080/customer 
```
which may return
```json
[
  {
    "customerId": 1,
    "companyName": "Company A",
    "address": "Moore St, Liverpool, NSW",
    "country": "Australia",
    "name": "Hue Minh Nguyen",
    "phone": "0123456789",
    "email": "hmn998@uowmail.edu.au",
    "position": "Technical Support",
    "numberOfCreatedOrders": 2
  },
  {
    "customerId": 2,
    "companyName": "Company B",
    "address": "King St, Melbourne, VIC",
    "country": "Australia",
    "name": "Nguyen Hue Minh",
    "phone": "0987654321",
    "email": "hmn998@gmail.com",
    "position": "Software Engineer",
    "numberOfCreatedOrders": 3
  },
  {
    "customerId": 3,
    "companyName": "Company C",
    "address": "Oxford Ave, Bankstown, NSW",
    "country": "Australia",
    "name": "Nguyen",
    "phone": "0123654987",
    "email": "hmn1234@gmail.com",
    "position": "Student",
    "numberOfCreatedOrders": 0
  }
]
```
The **numberOfCreatedOrders** may be different because orders were created randomly.

#### 5. Get customer's order history
- get customer with `ID = 1`
- communications:
  + communicate with procurement-bounded-context to get order data
  + procurement-bounded-context communicate with sales-bounded-context to get product data
```shell
curl -X GET http://localhost:8080/customer/1/order-history
```
which may return
```json
{
  "customerId": 1,
  "companyName": "Company A",
  "address": "Moore St, Liverpool, NSW",
  "country": "Australia",
  "name": "Hue Minh Nguyen",
  "phone": "0123456789",
  "email": "hmn998@uowmail.edu.au",
  "position": "Technical Support",
  "orderList": [
    {
      "orderId": "ORDER071020230107511",
      "product": {
        "id": 7,
        "productCategory": "Fruit",
        "name": "Orange",
        "price": 0.7,
        "productDetail": {
          "description": "Juicy orange",
          "comment": "Imported from Spain"
        }
      },
      "quantity": 42
    },
    {
      "orderId": "ORDER071020230108641",
      "product": {
        "id": 8,
        "productCategory": "Vegetable",
        "name": "Broccoli",
        "price": 1.3,
        "productDetail": {
          "description": "Green broccoli",
          "comment": "Organic"
        }
      },
      "quantity": 50
    }
  ]
}
```
The **numberOfCreatedOrders** and **orderId** may be different because orders were created randomly.

### II. sales-bounded-context
The **sales-bounded-context** contains patterns including entity, value object, aggregate, event and domain service.

Demonstrating Use Cases

#### 1. Create new product with product detail
- event patterns: new ProductCreatedEvent was created (check Analytics Bounded Context console)
- messaging
  + analytics-bounded-context listen to ProductCreatedEvent to generate the summary from product data
```shell
curl -X POST -H "Content-Type:application/json" -d "{\"productCategory\":\"Fruit\", \"name\": \"Banana\", \"price\": 15.20, \"description\":\"Made in Australia\", \"comment\": \"Unripe\"}" http://localhost:8081/product
```
which returns
```json
{
  "productId": 16,
  "productCategory": "Fruit",
  "name": "Banana",
  "price": 15.2,
  "description": "Made in Australia",
  "comment": "Unripe",
  "numberOfCreatedOrders": 0
}
```

#### 2. Update product and product detail
- update product with `ID = 16` and its detail
- event patterns: new ProductUpdatedEvent was created
```shell
curl -X PUT -H "Content-Type:application/json" -d "{\"productCategory\":\"Vegetable\", \"name\": \"Eggplant\", \"description\":\"Purple Vegetable\"}" http://localhost:8081/product/16
```
which returns
```json
{
  "productId": 16,
  "productCategory": "Vegetable",
  "name": "Eggplant",
  "price": 15.2,
  "description": "Purple Vegetable",
  "comment": "Unripe",
  "numberOfCreatedOrders": 0
}
```

#### 3. Get product and its product detail using id
- get product with `ID = 1` and its detail
```shell
curl -X GET http://localhost:8081/product/1
```
which may return
```json
{
  "productId": 1,
  "productCategory": "Meat",
  "name": "Chicken",
  "price": 10.5,
  "description": "Free cage chicken",
  "comment": "Produced in Australia",
  "numberOfCreatedOrders": 0
}
```
The **numberOfCreatedOrders** may be different because orders were created randomly.

#### 4. Get all products
```shell
curl -X GET http://localhost:8081/product 
```
which may return
```json
[
  {
    "productId": 1,
    "productCategory": "Meat",
    "name": "Chicken",
    "price": 10.5,
    "description": "Free cage chicken",
    "comment": "Produced in Australia",
    "numberOfCreatedOrders": 0
  },
  {
    "productId": 2,
    "productCategory": "Fruit",
    "name": "Apple",
    "price": 0.8,
    "description": "Crispy red apple",
    "comment": "Grown in USA",
    "numberOfCreatedOrders": 0
  },
  {
    "productId": 3,
    "productCategory": "Vegetable",
    "name": "Carrot",
    "price": 0.5,
    "description": "Orange crunchy carrot",
    "comment": "Grown Locally",
    "numberOfCreatedOrders": 0
  },
  {
    "productId": 4,
    "productCategory": "Dairy",
    "name": "Milk",
    "price": 1.2,
    "description": "Fresh milk",
    "comment": "Produced in Canada",
    "numberOfCreatedOrders": 1
  },
  {
    "productId": 5,
    "productCategory": "Grain",
    "name": "Rice",
    "price": 1.5,
    "description": "Long grain rice",
    "comment": "Imported from Asia",
    "numberOfCreatedOrders": 0
  },
  {
    "productId": 6,
    "productCategory": "Meat",
    "name": "Beef",
    "price": 15.0,
    "description": "Grass-fed beef",
    "comment": "Sourced from New Zealand",
    "numberOfCreatedOrders": 0
  },
  {
    "productId": 7,
    "productCategory": "Fruit",
    "name": "Orange",
    "price": 0.7,
    "description": "Juicy orange",
    "comment": "Imported from Spain",
    "numberOfCreatedOrders": 1
  },
  {
    "productId": 8,
    "productCategory": "Vegetable",
    "name": "Broccoli",
    "price": 1.3,
    "description": "Green broccoli",
    "comment": "Organic",
    "numberOfCreatedOrders": 1
  },
  {
    "productId": 9,
    "productCategory": "Dairy",
    "name": "Cheese",
    "price": 3.0,
    "description": "Matured cheddar",
    "comment": "Produced in France",
    "numberOfCreatedOrders": 0
  },
  {
    "productId": 10,
    "productCategory": "Grain",
    "name": "Wheat",
    "price": 0.9,
    "description": "Healthy whole wheat",
    "comment": "Grown in USA",
    "numberOfCreatedOrders": 0
  },
  {
    "productId": 11,
    "productCategory": "Meat",
    "name": "Pork",
    "price": 12.0,
    "description": "Organic pork",
    "comment": "Sourced from Germany",
    "numberOfCreatedOrders": 0
  },
  {
    "productId": 12,
    "productCategory": "Fruit",
    "name": "Banana",
    "price": 0.6,
    "description": "Yellow ripe banana",
    "comment": "Grown in Ecuador",
    "numberOfCreatedOrders": 0
  },
  {
    "productId": 13,
    "productCategory": "Vegetable",
    "name": "Spinach",
    "price": 1.2,
    "description": "Fresh spinach",
    "comment": "Organic",
    "numberOfCreatedOrders": 0
  },
  {
    "productId": 14,
    "productCategory": "Dairy",
    "name": "Yogurt",
    "price": 1.8,
    "description": "Greek yogurt",
    "comment": "Produced in Greece",
    "numberOfCreatedOrders": 1
  },
  {
    "productId": 15,
    "productCategory": "Grain",
    "name": "Oats",
    "price": 0.7,
    "description": "Rolled oats",
    "comment": "Imported from Scotland",
    "numberOfCreatedOrders": 1
  },
  {
    "productId": 16,
    "productCategory": "Vegetable",
    "name": "Eggplant",
    "price": 15.2,
    "description": "Purple Vegetable",
    "comment": "Unripe",
    "numberOfCreatedOrders": 0
  }
]
```
The **numberOfCreatedOrders** may be different because orders were created randomly.

#### 5. Get all created orders containing a product
- get all created orders containing a product having `ID = 4`
- communications:
  + communicate with procurement-bounded-context to get order data
  + procurement-bounded-context communicate with customer-account-bounded-context to get customer data
```shell
curl -X GET http://localhost:8081/product/4/all-orders
```
which may return
```json
{
  "productId": 4,
  "productCategory": "Dairy",
  "name": "Milk",
  "price": 1.2,
  "description": "Fresh milk",
  "comment": "Produced in Canada",
  "orderList": [
    {
      "orderId": "ORDER071020230204665",
      "supplier": {
        "id": 2,
        "companyName": "Company B",
        "address": "King St, Melbourne, VIC",
        "country": "Australia",
        "contact": {
          "name": "Nguyen Hue Minh",
          "phone": "0987654321",
          "email": "hmn998@gmail.com",
          "position": "Software Engineer"
        }
      },
      "quantity": 32
    }
  ]
}
```
The **orderList** may be different because orders were created randomly.

### III. procurement-bounded-context
The **procurement-bounded-context** contains patterns including entity, value object, aggregate, event and domain service.

Demonstrating Use Cases

#### 1. Create new order
- event patterns: new OrderCreatedEvent was created (check Analytics Bounded Context console)
- communications:
  + communicate with customer-account-bounded-context to get customer data
  + communicate with sales-bounded-context to get product data
- messaging
  + customer-account-bounded-context listen to OrderCreatedEvent and update the created orders list of associated customer
  + sales-bounded-context listen to OrderCreatedEvent and update the created orders list of associated product
  + analytics-bounded-context listen to OrderCreatedEvent to generate the summary from order data
```shell
curl -X POST -H "Content-Type:application/json" -d "{\"supplier\":2, \"product\": 1, \"quantity\": 12}" http://localhost:8082/order
```
which may return
```json
{
  "orderId": "ORDER071020230201648",
  "quantity": 12,
  "companyName": "Company B",
  "address": "King St, Melbourne, VIC",
  "country": "Australia",
  "productCategory": "Meat",
  "name": "Chicken",
  "price": 10.5
}
```
The **orderId** may be different because orders were created randomly.

Check if a new order is added into created orders list of the product with `ID = 1`
```shell
curl -X GET http://localhost:8081/product/1/all-orders
```
now may return
```json
{
  "productId": 1,
  "productCategory": "Meat",
  "name": "Chicken",
  "price": 10.5,
  "description": "Free cage chicken",
  "comment": "Produced in Australia",
  "orderList": [
    {
      "orderId": "ORDER071020230201648",
      "supplier": {
        "id": 2,
        "companyName": "Company B",
        "address": "King St, Melbourne, VIC",
        "country": "Australia",
        "contact": {
          "name": "Nguyen Hue Minh",
          "phone": "0987654321",
          "email": "hmn998@gmail.com",
          "position": "Software Engineer"
        }
      },
      "quantity": 12
    }
  ]
}
```
A new order has been added

Check if a new order is added into created orders list of the customer with `ID = 2`
```shell
curl -X GET http://localhost:8080/customer/2/order-history
```
now may return
```json
{
  "customerId": 2,
  "companyName": "Company B",
  "address": "King St, Melbourne, VIC",
  "country": "Australia",
  "name": "Nguyen Hue Minh",
  "phone": "0987654321",
  "email": "hmn998@gmail.com",
  "position": "Software Engineer",
  "orderList": [
    {
      "orderId": "ORDER071020230215510",
      "product": {
        "id": 15,
        "productCategory": "Grain",
        "name": "Oats",
        "price": 0.7,
        "productDetail": {
          "description": "Rolled oats",
          "comment": "Imported from Scotland"
        }
      },
      "quantity": 16
    },
    {
      "orderId": "ORDER071020230214894",
      "product": {
        "id": 14,
        "productCategory": "Dairy",
        "name": "Yogurt",
        "price": 1.8,
        "productDetail": {
          "description": "Greek yogurt",
          "comment": "Produced in Greece"
        }
      },
      "quantity": 4
    },
    {
      "orderId": "ORDER071020230204665",
      "product": {
        "id": 4,
        "productCategory": "Dairy",
        "name": "Milk",
        "price": 1.2,
        "productDetail": {
          "description": "Fresh milk",
          "comment": "Produced in Canada"
        }
      },
      "quantity": 32
    },
    {
      "orderId": "ORDER071020230201648",
      "product": {
        "id": 1,
        "productCategory": "Meat",
        "name": "Chicken",
        "price": 10.5,
        "productDetail": {
          "description": "Free cage chicken",
          "comment": "Produced in Australia"
        }
      },
      "quantity": 12
    }
  ]
}
```
A new order with has been added

#### 2. Update order
- update order having `ID = ORDER071020230201648`. This id may not exist because the orderId was generated randomly. It would be different from time to time.
- event patterns: new **OrderUpdatedEvent** was created
- communications:
  + communicate with customer-account-bounded-context to get customer data
  + communicate with sales-bounded-context to get product data
```shell
curl -X PUT -H "Content-Type:application/json" -d "{\"quantity\": 120}" http://localhost:8082/order/ORDER071020230201648
```
which may return
```json
{
  "orderId": "ORDER071020230201648",
  "quantity": 120,
  "companyName": "Company B",
  "address": "King St, Melbourne, VIC",
  "country": "Australia",
  "productCategory": "Meat",
  "name": "Chicken",
  "price": 10.5
}
```

#### 3. Get order by id
- get order having `ID = ORDER071020230201648`. This id may not exist because the orderId was generated randomly. It would be different from time to time.
- communications:
  + communicate with customer-account-bounded-context to get customer data
  + communicate with sales-bounded-context to get product data
```shell
curl -X GET http://localhost:8082/order/ORDER071020230201648
```
which may return
```json
{
  "orderId": "ORDER071020230201648",
  "quantity": 120,
  "companyName": "Company B",
  "address": "King St, Melbourne, VIC",
  "country": "Australia",
  "productCategory": "Meat",
  "name": "Chicken",
  "price": 10.5
}
```

#### 4. Get all orders
- communications:
  + communicate with customer-account-bounded-context to get customer data
  + communicate with sales-bounded-context to get product data
```shell
curl -X GET http://localhost:8082/order   
```
which may return
```json
[
  {
    "orderId": "ORDER071020230107511",
    "quantity": 42,
    "companyName": "Company A",
    "address": "Moore St, Liverpool, NSW",
    "country": "Australia",
    "productCategory": "Fruit",
    "name": "Orange",
    "price": 0.7
  },
  {
    "orderId": "ORDER071020230215510",
    "quantity": 16,
    "companyName": "Company B",
    "address": "King St, Melbourne, VIC",
    "country": "Australia",
    "productCategory": "Grain",
    "name": "Oats",
    "price": 0.7
  },
  {
    "orderId": "ORDER071020230214894",
    "quantity": 4,
    "companyName": "Company B",
    "address": "King St, Melbourne, VIC",
    "country": "Australia",
    "productCategory": "Dairy",
    "name": "Yogurt",
    "price": 1.8
  },
  {
    "orderId": "ORDER071020230204665",
    "quantity": 32,
    "companyName": "Company B",
    "address": "King St, Melbourne, VIC",
    "country": "Australia",
    "productCategory": "Dairy",
    "name": "Milk",
    "price": 1.2
  },
  {
    "orderId": "ORDER071020230108641",
    "quantity": 50,
    "companyName": "Company A",
    "address": "Moore St, Liverpool, NSW",
    "country": "Australia",
    "productCategory": "Vegetable",
    "name": "Broccoli",
    "price": 1.3
  },
  {
    "orderId": "ORDER071020230201648",
    "quantity": 120,
    "companyName": "Company B",
    "address": "King St, Melbourne, VIC",
    "country": "Australia",
    "productCategory": "Meat",
    "name": "Chicken",
    "price": 10.5
  }
]
```
The order list may be different because orders were created randomly.