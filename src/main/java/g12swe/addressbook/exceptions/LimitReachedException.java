package g12swe.addressbook.exceptions;


public class LimitReachedException extends Exception {

    /**
     * Creates a new instance of <code>LimitReachedException</code> without
     * detail message.
     */
    public LimitReachedException() {
    }

    /**
     * Constructs an instance of <code>LimitReachedException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public LimitReachedException(String msg) {
        super(msg);
    }
}
