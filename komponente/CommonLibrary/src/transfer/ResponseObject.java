/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transfer;

import java.io.Serializable;
import util.ResponseStatus;

/**
 *
 * @author veljko
 */
public class ResponseObject implements Serializable{
    
    private static final long serialVersionUID = 123;
    private ResponseStatus status;
    private Object data;
    private String errorMessage;

    public ResponseObject() {
    }

    public ResponseObject(ResponseStatus status, Object data, String errorMessage) {
        this.status = status;
        this.data = data;
        this.errorMessage = errorMessage;
    }
    
    public ResponseStatus getStatus() {
        return status;
    }

    public void setStatus(ResponseStatus status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    
    
}
