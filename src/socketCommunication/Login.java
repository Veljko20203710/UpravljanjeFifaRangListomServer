/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socketCommunication;

import applicationController.ApplicationController;
import domain.User;
import exceptions.DatabaseException;
import exceptions.NoSuchUserException;
import java.util.HashMap;
import java.util.logging.Level;
import org.apache.log4j.Logger;
import transfer.RequestObject;
import transfer.ResponseObject;
import util.ResponseStatus;

/**
 *
 * @author veljko
 */
public class Login implements Service {

    private final Logger LOGGER = Logger.getLogger(Login.class);

    @Override
    public ResponseObject execute(RequestObject requestObject) {
        ResponseObject responseObject = new ResponseObject();
        try {
            HashMap<String, String> conditions
                    = (HashMap<String, String>) requestObject.getData();
            User user = ApplicationController.getInstance().login(conditions);
            responseObject.setData(user);
            responseObject.setStatus(ResponseStatus.SUCCESS);
        } catch (DatabaseException ex) {
            responseObject.setData(new DatabaseException());
            responseObject.setStatus(ResponseStatus.ERROR);
            LOGGER.error(ex);
        } catch (NoSuchUserException ex) {
            responseObject.setData(ex);
            responseObject.setStatus(ResponseStatus.ERROR);
            LOGGER.error(ex);
        }
        return responseObject;
    }

}
