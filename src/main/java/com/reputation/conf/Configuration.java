package com.reputation.conf;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configuration {
    Properties properties;
    String fileName;

    public Configuration(String fileName) {
        this.properties = new Properties();
        this.fileName = fileName;
        load();
    }

    public String get(String key) {
        return ((String) this.properties.get(key));
    }

    public void load() {
        InputStream input;
        try {
            input = ClassLoader.getSystemClassLoader().getResourceAsStream(fileName);
            properties.load(input);

        } catch (IOException io) {
            io.printStackTrace();

        }
    }
}