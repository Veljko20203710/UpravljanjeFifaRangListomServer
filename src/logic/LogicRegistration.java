/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import database.ConnectionFactory;
import domain.User;
import exceptions.BusyUsernameException;
import java.util.HashMap;
import database.DBBroker;
import exceptions.DatabaseException;
import exceptions.IllegalUserState;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author Veljko
 */
public class LogicRegistration {

    private final static Logger LOGGER = Logger.getLogger(LogicRegistration.class);
    private static DBBroker dBBroker;

    public static void execute(HashMap<String, String> conditions) throws BusyUsernameException, DatabaseException {
        try (Connection connection = ConnectionFactory.getInstance().getConnection();) {
            dBBroker = new DBBroker(connection);
            User user = new User();
            user.setCondtions(conditions);
            isUsernameBusy(conditions);
            register(user, conditions, dBBroker);
            dBBroker.commitTransaction();
            LOGGER.info("Succesfully executed LogicRegistration");
        } catch (BusyUsernameException ex) {
            LOGGER.info(ex);
            dBBroker.rollbackTransaction();
            throw ex;
        } catch (Exception ex) {
            LOGGER.error(ex);
            dBBroker.rollbackTransaction();
            throw new DatabaseException();
        }
    }

    private static void isUsernameBusy(HashMap<String,String> conditions) throws BusyUsernameException, SQLException {
        User user = new User();
        String username = conditions.get("username");
        HashMap<String,String> usernameHashMap = new HashMap<>();
        usernameHashMap.put("username",username);
        user.setCondtions(usernameHashMap);
        List<User> users = (List<User>) dBBroker.selectRecordWithCondition(user);
        if (!users.isEmpty()) {
            throw new BusyUsernameException("Username is busy.");
        }
    }

    private static void register(User user, HashMap<String, String> conditions, DBBroker dBBroker) throws IllegalUserState {
        String username = conditions.get("username");
        String password = conditions.get("password");
        user.setUsername(username);
        user.setPassword(password);
        user.setActive(true);
        user.setAdministator(false);
        dBBroker.insertRecord(user);
    }
}
