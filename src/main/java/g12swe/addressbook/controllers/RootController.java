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
    
    /**
     * @brief Root container for managing the layout.
     *
     * This HBox serves as the root layout container, dynamically displaying either one or both views
     * based on the application window's width.
     */
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

            contactController.loadContactDetails(null);
            
        } catch (IOException e) {}
    }
    
    
    /**
     * @brief Displays only MainView on the stage.
     * 
     * This function is called by <code>App</code> to manage and keep the layout
     * of the application as responsive as possible (specifically, when the
     * width of the stage is less than 400px wide).
     */
    public void showMainViewOnly() {
        rootView.getChildren().clear(); ///< Removes all chiled nodes from the rootView
        rootView.getChildren().add(mainView); ///< adds the mainView as the only child
        HBox.setHgrow(mainView, Priority.ALWAYS);  ///< Forces the mainView to grow and occupy all available space.
    }

    /**
     * @brief Displays both MainView and ContollerView on the stage
     * 
     * This function is called by <code>App</code> to manage and keep the layout
     * of the application as responsive as possible (specifically, when the
     * width of the stage is more than 400px wide).
     */
    public void showBothViews() {
        rootView.getChildren().clear(); ///< clears the container
        rootView.getChildren().addAll(mainView, secondaryView); ///< adds both views to rootView
        HBox.setHgrow(mainView, Priority.ALWAYS); ///< Configures each view's growth
        HBox.setHgrow(secondaryView, Priority.ALWAYS);
    }

}
