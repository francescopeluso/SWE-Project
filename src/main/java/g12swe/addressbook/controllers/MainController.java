package g12swe.addressbook.controllers;

import ezvcard.Ezvcard;
import ezvcard.VCard;
import ezvcard.VCardVersion;
import ezvcard.property.StructuredName;
import g12swe.addressbook.exceptions.InvalidEmailAddressException;
import g12swe.addressbook.exceptions.InvalidPhoneNumberException;
import g12swe.addressbook.models.AddressBook;
import g12swe.addressbook.models.contacts.Contact;
import g12swe.addressbook.models.contacts.EntryCategory;

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
 * @brief Controller for managing the main view of the address book application.
 *
 * This controller is responsible for handling the main interface, which displays a list of contacts,
 * and all the buttons and menus that implements part of the software features.
 */
public class MainController implements Initializable {

    /**
     * Reference to AddressBook which is initialized in <code>initialize()</code>
     */
    private AddressBook ab;
    
    /**
     * This <code>ObservableList</code> is used to synchronize the content
     * of the TableView FXML object with the content of the TreeSed used
     * in the <code>AddressBook</code> to contain all the contacts.
     */
    private ObservableList<Contact> observableContacstList;
    
    /**
     * @brief Displays a warning alert for unimplemented features.
     *
     * This method creates and shows an alert dialog informing the user that
     * the selected feature is not yet implemented.
     */
    
    private void workInProgressAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Attenzione");
        alert.setHeaderText("Funzione non disponibile.");
        alert.setContentText("A quanto pare non abbiamo implementato ancora questa funzione.");
        alert.showAndWait();
    }
    
    @FXML
    private Button addContactBtn; ///< Button for adding a new contact.
    @FXML
    private ListView<Contact> contactListView; ///< ListView displaying the contact list.

    /**
     * @brief Initializes the controller and its bindings.
     *
     * This method sets up the initial state of the controller, including loading
     * the AddressBook, populating the UI, and configuring bindings.
     *
     * @param url URL for the root object.
     * @param rb ResourceBundle for localization.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ab = new AddressBook();
        
        // RIMUOVI APPENA IL PRODOTTO È COMPLETO ---
        Contact c1 = new Contact("francesco", "peluso");
        try {
            c1.addEmailAddress("fp@gmail.com", EntryCategory.WORK);
            c1.addPhoneNumber("3281644453", EntryCategory.WORK);
        } catch (InvalidEmailAddressException ex) {
            
        } catch (InvalidPhoneNumberException ex) {
            
        }
        
        ab.addContact(new Contact("Francesco", "Peluso"));
        ab.addContact(new Contact("Gerardo", "Selce"));
        ab.addContact(new Contact("Sharon", "Schiavano"));
        ab.addContact(new Contact("Valerio", "Volzone"));
        // --- FINO A QUI.
        
        
        // PROVA PER VCARD
        /*VCard vcard = new VCard();
        
        StructuredName n = new StructuredName();
        n.setFamily(c1.getSurname());
        n.setGiven(c1.getName());
        
        vcard.setStructuredName(n);
        vcard.addEmail(c1.getEmailAddresses().get(0).getEmailAddress());
        vcard.addTelephoneNumber(c1.getPhoneNumbers().get(0).getPhoneNumber());
        
        String str = Ezvcard.write(vcard).version(VCardVersion.V4_0).go();
        System.out.println(str);*/
        // FINE PROVA PER VCARD
        
        // Create an ObservableList from AddressBook contacts.
        observableContacstList = FXCollections.observableArrayList(ab.getContactList());
        contactListView.setItems(observableContacstList);

        // Customize ListView cells to display contact names and surnames.
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
    
     /**
     * @brief Closes the application.
     *
     * This method exits the application when the exit button is clicked.
     *
     * @param event The action event triggered by the user.
     */

    @FXML
    private void exitProgram(ActionEvent event) {
        Platform.exit();
    }
    
    /**
     * @brief Opens the documentation in the web browser.
     *
     * Navigates to the documentation hosted on GitHub.
     *
     * @param event The action event triggered by the user.
     */

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
    
      /**
     * @brief Opens the GitHub repository in the web browser.
     *
     * Navigates to the repository's URL.
     *
     * @param event The action event triggered by the user.
     */

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

    /**
     * @brief Handles importing contacts from a vCard file.
     *
     * Currently not implemented. Displays a warning alert.
     *
     * @param event The action event triggered by the user.
     */
    @FXML
    private void importVCard(ActionEvent event) {
        this.workInProgressAlert();
    }
    
    /**
     * @brief Handles exporting contacts from a vCard file.
     *
     * Currently not implemented. Displays a warning alert.
     *
     * @param event The action event triggered by the user.
     */

    @FXML
    private void exportVCard(ActionEvent event) {
        this.workInProgressAlert();
    }
    /**
     * @brief Handles inserting a new contact.
     *
     * Currently not implemented. Displays a warning alert.
     *
     * @param event The action event triggered by the user.
     */
    @FXML
    private void insertContact(ActionEvent event) {
        this.workInProgressAlert();
    }
    
    /**
     * @brief Handles deleting a contact.
     *
     * Currently not implemented. Displays a warning alert.
     *
     * @param event The action event triggered by the user.
     */

    @FXML
    private void deleteContact(ActionEvent event) {
        this.workInProgressAlert();
    }
    
    
    /**
     * @brief Resets the AddressBook to its initial state.
     *
     * Currently not implemented. Displays a warning alert.
     *
     * @param event The action event triggered by the user.
     */

    @FXML
    private void reinitializeAddressBook(ActionEvent event) {
        this.workInProgressAlert();
    }
    
}
