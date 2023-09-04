# CSCI318 Group Assignment - Part B
- University of Wollongong
- Campus Liverpool - South Western Sydney
- CSCI318 - Software Engineering Practices & Principles
- Session: Spring 2023

### Project setup

#### customer-service:
```properties
server.port=8080
spring.h2.console.enabled=true
spring.datasource.url=jdbc:h2:mem:testdb
```
The console [http://localhost:8080/h2-console/](http://localhost:8080/h2-console/).
To log on, change the value in the `JDBC URL` entry to `jdbc:h2:mem:testdb`.

#### product-service:
```properties
server.port=8081
spring.h2.console.enabled=true
spring.datasource.url=jdbc:h2:mem:testdb
```
The console [http://localhost:8081/h2-console/](http://localhost:8081/h2-console/).
To log on, change the value in the `JDBC URL` entry to `jdbc:h2:mem:testdb`.

#### order-service:
```properties
server.port=8082
spring.h2.console.enabled=true
spring.datasource.url=jdbc:h2:mem:testdb
```
The console [http://localhost:8082/h2-console/](http://localhost:8082/h2-console/).
To log on, change the value in the `JDBC URL` entry to `jdbc:h2:mem:testdb`.

### Domain Event & Communications
This version includes the domain event patterns and communications between services. 

#### I. product-service
The __product-service__ implements only one way of publishing and handling domain events which is the
`AbstractAggregateRoot` generic class.

Demonstrating Use Cases

__1. Get all created orders containing a product__
- get all created orders containing a product having `ID = 1`
- communications:
  + communicate with order-service to get order data
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
      "id":1,
      "supplier":1,
      "product":1,
      "quantity":10
    },
    {
      "id":2,
      "supplier":3,
      "product":1,
      "quantity":100
    }
  ]
}
```

__2. Create new product with product detail__
- event patterns: New event called __Create__ is created (check PRODUCT_EVENT in h2-console)
```shell
curl -X POST -H "Content-Type:application/json" -d "{\"productCategory\":\"Fruit\", \"name\": \"Banana\", \"price\": 15.20, \"description\":\"Made in Australia\", \"comment\": \"Unriped\"}" http://localhost:8081/product
```
which returns
```json
{
  "productId":5,
  "productCategory":"Fruit",
  "name":"Banana",
  "price":15.2,
  "description":"Made in Australia",
  "comment":"Unriped"
}
```

__3. Update product and product detail__
- update product with `ID = 5` and its detail
- event patterns: New event called __Update__ is created (check PRODUCT_EVENT in h2-console)
```shell
curl -X PUT -H "Content-Type:application/json" -d "{\"productCategory\":\"Vegetable\", \"name\": \"Eggplant\", \"description\":\"Purple Vegetable\"}" http://localhost:8081/product/5
```
which returns
```json
{
  "productId":5,
  "productCategory":"Vegetable",
  "name":"Eggplant",
  "price":15.2,
  "description":"Purple Vegetable",
  "comment":"Unriped"
}
```

#### II. order-service
The __order-service__ implements two ways of publishing and handling 
domain events which are enabled in Spring Boot, i.e., the `AbstractAggregateRoot` generic class and the 
`ApplicationEventPublisher` interface.

Demonstrating Use Cases

__1. Create new order__
- event patterns: New event called __Create__ is created (check ORDER_EVENT in h2-console)
- communications: 
  + communicate with customer-service to get customer data
  + communicate with product-service to get product data
  + communicate with product-service to add orderId to createdOrders for product object (check PRODUCT_EVENT in h2-console,
a new event called "Order" was created as a new order for the product was made)
```shell
curl -X POST -H "Content-Type:application/json" -d "{\"supplier\":3, \"product\": 1, \"quantity\": 12}" http://localhost:8082/order
```
which returns
```json
{
  "orderId":4,
  "supplier":3,
  "product":1,
  "quantity":12,
  "companyName":"Company B",
  "address":"Auckland",
  "country":"New Zealand",
  "productCategory":"Meat",
  "name":"Chicken",
  "price":15.2
}
```

__2. Update order__
- update order having `ID = 4`
- event patterns: New event called __Update__ is created (check ORDER_EVENT in h2-console)
```shell
curl -X PUT -H "Content-Type:application/json" -d "{\"quantity\": 120}" http://localhost:8082/order/4
```
which returns
```json
{
  "orderId":4,
  "supplier":3,
  "product":1,
  "quantity":120,
  "companyName":"Company B",
  "address":"Auckland",
  "country":"New Zealand",
  "productCategory":"Meat",
  "name":"Chicken",
  "price":15.2
}
```

__3. Get order by id__
- get order having `ID = 1`
- communications:
  + communicate with customer-service to get customer data
  + communicate with product-service to get product data
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
  "address":"Liverpool, South Western Sydney",
  "country":"Australia",
  "productCategory":"Meat",
  "name":"Chicken",
  "price":15.2
}
```