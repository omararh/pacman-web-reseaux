package fr.angers.m1.constants;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class BaseDeDonnesConf implements Configuration {
    public static String className = config.getString("jdbc.driver.class");
    public static String url = config.getString("jdbc.url");
    public static String dataBaseName = config.getString("jdbc.dataBaseName");
    public static String login = config.getString("jdbc.login");
    public static String password = config.getString("jdbc.password");
}
