/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import Entities.User;
import interfaces.IUsers;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.stage.FileChooser;
import Services.userservices;
import Utils.BCrypt;

/**
 * FXML Controller class
 *
 * @author thepoet
 */
public class profileController implements Initializable {

    @FXML
    private AnchorPane signupPane;
    @FXML
    private JFXTextField txtfirstname;
    @FXML
    private JFXPasswordField txtpasswod;
    @FXML
    private JFXTextField txtlastname;
    @FXML
    private Label lblstatus;
    @FXML
    private JFXButton btnupdate;
    @FXML
    private Label txtemail;
    
    @FXML
    private Label lblemail;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    
    
    void setUserInfo(String mail) {
    
            if (mail == null) {
                return;
            }
            IUsers u1 = new userservices();
            User user = u1.findByMail(mail); 
            txtlastname.setText(user.getNom());
            txtemail.setText(user.getEmail());
            txtpasswod.setText(user.getPassword());
             }    
    
    @FXML
     void updateaccount(MouseEvent event) {
         
        IUsers u1 = new userservices();
        User user = u1.findByMail(txtemail.getText());
        if (txtlastname != null){
                user.setNom(txtlastname.getText());}
        if (txtpasswod != null){
                String hashed = BCrypt.hashpw(txtpasswod.getText(), BCrypt.gensalt());
                user.setPassword((txtpasswod.getText()));
                }
        
           
        u1.update(user);
        setUserInfo(user.getEmail());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Confirm");
                alert.setHeaderText(null);
                alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                alert.setContentText("Modifications Successfull");
                alert.showAndWait();
    }
    
    
       
    
}
