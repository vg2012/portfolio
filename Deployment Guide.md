# Portfolio Management System

This is a application to maintain the stock Portfolio

## Deploying the application locally
Pre-installation requirement
1. Install Java 1.6 amd Maven  

Installation Instructions:
Step 1:
Download the code from gitHub
	https://github.com/vg2012/portfolio
Step 2:
Build with:
    $mvn clean install

Step 3:
Run it with:
    $java -jar target/dependency/webapp-runner.jar --path "" target/*.war

Notes: Used Java 1.6, webapp-runner from Heroku.

The application has been tested in Firefox