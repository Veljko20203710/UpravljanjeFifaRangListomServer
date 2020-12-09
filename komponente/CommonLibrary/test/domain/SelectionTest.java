/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import exceptions.IllegalPointsException;
import exceptions.IllegalRankingException;
import exceptions.IllegalSelectionException;
import junit.framework.TestCase;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 *
 * @author Veljko
 *
 */
public class SelectionTest {

    Selection selection;

    @BeforeTest
    public void set_up() throws IllegalSelectionException, IllegalRankingException, IllegalPointsException {
        Confederation confederation = new Confederation(1);
        confederation.setName("EUROPE");
        confederation.setStrenght(1);
        selection = new Selection("Serbia", confederation);
    }

    @Test(expectedExceptions = IllegalSelectionException.class)
    public void createSelectionWithoutName() throws IllegalSelectionException {
        Confederation confederation = new Confederation(1);
        confederation.setName("EUROPE");
        confederation.setStrenght(1);
        selection = new Selection("", confederation);
    }

    @Test(expectedExceptions = IllegalSelectionException.class)
    public void createSelectionWithoutConfederation() throws IllegalSelectionException {
        selection = new Selection("Serbia", null);
    }

    @Test
    public void TestNewSelectionWithParametars() {
        Confederation confederation = new Confederation(1);
        confederation.setName("EUROPE");
        confederation.setStrenght(1);
        TestCase.assertTrue(selection.getName().equals("Serbia") && selection.getConfederation().equals(confederation));
    }

    @Test
    public void TestTwoSelections() throws IllegalSelectionException {
       Confederation confederation = new Confederation(1);
        confederation.setName("EUROPE");
        confederation.setStrenght(1);
        Selection selection2 = new Selection("Serbia", confederation);
        TestCase.assertEquals(selection, selection2);
    }

    @Test(expectedExceptions = IllegalSelectionException.class)
    public void TestNameSetterWithEmptyString() throws IllegalSelectionException {
        selection.setName("");
    }

    @Test(expectedExceptions = IllegalSelectionException.class)
    public void TestConfederationSetterWithNull() throws IllegalSelectionException {
        selection.setConfederation(null);
    }

    @Test(expectedExceptions = IllegalRankingException.class)
    public void TestRangWithNegativeNumber() throws IllegalRankingException {
        selection.setRang(-1);
    }

    @Test()
    public void TestRangWithPositiveNumber() throws IllegalRankingException {
        int rang = 1;
        selection.setRang(rang);
        TestCase.assertEquals(selection.getRang(), rang);
    }

    @Test(expectedExceptions = IllegalPointsException.class)
    public void TestPointsWithNegativeNumber() throws IllegalPointsException {
        selection.setPoints(-1);
    }

    @Test()
    public void TestPointsWithPositiveNumber() throws IllegalPointsException {
        int points = 1;
        selection.setPoints(points);
        TestCase.assertEquals(selection.getPoints(), points);
    }

    @Test()
    public void TestEqualityWithSameSelection() {
        TestCase.assertEquals(selection, selection);
    }

    @Test()
    public void TestEqualityWithSameAtributes() throws IllegalPointsException, IllegalRankingException, IllegalSelectionException {
        selection.setPoints(100);
        selection.setRang(1);

        Selection newSelection = new Selection();
        newSelection.setName("Serbia");
       Confederation confederation = new Confederation(1);
        confederation.setName("EUROPE");
        confederation.setStrenght(1);
        newSelection.setConfederation(confederation);
        newSelection.setPoints(100);
        newSelection.setRang(1);

        TestCase.assertEquals(selection, newSelection);
    }

    @Test()
    public void TestEqualityWithWithDifferentName() throws IllegalPointsException, IllegalRankingException, IllegalSelectionException {
        selection.setPoints(100);
        selection.setRang(1);

        Selection newSelection = new Selection();
        newSelection.setName("Italy");
        Confederation confederation = new Confederation(1);
        confederation.setName("EUROPE");
        confederation.setStrenght(1);
        newSelection.setConfederation(confederation);
        newSelection.setPoints(100);
        newSelection.setRang(1);

        TestCase.assertNotSame(selection, newSelection);
    }

    @Test()
    public void TestEqualityWithWithDifferentConfederation() throws IllegalPointsException, IllegalRankingException, IllegalSelectionException {
        selection.setPoints(100);
        selection.setRang(1);

        Selection newSelection = new Selection();
        newSelection.setName("Serbia");
        Confederation confederation = new Confederation(1);
        confederation.setName("EUROPE");
        confederation.setStrenght(1);
        newSelection.setConfederation(confederation);
        newSelection.setPoints(100);
        newSelection.setRang(1);

        TestCase.assertNotSame(selection, newSelection);
    }

}
