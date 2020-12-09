/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socketCommunication;

import applicationController.ApplicationController;
import domain.Match;
import domain.Selection;
import exceptions.DatabaseException;
import java.util.HashMap;
import java.util.List;
import transfer.RequestObject;
import transfer.ResponseObject;
import util.ResponseStatus;

/**
 *
 * @author Veljko
 */
public class UpdateSelection implements Service {

    @Override
    public ResponseObject execute(RequestObject requestObject) throws Exception {
        HashMap<String, Object> data = (HashMap<String, Object>) requestObject.getData();
        Selection selection = (Selection) data.get("selection");
        List<Match> matches = (List<Match>) data.get("matches");
        ResponseObject responseObject = new ResponseObject();
        try {
            ApplicationController.getInstance().updateSelection(selection,matches);
            responseObject.setStatus(ResponseStatus.SUCCESS);
        } catch (DatabaseException ex) {
            responseObject.setStatus(ResponseStatus.ERROR);
            responseObject.setErrorMessage("Server side error.");
            responseObject.setData(new DatabaseException());
        }
        return responseObject;
    }
}
