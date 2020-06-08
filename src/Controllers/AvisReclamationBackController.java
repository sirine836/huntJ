/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author cyrine
 */
public class AvisReclamationBackController implements Initializable {

    @FXML
    private AnchorPane avr;
    @FXML
    private JFXButton btnrec;
    @FXML
    private JFXButton btnreview;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

   
      @FXML
    private void btnrec(ActionEvent event) throws IOException {
        avr.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("/gui/TroubleShootingBack.fxml"));
        avr.getChildren().add(parent);
        avr.toFront();
    }

    @FXML
    private void btnreview(ActionEvent event) throws IOException {
        avr.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("/gui/ReviewBack.fxml"));
        avr.getChildren().add(parent);
        avr.toFront();
    }
   
}
