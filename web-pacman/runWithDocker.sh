#!/bin/bash

VERSION=$(mvn help:evaluate -Dexpression=project.version -q -DforceStdout)
APP_IMAGE="web-pacman:$VERSION"

APP_CONTAINER="web-pacman-container"
MYSQL_CONTAINER="mysql-container"
DOCKER_NETWORK_NAME="Pacman"

mvn clean install
docker rm -f $APP_CONTAINER
docker rmi -f $APP_IMAGE

# Vérifier si le réseau existe
if docker network inspect $DOCKER_NETWORK_NAME &>/dev/null; then
    echo "Le réseau Docker $DOCKER_NETWORK_NAME existe déjà. Aucune action nécessaire."
else
    # Créer le réseau Docker s'il n'existe pas
    docker network create $DOCKER_NETWORK_NAME
    echo "Le réseau Docker $DOCKER_NETWORK_NAME a été créé avec succès."
fi

docker build -t $APP_IMAGE .


# Vérifier si le conteneur de votre application est en cours d'exécution, sinon le démarrer
if [ ! "$(docker ps -q -f name=$APP_CONTAINER)" ]; then
    if [ "$(docker ps -aq -f status=exited -f name=$APP_CONTAINER)" ]; then
        # Supprimer le conteneur de votre application s'il existe déjà
        docker rm $APP_CONTAINER
    fi
    # Démarrer le conteneur de votre application avec la liaison au conteneur MySQL
    docker run --name $APP_CONTAINER -p 8080:8080 --link $MYSQL_CONTAINER --network $DOCKER_NETWORK_NAME $APP_IMAGE
fi
