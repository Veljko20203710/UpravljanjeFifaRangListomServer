package exceptions;

public class IllegalMatchStateException extends Exception {

    /**
     * Creates a new instance of <code>IllegalMatchStateException</code> without detail message.
     */
    public IllegalMatchStateException() {
    }

    /**
     * Constructs an instance of <code>IllegalMatchStateException</code> with the specified detail
     * message.
     *
     * @param msg the detail message.
     */
    public IllegalMatchStateException(String msg) {
        super(msg);
    }

}
