/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import database.ConnectionFactory;
import database.DBBroker;
import domain.MatchType;
import exceptions.DatabaseException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.log4j.Logger;

/**
 *
 * @author Veljko
 */
public class LogicGetAllMatchTypes {
    
    private final static Logger LOGGER = Logger.getLogger(LogicGetAllMatchTypes.class);
    private static DBBroker dBBroker;

    public static List<MatchType> execute() throws DatabaseException {
        try(Connection connection = ConnectionFactory.getInstance().getConnection();) {
            dBBroker = new DBBroker(connection);
            List<MatchType> matchTypes = (List<MatchType>) dBBroker.selectRecord(new MatchType());
            dBBroker.commitTransaction();
            LOGGER.info("Succesfully executed LogicGetAllMatchTypes");
            return sortMatchTypesByName(matchTypes);
        } catch (SQLException ex) {
            LOGGER.error(ex);
            dBBroker.rollbackTransaction();
            throw new DatabaseException();
        }
    }

    private static List<MatchType> sortMatchTypesByName(List<MatchType> matchesTypes) {
        return matchesTypes.stream()
                .sorted(Comparator.comparing(MatchType::getName))
                .collect(Collectors.toList());
    }
}
