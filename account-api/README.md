# Account API

API service to create account and transfer money

Implementation        

Java 8,
Spring MVC,
Spring Data,
Spring Core
H2 in memory database

## Build

    mvn clean install

## Run

    mvn clean spring-boot:run

## Deploy

    git push origin master

## Documentation

http://localhost:8080/swagger-ui.html

### Create an account

Request URL: http://localhost:8080/account

curl -X POST --header "Content-Type: application/json" --header "Accept: application/json" -d "{\"accountNumber\":\"NLABNA0001\",\"balance\":120.0}" "http://localhost:8080/account"

{
  "id": "1fb62149-4e63-4865-81cd-5a791515e8e6"
}
    
### Transfer money
     
Request URL : http://localhost:8080/account/transfer

curl -X POST --header "Content-Type: application/json" --header "Accept: application/json" -d "{\"senderAccountNumber\":\"NLABNA0002\",\"receiverAccountNumber\":\"NLABNA0001\",\"amount\":10.0}" "http://localhost:8080/account/transfer"

{
  "transfer": {
    "id": 1,
    "transactionId": "54259ce7-8511-4ae8-a1e7-515d34dc8482",
    "senderAccountNumber": "NLABNA0002",
    "receiverAccountNumber": "NLABNA0001",
    "amount": 10,
    "identifier": "54259ce7-8511-4ae8-a1e7-515d34dc8482"
  },
  "id": "54259ce7-8511-4ae8-a1e7-515d34dc8482"
}

