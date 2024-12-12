package g12swe.addressbook.controllers;

import g12swe.addressbook.models.AddressBook;
import g12swe.addressbook.models.contacts.Contact;
import g12swe.addressbook.service.ContactFileService;
import g12swe.addressbook.service.FileService;
import g12swe.addressbook.service.ImportExportService;


import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import javafx.collections.ObservableSet;
import javafx.collections.SetChangeListener;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;


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
        observableContactsList = FXCollections.observableArrayList();

        ImportExportService importService = new ImportExportService("/Users/fp/G12-Rubrica/rubrica.vcf", ab.getContactList());
        FileService fileService = new FileService("/Users/fp/G12-RubricaG12-Rubrica-savefile.bin", ab.getContactList());
        
        try {
            ab.initialize(importService.importFromFile());
            observableContactsList.setAll(ab.getContactList());
            contactListView.setItems(observableContactsList);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
           
        ContactFileService cfs = new ContactFileService(fileService, true);
        cfs.setOnSucceeded(event -> {
            
            System.out.println("Contatti importati");
            ObservableSet<Contact> importedContacts = cfs.getValue();
            
            if (importedContacts != null) {
                ab.getContactList().clear();
                observableContactsList.clear();
                ab.getContactList().addAll(importedContacts);
                observableContactsList.addAll(importedContacts);
            }
                
        });

        cfs.setOnFailed(event -> {
            System.out.println("Errore nell'importazione");
        });

        cfs.start(); 
        

        ab.getContactList().addListener((SetChangeListener.Change<? extends Contact> change) -> {
            if (change.wasAdded()) {
                observableContactsList.add(change.getElementAdded());
            }
            if (change.wasRemoved()) {
                observableContactsList.remove(change.getElementRemoved());
            }
            
            contactListView.setItems(observableContactsList);
        });

        // Customize ListView cells to display contact names and surnames
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

        // Add search filter
        searchField.addEventFilter(KeyEvent.KEY_RELEASED, keyEvent -> filterContacts());

        // Add listener for contact selection changes
        contactListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (contactController != null && newValue != null) {
                contactController.loadContactDetails(newValue);
            }
        });

        // Try to load first contact if available
        if (!ab.getContactList().isEmpty() && contactController != null) {
            contactListView.getSelectionModel().selectFirst();
            Contact firstContact = contactListView.getSelectionModel().getSelectedItem();
            if (firstContact != null) {
                contactController.loadContactDetails(firstContact);
            }
        }
    }
    
    /**
     * Associate this controller to the ContactController of the application.
     * @param contactController 
     */
    public void setContactController(ContactController contactController) {
        this.contactController = contactController;
    }

    public AddressBook getAddressBook() {
        return ab;
    }
    
    public void updateListView() {
        List<Contact> sortedList = new ArrayList<>(ab.getContactList());
        Collections.sort(sortedList);
        observableContactsList.setAll(sortedList);
        
        contactListView.setItems(observableContactsList);

        if (contactController != null && contactController.getSelectedContact() != null) {
            for (int i = 0; i < observableContactsList.size(); i++) {
                if (observableContactsList.get(i).equals(contactController.getSelectedContact())) {
                    contactListView.getSelectionModel().select(i);
                    break;
                }
            }
        }
    }
    
    private void workInProgressAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Attenzione");
        alert.setHeaderText("Funzione non disponibile.");
        alert.setContentText("A quanto pare non abbiamo implementato ancora questa funzione.");
        alert.showAndWait();
    }
    
     /**
     * @brief Closes the application.
     *
     * This method exits the application when the exit button is clicked.
     *
     * @param event The action event triggered by the user.
     */

    @FXML
    private void exitProgram(ActionEvent event) throws IOException {
        FileService fileService = new FileService("/Users/fp/G12-Rubrica/G12-Rubrica-savefile.bin", ab.getContactList());
        fileService.exportToFile();
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
        } catch (Exception e) {}
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
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ContactView.fxml"));
            Parent root = loader.load();
            
            ContactController contactController = loader.getController();
            contactController.setMainController(this);
            contactController.prepareForNewContact(); // Call new method
            
            Stage stage = new Stage();
            stage.setTitle("Nuovo Contatto");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(contactListView.getScene().getWindow());
            stage.showAndWait();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addContact(Contact contact) {
        ab.addContact(contact);
        updateListView();
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
        Contact toDelete = contactListView.getSelectionModel().getSelectedItem();
        if (toDelete != null) {
            ab.removeContact(toDelete);
        }
        
        observableContactsList.setAll(ab.getContactList());
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
    
    private void filterContacts() {
        String searchText = searchField.getText().toLowerCase();
        
        ObservableList<Contact> filteredList = FXCollections.observableArrayList();
        for (Contact contact : ab.getContactList()) {
            if ((contact.getName() + " " + contact.getSurname()).toLowerCase().contains(searchText)) {
                filteredList.add(contact);
            }
        }
        
        contactListView.setItems(filteredList);
        contactListView.getSelectionModel().selectFirst();
    }
    
}
