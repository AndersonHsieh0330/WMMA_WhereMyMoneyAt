# WMMA_WhereMyMoneyAt
This is the RestAPI implementation of WMMA, built with Spring boot and MySQL. 

WMMA, which stands for WhereMyMoneyAt is an app that helps you keep track of your expenses and money transactions.

The RestAPI setup is as below:

MySQL Server runs on localhost:3306 with db named WMMA

GET: http://localhost:8080/api/v1/transaction?all=<true/false>&from=<SpecialFormattedDate>&to=<SpecialFormattedDate>

Parameters:
- all : Required, only when it's the exact character "true", the API would return all the transactions. 
- from : Not required, must be in the form of YYYY-MM-DD with a '-' character in between numbers. Ex. 2021-11-08
- to : Not required, must be in the form of YYYY-MM-DD with a '-' character in between numbers. Ex. 2021-11-09

PUT: http://localhost:8080/api/v1/transaction?name=<String>&amount=<Double>

Parameters:
- name : Required, can be any String. Ex. Cheese
- amount : Required, can be a decimal number and negative number. Ex. 3.99

POST: http://localhost:8080/api/v1/transaction/<id>?name=<String>&amount=<Double>&time=<SpecialFormattedDateTime>

Path Variable:
- id : Required, the id of the transaction that is to be edited.

Parameters:
- name : Not required, can be any String. Ex. Cheese
- amount : Not required, can be a decimal number and negative number. Ex. 3.99
- time : Not required, must be in the form of YYYY-MM-DD-HH-MM-SS with a '-' character in between numbers(24 hr). Ex. 2021-11-09-14-15-06-55
  
DELETE : http://localhost:8080/api/v1/transaction/<id>

Path Variable:
- id : Required, the id of the transaction that is to be edited.


