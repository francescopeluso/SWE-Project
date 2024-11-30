package g20swe.addressbook.controller;

import g20swe.addressbook.model.AddressBook;
import g20swe.addressbook.model.contacts.Contact;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

public class MainController implements Initializable {

    private AddressBook ab;
    private ObservableList<Contact> observableContacstList;
    
    @FXML
    private Button addContactBtn;
    @FXML
    private ListView<Contact> contactListView;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ab = new AddressBook();
        
        // RIMUOVI APPENA IL PRODOTTO È COMPLETO
        ab.addContact(new Contact("Francesco", "Peluso"));
        ab.addContact(new Contact("Gerardo", "Selce"));
        ab.addContact(new Contact("Sharon", "Schiavano"));
        ab.addContact(new Contact("Valerio", "Volzone"));
        
        observableContacstList = FXCollections.observableArrayList(ab.getContactList());
        contactListView.setItems(observableContacstList);

        contactListView.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Contact contact, boolean empty) {
                super.updateItem(contact, empty);
                if (empty || contact == null) {
                    setText(null);
                } else {
                    setText(contact.getName() + " " + contact.getSurname());
                }
            }
        });
                
    }
    
}
