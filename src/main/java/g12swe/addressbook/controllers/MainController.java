package g12swe.addressbook.controllers;

import g12swe.addressbook.models.AddressBook;
import g12swe.addressbook.models.contacts.Contact;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.application.Platform;
import javafx.scene.control.Alert;

import java.awt.Desktop;
import java.net.URI;


/**
 * @file MainController.java
 * @brief
 * 
 * More detailed information about the file and its role is in the project.
 * 
 */
public class MainController implements Initializable {

    private AddressBook ab;
    private ObservableList<Contact> observableContacstList;
    
    private void workInProgressAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Attenzione");
        alert.setHeaderText("Funzione non disponibile.");
        alert.setContentText("A quanto pare non abbiamo implementato ancora questa funzione.");
        alert.showAndWait();
    }
    
    @FXML
    private Button addContactBtn;
    @FXML
    private ListView<Contact> contactListView;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ab = new AddressBook();
        
        // RIMUOVI APPENA IL PRODOTTO È COMPLETO ---
        ab.addContact(new Contact("Francesco", "Peluso"));
        ab.addContact(new Contact("Gerardo", "Selce"));
        ab.addContact(new Contact("Sharon", "Schiavano"));
        ab.addContact(new Contact("Valerio", "Volzone"));
        // --- FINO A QUI.
        
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

    @FXML
    private void exitProgram(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    private void openDocumentation(ActionEvent event) {
        try {
            Desktop desktop = Desktop.getDesktop();
            if (Desktop.isDesktopSupported() && desktop.isSupported(Desktop.Action.BROWSE)) {
                URI uri = new URI("https://github.com/francescopeluso/SWE-Project/tree/main/docs");
                desktop.browse(uri);
            } else {
                System.err.println("Browsing not supported on this system.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void openRepository(ActionEvent event) {
        try {
            Desktop desktop = Desktop.getDesktop();
            if (Desktop.isDesktopSupported() && desktop.isSupported(Desktop.Action.BROWSE)) {
                URI uri = new URI("https://github.com/francescopeluso/SWE-Project.git");
                desktop.browse(uri);
            } else {
                System.err.println("Browsing not supported on this system.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void importVCard(ActionEvent event) {
        this.workInProgressAlert();
    }

    @FXML
    private void exportVCard(ActionEvent event) {
        this.workInProgressAlert();
    }

    @FXML
    private void insertContact(ActionEvent event) {
        this.workInProgressAlert();
    }

    @FXML
    private void deleteContact(ActionEvent event) {
        this.workInProgressAlert();
    }

    @FXML
    private void reinitializeAddressBook(ActionEvent event) {
        this.workInProgressAlert();
    }
    
}
