/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socketCommunication;

import applicationController.ApplicationController;
import domain.Selection;
import exceptions.DatabaseException;
import exceptions.SavedSelectionException;
import transfer.RequestObject;
import transfer.ResponseObject;
import util.ResponseStatus;

/**
 *
 * @author veljko
 */
public class SaveSelection implements Service {

    @Override
    public ResponseObject execute(RequestObject requestObject) {
        ResponseObject responseObject = new ResponseObject();
        Selection selection = (Selection) requestObject.getData();
        try {
            ApplicationController.getInstance().saveSelection(selection);
            responseObject.setStatus(ResponseStatus.SUCCESS);
        } catch (SavedSelectionException | DatabaseException ex) {
            responseObject.setStatus(ResponseStatus.ERROR);
            responseObject.setData(ex);
        }
        return responseObject;
    }

}
