#!/bin/bash

# Compilation et packaging du projet
mvn package

# Copie du fichier WAR vers le dossier de déploiement de Tomcat
cp target/web-pacman.war /Users/omararharbi/apache-eclipse/apache-tomcat-8.0.53/webapps/

# Démarrage de Tomcat en mode console
/Users/omararharbi/apache-eclipse/apache-tomcat-8.0.53/bin/catalina.sh run

