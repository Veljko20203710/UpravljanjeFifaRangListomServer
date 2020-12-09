/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applicationController;

import domain.Confederation;
import domain.Match;
import domain.MatchType;
import domain.Selection;
import domain.User;
import exceptions.BusyUsernameException;
import exceptions.DatabaseException;
import exceptions.NoSuchUserException;
import exceptions.SavedSelectionException;
import java.util.HashMap;
import java.util.List;
import logic.LogicCalculateRangList;
import logic.LogicDatabaseProperties;
import logic.LogicDeactiveSelection;
import logic.LogicDeleteMatch;
import logic.LogicDeleteSelection;
import logic.LogicGetAllConfederations;
import logic.LogicGetAllMatchTypes;
import logic.LogicGetAllMatches;
import logic.LogicGetAllSelections;
import logic.LogicGetAllUsers;
import logic.LogicGetMatchesBySelection;
import logic.LogicGetRangList;
import logic.LogicLogin;
import logic.LogicRegistration;
import logic.LogicSaveMatch;
import logic.LogicSaveSelection;
import logic.LogicUpdateSelection;
import logic.LogicUpdateUsers;
import org.apache.log4j.Logger;

/**
 *
 * @author Veljko
 */
public class ApplicationController {

    private final static Logger LOGGER = Logger.getLogger(ApplicationController.class);
    private static ApplicationController instance;

    private ApplicationController() {
    }

    public static ApplicationController getInstance() {
        if (instance == null) {
            instance = new ApplicationController();
            LOGGER.info("Application Controller created.");
        }
        return instance;
    }

    public List<Confederation> getAllConfederations() throws DatabaseException {
        return LogicGetAllConfederations.execute();
    }

    public List<MatchType> getAllMatchTypes() throws DatabaseException {
        return LogicGetAllMatchTypes.execute();
    }

    public List<User> getAllUsers() throws DatabaseException {
        return LogicGetAllUsers.execute();
    }

    public void calculateRangList() throws DatabaseException {
        LogicCalculateRangList.execute();
    }

    public void deactiveSelection(Selection selection) throws DatabaseException {
        LogicDeactiveSelection.execute(selection);
    }

    public void deleteMatch(Match match) throws DatabaseException {
        LogicDeleteMatch.execute(match);
    }

    public void saveMatch(Match match) throws DatabaseException {
        LogicSaveMatch.execute(match);
    }

    public void updateUsers(List<User> users) throws DatabaseException {
        LogicUpdateUsers.execute(users);
    }

    public void updateSelection(Selection selection, List<Match> matches) throws DatabaseException {
        LogicUpdateSelection.execute(selection, matches);
    }

    public void saveSelection(Selection selection) throws DatabaseException, SavedSelectionException {
        LogicSaveSelection.execute(selection);
    }

    public void deleteSelection(Selection selection) throws DatabaseException {
        LogicDeleteSelection.execute(selection);
    }

    public List<Match> getAllMatches() throws DatabaseException {
        return LogicGetAllMatches.execute();
    }

    public List<Match> getMatchesBySelection(Selection selection) throws DatabaseException {
        return LogicGetMatchesBySelection.execute(selection);
    }

    public List<Selection> getRangList() throws DatabaseException {
        return LogicGetRangList.execute();
    }

    public List<Selection> getAllSelections() throws DatabaseException {
        return LogicGetAllSelections.execute();
    }

    public User login(HashMap<String, String> conditions) throws DatabaseException, NoSuchUserException {
        return LogicLogin.execute(conditions);
    }

    public void registration(HashMap<String, String> conditions) throws DatabaseException, BusyUsernameException {
        LogicRegistration.execute(conditions);
    }

    public void writeUrl(String url) {
        LogicDatabaseProperties.writeUrl(url);
    }

    public void writeUsername(String username) {
        LogicDatabaseProperties.writeUsername(username);
    }

    public void writePassword(String password) {
        LogicDatabaseProperties.writePassword(password);
    }

    public String readUrl() {
        return LogicDatabaseProperties.readUrl();
    }

    public String readPassword() {
        return LogicDatabaseProperties.readPassword();
    }

    public String readUsername() {
        return LogicDatabaseProperties.readUsername();
    }

}
