package fr.angers.m1.constants;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public interface Configuration {
    public static Config config = ConfigFactory.load("application.conf");
}
