# Customer Statement Validation Application

	Customer statement validation application;

	Validate customer statement (csv,xml format);
	
	*All input data must be unique
	*End balance should not be negative

	Used technologies for implementation        

	Java 8,
	Spring Boot,
	Spring Data,
	Spring Batch
	H2 in memory database
	
## Configuration

	the following parameters needs to be updated in application.properties
	
	Samples:
	
	server.port=8081
	customer.statement.xml.file.input=file:\\statement\\input\\xml\\records.xml
	customer.statement.csv.file.input=file:\\statement\\input\\csv\\records.csv	
	customer.statement.file.output=C:\\statement\\output
	
## Build

    mvn clean install

## Run

    mvn clean spring-boot:run

## Job Run
   
    http://localhost:8081/xmltocsv - to trigger xml files processing
    http://localhost:8081/csvtocsv - to trigger csv files processing
    
    check console
   
    sample processed file; output.${UUID}.csv

## Deploy

    git push origin master

## For Next steps

	Quartz could be added for job running
	Create file pattern to process multiple reader using MultiItemReader
	Archiving step could be added.
	Ehcache could be added for the query
	Test classes could be varied