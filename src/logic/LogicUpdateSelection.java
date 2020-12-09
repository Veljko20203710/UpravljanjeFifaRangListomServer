/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import database.ConnectionFactory;
import domain.Selection;
import database.DBBroker;
import domain.Match;
import exceptions.DatabaseException;
import java.sql.Connection;
import java.util.LinkedList;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author Veljko
 */
public class LogicUpdateSelection {

    private static List<Match> matchesFromDB = new LinkedList<>();
    private final static Logger LOGGER = Logger.getLogger(LogicUpdateSelection.class);
    private static DBBroker dBBroker;

    public static void execute(Selection selection, List<Match> matches) throws DatabaseException {
        matchesFromDB = LogicGetMatchesBySelection.execute(selection);
        try (Connection connection = ConnectionFactory.getInstance().getConnection();) {
            dBBroker = new DBBroker(connection);
            deleteMatches(matches, dBBroker);
            addMatches(matches, dBBroker);
            dBBroker.updateRecord(selection);
            dBBroker.commitTransaction();
            LOGGER.info("Succesfully executed LogicUpdateSelection");
        } catch (Exception ex) {
            LOGGER.error(ex);
            dBBroker.rollbackTransaction();
            throw new DatabaseException();
        }

    }

    private static void deleteMatches(List<Match> matches, DBBroker dBBroker) {
        for (Match match : matchesFromDB) {
            if (!matches.contains(match)) {
                dBBroker.deleteRecord(match);
            }
        }
    }

    private static void addMatches(List<Match> matches, DBBroker dBBroker) {
        for (Match match : matches) {
            if (!matchesFromDB.contains(match)) {
                dBBroker.insertRecord(match);
            }
        }
    }

}
