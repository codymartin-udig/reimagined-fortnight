# whiteboard-microservices
Microservice demonstration

NOTE: Unfortunately, this app has a few dependencies that must be setup in the short term 
so it might be slightly difficult to get this thing up and running, but feel free to look at the source code

Also, if someone wants to make use of Vagrant files and Docker compose files, I would happily support them in that effort


App Breakdown

API Gateway (Node JS)
-Basic HTTP routing service
-There are three registered routes that will redirect HTTP requests to the specified services

The routes are:
/content
/app-api
/vendor-api

App-API (Java)
Spring Boot Application served via embedded Apache Tomcat
-Exposes a few endpoints for CRUD operations on Mongo DB

Vendor-API (Java) (Basically a copy of that ^)
Spring Boot Application served via embedded Apache Tomcat
-Exposes a few endpoints for CRUD operations on Mongo DB

Content (Apache Web Server)
-Content is being served by a Apache Web Server
