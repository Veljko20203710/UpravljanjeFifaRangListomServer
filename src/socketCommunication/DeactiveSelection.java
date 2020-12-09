/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socketCommunication;

import applicationController.ApplicationController;
import domain.Selection;
import exceptions.DatabaseException;
import transfer.RequestObject;
import transfer.ResponseObject;
import util.ResponseStatus;

/**
 *
 * @author Veljko
 */
public class DeactiveSelection implements Service {

    @Override
    public ResponseObject execute(RequestObject requestObject) {
        ResponseObject responseObject = new ResponseObject();
        Selection selection = (Selection) requestObject.getData();
        try {
            ApplicationController.getInstance().deactiveSelection(selection);
            responseObject.setStatus(ResponseStatus.SUCCESS);
        } catch (DatabaseException ex) {
            responseObject.setStatus(ResponseStatus.ERROR);
            responseObject.setErrorMessage("Server side error.");
            responseObject.setData(new DatabaseException());
        }
        return responseObject;
    }

}
