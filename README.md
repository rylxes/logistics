# logistics

## DB
MySQL

## application.properties
**These values should be changed**
```bash
spring.datasource.url=jdbc:mysql://${MYSQL_HOST:localhost}:3306/logistics
spring.datasource.username=root
spring.datasource.password=root
```


## installation
```bash
mvn clean install
```

on the first run 2 sample DB records are created


## swagger url
http://localhost:8080/swagger-ui/#/


