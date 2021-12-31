# WMMA_WhereMyMoneyAt_RestAPI
WMMA, which stands for WhereMyMoneyAt is an app that helps you keep track of your expenses and money transactions. 
This is the RestAPI implementation of WMMA, built with Spring boot, deploy to Heroku with Heroku PostgreSQL. This is my first time using Spring Boot to build build a Restful API following the three layer(API, Service, DataAccess) patterns. I've always wondered how an HTTP URI can deliver so much information. This project has inspired me in becoming a full stack developer, who not only makes a pretty user interface, but also has the ability to handle things in the backend. This project also inspired me to take a SQL course to enhance my understanding of the language.

During the development process, I linked the API locally to a MySQL server on my laptop. However I really want to make it accessable to the public and Heroku PostgreSQL offers a little more space than other remote SQL database(trial plan). Thus I migrated to PostgreSQL, which allows me to explore difference backend technologies.

**Note that Heroku puts web service to sleep after a certain amount of time. Thus the first request might take longer than usual. Please wait for a little longer or send another GET request to wake up the server, if the respond time appears to be slow.

The RestAPI setup is as below:

-----------------------------------------------------------------------------------------------

GET: https://where-my-money-at.herokuapp.com/api/v1/transaction?all=[true/false]&from=[SpecialFormattedDate]&to=[SpecialFormattedDate]

Parameters:
- all : Required, only when it's the exact character "true", the API would return all the transactions. 
- from : Not required, must be in the form of YYYY-MM-DD with a '-' character in between numbers. Ex. 2021-11-08
- to : Not required, must be in the form of YYYY-MM-DD with a '-' character in between numbers. Ex. 2021-11-09

PUT: https://where-my-money-at.herokuapp.com/api/v1/transaction?name=[String]&amount=[Double]

Parameters:
- name : Required, can be any String. Ex. Cheese
- amount : Required, can be a decimal number and negative number. Ex. 3.99

POST: https://where-my-money-at.herokuapp.com/api/v1/transaction/[id]?name=[String]&amount=[Double]&time=[SpecialFormattedDateTime]

Path Variable:
- id : Required, the id of the transaction that is to be edited.

Parameters:
- name : Not required, can be any String. Ex. Cheese
- amount : Not required, can be a decimal number and negative number. Ex. 3.99
- time : Not required, must be in the form of YYYY-MM-DD-HH-MM with a '-' character in between numbers(24 hr). Ex. 2021-11-09-14-15-06
  
DELETE : https://where-my-money-at.herokuapp.com/api/v1/transaction/[id]

Path Variable:
- id : Required, the id of the transaction that is to be edited.

-----------------------------------------------------------------------------------------------

WMMA_RestAPI supports:
- Create, Read, Update, Delete Transaction data
- Auto record the time of each transaction entry

Some new things I’ve learned:
- Spring Boot Framework
- Spring Data JPA library

Resources Used:
- Spring Data JPA Library
- Spring web
- Heroku PostgreSQL
