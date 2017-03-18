/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pandemic.FX;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author Jirka
 */
public class Pandemic extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        /*
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setMaximized(true);
        stage.setScene(scene);
        stage.show();
        */
        
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
        

        Scene scene = new Scene((Pane) loader.load());
        stage.setScene(scene);
        stage.setMaximized(true);
        
        FXMLDocumentController controller = loader.<FXMLDocumentController>getController();
        controller.setListenersHyperlink(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
                getHostServices().showDocument("https://docs.google.com/document/d/1VGDc8NTc_VWKS3_ApZ0GeE6Dp36aGCtCiTFFfHKyC-A/pub");
            }
        });
        
        stage.setTitle("Ntrophy - logika");
        
        stage.show();
        
     //   controller.rescale(stage.getWidth(), stage.getHeight());
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
