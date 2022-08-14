# News API
Simple news rest api created with Java 11, Spring, PostgreSQL DB  
![Logo](Images/img.png)

## Table of contents
* [General info](#general-info)
* [Technologies](#technologies)
* [Setup](#setup)
* [Project Status](#project-status)
* [Contacts](#contacts)

## General info
This project is simple application for working with news.  
You can create, read, update news and leave comments under it.   
For available methods you can read it in swagger-ui. 
* http://localhost:8080/swagger-ui/index.html
___
  ![News ](Images/news_controller.png)
___
  ![Comments ](Images/comments_controller.png)

## Technologies
Project is created with:
* Java version: 11
* Spring boot version: 2.7.2
* PostgreSQL version: 14.2
* Docker v20.10.17

## Setup
For run news-api + postgres in Docker container you should:
1) Save project from GitHub repository on your computer.
2) Build project by idea (use task build for gradle) or ./gradlew build in command line.
3) Build docker image being in directory, where located file 'docker-compose.yml'
4) Build docker images by using command 'docker-compose build'.
5) Run container by using command 'docker-compose up'.
6) For shutdown container you have to use command 'docker-compose down'.

## Project Status
* In progress

## Contacts
* Email: **tnkmaze4.0@gmail.com**

