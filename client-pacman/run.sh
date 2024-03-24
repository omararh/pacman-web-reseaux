#!/bin/bash

rm dependency-reduced-pom.xml
mvn clean package
mv target/client-pacman-*.jar target/client-pacman.jar
chmod 777 target/client-pacman.jar
java -jar target/client-pacman.jar
