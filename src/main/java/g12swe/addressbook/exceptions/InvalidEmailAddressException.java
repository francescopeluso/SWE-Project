package g12swe.addressbook.exceptions;

public class InvalidEmailAddressException extends Exception {

    /**
     * Creates a new instance of <code>InvalidEmailAddressException</code>
     * without detail message.
     */
    public InvalidEmailAddressException() {
    }

    /**
     * Constructs an instance of <code>InvalidEmailAddressException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public InvalidEmailAddressException(String msg) {
        super(msg);
    }
}
