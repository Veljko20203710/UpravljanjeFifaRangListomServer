/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socketCommunication;

import applicationController.ApplicationController;
import exceptions.BusyUsernameException;
import exceptions.DatabaseException;
import exceptions.ServerException;
import java.util.HashMap;
import transfer.RequestObject;
import transfer.ResponseObject;
import util.ResponseStatus;

/**
 *
 * @author veljko
 */
public class Registration implements Service {

    public String username;
    public String password;

    @Override
    public ResponseObject execute(RequestObject requestObject) {
        HashMap<String, String> conditions
                = (HashMap<String, String>) requestObject.getData();
        ResponseObject responseObject = new ResponseObject();
        try {
            ApplicationController.getInstance().registration(conditions);
        } catch (BusyUsernameException ex) {;
            responseObject.setStatus(ResponseStatus.ERROR);
            responseObject.setErrorMessage("Username is busy.");
            responseObject.setData(ex);
        } catch (DatabaseException ex) {
            responseObject.setStatus(ResponseStatus.ERROR);
            responseObject.setErrorMessage("Server side error.");
            responseObject.setData(new ServerException());
        } 
        return responseObject;
    }

}
