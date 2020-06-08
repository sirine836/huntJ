/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import Controllers.LoginController;

import Entities.User;
import interfaces.IUsers;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import Services.userservices;
import Utils.BCrypt;

/**
 * FXML Controller class
 *
 * @author nahdi
 */
public class RegisterController implements Initializable {
	 @FXML
	    private Label lblstatus;
	    @FXML
	    private AnchorPane signupPane;
	    @FXML
	    private ImageView signupimgprog;
	    //@FXML
	    //private TextField txtfirstname;
	    @FXML
	    private PasswordField txtpasswod;
	    @FXML
	    private Button btncreate;
	    @FXML
	    private TextField txtlastname;
	    @FXML
	    private TextField txtemail;
	    @FXML
	    private ImageView profileimg;

	
	
    @FXML
    private Button backtologin;
    /**
     * Initializes the controller class.
     */
	 @FXML
	    private void gotologininterface(MouseEvent event) {
		 backtologin.getScene().getWindow().hide();
	        try {
	            Stage dashboardStage = new Stage();
	            dashboardStage.setTitle("");
	            Parent root = FXMLLoader.load(getClass().getResource("/gui/Login.fxml"));
	            Scene scene = new Scene(root);
	            dashboardStage.setScene(scene);
	            dashboardStage.show();
	        } catch (IOException ex) {
	            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);

	        }
	    }
	 
	 @FXML
	    private void createaccountaction(MouseEvent event) {
	        
	       IUsers user = new userservices();
	       
	       String Email = txtemail.getText();
	       User u1 = user.findByMail(Email);
	       String hashed = BCrypt.hashpw(txtpasswod.getText(), BCrypt.gensalt());
	       User u = new User(txtlastname.getText(), Email, hashed);
	       if (u1 != null) {
	                lblstatus.setTextFill(Color.TOMATO);
	                lblstatus.setText("This account is already registred.");
	            } 
	       else if (u1 == null){ 
	         if (txtlastname.getText().isEmpty() || txtemail.getText().isEmpty() || txtpasswod.getText().isEmpty()) {
	            lblstatus.setTextFill(Color.TOMATO);
	            lblstatus.setText("Please fill out all fields");
	            }
	         else {
	                lblstatus.setText("");
	                PauseTransition pauseTransition = new PauseTransition();
	                pauseTransition.setDuration(Duration.seconds(3));
	                pauseTransition.setOnFinished(ev -> {
	                user.add(u);
	                btncreate.getScene().getWindow().hide();
	                try {
	            Stage stage = new Stage();
	            stage.setTitle("");
	            Parent root = FXMLLoader.load(getClass().getResource("/gui/Login.fxml"));
	            Scene scene = new Scene(root);
	            stage.setScene(scene);
	            stage.show();
	        } catch (IOException ex) {
	            Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
	        }
	                 });   
	        pauseTransition.play();
	        Alert alert = new Alert(AlertType.INFORMATION);
	                alert.setTitle("Confirmation");
	                alert.setHeaderText(null);
	                alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
	                alert.setContentText("Account created with success!");
	                alert.showAndWait();
	              }
	                        
	         }  
	         
	    }
	   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
