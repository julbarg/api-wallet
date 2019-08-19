# API Wallet
## LeoVegas

API Wallet to manage credit/debit transaction for clients

### Build With
- Spring Boot 2.1.7 
- Spring 5.1.9 
- Intellij - IDE
- H2 - Database
- Heroku - Cloud plataform
- Lombok - (It should be installed and enabled if Intellij is used)
- Swagger - Documentation


### Getting Started
The project can be deploy **local** or use **Heroku**

#### Heroku - Already deployed
 - Root: https://api-wallet.herokuapp.com/
 - Swagger Documentation: https://api-wallet.herokuapp.com/swagger-ui.html
 - H2 Console: https://api-wallet.herokuapp.com/h2-console/ Use the following configuration:
    - JDBC URL: jdbc:h2:mem:testdb

#### Local
- To deploy in **local** using terminal use gradle
```shell
$ ./gradlew bootRun
```

 - To deploy in **IntelliJ IDEA**, first install and enable lombok
![alt text](https://i.stack.imgur.com/Gngfe.png)
And then run ApiWalletApplication.java

##### Local URLs
- Root: http://localhost:8080/
- Swagger Documentation: http://localhost:8080/swagger-ui.html
- H2 Console: http://localhost:8080/h2-console/
    - JDBC URL: jdbc:h2:mem:testdb        



### Database Structure
```
 ACCOUNT
    ID
    ACCOUNT_NUMBER
    BALANCE
    CLIENT_ID
 
 CLIENT
    ID
    FIST_NAME
    LAST_NAME
 
 TRANSACTION
    ID
    AMOUNT
    DATE
    TRANSACTION_ID
    TRANSACTION_TYPE
    ACCOUNT_ID
```

### Data preload when the app is deployed
| Account Number  | Balance       | First Name    | LastName      |
| --------------- | ------------- | ------------- | ------------- |
| 1024501252      |     0.0       |    Julian     |   Barragan    |
| 8795414         |    250.0      |     Bob       |   Marley      |
| 987451          |    799.0      |     Tony      |   Stark       |
| 854745          |   4515.8      |     Bruce     |   Wayne       |
| 987454          |   74554.7     |      Bob      |   Dylan       |

This is not the structure of databases in only informative

### Postman collection
There is a Postman documentation in the following path:
- api-wallet/src/main/resources/documents/APIWalletPostam.json
