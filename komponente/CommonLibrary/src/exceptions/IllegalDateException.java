package exceptions;

public class IllegalDateException extends Exception {

    
    private String msg;
    
    /**
     * Creates a new instance of <code>IllegalDateException</code> without detail message.
     */
    public IllegalDateException() {
        
    }


    /**
     * Constructs an instance of <code>IllegalDateException</code> with the specified detail
     * message.
     *
     * @param msg the detail message.
     */
    public IllegalDateException(String msg) {
        super(msg);
    }
}
