package g12swe.addressbook.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;

/**
 * @file RootController.java
 * @brief Controller for managing the overall structure and flow of the application.
 * 
 * Responsibilities include loading and initializing views, including the main interface
 * and detailed contact views. This controller ensures smooth navigation and layout
 * responsiveness as user resizes the application window.
 */
public class RootController implements Initializable {

    @FXML
    private StackPane mainView;
    @FXML
    private StackPane secondaryView;
    @FXML
    private HBox rootView;
    
    private MainController mainController;
    private ContactController contactController;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // carico MainController dall'FXML della root
            FXMLLoader mainLoader = new FXMLLoader(getClass().getResource("/fxml/MainView.fxml"));
            Parent mainViewParent = mainLoader.load();
            mainController = mainLoader.getController();
            mainView.getChildren().setAll(mainViewParent);
            
            // carico ContactController dall'FXML della root
            FXMLLoader contactLoader = new FXMLLoader(getClass().getResource("/fxml/ContactView.fxml"));
            Parent contactViewParent = contactLoader.load();
            contactController = contactLoader.getController();
            secondaryView.getChildren().setAll(contactViewParent);
            
            // collego i controller tra di loro
            mainController.setContactController(contactController);
            contactController.setMainController(mainController);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    /**
     * @brief Shows only MainView on the stage.
     * 
     * This function is called by <code>App</code> to manage and keep the layout
     * of the application as more responsive as possible (specifically, when the
     * width of the stage is less than 400px wide).
     */
    public void showMainViewOnly() {
        rootView.getChildren().clear(); // Rimuove tutte le view
        rootView.getChildren().add(mainView); // Aggiunge solo MainView
        HBox.setHgrow(mainView, Priority.ALWAYS); // Forza MainView a occupare tutto lo spazio
    }

    /**
     * @brief Shows both MainView and ContollerView on the stage
     * 
     * This function is called by <code>App</code> to manage and keep the layout
     * of the application as more responsive as possible (specifically, when the
     * width of the stage is more than 400px wide).
     */
    public void showBothViews() {
        rootView.getChildren().clear(); // Ripulisce il contenitore
        rootView.getChildren().addAll(mainView, secondaryView); // Aggiunge entrambe le view
        HBox.setHgrow(mainView, Priority.ALWAYS); // Configura la crescita delle view
        HBox.setHgrow(secondaryView, Priority.ALWAYS);
    }

}
