/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import database.ConnectionFactory;
import domain.Match;
import database.DBBroker;
import exceptions.DatabaseException;
import java.sql.Connection;
import java.sql.SQLException;
import org.apache.log4j.Logger;

/**
 *
 * @author Veljko
 */
public class LogicSaveMatch {
    
    private final static Logger LOGGER = Logger.getLogger(LogicSaveMatch.class);
    private static DBBroker dBBroker;

    public static void execute(Match match) throws DatabaseException {
        try (Connection connection = ConnectionFactory.getInstance().getConnection();) {
            dBBroker = new DBBroker(connection);;
            dBBroker.insertRecord(match);
            dBBroker.commitTransaction();
            LOGGER.info("Succesfully executed LogicSaveMatch");
        } catch (SQLException ex) {
            LOGGER.error(ex);
            dBBroker.rollbackTransaction();
            throw new DatabaseException();
        }
    }
}

