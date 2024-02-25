# Utilisez une image de base avec un serveur d'application Java EE (ex. Tomcat)
FROM tomcat:9.0-jdk8

# Copiez le fichier WAR dans le répertoire webapps de Tomcat
COPY target/web-pacman.war /usr/local/tomcat/webapps/

# Exposez le port sur lequel le serveur d'application écoute (par défaut, Tomcat écoute sur le port 8080)
EXPOSE 8080

# Commande à exécuter lorsque le conteneur démarre
CMD ["catalina.sh", "run"]
