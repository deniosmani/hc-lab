Hello!

This repository contains code for my implementation of HC-lab project spec (you can see pdf with project requirements). I implemented this full-stack application with Angular on front and Spring Boot at the back.
Version of Angular is 13.3 and version of Spring Boot is 2.6.7. I used MySql database(using SQLyog as rdbms). 
When you pull this code you will need to do next commands:
	In root of front-end part: npm install
	In root of back-end part: mvn clean install
After that you can run backend application as java aplication and it will deploy back-end app on localhost:8080. For front-end u should call command ng serve in root, and it will run application on localhost:4200.
Now you are ready to use app.