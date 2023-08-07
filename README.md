## Rewards demo Application
A spring boot application that calculates rewards based on customer transactions

## Build

mvn clean install

## Run

java -jar .\target\demo-0.0.1-SNAPSHOT.jar

## Create a transaction
```bash

POST 'http://localhost:8080/transaction/spent' 


JSON:
{
"customerId": "10000002",
"amountSpent": 120
}

```
## Get Rewards total:
```bash

GET 'http://localhost:8080/reward/total/10000002'

10000002 is the customerId

success response:
{
"customerId": "10000002",
"total": 90
}


If no transactions error message is shown:
{
"message": "No transactions found"
}
```

## Get Rewards by Month
```bash

GET 'http://localhost:8080/reward/month/10000002' 

success response:
{
    "customerId": "10000002",
    "monthlyRewards": {
        "8": 90
    }
}
If no transactions error message is shown:
 {
    "message": "No transactions found"
}
```

## Other
/src/main/resources
data.sql   (used to setup customer master table)

## H2 console
```bash
Application uses an in memory H2 database.

http://localhost:8080/h2-console
JDBC url: jdbc:h2:mem:testdb
```