package g12swe.addressbook.service;

import g12swe.addressbook.models.AddressBook;

/**
 * @file ImportExportService.java
 * @brief Service for importing and exporting contacts in the address book application.
 *
 * This class facilitates the transfer of contact data to and from external sources, 
 * supporting various formats as needed (mainly, the vCard format).
 *
 * Key functionalities include:
 * - Importing individual contacts or entire address books from external files.
 * - Exporting contact data to standard formats for sharing or backup purposes.
 *
 * This service builds on the file operations provided by <code>FileService</code> 
 * and integrates with the application's controllers for seamless user interaction.
 */
public class ImportExportService extends AddressBookService{

    @Override
    public void saveToFile(String filename) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
