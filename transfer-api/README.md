# Payment Transfer API

	This REST based API is developed for the requirements that are stated in TechnicalAssingment.pdf
	
	Used technologies for implementation        
	
	Java 8,
	Spring MVC,
	Spring Data,
	Spring Boot
	H2 in memory database
	
	This REST based API provides createAccount and makeTransfer POST services.
	
## Build

    mvn clean install

## Run

    mvn clean spring-boot:run
    
## Run Profile

	There are application.properties files for production, test, dev environments.
	
	To run the application with different profile:
	
	mvn spring-boot:run -Drun.profiles=${profile}
	
	Development is the default environment, 
	
	To run the application for the other profiles: 
	
	set <activeByDefault>false</activeByDefault>    

## Deploy

    git push origin master

## Documentation

	http://localhost:8081/swagger-ui.html


## POST /transfer/v1/api/createAccount Service

	http://localhost:8081/transfer/v1/api/createAccount
	
	CURL Command:
	
	curl -X POST --header "Content-Type: application/json" --header "Accept: */*" -d "{
	  \"balance\": 50,
	  \"name\": \"Sender\"
	}" "http://localhost:8081/transfer/v1/api/createAccount"
	
	Sample Response:
	
	{"result": "success", "account": { "name": "Sender", "balance": 50,"accountCreationDate": "2018-02-04 17:48:26.086"}}
	
## POST /transfer/v1/api/makeTransfer Service

	http://localhost:8081/transfer/v1/api/makeTransfer
	
	CURL Command:
	
	curl -X POST --header "Content-Type: application/json" --header "Accept: */*" -d "{
	  \"amount\": 20,
	  \"receiverAccountName\": \"Sender\",
	  \"senderAccountName\": \"Receiver\"
	}" "http://localhost:8081/transfer/v1/api/makeTransfer"
	
	Sample Response:
	
	{ "result": "success", "transfer": { "transferId": "1729e26a-4a73-4115-b9c2-4cf439416658", "transferDate": "2018-02-04 18:20:04.434" }}
	
## In Memory Database Access

	http://localhost:8081/h2-console/
	
	jdbc url: jdbc:h2:mem:testdb	then connect.
	
## Notes

## Next Steps
