#### Run instructions:

The project is already packaged and can run with the following ways
1. From terminal with mvn installed, run from main project dir

        mvn spring-boot:run 
        


Then access the application from url:
http://localhost:8080/home

2. Alternatively, the app can be started using an IDE like IntelliJ.

Note: The database has no data inside so the app execution should start with signup of a new user.


#### Test execution instructions:
1. From terminal with mvn installed, run run from main project dir

         mvn clean test -Dwebdriver.base.url=http:localhost:8080/home
        


2. Alternatively, the tests can be run using an IDE like IntelliJ.

####Some general notes on the project:
The project is implemented using Spring Boot framework.

Also some selenium test are implemented the cover the 100% of classes and 75% of the code.

The sorting and filtering functionality was not implemented. (Probably it would have been easier if I had use JPA insted of mybatis but it was too late to change my implementation)

Extra functionality of add and delete a movie is implemented.

The database I used was H2. One improvement that I thought of but didn't have time to implement is to replace it with a dockerized MySql db.