/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi;

import domain.Confederation;
import domain.Match;
import domain.MatchType;
import domain.Selection;
import domain.User;
import java.rmi.Remote;
import java.util.List;

/**
 *
 * @author Veljko
 */
public interface RMISystemOperation extends Remote {

    public void deactiveSelection(Selection selection) throws Exception;

    public void deleteMatch(Match match) throws Exception;

    public void deleteSelection(Selection selection) throws Exception;

    public List<Selection> getAllSelection() throws Exception;

    public List<Match> getAllMatches() throws Exception;
    
    public List<Match> getMatchesBySelection(Selection selection) throws Exception;

    public User login(String username, String password) throws Exception;

    public List<Selection> getRangList() throws Exception;

    public void register(String username, String password) throws Exception;

    public void saveMatch(Match match) throws Exception;

    public void saveSelection(Selection selection) throws Exception;

    public List<Confederation> getAllConfederations() throws Exception;

    public List<MatchType> getAllMatchTypes() throws Exception;

    public void calculateRangList() throws Exception;

    public List<User> getAllUsers() throws Exception;
    
    public void updateSelection(Selection selection, List<Match> matches) throws Exception;

    public void updateUsers(List<User> users) throws Exception;
    
}
