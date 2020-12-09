package socketCommunication;


import transfer.RequestObject;
import transfer.ResponseObject;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author veljko
 */
public interface Service {
    
    public ResponseObject execute(RequestObject requestObject) throws Exception;
}
