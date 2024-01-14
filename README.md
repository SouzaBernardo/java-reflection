# Reflection in JAVA
## About this repository

This repository simulates an API that includes a product controller listing the products in XML format.
To achieve this, some conventions have been defined:

- Input: start with '/' followed by the controller's initials.
- Parameters: we can pass parameters in the input, for example: `/product/listXML` will execute the `listXML` method in the `ProductController.class`.
  - It is also possible to pass the product ID as follows: `/product/1` to return the product with ID 1.

### Output examples:

- `/product/listXML`:
```XML
<list>
  <product>
    <id>1</id>
    <name>Shoes</name>
    <price>20.0</price>
    <brand>Nike</brand>
  </product>
  <product>
      <id>2</id>
      <name>Shirt</name>
      <price>21.0</price>
      <brand>Nike</brand>
    </product>
</list>
```
-  `/product/1`:
```XML
<product>
  <id>1</id>
  <name>Shoes</name>
  <price>20.0</price>
  <brand>Nike</brand>
</product>
```

## Technologies:
- Java 21
- Gradle
- Reflection API
