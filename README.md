Portfolio Management System
Application to maintain the stock Portfolio

This application queries Yahoo service to fetch real time quotes and saves to database.

Deploying the application locally
Pre-installation requirement

Install Java 1.6 amd Maven
Installation Instructions: Step 1: Download the code from gitHub https://github.com/vg2012/portfolio extract the code to local drive Step 2: Build with: $ mvn clean install

Note: $ is the directory where the pom.xml exists
Step 3: Run it with: $ java -jar target/dependency/webapp-runner.jar --path "" target/*.war

Note: webapp-runner is from Heroku and it will be installed in target/dependency with pom.xml configurations
Use DbCreationScript if you want to use local database. Database properties can be chaged in src/main/resources/stocks-dao.properties

Note: The application has been tested in Firefox and IE. Minor issue noticed in IE
