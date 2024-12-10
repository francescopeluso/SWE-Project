/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package g12swe.addressbook.exceptions;

/**
 *
 * @author fp
 */
public class MandatoryFieldsException extends Exception {

    /**
     * Creates a new instance of <code>MandatoryFieldsException</code> without
     * detail message.
     */
    public MandatoryFieldsException() {
    }

    /**
     * Constructs an instance of <code>MandatoryFieldsException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public MandatoryFieldsException(String msg) {
        super(msg);
    }
}
