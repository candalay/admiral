# Mortgage Calculation API
	
	Used technologies for implementation        
	
	Java 8,
	Spring MVC,
	Spring Data,
	Spring Boot
	H2 in memory database

## Build

    mvn clean install

## Run

    mvn clean spring-boot:run
    
## Run Profile

	There are application.properties files for production, test, dev environments.
	
	To run the application with different profile
	
	mvn spring-boot:run -Drun.profiles=${profile}
	
	Development is a default environment, 
	
	To run the application for only other profiles 
	
	set <activeByDefault>false</activeByDefault>    

## Deploy

    git push origin master

## Documentation

	http://localhost:8081/swagger-ui.html


## GET /mortgage/v1/api/interest-rates Service

	http://localhost:8081/mortgage/v1/api/interest-rates
	
	CURL Command:
	
	curl -X GET --header "Accept: */*" "http://localhost:8081/mortgage/v1/api/interest-rates
	
	Sample Response:
	
	{"interestRates":[{"maturityPeriod": 50, "interestRate": 7.9, "lastUpdatedDate": "2018-01-25 18:13:57.759"}]}
	
## POST /mortgage/v1/api/mortgage-check Service

	http://localhost:8081/mortgage/v1/api/mortgage-check
	
	CURL Command:
	
	curl -X POST --header "Content-Type: application/json" --header "Accept: */*" -d "{
		  \"homeValue\": 350000,
		  \"income\": 65000,
		  \"loanValue\": 125000,
		  \"maturityPeriod\": 5
		}" "http://localhost:8081/mortgage/v1/api/mortgage-check"
	
	Sample Response:
	
	{ "mortgageFeasibility": { "feasibleMortgage": true, "monthlyCost": 2177.33 }}
	
## In Memory Database Access

	http://localhost:8081/h2-console/
	
	jdbc url: jdbc:h2:mem:testdb	then connect.