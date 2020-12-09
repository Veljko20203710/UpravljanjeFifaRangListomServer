/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socketCommunication;

import applicationController.ApplicationController;
import domain.MatchType;
import exceptions.DatabaseException;
import exceptions.ServerException;
import java.util.List;
import transfer.RequestObject;
import transfer.ResponseObject;
import util.ResponseStatus;

/**
 *
 * @author Veljko
 */
public class GetAllMatchTypes implements Service {

    @Override
    public ResponseObject execute(RequestObject requestObject) {
        ResponseObject responseObject = new ResponseObject();
        try {
            List<MatchType> matchTypes = ApplicationController.getInstance().getAllMatchTypes();
            responseObject.setStatus(ResponseStatus.SUCCESS);
            responseObject.setData(matchTypes);
        } catch (DatabaseException ex) {
            responseObject.setStatus(ResponseStatus.ERROR);
            responseObject.setErrorMessage("Server side error.");
            responseObject.setData(new ServerException());
        }
        return responseObject;
    }

}
