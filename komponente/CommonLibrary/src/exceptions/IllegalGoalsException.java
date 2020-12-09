package exceptions;

public class IllegalGoalsException extends Exception {

    /**
     * Creates a new instance of <code>IllegalGoalsException</code> without detail message.
     */
    public IllegalGoalsException() {
    }

    /**
     * Constructs an instance of <code>IllegalGoalsException</code> with the specified detail
     * message.
     *
     * @param msg the detail message.
     */
    public IllegalGoalsException(String msg) {
        super(msg);
    }
}
