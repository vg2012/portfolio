# Portfolio Management System

Application to maintain the stock Portfolio

## Deploying the application locally
Pre-installation requirement
1. Install Java 1.6 amd Maven  

Installation Instructions:
Step 1:
Download the code from gitHub
	https://github.com/vg2012/portfolio
	extract the code to local drive
Step 2:
Build with:
    $ mvn clean install
    
    Note: $ is the directory where the pom.xml exists
Step 3:
Run it with:
    $ java -jar target/dependency/webapp-runner.jar --path "" target/*.war
    
    Notes: webapp-runner is from Heroku and it will be installed in target/dependency with pom.xml configurations

The application has been tested in Firefox and IE. Minor issues found in IE