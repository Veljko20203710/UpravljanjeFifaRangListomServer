/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmiCommunication;

import applicationController.ApplicationController;
import domain.Confederation;
import domain.Match;
import domain.MatchType;
import domain.Selection;
import domain.User;
import exceptions.DatabaseException;
import exceptions.ServerException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.List;
import org.apache.log4j.Logger;
import rmi.RMISystemOperation;

/**
 *
 * @author Veljko
 */
public class RMIServer extends UnicastRemoteObject implements RMISystemOperation {

    private final Logger LOGGER = org.apache.log4j.Logger.getLogger(RMIServer.class);

    public RMIServer() throws RemoteException {
        super();
    }

    @Override
    public void deactiveSelection(Selection selection) throws Exception {
        try {
            ApplicationController.getInstance().deleteSelection(selection);
        } catch (DatabaseException ex) {
            LOGGER.error("RMI:" + ex);
            throw new ServerException();
        }
    }

    @Override
    public void deleteMatch(Match match) throws Exception {
        try {
            ApplicationController.getInstance().deleteMatch(match);
        } catch (DatabaseException ex) {
            LOGGER.error("RMI:" + ex);
            throw new ServerException();
        }
    }

    @Override
    public void deleteSelection(Selection selection) throws Exception {
        try {
            ApplicationController.getInstance().deleteSelection(selection);
        } catch (DatabaseException ex) {
            LOGGER.error("RMI:" + ex);
            throw new ServerException();
        }
    }

    @Override
    public List<Selection> getAllSelection() throws Exception {
        try {
            return ApplicationController.getInstance().getAllSelections();
        } catch (DatabaseException ex) {
            LOGGER.error("RMI:" + ex);
            throw new ServerException();
        }
    }

    @Override
    public List<Match> getAllMatches() throws Exception {
        try {
            return ApplicationController.getInstance().getAllMatches();
        } catch (DatabaseException ex) {
            LOGGER.error("RMI:" + ex);
            throw new ServerException();
        }
    }
    
  
    @Override
    public List<Match> getMatchesBySelection(Selection selection) throws Exception {
        try {
            return ApplicationController.getInstance().getMatchesBySelection(selection);
        } catch (DatabaseException ex) {
            LOGGER.error("RMI:" + ex);
            throw new ServerException();
        }
    }

    @Override
    public List<Selection> getRangList() throws Exception {
        try {
            return ApplicationController.getInstance().getRangList();
        } catch (DatabaseException ex) {
            LOGGER.error("RMI:" + ex);
            throw new ServerException();
        }
    }

    @Override
    public User login(String username, String password) throws Exception {
        try {
            HashMap<String, String> conditions = new HashMap<>();
            conditions.put("username", username);
            conditions.put("password", password);
            return ApplicationController.getInstance().login(conditions);
        } catch (DatabaseException ex) {
            LOGGER.error("RMI:" + ex);
            throw new ServerException();
        }
    }

    

    @Override 
    public void saveSelection(Selection selection) throws Exception {
        try {
            ApplicationController.getInstance().saveSelection(selection);
        } catch (DatabaseException ex) {
            LOGGER.error("RMI:" + ex);
            throw new ServerException();
        }
    }

    @Override
    public void saveMatch(Match match) throws Exception {
        try {
            ApplicationController.getInstance().saveMatch(match);
        } catch (DatabaseException ex) {
            LOGGER.error("RMI:" + ex);
            throw new ServerException();
        }
    }

    @Override
    public void updateSelection(Selection selection, List<Match> matches) throws Exception {
        try {
            ApplicationController.getInstance().updateSelection(selection, matches);
        } catch (DatabaseException ex) {
            LOGGER.error("RMI:" + ex);
            throw new ServerException();
        }
    }

    @Override
    public List<Confederation> getAllConfederations() throws Exception {
        try {
            return ApplicationController.getInstance().getAllConfederations();
        } catch (DatabaseException ex) {
            LOGGER.error("RMI:" + ex);
            throw new ServerException();
        }
    }

    @Override
    public List<MatchType> getAllMatchTypes() throws Exception {
        try {
            return ApplicationController.getInstance().getAllMatchTypes();
        } catch (DatabaseException ex) {
            LOGGER.error("RMI:" + ex);
            throw new ServerException();
        }
    }

    @Override
    public void calculateRangList() throws Exception {
        try {
            ApplicationController.getInstance().calculateRangList();
        } catch (DatabaseException ex) {
            LOGGER.error("RMI:" + ex);
            throw new ServerException();
        }
    }

    @Override
    public List<User> getAllUsers() throws Exception {
        try {
            return ApplicationController.getInstance().getAllUsers();
        } catch (DatabaseException ex) {
            LOGGER.error("RMI:" + ex);
            throw new ServerException();
        }
    }

    @Override
    public void updateUsers(List<User> users) throws Exception {
        try {
            ApplicationController.getInstance().updateUsers(users);
        } catch (DatabaseException ex) {
            LOGGER.error("RMI:" + ex);
            throw new ServerException();
        }
    }

    @Override
    public void register(String username, String password) throws Exception {
         try {
            HashMap<String, String> conditions = new HashMap<>();
            conditions.put("username", username);
            conditions.put("password", password);
            ApplicationController.getInstance().registration(conditions);
        } catch (DatabaseException ex) {
            LOGGER.error("RMI:"+ex);
            throw new ServerException();
        }
    }
}
