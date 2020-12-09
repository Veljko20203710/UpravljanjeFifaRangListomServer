/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import database.ConnectionFactory;
import domain.Match;
import domain.Selection;
import java.util.HashMap;
import java.util.List;
import database.DBBroker;
import exceptions.DatabaseException;
import java.sql.Connection;
import org.apache.log4j.Logger;

/**
 *
 * @author Veljko
 */
public class LogicDeleteSelection {
    
    private final static Logger LOGGER = Logger.getLogger(LogicDeleteSelection.class);
    private static DBBroker dBBroker;

    public static void execute(Selection selection) throws DatabaseException {
        try(Connection connection = ConnectionFactory.getInstance().getConnection();) {
            dBBroker = new DBBroker(connection);
            List<Match> matches = (List<Match>) getHostMatches(selection, dBBroker);
            matches.addAll(getAwayMatches(selection, dBBroker));
            for (Match match : matches) {
                dBBroker.deleteRecord(match);
            }
            dBBroker.deleteRecord(selection);
            dBBroker.commitTransaction();
            LOGGER.info("Succesfully executed LogicDeleteSelection");
        } catch (Exception ex) {
            LOGGER.error(ex);
            dBBroker.rollbackTransaction();
            throw new DatabaseException();
        }
    }

    private static List<Match> getHostMatches(Selection selection, DBBroker dBBroker) throws Exception {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("host", selection.getId() + "");
        Match match = new Match();
        match.setCondtions(hashMap);
        return (List<Match>) dBBroker.selectRecordWithCondition(match);
    }

    private static List<Match> getAwayMatches(Selection selection, DBBroker dBBroker) throws Exception {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("away", selection.getId() + "");
        Match match = new Match();
        match.setCondtions(hashMap);
        return (List<Match>) dBBroker.selectRecordWithCondition(match);
    }
}
