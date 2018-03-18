Cool calculator that helps to split amount to invest between selected mutual funds based on taken investment strategy.

## Application modules:
* db-migrations - liquibase for postgres database migrations,
* ops-local - docker-compose containers, that helps to build and run the whole app nicely,
* persistence - spring data-jpa layer used to pull data from postgres db,
* wfe-services - rest service made with spring boot - contains business logic,
* wfe-spa - single page application made in angularjs (1.5), used yeoman generator to create angular with gulp boilerplate. Used to communicate with wfe-services and display data in web browser.

## Prerequisities (macOS):
* jdk 8 ([Java SE Development Kit 8 - Downloads](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)),
* maven (`brew install maven`),
* docker ([Install Docker for Mac | Docker Documentation](https://docs.docker.com/docker-for-mac/install/)),
* docker-compose ([Install Docker Compose | Docker Documentation](https://docs.docker.com/compose/install/)),
* nodejs (`brew install node`),
* bower.js (`npm install -g bower`),
* gulp.js (`npm install -g gulp`).

## Getting started
Go to `ops-local` folder and run the `start-local-environment.sh`  and open `http://localhost:8888/` in your favourite web browser (ran in Google Chrome) .

### What the `start-local-environment.sh` does?
* builds optimized version of angularjs application and moves it to nginx container,
* creates `postgres-alpine` docker container and runs liquidate migrations,
* builds dependencies and then jar of `wfe-services` and moves it to java8-alpine docker image,
* builds and runs docker-compose containers described in `docker-compose.yml`.

## Unit tests coverage report
In `wfe-services` run `mvn clean package` in order to generate JaCoCo coverage reports. They’ll be in `target/site/index.html`.

## Tech stack
* Java 8,
* Spring Boot & Spring Data-JPA,
* Lombok,
* Hibernate,
* JUnit / JaCoCo,
* Liquibase / Postgres,
* Maven,
* Docker & docker-compose,
* AngularJS / Gulp / Bower / Yeoman boilerplate generator.

## More info about wfe-spa
[Go to readme](./wfe-spa/readme.md) in wfe-spa folder.
