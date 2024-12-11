package g12swe.addressbook.exceptions;

public class AppDirOrFilesException extends RuntimeException {

    /**
     * Creates a new instance of <code>BaseDirectoryException</code>
     * without detail message.
     */
    public AppDirOrFilesException() {
    }

    /**
     * Constructs an instance of <code>BaseDirectoryException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public AppDirOrFilesException(String msg) {
        super(msg);
    }
}
