/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 *
 * @author veljko
 */
public class IllegalSelectionName extends Exception {

    /**
     * Creates a new instance of <code>IllegalSelectionException</code> without
     * detail message.
     */
    public IllegalSelectionName() {
    }

    /**
     * Constructs an instance of <code>IllegalSelectionException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public IllegalSelectionName(String msg) {
        super(msg);
    }
}
