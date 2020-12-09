/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import database.ConnectionFactory;
import domain.Selection;
import database.DBBroker;
import exceptions.DatabaseException;
import exceptions.SavedSelectionException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author Veljko
 */
public class LogicSaveSelection {

    private final static Logger LOGGER = Logger.getLogger(LogicSaveSelection.class);
    private static DBBroker dBBroker;

    public static void execute(Selection selection) throws DatabaseException, SavedSelectionException {
        try (Connection connection = ConnectionFactory.getInstance().getConnection();) {
            dBBroker = new DBBroker(connection);
            selection.setPoints(0);
            selection.setActive(true);
            selection.setRang(getSelectionSize(dBBroker));
            isSelectionAlreadySaved(dBBroker, selection);
            dBBroker.insertRecord(selection);
            dBBroker.commitTransaction();
            LOGGER.info("Succesfully executed LogicSaveSelection");
        } catch (SavedSelectionException ex) {
            LOGGER.error(ex);
            dBBroker.rollbackTransaction();
            throw new SavedSelectionException();
        } catch (Exception ex) {
            LOGGER.error(ex);
            dBBroker.rollbackTransaction();
            throw new DatabaseException();
        }
    }
    
    

    private static int getSelectionSize(DBBroker dBBroker) throws SQLException {
        return ((List<Selection>) dBBroker.selectRecord(new Selection())).size();
    }

    private static void isSelectionAlreadySaved(DBBroker dBBroker, Selection selection) throws SQLException, SavedSelectionException {
        List<Selection> selections = (List<Selection>) dBBroker.selectRecord(selection);
        for (Selection selectionInDB : selections) {
            if (selectionInDB.getName().equals(selection.getName())) {
                throw new SavedSelectionException();
            }
        }
    }
}
