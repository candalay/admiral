# File Monitor Application

This application monitors a configured text file and displays the counts of the INFO, ERROR, WARNING parameters periodically on gui

    Format: datetime severity some message

    Example:
    2016-09-20 16:23:10,994 INFO Some info message

    2016-09-20 16:23:11,994 INFO Some other info message

    2016-09-20 16:23:12,994 WARNING Some warning message

    2016-09-20 16:23:13,994 WARNING Some other warning message

    2016-09-20 16:23:14,994 ERROR Some error message


Implementation        

    Java 8, 
    Spring Core,
    Spring Websocket,
    Spring Boot

## Configuration

    Integer - file.monitor.sleep.interval - wait time
    String - file.path - path of the file

## Build

    mvn clean install
    
## Run

    mvn clean spring-boot:run
    
## GUI Access

    http://localhost:8081/monitor.html
    
    - Click connect
    - Click Start to monitor file
 
## Deploy

    git push origin master


