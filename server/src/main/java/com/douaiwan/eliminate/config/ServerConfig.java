package com.douaiwan.eliminate.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ServerConfig {
    private static ServerConfig init = null;
    public Properties properties = new Properties();
    public static ServerConfig getInstance(){
        if(init == null){
            init = new ServerConfig();
        }
        return init;
    }

    public ServerConfig(){
        try {
            //"../config/config.properties"
            FileInputStream inputFile = new FileInputStream("D:/config/config.properties");
            properties.load( inputFile );

            inputFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
