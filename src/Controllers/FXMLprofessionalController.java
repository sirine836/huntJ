/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Professional;
import Services.ServiceProfessional;
import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author VENOM
 */
public class FXMLprofessionalController implements Initializable {

    @FXML
    private TextField first;
    @FXML
    private TextField last;
    @FXML
    private TextField email;
    @FXML
    private TextField soft;
    @FXML
    private Button cv;
    @FXML
    private Button ajouterPro;
    @FXML
    private AnchorPane image;
    @FXML
    private Button clear;

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
    private void ajouter(ActionEvent event) throws SQLException {
        String Last = last.getText();
        String First = first.getText();
        String Email = email.getText();
        String Soft = soft.getText();
        String CV = cv.getText();
        ServiceProfessional sp = new ServiceProfessional();
        Professional p = new Professional(First, Last,Email,Soft,CV);
        sp.addProfessional(p);
        System.out.println("ADDED SUCCESSFULLY!");
    }
     @FXML
    private void ClearFields(ActionEvent event) {  //Vider les champs **DONE**
         last.clear();
         first.clear();
         email.clear();
         soft.clear();
    }
    
    @FXML
    private void uploadCV(ActionEvent event) {
        FileChooser F = new FileChooser();
        F.setTitle("Choose a file.");
        F.getExtensionFilters().addAll();
        File f = F.showOpenDialog(image.getScene().getWindow());
        if(f != null){
            cv.setText(f.toString());
        }
    }
    
}
