/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;


/**
 * FXML Controller class
 *
 * @author cyrine
 */
public class ValiderController implements Initializable {
 @FXML
    private AnchorPane anc;
  @FXML
    private Pane firstpane;
   @FXML
    private Button backImageV;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
     @FXML
    private void backImageV(ActionEvent event) throws IOException {
         try {
    firstpane.getChildren().clear();
       Parent parent = FXMLLoader.load(getClass().getResource("/gui/panier.fxml"));
       firstpane.getChildren().add(parent);
       firstpane.toFront();
      } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
