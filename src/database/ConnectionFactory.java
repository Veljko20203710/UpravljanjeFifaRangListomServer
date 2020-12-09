/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.apache.log4j.Logger;

/**
 *
 * @author Veljko
 */
public class ConnectionFactory {

    private Connection connection;
    private static ConnectionFactory instance;
    private final Logger LOGGER = Logger.getLogger(ConnectionFactory.class);

    private ConnectionFactory() throws SQLException {
        String url = DatabaseProperties.readUrl().trim();
        String username = DatabaseProperties.readUsername().trim();
        String password = DatabaseProperties.readPassword().trim();
        try {
            connection = DriverManager.getConnection(url, username, password);
            connection.setAutoCommit(false);
            LOGGER.info("Database connection created.");
        } catch (SQLException ex) {
            LOGGER.error(ex.getMessage());
            throw ex;
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static ConnectionFactory getInstance() throws SQLException {
        if (instance == null || instance.connection.isClosed()) {
            instance = new ConnectionFactory();
        }
        return instance;
    }
}
