package com.freecrm.automation.dataProviders;

import com.freecrm.automation.enums.DriverType;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileReader {
    private static ConfigFileReader configFileReader;
    private final Properties properties;

    private ConfigFileReader() {
        FileInputStream fis;
        try {
            fis = new FileInputStream("configs/Configuration.properties");
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

    public static ConfigFileReader getInstance() {
        if (configFileReader == null) {
            configFileReader = new ConfigFileReader();
        }
        return configFileReader;
    }

    public long getImplicitlyWait() {
        String implicitlyWait = properties.getProperty("implicitlyWait");
        if(implicitlyWait != null) return Long.parseLong(implicitlyWait);
        else throw new RuntimeException("implicitlyWait not specified in the Configuration.properties file.");
    }

    public String getApplicationUrl() {
        String url = properties.getProperty("url");
        if(url != null) return url;
        else throw new RuntimeException("url not specified in the Configuration.properties file.");
    }

    public String getEmail() {
        String email = properties.getProperty("email");
        if(email != null) return email;
        else throw new RuntimeException("email not specified in the Configuration.properties file.");
    }

    public String getPassword() {
        String password = properties.getProperty("password");
        if(password != null) return password;
        else throw new RuntimeException("password not specified in the Configuration.properties file.");
    }

    public DriverType getBrowser() {
        String browserName = properties.getProperty("browser");
        if(browserName == null || browserName.equalsIgnoreCase("chrome")) return DriverType.CHROME;
        else if(browserName.equalsIgnoreCase("firefox")) return DriverType.FIREFOX;
        else if(browserName.equalsIgnoreCase("edge")) return DriverType.EDGE;
        else throw new RuntimeException("Browser Name Key value in Configuration.properties is not matched : " + browserName);
    }


    public Boolean getBrowserWindowSize() {
        String windowSize = properties.getProperty("windowMaximize");
        if(windowSize != null) return Boolean.valueOf(windowSize);
        return true;
    }

    public String getExcelFilePath() {
        String excelPath = properties.getProperty("excelFilePath");
        if(excelPath != null) return excelPath;
        else throw new RuntimeException("excelPath not specified in the Configuration.properties file.");
    }
}
