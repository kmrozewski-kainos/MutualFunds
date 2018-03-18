#!/usr/bin/env bash

# Build wfe-spa package in /wfe-spa/dist
rm -r ./wfe-spa/dist

cd ../wfe-spa
set -e
npm install
bower install
gulp

cp -r ./dist ../ops-local/wfe-spa
cd ../ops-local

# Run db-migrations
docker-compose up -d --build db
docker-compose up --build dockerize
mvn clean install -f ../db-migrations/pom.xml -D liquibase.properties=${PWD}/db/liquibase.properties

# Build wfe-services
mvn -N install -f ../pom.xml
mvn clean install -f ../persistence/pom.xml
mvn clean package -DskipTests -f ../wfe-services/pom.xml

cp ../wfe-services/target/wfe-services-*.jar ./wfe-services/wfe-services.jar

# Start docker-compose
docker-compose up --build
