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
public class IllegalSelectionException extends Exception {

    /**
     * Creates a new instance of <code>IllegalSelectionException</code> without
     * detail message.
     */
    public IllegalSelectionException() {
    }

    /**
     * Constructs an instance of <code>IllegalSelectionException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public IllegalSelectionException(String msg) {
        super(msg);
    }
}
