/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import Entities.Seller;
import Services.ServiceSeller;
import java.io.File;
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
public class FXMLsellerController implements Initializable {

    @FXML
    private AnchorPane image;
    @FXML
    private TextField sellername;
    @FXML
    private TextField taxnumber;
    @FXML
    private TextField rcs;
    @FXML
    private TextField tva;
    @FXML
    private TextField siren;
    @FXML
    private TextField fax;
    @FXML
    private TextField phonenumber;
    @FXML
    private TextField email;
    @FXML
    private Button license;
    @FXML
    private Button addSeller;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @FXML
    private void ajouter(ActionEvent event) throws SQLException {
        String Company= sellername.getText();
        String RCS = rcs.getText();
        String TAX = taxnumber.getText();
        String TVA = tva.getText();
        String SIREN = siren.getText();
        String FAX = fax.getText();
        String Phone = phonenumber.getText();
        String Email = email.getText();
        String License = license.getText();
        ServiceSeller ss = new ServiceSeller();
        Seller s = new Seller(sellername, rcs,taxnumber,tva, siren, fax, phonenumber, email, license);
        ss.addSeller(s);
        System.out.println("ADDED SUCCESSFULLY!");
    }
    @FXML
    private void ClearFields(ActionEvent event) {  //Vider les champs **DONE**
         sellername.clear();
         rcs.clear();
         taxnumber.clear();
         tva.clear();
         siren.clear();
         fax.clear();
         phonenumber.clear();
         email.clear();
         
    }
    private void uploadCV(ActionEvent event) {
        FileChooser F = new FileChooser();
        F.setTitle("Choose a file.");
        F.getExtensionFilters().addAll();
        File f = F.showOpenDialog(image.getScene().getWindow());
        if(f != null){
            license.setText(f.toString());
        }
    }
    
}
