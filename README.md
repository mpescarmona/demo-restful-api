# Getting Started

# demo-restful-api

### Overview
This is a demo application of a spring boot backend API that registers an user into a h2 in-memory database.  

* The application uses the default user and password preconfigured by Spring Boot to login in the H2 database instance. The default user is 'user' without any password
* By default, a new database **testdb** will be created on the H2 in-memory instance, as well as two tables called **USER** and **PHONE** inside the database.
* It is not necessary to run any initial script to perform the initial data feed on the **testdb** database, but some sample records will be added automatically at startup.    

### Running your service

```
$ ./gradlew bootRun
```
After that, your application should be up and running on [localhost:8080](http://localhost:8080)

### Tech stack
This is a quick list of the technologies and dependencies used to implement this demo:
* Java, version 1.8
* Apache Tomcat, version 9.0.16
* Spring Boot, version 2.2.0 RELEASE
    * Spring-boot-starter-data-jpa, version 2.2.0 RELEASE
    * Spring-boot-starter-data-rest, version 2.2.0 RELEASE
        * Spring-boot-starter-web, version 2.2.0 RELEASE
    * Spring-boot-starter-test, version 2.2.0 RELEASE
* Junit, version 5
* Mockito, version 2.23.4
* Lombok, version 1.18.6
* Hamcrest, version 1.3
* Swagger and Swagger-ui, version 2.6.0

### API documentation
As this demo application uses **Swagger** and **Swagger-ui,** you can easily view and try the REST endpoints exposed by this backend API.

With the application up and running, you can go to the swagger-ui url in your web browser, by default:
* [Swagger-ui: demo-restful-api](http://localhost:8080/swagger-ui.html#)

The backend API exposes two endpoints:
* [POST /demo-api/user/register](http://localhost:8080/swagger-ui.html#!/register-controller/registerNewUserUsingPOST)
* [GET /demo-api/user/{email}](http://localhost:8080/swagger-ui.html#!/register-controller/findUserByEmailUsingGET)
