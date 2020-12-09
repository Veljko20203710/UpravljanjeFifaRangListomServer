/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;


/**
 *
 * @author Veljko
 */
public class NoSuchUserException extends Exception{
    
    private String msg;
    
    /**
     * Creates a new instance of <code>SQLException</code> without detail message.
     */
    public NoSuchUserException() {
        
    }

    /**
     * Constructs an instance of <code>SQLException</code> with the specified detail
     * message.
     *
     * @param msg the detail message.
     */
    public NoSuchUserException(String msg) {
        super(msg);
    }
    
}
