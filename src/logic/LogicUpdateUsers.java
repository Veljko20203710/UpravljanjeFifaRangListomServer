/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import database.ConnectionFactory;
import database.DBBroker;
import domain.User;
import exceptions.DatabaseException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author Veljko
 */
public class LogicUpdateUsers {

    private final static Logger LOGGER = Logger.getLogger(LogicUpdateUsers.class);
    private static DBBroker dBBroker;

    public static void execute(List<User> users) throws DatabaseException {
        try (Connection connection = ConnectionFactory.getInstance().getConnection();) {
            dBBroker = new DBBroker(connection);
            for (User user : users) {
                dBBroker.updateRecord(user);
            }
            dBBroker.commitTransaction();
            LOGGER.info("Succesfully executed LogicUpdateUsers");
        } catch (SQLException ex) {
            LOGGER.error(ex);
            dBBroker.rollbackTransaction();
            throw new DatabaseException();
        }
    }
}
