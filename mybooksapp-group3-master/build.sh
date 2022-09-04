#!/bin/bash
cd userservice
source ./env-variable.sh
mvn clean package
docker build -t userservice .
cd ..
cd favouriteservice
source ./env-variable.sh
mvn clean package
docker build -t favouriteservice .
cd ..
cd MyBooksUI
ng build
docker build -t mybooksui .
cd ..