/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import database.ConnectionFactory;
import domain.User;
import java.util.HashMap;
import java.util.List;
import database.DBBroker;
import exceptions.DatabaseException;
import exceptions.NoSuchUserException;
import java.sql.Connection;
import java.sql.SQLException;
import org.apache.log4j.Logger;

/**
 *
 * @author Veljko
 */
public class LogicLogin {

    private final static Logger LOGGER = Logger.getLogger(LogicLogin.class);
    private static DBBroker dBBroker;

    public static User execute(HashMap<String, String> conditions) throws DatabaseException, NoSuchUserException {
        try (Connection connection = ConnectionFactory.getInstance().getConnection();) {
            dBBroker = new DBBroker(connection);
            User user = new User();
            user.setCondtions(conditions);
            List<User> users = (List<User>) dBBroker.selectRecordWithCondition(user);
            dBBroker.commitTransaction();
            if (users.isEmpty()) {
                LOGGER.info("Deleted user "+user.getUsername()+" is trying to login");
                throw new NoSuchUserException();
            } else {
                LOGGER.info("Succesfully executed LogicLogin");
                return users.get(0);
            }
        } catch (SQLException ex) {
            LOGGER.error(ex.getMessage());
            dBBroker.rollbackTransaction();
            throw new DatabaseException();
        }

    }

}
