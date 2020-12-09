/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import database.ConnectionFactory;
import domain.Match;
import domain.Selection;
import exceptions.IllegalMatchStateException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import database.DBBroker;
import domain.MatchType;
import exceptions.DatabaseException;
import java.sql.Connection;
import java.sql.SQLException;
import org.apache.log4j.Logger;

/**
 *
 * @author Veljko
 */
public class LogicGetMatchesBySelection {

    private final static Logger LOGGER = Logger.getLogger(LogicGetMatchesBySelection.class);
    private static DBBroker dBBroker;

    public static List<Match> execute(Selection selection) throws DatabaseException {
        try (Connection connection = ConnectionFactory.getInstance().getConnection();) {
            dBBroker = new DBBroker(connection);
            List<Match> matches = new LinkedList<>();
            matches.addAll(getHostMatches(selection, dBBroker));
            matches.addAll(getAwayMatches(selection, dBBroker));

            matches = joinSelectionAndMatches(matches, dBBroker);
            matches = joinMatchesAndMatchTypes(matches, dBBroker);

            dBBroker.commitTransaction();
            LOGGER.info("Succesfully executed LogicGetMatchesBySelection");
            return sortMatchesByDate(matches, dBBroker);
        } catch (SQLException | IllegalMatchStateException ex) {
            LOGGER.error(ex);
            dBBroker.rollbackTransaction();
            throw new DatabaseException();
        }
    }

    private static List<Match> getHostMatches(Selection selection, DBBroker dBBroker) throws SQLException {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("host", selection.getId() + "");
        Match match = new Match();
        match.setCondtions(hashMap);
        return ((List<Match>) dBBroker.selectRecordWithCondition(match));
    }

    private static List<Match> getAwayMatches(Selection selection, DBBroker dBBroker) throws SQLException {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("away", selection.getId() + "");
        Match match = new Match();
        match.setCondtions(hashMap);
        return ((List<Match>) dBBroker.selectRecordWithCondition(match));
    }

    private static List<Selection> getAllSelections(DBBroker dBBroker) throws SQLException {
        return (List<Selection>) dBBroker.selectRecord(new Selection());
    }

    private static List<MatchType> getAllMatchTypes(DBBroker dBBroker) throws SQLException {
        return (List<MatchType>) dBBroker.selectRecord(new MatchType());
    }

    private static List<Match> sortMatchesByDate(List<Match> matches, DBBroker dBBroker) {
        return matches.stream()
                .sorted(Comparator.comparing(Match::getDate).reversed())
                .collect(Collectors.toList());

    }

    private static List<Match> joinSelectionAndMatches(List<Match> matches, DBBroker dBBroker) throws IllegalMatchStateException, SQLException {
        List<Selection> selections = getAllSelections(dBBroker);

        for (Match match : matches) {
            for (Selection selection : selections) {
                if (match.getHost().getId()
                        == selection.getId()) {
                    match.setHost(selection);
                }
                if (match.getAway().getId()
                        == selection.getId()) {
                    match.setAway(selection);
                }
            }
        }
        return matches;
    }

    private static List<Match> joinMatchesAndMatchTypes(List<Match> matches, DBBroker dBBroker) throws IllegalMatchStateException, SQLException {
        List<MatchType> matchTypes = getAllMatchTypes(dBBroker);

        for (Match match : matches) {
            for (MatchType matchType : matchTypes) {
                if (matchType.getId() == match.getMatchType().getId()) {
                    match.setMatchType(matchType);
                }
            }
        }
        return matches;
    }
}
