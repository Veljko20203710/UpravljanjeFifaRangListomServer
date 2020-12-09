/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socketCommunication;

import applicationController.ApplicationController;
import domain.Selection;
import transfer.RequestObject;
import transfer.ResponseObject;

/**
 *
 * @author Veljko
 */
public class GetMatchesBySelection implements Service {

    @Override
    public ResponseObject execute(RequestObject requestObject) throws Exception {
        Selection selection = (Selection) requestObject.getData();
        ResponseObject responseObject = new ResponseObject();
        responseObject.setData(ApplicationController.getInstance().getMatchesBySelection(selection));
        return responseObject;
    }
}
