# chat Application

   This is an Chat API built using Spring Boot and MySql Database.
   The API allows users to chat with one user to another user.
   
### Requirments
 * IntelliJIDEA
 * Serverport: 8080 (use: localhost:8080)
 * Database name: test_db
 * MySql Database
 * Java version: 17
 * Everything is present in the pom.xml (no need to download any library)
### Steps to run program
 * Go to localhost:8080/
 * Create Database in MySql
 * Insert values through Postman or Swagger 
 
## There are Three models -
 * Users
 * Chat
 * Status
 
 ## Data flow will be like -
 * Controller
 * Service
 * Repository
 * Database
 
 ## Application properties for database configuration-
* springdoc.swagger-ui.path=/swagger
* spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
* spring.datasource.url = jdbc:mysql://localhost:3306/test_db
* spring.datasource.username = root
* spring.datasource.password = 123456
* spring.jpa.show-sql = true
* spring.jpa.hibernate.ddl-auto = update
* spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.MySQL5Dialect
