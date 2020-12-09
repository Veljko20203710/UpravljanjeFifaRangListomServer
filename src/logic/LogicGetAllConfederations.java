/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import database.ConnectionFactory;
import database.DBBroker;
import domain.Confederation;
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
public class LogicGetAllConfederations {
    
    private final static Logger LOGGER = Logger.getLogger(LogicGetAllConfederations.class);
    private static DBBroker dBBroker;

    public static List<Confederation> execute() throws DatabaseException {
        try(Connection connection = ConnectionFactory.getInstance().getConnection();) {
            dBBroker = new DBBroker(connection);
            List<Confederation> confederations;
            confederations = (List<Confederation>) dBBroker.selectRecord(new Confederation());
            dBBroker.commitTransaction();
            LOGGER.info("Succesfully executed LogicGetAllConfederations");
            return sortByName(confederations);
        } catch (SQLException ex) {
            LOGGER.error(ex);
            dBBroker.rollbackTransaction();
            throw new DatabaseException();
        }
    }

    private static List<Confederation> sortByName(List<Confederation> confederations) {
        return confederations.stream()
                .sorted(Comparator.comparing(Confederation::getName))
                .collect(Collectors.toList());
    }

}
