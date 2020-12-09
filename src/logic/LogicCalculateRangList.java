/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import database.ConnectionFactory;
import database.DBBroker;
import domain.Confederation;
import domain.Match;
import domain.MatchType;
import domain.Selection;
import exceptions.DatabaseException;
import exceptions.IllegalMatchStateException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author Veljko
 */
public class LogicCalculateRangList {

    private static final Logger LOGGER = Logger.getLogger(LogicCalculateRangList.class);
    private static DBBroker dBBroker;

    public static void execute() throws DatabaseException {
        try {
            Connection connection = ConnectionFactory.getInstance().getConnection();
            dBBroker = new DBBroker(connection);
            List<Selection> selections = getAllSelections();
            for (Selection selection : selections) {
                List<Match> matches = getAllMatchesSelection(selection);
                joinSelectionAndMatches(selections, matches);
                joinMatchesAndMatchTypes(matches);
                calcutePoints(matches, selection);
            }
            updateSelections(selections);
            dBBroker.commitTransaction();
            LOGGER.info("Succesfully executed LogicCalculateRangList");
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
            throw new DatabaseException();
        }
    }

    private static List<Selection> getAllSelections() throws Exception {
        try {
            List<Selection> selections = (List<Selection>) dBBroker.selectRecord(new Selection());
            List<Confederation> confederations = (List<Confederation>) dBBroker.selectRecord(new Confederation());
            for (Selection selection : selections) {
                for (Confederation confederation : confederations) {
                    if (selection.getConfederation().getId() == confederation.getId()) {
                        selection.getConfederation().setStrenght(confederation.getStrenght());
                    }
                }
            }
            dBBroker.commitTransaction();
            return selections;
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
            throw ex;
        }
    }

    private static List<Match> getAllMatchesSelection(Selection selection) throws Exception {
        List<Match> matches = getHostMatches(selection);
        matches.addAll(getAwayMatches(selection));
        return matches;
    }

    private static List<Match> getHostMatches(Selection selection) throws SQLException {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("host", selection.getId() + "");
        Match match = new Match();
        match.setCondtions(hashMap);
        return ((List<Match>) dBBroker.selectRecordWithCondition(match));
    }

    private static List<Match> getAwayMatches(Selection selection) throws SQLException {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("away", selection.getId() + "");
        Match match = new Match();
        match.setCondtions(hashMap);
        return ((List<Match>) dBBroker.selectRecordWithCondition(match));
    }

    private static void calcutePoints(List<Match> matches, Selection selection) {
        try {
            int points = 0;
            for (Match m : matches) {
                points += checkWinner(m, selection) * checkImportance(m) * checkDate(m)
                        * checkOpponentStrength(m, selection) * checkConfederationStrength(m, selection);
            }
            selection.setPoints(points);
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
    }

    private static double checkWinner(Match match, Selection selection) {
        if (match.getHost().equals(selection)) {
            return chechWinnerHost(match);
        } else {
            return chechWinnerAway(match);
        }
    }

    private static double chechWinnerAway(Match match) {
        if (match.getHostGoals() < match.getAwayGoals()) {
            return MatchConstants.WINNER;
        } else if (match.getHostGoals() == match.getAwayGoals()) {
            return MatchConstants.DRAW;
        } else {
            return MatchConstants.LOSE;
        }
    }

    private static double chechWinnerHost(Match match) {
        if (match.getHostGoals() > match.getAwayGoals()) {
            return MatchConstants.WINNER;
        } else if (match.getHostGoals() == match.getAwayGoals()) {
            return MatchConstants.DRAW;
        } else {
            return MatchConstants.LOSE;
        }
    }

    private static double checkImportance(Match m) {
        return m.getMatchType().getStrenght();
    }

    private static double checkDate(Match m) {
        Date date = new Date();

        if (m.getDate().getYear() == date.getYear()) {
            return MatchConstants.THISYEAR;
        } else if (m.getDate().getYear() + 1 == date.getYear()) {
            return MatchConstants.LASTYEAR;
        } else if (m.getDate().getYear() + 2 == date.getYear()) {
            return MatchConstants.TWOYEARSAGO;
        } else if (m.getDate().getYear() + 3 == date.getYear()) {
            return MatchConstants.THREEYEARSAGO;
        }
        return 0;
    }

    private static double checkOpponentStrength(Match match, Selection selection) {
        return MatchConstants.INITIALOPPONENTSTRENGHT - getOpponent(match, selection).getRang();
    }

    private static double checkConfederationStrength(Match match, Selection selection) {
        return getOpponent(match, selection).getConfederation().getStrenght();
    }

    private static Selection getOpponent(Match match, Selection selection) {
        if (selection.equals(match.getAway())) {
            return match.getHost();
        }
        return match.getAway();
    }

    private static void updateSelections(List<Selection> selections) {
        try {
            int ranking = 1;
            selections.sort(Comparator.comparingDouble(Selection::getPoints).reversed());
            for (Selection selection : selections) {
                selection.setRang(ranking++);
                dBBroker.updateRecord(selection);
            }
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
    }

    private static List<Match> joinSelectionAndMatches(List<Selection> selections, List<Match> matches) throws Exception {
        for (Match match : matches) {
            for (Selection selection : selections) {
                if (match.getHost().getId() == selection.getId()) {
                    match.setHost(selection);
                }
                if (match.getAway().getId() == selection.getId()) {
                    match.setAway(selection);
                }
            }
        }
        return matches;
    }

    private static List<Match> joinMatchesAndMatchTypes(List<Match> matches) throws IllegalMatchStateException, SQLException {
        List<MatchType> matchTypes = getAllMatchTypes();

        for (Match match : matches) {
            for (MatchType matchType : matchTypes) {
                if (matchType.getId() == match.getMatchType().getId()) {
                    match.setMatchType(matchType);
                }
            }
        }
        return matches;
    }

    private static List<MatchType> getAllMatchTypes() throws SQLException {
        return (List<MatchType>) dBBroker.selectRecord(new MatchType());
    }
}
