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
public class PanierController implements Initializable {

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

   
    @FXML
    private JFXButton btnrec;
    @FXML
    private JFXButton btnreview;
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
    private void btnrec(ActionEvent event) throws IOException {
        avr.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("TroubleShooting.fxml"));
        avr.getChildren().add(parent);
        avr.toFront();
    }

    @FXML
    private void btnreview(ActionEvent event) throws IOException {
        avr.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("Review.fxml"));
        avr.getChildren().add(parent);
        avr.toFront();
    }
    
}
