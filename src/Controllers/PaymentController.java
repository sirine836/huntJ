/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Services.LignePanierService;
import com.jfoenix.controls.JFXRadioButton;
import config.Config;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

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
    
   @FXML
    private Label calc;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        LignePanierService ser = new LignePanierService();
        
        try {
            calc.setText(String.valueOf(ser.calcul_total(Config.currentpanier)));
        } catch (SQLException ex) {
            Logger.getLogger(PaymentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
     @FXML
    private void backImageV(ActionEvent event) throws IOException {
         try {
        javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("Panier.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }  
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
