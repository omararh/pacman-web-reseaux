package fr.angers.m1.configuration;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class PacManConf {
    public static Config config = ConfigFactory.load("application.conf");
    public static String url = config.getString("serveur.pacman.url");
    public static int port= config.getInt("serveur.pacman.port");
}
