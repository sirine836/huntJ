/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import com.jfoenix.controls.JFXRadioButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

/**
 * FXML Controller class
 *
 * @author cyrine
 */
public class PaymentController implements Initializable {

    @FXML
    private TextField cardnumtxt;
    @FXML
    private TextField cardholdertxt;
    @FXML
    private TextField monthtxt;
    @FXML
    private TextField yeartxt;
    @FXML
    private PasswordField cvvtxt;
    @FXML
    private JFXRadioButton onlinepaybtn;
    @FXML
    private ToggleGroup payment;
    @FXML
    private JFXRadioButton codbtn;
    @FXML
    private Label amounttxt;
    @FXML
    private Label warning;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void onlinePayment(ActionEvent event) {
    }

    @FXML
    private void confirmPayment(ActionEvent event) {
    }

    @FXML
    private void cashOnDelivery(ActionEvent event) {
    }
    
}
