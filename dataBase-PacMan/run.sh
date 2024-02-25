#!/bin/bash

NETWORK_NAME="PacMan"
MYSQL_CONTAINER_NAME="mysql-container"
MYSQL_ROOT_PASSWORD="root"
MYSQL_DATABASE="Pacman"
MYSQL_USER="user"
MYSQL_PASSWORD="user"
INIT_SCRIPT_PATH="sql/init.sql"

if docker network inspect $NETWORK_NAME &>/dev/null; then
    echo "Le réseau Docker $NETWORK_NAME existe déjà. Aucune action nécessaire."
else
    docker network create $NETWORK_NAME
    echo "Le réseau Docker $NETWORK_NAME a été créé avec succès."
fi

docker run -d  -it --name $MYSQL_CONTAINER_NAME \
    --network $NETWORK_NAME \
    -e MYSQL_ROOT_PASSWORD=$MYSQL_ROOT_PASSWORD \
    -e MYSQL_DATABASE=$MYSQL_DATABASE \
    -e MYSQL_USER=$MYSQL_USER \
    -e MYSQL_PASSWORD=$MYSQL_PASSWORD \
    -p 3306:3306 \
    mysql:latest

sleep 10

docker exec -i $MYSQL_CONTAINER_NAME mysql -u$MYSQL_USER -p$MYSQL_PASSWORD $MYSQL_DATABASE < $INIT_SCRIPT_PATH

echo "La base de données a été initialisée avec $INIT_SCRIPT_PATH."
