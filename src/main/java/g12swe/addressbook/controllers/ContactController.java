package g12swe.addressbook.controllers;

import g12swe.addressbook.exceptions.InvalidEmailAddressException;
import g12swe.addressbook.exceptions.InvalidPhoneNumberException;
import g12swe.addressbook.exceptions.LimitReachedException;
import g12swe.addressbook.exceptions.MandatoryFieldsException;
import g12swe.addressbook.models.contacts.Contact;
import g12swe.addressbook.models.contacts.EmailAddress;
import g12swe.addressbook.models.contacts.ExtendedContact;
import g12swe.addressbook.models.contacts.PhoneNumber;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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
    
    
    /**
     * @brief VBox containing the list of phone numbers.
     */
    @FXML
    private VBox phoneNumbersList;
    
    /**
     * @brief VBox containing the list of email addresses.
     */
    @FXML
    private VBox emailAddressesList;
    
    /**
     * @brief TextField for the contact's first name.
     */
    @FXML
    private TextField firstNameField;
    
    /**
     * @brief TextField for the contact's last name.
     */
    @FXML
    private TextField lastNameField;
    
    /**
     * @brief Button for editing or saving the contact's details.
     */
    @FXML
    private Button editOrSaveButton;
    
     /**
     * @brief TextArea for the contact's address.
     */
    @FXML
    private TextArea addressField;
    
    /**
     * @brief TextField for the contact's preferred pronouns.
     */
    @FXML
    private TextField pronounsField;
    
    /**
     * @brief DatePicker for the contact's birthday.
     */
    @FXML
    private DatePicker birthdayPicker;
    
    /**
     * @brief TextArea for inserting notes about the contact.
     */
    @FXML
    private TextArea notesArea;

    @FXML
    private HBox phoneNumbersContainer; //< HBox containing the phone numbers list
    
    @FXML
    private HBox emailAddressesContainer; //< HBox containing the email addresses list
    
    @FXML
    private HBox addressContainer; //< HBox containing the address field
    
    @FXML
    private HBox birthdayContainer; //< HBox containing the birthday picker
    
    @FXML
    private HBox notesContainer; //< HBox containing the notes area
    
    @FXML
    private HBox pronounsContainer; //< HBox containing the pronouns field

    /**
     * @brief Flag indicating if the edit mode is enabled.
     */
    private boolean isEditMode = false;
    
    /**
     * @brief Currently selected contact.
     */
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
     * @brief Prepares the view for adding a new contact.
     *
     * Clears all fields, enables edit mode, and sets up the UI for adding a new contact.
     */
    public void prepareForNewContact() {
        this.selected = null;
        isEditMode = true;
        
        firstNameField.clear();
        lastNameField.clear();
        phoneNumbersList.getChildren().clear();
        emailAddressesList.getChildren().clear();
        addressField.clear();
        pronounsField.clear();
        birthdayPicker.setValue(null);
        notesArea.clear();
        
        addPhoneFieldWithListener("");
        addEmailFieldWithListener("");
        
        firstNameField.setEditable(true);
        lastNameField.setEditable(true);
        
        editOrSaveButton.setText("Aggiungi");
        
        editOrSaveButton.setOnAction(event -> {
            try {
                handleNewContactSave();
            } catch (LimitReachedException ex) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Errore");
                alert.setHeaderText("Errore durante il salvataggio");
                alert.setContentText(ex.getMessage());
                alert.showAndWait();
            }
        });
    }
    
    /**
     * @brief Handles saving a new contact.
     *
     * Validates input fields and adds the new contact to the address book.
     */

    private void handleNewContactSave() throws LimitReachedException {
        try {
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            
            if (firstName.isEmpty() && lastName.isEmpty()) {
                throw new MandatoryFieldsException("Deve essere inserito almeno uno tra il nome e il cognome.");
            }
            
            ExtendedContact newContact = new ExtendedContact(
                firstName, 
                lastName,
                addressField.getText(),
                birthdayPicker.getValue() != null ? 
                    LocalDateTime.of(birthdayPicker.getValue(), LocalTime.MIDNIGHT) : null,
                notesArea.getText(),
                pronounsField.getText()
            );
            
            for (Node node : phoneNumbersList.getChildren()) {
                if (node instanceof HBox) {
                    TextField phoneField = (TextField) ((HBox) node).getChildren().get(0);
                    String number = phoneField.getText();
                    if (!number.isEmpty()) {
                        newContact.addPhoneNumber(number, null);
                    }
                }
            }
            
            for (Node node : emailAddressesList.getChildren()) {
                if (node instanceof HBox) {
                    TextField emailField = (TextField) ((HBox) node).getChildren().get(0);
                    String email = emailField.getText();
                    if (!email.isEmpty()) {
                        newContact.addEmailAddress(email, null);
                    }
                }
            }

            if (!addressField.getText().isEmpty()) {
                newContact.setAddress(addressField.getText());
            }
            if (!pronounsField.getText().isEmpty()) {
                newContact.setPronouns(pronounsField.getText());
            }
            if (birthdayPicker.getValue() != null) {
                newContact.setBirthday(LocalDateTime.of(birthdayPicker.getValue(), LocalTime.MIDNIGHT));
            }
            if (!notesArea.getText().isEmpty()) {
                newContact.setNotes(notesArea.getText());
            }
            
            mainController.addContact(newContact);
            Stage stage = (Stage) editOrSaveButton.getScene().getWindow();
            stage.close();
            
        } catch (MandatoryFieldsException | InvalidPhoneNumberException | InvalidEmailAddressException ex) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Errore");
            alert.setHeaderText("Errore durante il salvataggio");
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }
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
        phoneField.setEditable(isEditMode);
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
        emailField.setEditable(isEditMode);
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

        if (contact == null || this.selected == null) {
            // Clear all fields
            firstNameField.clear();
            lastNameField.clear();
            phoneNumbersList.getChildren().clear();
            emailAddressesList.getChildren().clear();
            addressField.clear();
            pronounsField.clear();
            birthdayPicker.setValue(null);
            notesArea.clear();
            
            // Hide containers
            addressContainer.setVisible(false);
            addressContainer.setManaged(false);
            pronounsContainer.setVisible(false);
            pronounsContainer.setManaged(false);
            birthdayContainer.setVisible(false);
            birthdayContainer.setManaged(false);
            notesContainer.setVisible(false);
            notesContainer.setManaged(false);
            
            editOrSaveButton.setDisable(true);
            return;
        }

        // Reset edit button state
        editOrSaveButton.setDisable(false);

        List<PhoneNumber> contactPhoneNumbers = this.selected.getPhoneNumbers();
        List<EmailAddress> contactEmails = this.selected.getEmailAddresses();
        

        phoneNumbersList.getChildren().clear();
        emailAddressesList.getChildren().clear();
        

        for (PhoneNumber phone : contactPhoneNumbers) {
            addPhoneFieldWithListener(phone.getPhoneNumber());
        }
        

        for (EmailAddress email : contactEmails) {
            addEmailFieldWithListener(email.getEmailAddress());
        }
        

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

        if (contact instanceof ExtendedContact) {
            ExtendedContact extendedContact = (ExtendedContact) contact;
            addressField.setText(extendedContact.getAddress());
            pronounsField.setText(extendedContact.getPronouns());
            birthdayPicker.setValue(extendedContact.getBirthday() != null ? extendedContact.getBirthday().toLocalDate() : null);
            notesArea.setText(extendedContact.getNotes());
        } else {
            addressField.clear();
            pronounsField.clear();
            birthdayPicker.setValue(null);
            notesArea.clear();
        }


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
            isEditMode = true;
        } else {
            try {
                saveContactChanges();
                disableEditMode();
                editOrSaveButton.setText("Modifica");
                isEditMode = false;
            } catch (MandatoryFieldsException ex) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Errore");
                alert.setHeaderText("Errore durante il salvataggio");
                alert.setContentText(ex.getMessage());
                alert.showAndWait();
            }
        }
    }
    
    /**
     * Enables the edit mode for the contact details.
     */
    private void enableEditMode() {
        firstNameField.setEditable(true);
        lastNameField.setEditable(true);

        addressContainer.setManaged(true);
        addressContainer.setVisible(true);
        addressField.setEditable(true);

        pronounsContainer.setManaged(true);
        pronounsContainer.setVisible(true);
        pronounsField.setEditable(true);

        birthdayContainer.setManaged(true);
        birthdayContainer.setVisible(true);
        birthdayPicker.setDisable(false);

        notesContainer.setManaged(true);
        notesContainer.setVisible(true);
        notesArea.setEditable(true);

        for (Node node : phoneNumbersList.getChildren()) {
            HBox hbox = (HBox)node;
            TextField field = (TextField)hbox.getChildren().get(0);
            hbox.setManaged(true);
            hbox.setVisible(true);
            field.setEditable(true);
            ((Button)hbox.getChildren().get(1)).setVisible(true);
        }

        for (Node node : emailAddressesList.getChildren()) {
            HBox hbox = (HBox)node;
            TextField field = (TextField)hbox.getChildren().get(0);
            hbox.setManaged(true);
            hbox.setVisible(true);
            field.setEditable(true);
            ((Button)hbox.getChildren().get(1)).setVisible(true);
        }
    }
    
    /**
     * Disables the edit mode for the contact details.
     */
    private void disableEditMode() {
        firstNameField.setEditable(false);
        lastNameField.setEditable(false);

        if (addressField.getText() == null || addressField.getText().isEmpty()) {
            addressContainer.setManaged(false);
            addressContainer.setVisible(false);
        } else {
            addressContainer.setManaged(true);
            addressContainer.setVisible(true);
        }
        addressField.setEditable(false);

        if (pronounsField.getText() == null || pronounsField.getText().isEmpty()) {
            pronounsContainer.setManaged(false);
            pronounsContainer.setVisible(false);
        } else {
            pronounsContainer.setManaged(true);
            pronounsContainer.setVisible(true);
        }
        pronounsField.setEditable(false);

        if (birthdayPicker.getValue() == null) {
            birthdayContainer.setManaged(false);
            birthdayContainer.setVisible(false);
        } else {
            birthdayContainer.setManaged(true);
            birthdayContainer.setVisible(true);
        }
        birthdayPicker.setDisable(true);

        if (notesArea.getText() == null || notesArea.getText().isEmpty()) {
            notesContainer.setManaged(false);
            notesContainer.setVisible(false);
        } else {
            notesContainer.setManaged(true);
            notesContainer.setVisible(true);
        }
        notesArea.setEditable(false);

        for (Node node : phoneNumbersList.getChildren()) {
            HBox hbox = (HBox)node;
            TextField field = (TextField)hbox.getChildren().get(0);
            if (field.getText().isEmpty()) {
                hbox.setManaged(false);
                hbox.setVisible(false);
            }
            field.setEditable(false);
            ((Button)hbox.getChildren().get(1)).setVisible(false);
        }

        for (Node node : emailAddressesList.getChildren()) {
            HBox hbox = (HBox)node;
            TextField field = (TextField)hbox.getChildren().get(0);
            if (field.getText().isEmpty()) {
                hbox.setManaged(false);
                hbox.setVisible(false);
            }
            field.setEditable(false);
            ((Button)hbox.getChildren().get(1)).setVisible(false);
        }
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

        Contact oldContact = this.selected;

        ExtendedContact updatedContact = new ExtendedContact(
            firstName, 
            lastName,
            addressField.getText(),
            birthdayPicker.getValue() != null ? 
                LocalDateTime.of(birthdayPicker.getValue(), LocalTime.MIDNIGHT) : null,
            notesArea.getText(),
            pronounsField.getText()
        );

        for (Node node : phoneNumbersList.getChildren()) {
            if (node instanceof HBox) {
                TextField phoneField = (TextField) ((HBox) node).getChildren().get(0);
                String number = phoneField.getText();
                if (!number.isEmpty()) {
                    try {
                        updatedContact.addPhoneNumber(number, null);
                    } catch (InvalidPhoneNumberException e) { }
                }
            }
        }

        for (Node node : emailAddressesList.getChildren()) {
            if (node instanceof HBox) {
                TextField emailField = (TextField) ((HBox) node).getChildren().get(0);
                String email = emailField.getText();
                if (!email.isEmpty()) {
                    try {
                        updatedContact.addEmailAddress(email, null);
                    } catch (InvalidEmailAddressException e) { }
                }
            }
        }

        mainController.getAddressBook().updateContact(oldContact, updatedContact);
        this.selected = updatedContact;  // Keep the reference to the updated contact
        
        // Let MainController update its view with the new contact
        mainController.handleContactUpdated(updatedContact);
        mainController.saveAddressBookState();
    }
}