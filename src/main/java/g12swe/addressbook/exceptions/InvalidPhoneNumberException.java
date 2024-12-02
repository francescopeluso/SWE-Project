package g12swe.addressbook.exceptions;

public class InvalidPhoneNumberException extends Exception {

    /**
     * Creates a new instance of <code>InvalidPhoneNumberException</code>
     * without detail message.
     */
    public InvalidPhoneNumberException() {
    }

    /**
     * Constructs an instance of <code>InvalidPhoneNumberException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public InvalidPhoneNumberException(String msg) {
        super(msg);
    }
}
