# Steps to Set Up and Run the Project

This document provides a step-by-step guide to set up and run the project on
your local machine.

## Prerequisites

Before you begin, ensure you have met the following requirements:

- You have installed Java 21.
- You have installed Maven 3.6 or higher.

## Commands to Run the Project

```bash
    mvn clean package
    java -jar .\target\Demo1-0.0.1-SNAPSHOT.jar --spring.profiles.active=test
    
    If you want to run with the production profile, you need to setup the 
    MySQL database with following credentials:
        url: jdbc:mysql://localhost:3306/demo1
        username: root
        password: root
    java -jar .\target\Demo1-0.0.1-SNAPSHOT.jar --spring.profiles.active=prod
```



