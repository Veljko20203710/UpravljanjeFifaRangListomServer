/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socketCommunication;

import applicationController.ApplicationController;
import domain.Match;
import exceptions.DatabaseException;
import transfer.RequestObject;
import transfer.ResponseObject;
import util.ResponseStatus;

/**
 *
 * @author veljko
 */
public class SaveMatch implements Service {

    @Override
    public ResponseObject execute(RequestObject requestObject) throws Exception {
        ResponseObject responseObject = new ResponseObject();
        Match match = (Match) requestObject.getData();
        try {
            ApplicationController.getInstance().saveMatch(match);
            responseObject.setStatus(ResponseStatus.SUCCESS);
        } catch (DatabaseException ex) {
            responseObject.setStatus(ResponseStatus.ERROR);
            responseObject.setErrorMessage("Server side error.");
            responseObject.setData(new DatabaseException());
        }
        return responseObject;
    }

}
