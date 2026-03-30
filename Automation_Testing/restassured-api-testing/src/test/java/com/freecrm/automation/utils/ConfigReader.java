package com.freecrm.automation.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static ConfigReader configReader;
    private final Properties properties;

    private ConfigReader() {
        FileInputStream fis;
        try {
            fis = new FileInputStream("src/test/resources/configData/configuration.properties");
            properties = new Properties();
            try {
                properties.load(fis);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static ConfigReader getInstance() {
        if (configReader == null) {
            configReader = new ConfigReader();
        }
        return configReader;
    }

    public String getBaseUrl() {
        String baseUrl = properties.getProperty("baseUrl");
        if (baseUrl != null) return baseUrl;
        else throw new RuntimeException("base_Url not specified in the Configuration.properties file.");
    }

    public String getApiKey() {
        String apiKey = properties.getProperty("api_key");
        if (apiKey != null) return apiKey;
        else throw new RuntimeException("apiKey not specified in the Configuration.properties file.");
    }
}
