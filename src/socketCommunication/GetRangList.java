/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socketCommunication;

import applicationController.ApplicationController;
import domain.Selection;
import exceptions.DatabaseException;
import java.util.List;
import transfer.RequestObject;
import transfer.ResponseObject;
import util.ResponseStatus;

/**
 *
 * @author veljko
 */
public class GetRangList implements Service {


    @Override
    public ResponseObject execute(RequestObject requestObject) throws Exception {
        ResponseObject responseObject = new ResponseObject();
        try {
            List<Selection> selections = ApplicationController.getInstance().getRangList();
            responseObject.setStatus(ResponseStatus.SUCCESS);
            responseObject.setData(selections);
        } catch (DatabaseException ex) {
            responseObject.setStatus(ResponseStatus.ERROR);
            responseObject.setErrorMessage("Server side error.");
            responseObject.setData(new DatabaseException());
        }
        return responseObject;
    }
}
