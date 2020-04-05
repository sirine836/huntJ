/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author youss
 */
public class MainInterfaceController implements Initializable {

    @FXML
    private AnchorPane mainPane;
    @FXML
    private JFXButton btnclient;
    @FXML
    private Pane firstpane;
    @FXML
    private JFXButton logbtn;
    @FXML
    private FontAwesomeIcon logout;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnpan(ActionEvent event) throws IOException {
         firstpane.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("Panier.fxml"));
        firstpane.getChildren().add(parent);
        firstpane.toFront();
    }
    
    @FXML
    private void btnprod(ActionEvent event) throws IOException {
         firstpane.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("Menu.fxml"));
        firstpane.getChildren().add(parent);
        firstpane.toFront();
    }
    
    
    @FXML
    private void btnclient(ActionEvent event) throws IOException {
         firstpane.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("AvisReclamation.fxml"));
        firstpane.getChildren().add(parent);
        firstpane.toFront();
    }

    @FXML
    private void logout(MouseEvent event) {
    }

    @FXML
    private void logbtn(ActionEvent event) throws IOException {
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