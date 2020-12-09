/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import domain.Match;
import domain.Selection;
import exceptions.DatabaseException;
import java.util.LinkedList;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author Veljko
 */
public class LogicGetAllMatches {

    private final static org.apache.log4j.Logger LOGGER = Logger.getLogger(LogicGetAllMatches.class);

    public static List<Match> execute() throws DatabaseException {
        try {
            List<Selection> allSelection = LogicGetAllSelections.execute();
            List<Match> allMatches = new LinkedList<>();
            for (Selection selection : allSelection) {
                List<Match> matchesBySelection = LogicGetMatchesBySelection.execute(selection);
                for (Match match : matchesBySelection) {
                    if (!allMatches.contains(match)) {
                        allMatches.add(match);
                    }
                }
            }
            return allMatches;
        } catch (DatabaseException ex) {
            LOGGER.error(ex);
            throw new DatabaseException();
        }
    }

}
