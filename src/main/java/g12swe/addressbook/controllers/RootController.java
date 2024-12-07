package g12swe.addressbook.controllers;

import javafx.fxml.FXML;
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
public class RootController {
    
    /**
     * @brief Main view container.
     *
     * The StackPane represents the primary view of the application, displaying the main interface.
     */

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
