/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import exceptions.IllegalDateException;
import exceptions.IllegalGoalsException;
import exceptions.IllegalMatchStateException;
import exceptions.IllegalSelectionException;
import java.util.Date;
import junit.framework.TestCase;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 *
 * @author Veljko
 */
public class MatchTest {

    Selection testHost;
    Selection testAway;
    Match match;

    @BeforeTest
    void setUpClasses() throws IllegalSelectionException {
        Confederation confederation = new Confederation();
        confederation.setName("EUROPE");
        testHost = new Selection("Serbia", confederation);
        testAway = new Selection("Italy", confederation);
        match = new Match();
    }

    @Test(expectedExceptions = IllegalMatchStateException.class)
    public void emptyHost() throws IllegalMatchStateException {
        match.setHost(null);
    }

    @Test(expectedExceptions = IllegalMatchStateException.class)
    public void emptyAway() throws IllegalMatchStateException {
        match.setHost(null);
    }

    @Test(expectedExceptions = IllegalGoalsException.class)
    public void testHostGoalsNegative() throws IllegalGoalsException {
        match.setHostGoals(-1);
    }

    @Test(expectedExceptions = IllegalGoalsException.class)
    public void testAwayGoalsNegative() throws IllegalGoalsException {
        match.setAwayGoals(-1);
    }

    @Test(expectedExceptions = IllegalDateException.class)
    public void testDateInFuture() throws IllegalDateException {
        Date date = new Date();
        date.setYear(121);
        match.setDate(date);
    }

    @Test(expectedExceptions = IllegalDateException.class)
    public void testDateEmpty() throws IllegalDateException {
        match.setDate(null);
    }

    @Test(expectedExceptions = IllegalMatchStateException.class)
    public void nullMatchType() throws IllegalMatchStateException {
        match.setMatchType(null);
    }

    @Test
    public void testMatchesWithParameters() throws Exception {
        Date date = new Date();
        date.setYear(119);
        int awayGoals = 1;
        int hostGoals = 2;

        match.setHost(testHost);
        match.setAway(testAway);
        match.setDate(date);
        match.setAwayGoals(awayGoals);
        match.setHostGoals(hostGoals);
        MatchType matchType = new MatchType();
        matchType.setName("WORLD CUP");
        match.setMatchType(matchType);

        TestCase.assertTrue(match.getAway().equals(testAway) && match.getHost().equals(testHost)
                && match.getMatchType() == matchType && match.getDate().equals(date) && match.getAwayGoals() == awayGoals
                && match.getHostGoals() == hostGoals);
    }

    @Test
    public void testTwoMatches() throws Exception {
        Date date = new Date();
        date.setYear(119);
        int awayGoals = 1;
        int hostGoals = 2;
        User user = new User(1, "Veljko");

        match.setHost(testHost);
        match.setAway(testAway);
        match.setDate(date);
        match.setAwayGoals(awayGoals);
        match.setHostGoals(hostGoals);
        MatchType matchType = new MatchType();
        matchType.setName("WORLD CUP");
        match.setMatchType(matchType);
        match.setUser(user);

        Match match2 = new Match();
        match2.setHost(testHost);
        match2.setAway(testAway);
        match2.setDate(date);
        match2.setAwayGoals(awayGoals);
        match2.setHostGoals(hostGoals);
        match2.setMatchType(matchType);
        match2.setUser(user);

        TestCase.assertEquals(match2, match);
    }
}
