/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Panier;
import Services.LignePanierService;
import Services.PanierService;
import com.jfoenix.controls.JFXButton;
import config.Config;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author cyrine
 */
public class PaiementController implements Initializable {

    /**
     * Initializes the controller class.
     * 
     * 
     */
     @FXML
    private AnchorPane avr;
   @FXML
    private AnchorPane mainPane;
    @FXML
    private JFXButton btnprod;
    @FXML
    private JFXButton btnclient;
    @FXML
    private JFXButton backImageV;
    @FXML
    private JFXButton logbtn;
    @FXML
    private FontAwesomeIcon logout;
    @FXML
    private JFXButton btnpan;
    @FXML
    private Pane firstpane;
    
   @FXML
    private JFXButton retourcom;
  
  
    
    @FXML
    private Label calcl;

    /**
     * Initializes the controller class.
     */
    
    LignePanierService ser = new LignePanierService();
     Panier panier = new Panier();
      PanierService panierService = new PanierService();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
         try { 
            calcl.setText(String.valueOf(ser.calcul_total(Config.currentpanier)));
             System.out.println(ser.calcul_total(Config.currentpanier));
         } catch (SQLException ex) {
             Logger.getLogger(PaiementController.class.getName()).log(Level.SEVERE, null, ex);
         }
    }    

   @FXML
    private void btnpan(javafx.event.ActionEvent event) throws IOException {
         firstpane.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("Panier.fxml"));
        firstpane.getChildren().add(parent);
        firstpane.toFront();
    }
    
    @FXML
    private void btnprod(javafx.event.ActionEvent event) throws IOException {
         firstpane.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("Menu.fxml"));
        firstpane.getChildren().add(parent);
        firstpane.toFront();
    }
    
    
    @FXML
    private void btnclient(javafx.event.ActionEvent event) throws IOException {
         firstpane.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("AvisReclamation.fxml"));
        firstpane.getChildren().add(parent);
        firstpane.toFront();
    }
    
    @FXML
    private void retourcom(javafx.event.ActionEvent event) throws IOException {
         firstpane.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("commander.fxml"));
        firstpane.getChildren().add(parent);
        firstpane.toFront();
    }
    
    
    
   

    @FXML
    private void logout(MouseEvent event) throws IOException {
    }

    @FXML
    private void logbtn(javafx.event.ActionEvent event)  throws IOException {
         Stage stage1 = (Stage) mainPane.getScene().getWindow();
            
        stage1.close();
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.getIcons().add(new Image("/Images/logo.png"));
        stage.setTitle("T-HUNT");
        stage.show();
    }
           
    }    
    

