# Projet Pacman
Projet Pacman
# Prérequis
Assurez-vous d'avoir les éléments suivants installés sur votre système :
- Maven (version 3.x)
- Java JDK (version 8)

Vous pouvez vérifier si ils sont installés en exécutant les commandes suivantes dans votre terminal :
```bash
mvn -v
java --version
```
Assurez-vous que la version de Java affichée est la version 8.


# Installation
Clonez le projet, accédez au répertoire du projetet et suivez les étapes suivantes: 
## Stape 1 : Lancement de la base de données 
Executez cette commande 
```bash
./dataBase-PacMan/run.sh
```
## Stape 2 : Lancement du site web 
Executez cette commande 
```bash
./web-pacman/runWithDocker.sh
```
## Stape 3 : Lancement du serveur du jeu 
Ouvrez une nouveau tirminale et executez cette commande 
```bash
./serveur-pacman/runWithDocker.sh
```

## Stape 3 : Lancement du client du jeu 
Ouvrez une nouveau tirminale et executez cette commande 
```bash
./client-pacman/run.sh
```