package g12swe.addressbook.controllers;

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
import javafx.collections.SetChangeListener;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;


/**
 * @file MainController.java
 * @brief Controller for managing the main view of the address book application.
 *
 * This controller is responsible for handling the main interface, which displays a list of contacts,
 * and all the buttons and menus that implements part of the software features.
 */
public class MainController implements Initializable {
    
    /**
     * Reference to ContactController of this application.
     */
    private ContactController contactController;
    
    /**
     * Reference to AddressBook which is initialized in <code>initialize()</code>
     */
    private AddressBook ab;
    
    /**
     * This <code>ObservableList</code> is used to synchronize the content
     * of the TableView FXML object with the content of the TreeSed used
     * in the <code>AddressBook</code> to contain all the contacts.
     */
    private ObservableList<Contact> observableContactsList;
    
    @FXML
    private TextField searchField;
    
    @FXML
    private Button addContactBtn;
    
    @FXML
    private ListView<Contact> contactListView;

    /**
     * @brief Overrides the initialize method
     * 
     * Loads up the view and all the bindings needed.
     * 
     * @param url URL object
     * @param rb ResousceBundle object
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ab = new AddressBook();

        // RIMUOVI APPENA IL PRODOTTO È COMPLETO ---
        ab.addContact(new Contact("Francesco", "Peluso"));
        ab.addContact(new Contact("Gerardo", "Selce"));
        ab.addContact(new Contact("Sharon", "Schiavano"));
        ab.addContact(new Contact("Valerio", "Volzone"));

        /* TEST ONLY - DELETE LATER*/
        for (Contact c : ab.getContactList()) {
            try {
                c.addEmailAddress(c.getSurname() + c.getName() + "@g12swe.it", EntryCategory.WORK);
                c.addPhoneNumber("+39 351 123 4567", EntryCategory.WORK);

                if (!c.getName().equals("Valerio"))
                    c.addPhoneNumber("+39 351 765 4321", EntryCategory.WORK);
                if (c.getName().equals("Francesco"))
                    c.addPhoneNumber("+39 392 865 0010", EntryCategory.WORK);
            } catch (InvalidEmailAddressException | InvalidPhoneNumberException ex) {
                ex.printStackTrace();
            }
        }
        // --- FINO A QUI.

        observableContactsList = FXCollections.observableArrayList(ab.getContactList());
        contactListView.setItems(observableContactsList);

        ab.getContactList().addListener((SetChangeListener.Change<? extends Contact> change) -> {
            if (change.wasAdded()) {
                observableContactsList.add(change.getElementAdded());
            }
            if (change.wasRemoved()) {
                observableContactsList.remove(change.getElementRemoved());
            }
        });

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

        // Seleziona il primo contatto per impostazione predefinita
        contactListView.getSelectionModel().selectFirst();

        // Aggiungi il filtro di ricerca
        searchField.addEventFilter(KeyEvent.KEY_RELEASED, keyEvent -> filterContacts());

        // Aggiungi listener per visualizzare i dettagli del contatto selezionato
        contactListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (contactController != null && newValue != null) {
                contactController.loadContactDetails(newValue);
            }
        });
    }
    
    /**
     * Associate this controller to the ContactController of the application.
     * @param contactController 
     */
    public void setContactController(ContactController contactController) {
        this.contactController = contactController;
    }
    
    private void workInProgressAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Attenzione");
        alert.setHeaderText("Funzione non disponibile.");
        alert.setContentText("A quanto pare non abbiamo implementato ancora questa funzione.");
        alert.showAndWait();
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
        Contact toDelete = contactListView.getSelectionModel().getSelectedItem();
        if (toDelete != null) {
            ab.removeContact(toDelete);
            System.out.println(observableContactsList);
            System.out.println(ab.getContactList());
        }
        
        filterContacts();
    }

    @FXML
    private void reinitializeAddressBook(ActionEvent event) {
        this.workInProgressAlert();
    }

    @FXML
    private void viewClickedElement(MouseEvent event) {
        contactListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (contactController != null) {
                contactController.loadContactDetails(newValue);
            }
        });
    }
    
    private void filterContacts() {
        String searchText = searchField.getText().toLowerCase();
        
        ObservableList<Contact> filteredList = FXCollections.observableArrayList();
        for (Contact contact : ab.getContactList()) {
            if ((contact.getName() + " " + contact.getSurname()).toLowerCase().contains(searchText)) {
                filteredList.add(contact);
            }
        }
        
        contactListView.setItems(filteredList);
    }
    
}
