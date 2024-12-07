/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package g12swe.addressbook.service;

import g12swe.addressbook.models.contacts.Contact;
import g12swe.addressbook.models.AddressBook;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author Valerio
 */
public class provo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException {
        AddressBook ab = new AddressBook();
        ab.addContact(new Contact("Francesco", "Peluso"));
        ab.addContact(new Contact("Gerardo", "Selce"));
        ab.addContact(new Contact("Sharon", "Schiavano"));
        ab.addContact(new Contact("Valerio", "Volzone"));
                
        FileService fs = new FileService(ab);
        fs.saveToFile("uwu");
        AddressBook ab2 = FileService.readFromFile("uwu");
        System.out.println(ab2);
    }
    
}
