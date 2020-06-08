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
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author VENOM
 */
public class ChoiceRecrutController implements Initializable {
    @FXML
    private JFXButton Seller;
    @FXML
    private JFXButton Pro;
    
    @FXML
    private AnchorPane avr;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    @FXML
    void Seller (ActionEvent Event) throws IOException {
        
        avr.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("/gui/FXMLseller.fxml"));
        avr.getChildren().add(parent);
        avr.toFront();
        
    }
    
    @FXML
    void Pro (ActionEvent Event) throws IOException {
        
        avr.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("/gui/FXMLprofessional.fxml"));
        avr.getChildren().add(parent);
        avr.toFront();
        
    }
    
}
