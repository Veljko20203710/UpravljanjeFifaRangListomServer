/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import database.DatabaseProperties;

/**
 *
 * @author Veljko
 */
public class LogicDatabaseProperties {

    public static void writeUrl(String url) {
        DatabaseProperties.writeUrl(url);
    }

    public static void writeUsername(String username) {
        DatabaseProperties.writeUsername(username);
    }

    public static void writePassword(String password) {
        DatabaseProperties.writePassword(password);
    }
    
    public static String readUrl() {
        return  DatabaseProperties.readUrl();
    }
    
    public static String readPassword() {
        return DatabaseProperties.readPassword();
    }
    
    public static String readUsername() {
        return DatabaseProperties.readUsername();
    }
}
