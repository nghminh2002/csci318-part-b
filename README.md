# CSCI318 Group Assignment - Part B
- University of Wollongong
- Campus Liverpool - South Western Sydney
- CSCI318 - Software Engineering Practices & Principles
- Session: Spring 2023

### Project setup

#### customer-account-bounded-context:
```properties
server.port=8080
spring.h2.console.enabled=true
spring.datasource.url=jdbc:h2:mem:testdb
```
The console [http://localhost:8080/h2-console/](http://localhost:8080/h2-console/).
To log on, change the value in the `JDBC URL` entry to `jdbc:h2:mem:testdb`.

#### sales-bounded-context:
```properties
server.port=8081
spring.h2.console.enabled=true
spring.datasource.url=jdbc:h2:mem:testdb
```
The console [http://localhost:8081/h2-console/](http://localhost:8081/h2-console/).
To log on, change the value in the `JDBC URL` entry to `jdbc:h2:mem:testdb`.

#### procurement-bounded-context:
```properties
server.port=8082
spring.h2.console.enabled=true
spring.datasource.url=jdbc:h2:mem:testdb
```
The console [http://localhost:8082/h2-console/](http://localhost:8082/h2-console/).
To log on, change the value in the `JDBC URL` entry to `jdbc:h2:mem:testdb`.

### Patterns & Communications
This version includes the entity, value object, aggregate, event and domain service patterns and communications between services. 

#### I. customer-account-bounded-context
The __customer-account-bounded-context__ implements two ways of publishing and handling domain events which are enabled in Spring Boot, 
i.e., the `AbstractAggregateRoot` generic class and the `ApplicationEventPublisher` interface. This also contains patterns 
including entity, value object, aggregate, event and domain service.

Demonstrating Use Cases

__1. Create new customer with contact__
- event patterns: New event called __Create__ is created (check CUSTOMER_EVENT in h2-console)
```shell
curl -X POST -H "Content-Type:application/json" -d "{\"companyName\":\"Company C\", \"address\": \"Queen St, Sydney, NSW\", \"country\": \"Australia\", \"name\":\"Milly\", \"phone\": \"0123654987\", \"email\":\"hmn123@gmail.com\", \"position\":\"Student\"}" http://localhost:8080/customer
```
which returns
```json
{
  "customerId":3,
  "companyName":"Company C",
  "address":"Queen St, Sydney, NSW",
  "country":"Australia",
  "name":"Milly",
  "phone":"0123654987",
  "email":"hmn123@gmail.com",
  "position":"Student"
}
```

__2. Update customer__
- update customer with `ID = 3`
- event patterns: New event called __Update__ is created (check CUSTOMER_EVENT in h2-console)
```shell
curl -X PUT -H "Content-Type:application/json" -d "{\"address\": \"Oxford Ave, Bankstown, NSW\", \"name\":\"Nguyen\", \"email\":\"hmn1234@gmail.com\"}" http://localhost:8080/customer/3
```
which returns
```json
{
  "customerId":3,
  "companyName":"Company C",
  "address":"Oxford Ave, Bankstown, NSW",
  "country":"Australia",
  "name":"Nguyen",
  "phone":"0123654987",
  "email":"hmn1234@gmail.com",
  "position":"Student"
}
```

__3. Get customer by id__
- get customer with `ID = 1`
```shell
curl -X GET http://localhost:8080/customer/1
```
which returns
```json
{
  "customerId":1,
  "companyName":"Company A",
  "address":"Moore St, Liverpool, NSW",
  "country":"Australia",
  "name":"Hue Minh Nguyen",
  "phone":"0123456789",
  "email":"hmn998@uowmail.edu.au",
  "position":"Technical Support"
}
```

__4. Get all customers__
```shell
curl -X GET http://localhost:8080/customer 
```
which returns
```json
[
  {
    "customerId":1,
    "companyName":"Company A",
    "address":"Moore St, Liverpool, NSW",
    "country":"Australia",
    "name":"Hue Minh Nguyen",
    "phone":"0123456789",
    "email":"hmn998@uowmail.edu.au",
    "position":"Technical Support"
  },
  {
    "customerId":2,
    "companyName":"Company B",
    "address":"King St, Melbourne, VIC",
    "country":"Australia",
    "name":"Nguyen Hue Minh",
    "phone":"0987654321",
    "email":"hmn998@gmail.com",
    "position":"Software Engineer"
  },
  {
    "customerId":3,
    "companyName":"Company C",
    "address":"Oxford Ave, Bankstown, NSW",
    "country":"Australia",
    "name":"Nguyen",
    "phone":"0123654987",
    "email":"hmn1234@gmail.com",
    "position":"Student"
  }
]
```

#### II. sales-bounded-context
The __sales-bounded-context__ implements two ways of publishing and handling domain events which are enabled in Spring Boot,
i.e., the `AbstractAggregateRoot` generic class and the `ApplicationEventPublisher` interface. This also contains patterns
including entity, value object, aggregate, event and domain service.

Demonstrating Use Cases

__1. Get all created orders containing a product__
- get all created orders containing a product having `ID = 1`
- communications:
  + communicate with procurement-bounded-context to get order data
  + procurement-bounded-context communicate with customer-account-bounded-context to get customer data
```shell
curl -X GET http://localhost:8081/product/1/all-orders
```
which returns
```json
{
  "productId":1,
  "productCategory":"Meat",
  "name":"Chicken",
  "price":15.2,
  "description":"Free cage chicken",
  "comment":"Produced in Australia",
  "orderList":[
    {
      "orderId":1,
      "supplier":{
        "id":1,
        "companyName":"Company A",
        "address":"Moore St, Liverpool, NSW",
        "country":"Australia",
        "contact":{
          "name":"Hue Minh Nguyen",
          "phone":"0123456789",
          "email":"hmn998@uowmail.edu.au",
          "position":"Technical Support"
        }
      },
      "quantity":10
    },
    {
      "orderId":2,
      "supplier":{
        "id":2,
        "companyName":"Company B",
        "address":"King St, Melbourne, VIC",
        "country":"Australia",
        "contact":{
          "name":"Nguyen Hue Minh",
          "phone":"0987654321",
          "email":"hmn998@gmail.com",
          "position":"Software Engineer"
        }
      },
      "quantity":100
    }
  ]
}
```

__2. Create new product with product detail__
- event patterns: New event called __Create__ is created (check PRODUCT_EVENT in h2-console)
```shell
curl -X POST -H "Content-Type:application/json" -d "{\"productCategory\":\"Fruit\", \"name\": \"Banana\", \"price\": 15.20, \"description\":\"Made in Australia\", \"comment\": \"Unripe\"}" http://localhost:8081/product
```
which returns
```json
{
  "productId":3,
  "productCategory":"Fruit",
  "name":"Banana",
  "price":15.2,
  "description":"Made in Australia",
  "comment":"Unripe",
  "numberOfCreatedOrders":0
}
```

__3. Update product and product detail__
- update product with `ID = 3` and its detail
- event patterns: New event called __Update__ is created (check PRODUCT_EVENT in h2-console)
```shell
curl -X PUT -H "Content-Type:application/json" -d "{\"productCategory\":\"Vegetable\", \"name\": \"Eggplant\", \"description\":\"Purple Vegetable\"}" http://localhost:8081/product/3
```
which returns
```json
{
  "productId":3,
  "productCategory":"Vegetable",
  "name":"Eggplant",
  "price":15.2,
  "description":"Purple Vegetable",
  "comment":"Unripe",
  "numberOfCreatedOrders":0
}
```

__4. Get product and its product detail using id__
- get product with `ID = 1` and its detail
```shell
curl -X GET http://localhost:8081/product/1
```
which returns
```json
{
  "productId":1,
  "productCategory":"Meat",
  "name":"Chicken",
  "price":15.2,
  "description":"Free cage chicken",
  "comment":"Produced in Australia",
  "numberOfCreatedOrders":2
}
```

__5. Get all products__
```shell
curl -X GET http://localhost:8081/product 
```
which returns
```json
[
  {
    "productId":1,
    "productCategory":"Meat",
    "name":"Chicken",
    "price":15.2,
    "description":"Free cage chicken",
    "comment":"Produced in Australia",
    "numberOfCreatedOrders":2
  },{
    "productId":2,
    "productCategory":"Vegetable",
    "name":"Carrot",
    "price":5.0,
    "description":"Orange Vegetable",
    "comment":"Grown Locally",
    "numberOfCreatedOrders":1
  },{
    "productId":3,
    "productCategory":"Vegetable",
    "name":"Eggplant",
    "price":15.2,
    "description":"Purple Vegetable",
    "comment":"Unripe",
    "numberOfCreatedOrders":0
  }
]
```

#### III. procurement-bounded-context
The __procurement-bounded-context__ implements two ways of publishing and handling 
domain events which are enabled in Spring Boot, i.e., the `AbstractAggregateRoot` generic class and the 
`ApplicationEventPublisher` interface.

Demonstrating Use Cases

__1. Create new order__
- event patterns: New event called __Create__ is created (check ORDER_EVENT in h2-console)
- communications: 
  + communicate with customer-account-bounded-context to get customer data
  + communicate with sales-bounded-context to get product data
  + communicate with sales-bounded-context to add orderId to createdOrders for product object (check PRODUCT_EVENT in h2-console,
a new event called "Order" was created as a new order for the product was made)
```shell
curl -X POST -H "Content-Type:application/json" -d "{\"supplier\":2, \"product\": 1, \"quantity\": 12}" http://localhost:8082/order
```
which returns
```json
{
  "orderId":4,
  "supplier":2,
  "product":1,
  "quantity":12,
  "companyName":"Company B",
  "address":"King St, Melbourne, VIC",
  "country":"Australia",
  "productCategory":"Meat",
  "name":"Chicken",
  "price":15.2
} 
```
Check if a new order is added into created orders list of the product with `ID = 1`
```shell
curl -X GET http://localhost:8081/product/1/all-orders
```
now returns
```json
{
  "productId":1,
  "productCategory":"Meat",
  "name":"Chicken",
  "price":15.2,
  "description":"Free cage chicken",
  "comment":"Produced in Australia",
  "orderList":[
    {
      "orderId":1,
      "supplier":{
        "id":1,
        "companyName":"Company A",
        "address":"Moore St, Liverpool, NSW",
        "country":"Australia",
        "contact":{
          "name":"Hue Minh Nguyen",
          "phone":"0123456789",
          "email":"hmn998@uowmail.edu.au",
          "position":"Technical Support"
        }
      },
      "quantity":10
    },
    {
      "orderId":2,
      "supplier":{
        "id":2,
        "companyName":"Company B",
        "address":"King St, Melbourne, VIC",
        "country":"Australia",
        "contact":{
          "name":"Nguyen Hue Minh",
          "phone":"0987654321",
          "email":"hmn998@gmail.com",
          "position":"Software Engineer"
        }
      },
      "quantity":100
    },
    {
      "orderId":4,
      "supplier":{
        "id":2,
        "companyName":"Company B",
        "address":"King St, Melbourne, VIC",
        "country":"Australia",
        "contact":{
          "name":"Nguyen Hue Minh",
          "phone":"0987654321",
          "email":"hmn998@gmail.com",
          "position":"Software Engineer"
        }
      },
      "quantity":12
    }
  ]
}
```
A new order with `ID = 4` has been added

__2. Update order__
- update order having `ID = 4`
- event patterns: New event called __Update__ is created (check ORDER_EVENT in h2-console)
- communications:
  + communicate with customer-account-bounded-context to get customer data
  + communicate with sales-bounded-context to get product data
```shell
curl -X PUT -H "Content-Type:application/json" -d "{\"quantity\": 120}" http://localhost:8082/order/4
```
which returns
```json
{
  "orderId":4,
  "supplier":2,
  "product":1,
  "quantity":120,
  "companyName":"Company B",
  "address":"King St, Melbourne, VIC",
  "country":"Australia",
  "productCategory":"Meat",
  "name":"Chicken",
  "price":15.2
}
```

__3. Get order by id__
- get order having `ID = 1`
- communications:
  + communicate with customer-account-bounded-context to get customer data
  + communicate with sales-bounded-context to get product data
```shell
curl -X GET http://localhost:8082/order/1
```
which returns
```json
{
  "orderId":1,
  "supplier":1,
  "product":1,
  "quantity":10,
  "companyName":"Company A",
  "address":"Moore St, Liverpool, NSW",
  "country":"Australia",
  "productCategory":"Meat",
  "name":"Chicken",
  "price":15.2
}
```