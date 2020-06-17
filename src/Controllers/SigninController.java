/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import Entities.User;
import interfaces.IUsers;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.PauseTransition;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import Services.userservices;
import Utils.BCrypt;
import javafx.scene.image.Image;

/**
 *
 * @author thepoet
 */
public class SigninController implements Initializable {

    @FXML
    private JFXButton btngocreateaccount;
    @FXML
    private ImageView signinimgprog;
    @FXML
    private JFXTextField txtemail;
    
    @FXML
    private AnchorPane signinPane;
    @FXML
    private JFXPasswordField txtpassword;
    @FXML
    private JFXButton btnsignin;
    @FXML
    private Label labelerror;
    private SigninController globalCntrl;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        handleValidationlogin();
        signinimgprog.setVisible(false);
    }    

    @FXML
    private void opencreateaccountaction(MouseEvent event) {
        
        btngocreateaccount.getScene().getWindow().hide();
        try {
            signinimgprog.setVisible(false);
            Stage dashboardStage = new Stage();
            dashboardStage.setTitle("");
            Parent root = FXMLLoader.load(getClass().getResource("/gui/Signup.fxml"));
            Scene scene = new Scene(root);
            dashboardStage.setScene(scene);
            dashboardStage.show();
        } catch (IOException ex) {
            Logger.getLogger(SigninController.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
    
    
 
        IUsers user = new userservices();
    @FXML
    private void loginaccountaction(MouseEvent event) {
        
        
        User u1 = user.findByMail(txtemail.getText());
        if (txtemail.getText().isEmpty() || txtpassword.getText().isEmpty()) {
            labelerror.setTextFill(Color.TOMATO);
            labelerror.setText("Fill out all fields");}
        else {
        labelerror.setText("");    
        signinimgprog.setVisible(true);
        PauseTransition pauseTransition = new PauseTransition();
        pauseTransition.setDuration(Duration.seconds(3));
        pauseTransition.setOnFinished(ev -> {
            if (txtemail.getText().equals("admin@esprit.tn") && txtpassword.getText().equals("admin123")) {
                LoginAdmin();
            }
            
            else if (u1 == null) {
                labelerror.setTextFill(Color.TOMATO);
                labelerror.setText("Account doesn't exist");
                signinimgprog.setVisible(false);}
            
            else if (BCrypt.checkpw(txtpassword.getText(),u1.getPassword())) {
                    if (u1.getEnabled() == 0) {
                       signinimgprog.setVisible(false);
                       labelerror.setText("Account not activated");}
                    else{   Main.fos_user = u1;
                      Main.user_id = u1.getId();
                                               LoginUser();
                    }

                    }
            
            else {
                signinimgprog.setVisible(false);
                labelerror.setTextFill(Color.TOMATO);
                labelerror.setText("Check your parameters");
                 }

        });   
        pauseTransition.play();
      }
    }
    private void LoginUser() {
        btnsignin.getScene().getWindow().hide();
        try {
            signinimgprog.setVisible(false);
            Stage stage1 = (Stage) signinPane.getScene().getWindow();

                    stage1.close();
                    Stage stage = new Stage();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/MainInterface.fxml"));
                    Parent root = loader.load();
                    MainInterfaceController main = loader.getController();
                    main.transferMessage(txtemail.getText());
                    Scene scene = new Scene(root);

                    stage.setScene(scene);
                    stage.getIcons().add(new Image("/Images/logo.png"));
                    stage.setTitle("T-HUNT");
                    stage.show();
        } catch (IOException ex) {
            Logger.getLogger(SigninController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void LoginAdmin() {
        btnsignin.getScene().getWindow().hide();
        try {
            signinPane.setVisible(false);
           Stage stage1 = (Stage) signinPane.getScene().getWindow();

                    stage1.close();
                    Stage stage = new Stage();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/BackInterface.fxml"));
                    Parent root = loader.load();
                    BackInterfaceController main = loader.getController();
                    main.transferMessage(txtemail.getText());
                    Scene scene = new Scene(root);

                    stage.setScene(scene);
                    stage.getIcons().add(new Image("/Images/logo.png"));
                    stage.setTitle("T-HUNT");
                    stage.show();
        } catch (IOException ex) {
            Logger.getLogger(SigninController.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
    
    private void handleValidationlogin() {
        RequiredFieldValidator fieldValidator1 = new RequiredFieldValidator();
        fieldValidator1.setMessage("Required Field");
        
        txtemail.getValidators().add(fieldValidator1);
        txtemail.focusedProperty().addListener((ObservableValue<? extends Boolean> o, Boolean oldVal, Boolean newVal1) -> {
            if (!newVal1) {
                txtemail.validate();

            }
        });
        
        RequiredFieldValidator fieldValidator2 = new RequiredFieldValidator();
        fieldValidator2.setMessage("Required Field");
        
        txtpassword.getValidators().add(fieldValidator2);
        txtpassword.focusedProperty().addListener((ObservableValue<? extends Boolean> o, Boolean oldVal, Boolean newVal4) -> {
            if (!newVal4) {
                txtpassword.validate();

            }
        });

    }
    
  
    

}
