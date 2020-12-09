/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.apache.log4j.Logger;

/**
 *
 * @author Veljko
 */
public class DatabaseProperties {

    private final static Properties PROPERTIES = new Properties();
    private final static String FILE_OUTPUT_STREAM = "src/database/database.properties";
    private final static Logger LOGGER = Logger.getLogger(DatabaseProperties.class);

    static {
        try {
            InputStream inputStream = new FileInputStream(FILE_OUTPUT_STREAM);
            PROPERTIES.load(inputStream);
        } catch (IOException ex) {
            LOGGER.error(ex.getMessage());
        }
    }

    public static String readUrl() {
        return PROPERTIES.getProperty("url");
    }

    public static String readUsername() {
        return PROPERTIES.getProperty("username");
    }

    public static String readPassword() {
        return PROPERTIES.getProperty("password");
    }
    
    public static void writeUrl(String url) {
        PROPERTIES.setProperty("url", url);
    }
    
    public static void writeUsername(String username) {
        PROPERTIES.setProperty("username", username);
    }
    
    public static void writePassword(String password) {
        PROPERTIES.setProperty("password", password);
    }
}
