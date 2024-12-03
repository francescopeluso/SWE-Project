package g12swe.addressbook;

import g12swe.addressbook.controllers.RootController;
import javafx.fxml.FXMLLoader;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/RootView.fxml"));
        Parent root = loader.load();
        
        Scene scene = new Scene(root);
        
        stage.widthProperty().addListener((o, oldValue, newValue) -> {
            
            RootController controller = loader.getController();
            if (newValue.intValue() < 400) {
                controller.showMainViewOnly();
                
                if(newValue.intValue() < 256) {
                    stage.setResizable(false);
                    stage.setWidth(256);
                    stage.setResizable(true);
                }
                
            } else {
                controller.showBothViews();
            }
            
        });
        
        stage.heightProperty().addListener((o, oldValue, newValue)->{
            if(newValue.intValue() < 440F) {
                stage.setResizable(false);
                stage.setHeight(440);
                stage.setResizable(true);
            }
        });
                
        stage.setTitle("Gruppo 20 | Rubrica telefonica");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}