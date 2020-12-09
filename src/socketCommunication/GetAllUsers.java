/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socketCommunication;

import applicationController.ApplicationController;
import domain.User;
import exceptions.DatabaseException;
import java.util.List;
import transfer.RequestObject;
import transfer.ResponseObject;
import util.ResponseStatus;

/**
 *
 * @author Veljko
 */
public class GetAllUsers implements Service {

    @Override
    public ResponseObject execute(RequestObject requestObject) {
        ResponseObject responseObject = new ResponseObject();
        try {
            List<User> users = ApplicationController.getInstance().getAllUsers();
            responseObject.setStatus(ResponseStatus.SUCCESS);
            responseObject.setData(users);
        } catch (DatabaseException ex) {
            responseObject.setStatus(ResponseStatus.ERROR);
            responseObject.setErrorMessage("Server side error.");
            responseObject.setData(new DatabaseException());
        }
        return responseObject;
    }
}
