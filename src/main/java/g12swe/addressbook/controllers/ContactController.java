package g12swe.addressbook.controllers;

import g12swe.addressbook.exceptions.InvalidEmailAddressException;
import g12swe.addressbook.exceptions.InvalidPhoneNumberException;
import g12swe.addressbook.exceptions.MandatoryFieldsException;
import g12swe.addressbook.models.contacts.Contact;
import g12swe.addressbook.models.contacts.EmailAddress;
import g12swe.addressbook.models.contacts.PhoneNumber;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * @file ContactController.java
 * @brief Controller for managing interactions and data display in the <code>ContactView</code>.
 *
 * This class handles the presentation and manipulation of individual contact details 
 * as part of the overall application flow.
 */
public class ContactController {
    
    /**
     * Reference to MainController of this application.
     */
    private MainController mainController;
    
    @FXML
    private Label fullNameLabel;
    @FXML
    private VBox phoneNumbersList;
    @FXML
    private VBox emailAddressesList;
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private Button editOrSaveButton;
    
    private boolean isEditMode = false;
    private Contact selected = null;
    
    /**
     * Associate this controller to the MainController of the application.
     * @param mainController 
     */
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    /**
     * Gets the currently selected contact.
     * @return the selected contact
     */
    public Contact getSelectedContact() {
        return this.selected;
    }

    /**
     * Adds a new phone field with a listener for dynamic management.
     * @param initialValue initial value of the field
     */
    private void addPhoneFieldWithListener(String initialValue) {
        if (phoneNumbersList.getChildren().size() >= 3) {
            return;
        }

        HBox phoneBox = new HBox(5);
        TextField phoneField = new TextField(initialValue);
        phoneField.setPromptText("Numero di telefono");
        Button removeButton = new Button("-");
        removeButton.getStyleClass().add("remove-btn");
        
        phoneField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (phoneBox.equals(phoneNumbersList.getChildren().get(phoneNumbersList.getChildren().size() - 1)) 
                && !newValue.isEmpty() 
                && phoneNumbersList.getChildren().size() < 3) {
                addPhoneFieldWithListener("");
            }
        });

        removeButton.setOnAction(e -> {
            phoneNumbersList.getChildren().remove(phoneBox);
            if (phoneNumbersList.getChildren().isEmpty()) {
                addPhoneFieldWithListener("");
            }
        });

        phoneBox.getChildren().addAll(phoneField, removeButton);
        phoneNumbersList.getChildren().add(phoneBox);
    }

    /**
     * Adds a new email field with a listener for dynamic management.
     * @param initialValue initial value of the field
     */
    private void addEmailFieldWithListener(String initialValue) {

        if (emailAddressesList.getChildren().size() >= 3) {
            return;
        }

        HBox emailBox = new HBox(5);
        TextField emailField = new TextField(initialValue);
        emailField.setPromptText("Indirizzo email");
        Button removeButton = new Button("-");
        removeButton.getStyleClass().add("remove-btn");
        
        emailField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (emailBox.equals(emailAddressesList.getChildren().get(emailAddressesList.getChildren().size() - 1)) 
                && !newValue.isEmpty() 
                && emailAddressesList.getChildren().size() < 3) {
                addEmailFieldWithListener("");
            }
        });

        removeButton.setOnAction(e -> {
            emailAddressesList.getChildren().remove(emailBox);
            if (emailAddressesList.getChildren().isEmpty()) {
                addEmailFieldWithListener("");
            }
        });

        emailBox.getChildren().addAll(emailField, removeButton);
        emailAddressesList.getChildren().add(emailBox);
    }

    /**
     * Loads up the contact details for the selected contact.
     * @param contact the contact to load details for
     */
    public void loadContactDetails(Contact contact) {

        this.selected = contact;

        List<PhoneNumber> contactPhoneNumbers = this.selected.getPhoneNumbers();
        List<EmailAddress> contactEmails = this.selected.getEmailAddresses();
        
        // Clear existing fields
        phoneNumbersList.getChildren().clear();
        emailAddressesList.getChildren().clear();
        
        // Load phone numbers
        for (PhoneNumber phone : contactPhoneNumbers) {
            addPhoneFieldWithListener(phone.getPhoneNumber());
        }
        
        // Load email addresses
        for (EmailAddress email : contactEmails) {
            addEmailFieldWithListener(email.getEmailAddress());
        }
        
        // Add empty fields if needed
        if (contactPhoneNumbers.isEmpty() || 
            !((TextField)((HBox)phoneNumbersList.getChildren().get(
                phoneNumbersList.getChildren().size() - 1)).getChildren().get(0)).getText().isEmpty()) {
            addPhoneFieldWithListener("");
        }
        
        if (contactEmails.isEmpty() || 
            !((TextField)((HBox)emailAddressesList.getChildren().get(
                emailAddressesList.getChildren().size() - 1)).getChildren().get(0)).getText().isEmpty()) {
            addEmailFieldWithListener("");
        }

        firstNameField.setText(this.selected.getName());
        lastNameField.setText(this.selected.getSurname());

        editOrSaveButton.setText("Modifica");
        
        isEditMode = false;
        disableEditMode();
    }
    
    /**
     * Handles the click on edit/save button.
     * @param event the event that triggered the action
     */
    @FXML
    private void handleEditOrSaveAction(ActionEvent event) {
        if (!isEditMode) {
            enableEditMode();
            editOrSaveButton.setText("Salva");
        } else {
            try {
                saveContactChanges();
                disableEditMode();
                editOrSaveButton.setText("Modifica");
            } catch (MandatoryFieldsException ex) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Errore");
                alert.setHeaderText("Errore durante il salvataggio");
                alert.setContentText(ex.getMessage());
                alert.showAndWait();
            }
        }
        isEditMode = !isEditMode;
    }
    
    /**
     * Enables the edit mode for the contact details.
     */
    private void enableEditMode() {
        firstNameField.setEditable(true);
        lastNameField.setEditable(true);
    }
    
    /**
     * Disables the edit mode for the contact details.
     */
    private void disableEditMode() {
        firstNameField.setEditable(false);
        lastNameField.setEditable(false);
    }

    /**
     * Saves the changes made to the contact details.
     * @throws MandatoryFieldsException if not at least one of the name or surname is provided
     */
    private void saveContactChanges() throws MandatoryFieldsException {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        
        if (firstName.length() < 1 && lastName.length() < 1) {
            throw new MandatoryFieldsException("Deve essere inserito almeno uno tra il nome e il cognome.");
        }
        
        // Creiamo una copia del contatto originale
        Contact oldContact = this.selected;
        Contact updatedContact = new Contact(firstName, lastName);
        
        // Copiamo tutti i dati dal contatto originale a quello nuovo
        for (PhoneNumber phone : oldContact.getPhoneNumbers()) {
            try {
                updatedContact.addPhoneNumber(phone.getPhoneNumber(), phone.getCategory());
            } catch (InvalidPhoneNumberException e) {
                // Gestiamo l'eccezione se necessario
            }
        }
        
        for (EmailAddress email : oldContact.getEmailAddresses()) {
            try {
                updatedContact.addEmailAddress(email.getEmailAddress(), email.getCategory());
            } catch (InvalidEmailAddressException e) {
                // Gestiamo l'eccezione se necessario
            }
        }
        
        // Aggiorniamo la rubrica con il nuovo contatto
        mainController.getAddressBook().updateContact(oldContact, updatedContact);
        this.selected = updatedContact;
        
        mainController.updateListView();
    }
}