/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import database.ConnectionFactory;
import domain.Selection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import database.DBBroker;
import domain.Confederation;
import exceptions.DatabaseException;
import exceptions.IllegalSelectionException;
import java.sql.Connection;
import java.sql.SQLException;
import org.apache.log4j.Logger;

/**
 *
 * @author Veljko
 */
public class LogicGetRangList {

    private final static Logger LOGGER = Logger.getLogger(LogicGetRangList.class);
    private static DBBroker dBBroker;

    public static List<Selection> execute() throws DatabaseException {
        try (Connection connection = ConnectionFactory.getInstance().getConnection();) {
            dBBroker = new DBBroker(connection);
            Selection selection = new Selection();
            HashMap<String, String> conditions = new HashMap<>();
            conditions.put("active", "true");
            selection.setCondtions(conditions);
            List<Selection> selections = (List<Selection>) dBBroker.selectRecordWithCondition(selection);
            selections = joinSelectionsAndConfederations(selections, dBBroker);
            selections = sortByPoints(selections);
            dBBroker.commitTransaction();
            LOGGER.info("Succesfully executed LogicGetRangList");
            return selections;
        } catch (SQLException | IllegalSelectionException ex) {
            LOGGER.error(ex);
            dBBroker.rollbackTransaction();
            throw new DatabaseException();
        }
    }

    private static List<Selection> sortByPoints(List<Selection> selections) {
        return selections.stream()
                .sorted(Comparator.comparing(Selection::getPoints).reversed())
                .collect(Collectors.toList());
    }

    private static List<Confederation> getAllConfederations(DBBroker dBBroker) throws SQLException {
        return (List<Confederation>) dBBroker.selectRecord(new Confederation());
    }

    private static List<Selection> joinSelectionsAndConfederations(List<Selection> selections, DBBroker dBBroker) throws IllegalSelectionException, SQLException {
        List<Confederation> confederations = getAllConfederations(dBBroker);
        for (Selection selection : selections) {
            for (Confederation confederation : confederations) {
                if (selection.getConfederation().getId() == confederation.getId()) {
                    selection.setConfederation(confederation);
                }
            }
        }
        return selections;
    }
}
