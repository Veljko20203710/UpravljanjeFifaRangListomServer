/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transfer;

import java.io.Serializable;

/**
 *
 * @author veljko
 */
public class RequestObject implements Serializable{
    private static final long serialVersionUID = 1;
    private String operation;
    private Object data;
    
    public RequestObject() {
        
    }
    
    public  RequestObject(String operation,Object data) {
        this.operation = operation;
        this.data = data;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
    
    
}
