# About this project

This is a microservice based on Spring Boot to read JSON from two different endpoints and filter data based on provided parameters.
The goal of this project is search available flights from point A to point B. It is possible to have only one airport connection between those points.

# How run this project 
go to ${projectRoot}/


execute the project using the command below:

```
mvn -f spring-boot:run
```

To access the application, please check the URL below:

http://localhost:8080/interconnections/departure={IATA_CODE}&arrival={IATA_CODE}&departureDateTime={yyyy-MM-ddTHH:mm}&arrivalDateTime={yyyy-MM-ddTHH:mm}


Example:

http://localhost:8080/interconnections?departure=DUB&arrival=WRO&departureDateTime=2018-08-01T10:00&arrivalDateTime=2018-08-02T22:00



## Generatin WAR file

go to ${projectRoot}/


execute a command below:

mvn package
