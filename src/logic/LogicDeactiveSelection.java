/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import database.ConnectionFactory;
import database.DBBroker;
import domain.Selection;
import exceptions.DatabaseException;
import java.sql.Connection;
import java.sql.SQLException;
import org.apache.log4j.Logger;

/**
 *
 * @author Veljko
 */
public class LogicDeactiveSelection {
    
    private static final Logger LOGGER = Logger.getLogger(LogicDeactiveSelection.class);
    private static DBBroker dBBroker;

    public static void execute(Selection selection) throws DatabaseException {
        try (Connection connection = ConnectionFactory.getInstance().getConnection();) {
            dBBroker = new DBBroker(connection);
            selection.setActive(false);
            dBBroker.updateRecord(selection);
            dBBroker.commitTransaction();
            LOGGER.info("Succesfully executed LogicDeactiveSelection");
        } catch (SQLException ex) {
            LOGGER.error(ex.getMessage());
            dBBroker.rollbackTransaction();
            throw new DatabaseException();
        }
    }
}
