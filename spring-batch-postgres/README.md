
spring boot, spring batch, and postgres
===

This shows an integration with spring boot 2.x, spring web, spring batch, and postgres to kick off a simple workflow.

It is based off of [spring examples](https://github.com/spring-guides/gs-batch-processing)

```
# create database (in PostgreSQL as admin)
CREATE database spring_batch_test;

# Build spring boot application (or use your editor)
gradle build


# start spring boot application (or use your editor)
gradle bootRun

curl -X post http://localhost:8080/jobs/start

or

Use Postman:
HTTP Method - POST
URL - http://localhost:8080/jobs/start
```