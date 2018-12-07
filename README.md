# Monitoring-Database-Service

#!/bin/bash

cd ./Monitoring-Execution-Service && mvn clean package
cd ..
cd ./Monitoring-Database-Service && mvn clean package
cd ..

docker-compose stop
docker-compose rm -f

docker rmi crossdatabasemonitoring_monitor-offline-scheduler
docker rmi crossdatabasemonitoring_monitor-execution-service
docker rmi crossdatabasemonitoring_monitor-database-service

docker-compose up
