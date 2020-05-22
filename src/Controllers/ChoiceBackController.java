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
 * @author h^
 */
public class ChoiceBackController implements Initializable {
    
    @FXML
    private JFXButton btnreser;
    @FXML
    private JFXButton btnevalua;
    @FXML
    private JFXButton btnev;
    @FXML
    private AnchorPane avr;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    
    @FXML
    private void btnreser(ActionEvent event) throws IOException {
        avr.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("/gui/ShowReservations.fxml"));
        avr.getChildren().add(parent);
        avr.toFront();
    }
    
 
    @FXML
    private void btnevalua(ActionEvent event) throws IOException {
        avr.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("/gui/ShowEvaluations.fxml"));
        avr.getChildren().add(parent);
        avr.toFront();
    }
    
    @FXML
    private void btnev(ActionEvent event) throws IOException {
        avr.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("/gui/EventsBack.fxml"));
        avr.getChildren().add(parent);
        avr.toFront();
    }

    
}
