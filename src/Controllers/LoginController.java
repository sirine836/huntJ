/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import com.jfoenix.controls.JFXButton;
import Services.userservices;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import static Controllers.Main.fos_user;
import Entities.User;
import Utils.BCrypt;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author nahdi
 */
public class LoginController implements Initializable {
	@FXML
    private ImageView signinimgprog;
	 @FXML
	    private AnchorPane signinPane;
    @FXML
    private JFXButton btnlogin;
    @FXML
    private JFXButton btngotoregister;
    @FXML
    private TextField txtemail;
    @FXML
    private PasswordField txtpassword;
    @FXML
    private Label labelerror;
          
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    @FXML
    private void opencreateaccountaction(MouseEvent event) {
        
        btngotoregister.getScene().getWindow().hide();
        try {
            Stage dashboardStage = new Stage();
            dashboardStage.setTitle("");
            Parent root = FXMLLoader.load(getClass().getResource("/gui/Register.fxml"));
            Scene scene = new Scene(root);
            dashboardStage.setScene(scene);
            dashboardStage.show();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
        userservices user = new userservices();
   
        
    /*@FXML
    private void loginaccountaction(MouseEvent event) {
        
        
        User u1 = user.findByMail(txtemail.getText());
        if (txtemail.getText().isEmpty() || txtpassword.getText().isEmpty()) {
            labelerror.setTextFill(Color.TOMATO);
            labelerror.setText("Please fill out all fields");}
        else {
        labelerror.setText("");    
        PauseTransition pauseTransition = new PauseTransition();
        pauseTransition.setDuration(Duration.seconds(3));
        pauseTransition.setOnFinished(ev -> {
            if (txtemail.getText().equals("admin@esprit.tn") && txtpassword.getText().equals("admin123")) {
               LoginAdmin();
           }
            
            else if (u1 == null) {
                labelerror.setTextFill(Color.TOMATO);
                labelerror.setText("This account does not exist");
                }
          
            else if (u1.getPassword()!= null) {
                       LoginUser();}
            
            else {
                labelerror.setTextFill(Color.TOMATO);
                labelerror.setText("Verify parameters");
                 }

        });   
        pauseTransition.play();
      }
    }
    private void LoginUser() {
        btnlogin.getScene().getWindow().hide();
        try {
 //            signinimgprog.setVisible(false);
            Stage dashboardStage = new Stage();
            dashboardStage.setTitle("");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/MainInterface.fxml"));
            Parent root = loader.load();
           MainInterfaceController main = loader.getController();
           main.transferMessage(txtemail.getText());
           Scene scene = new Scene(root);
            dashboardStage.setScene(scene);
            dashboardStage.show();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    private void LoginAdmin() {
    	btnlogin.getScene().getWindow().hide();
        try {
			signinPane.setVisible(false);
            Stage dashboardStage = new Stage();
            dashboardStage.setTitle("");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/ListUsers.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            dashboardStage.setScene(scene);
            dashboardStage.show();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);

        }
    }*/
        
    Connection con = null;
    PreparedStatement preparedStatement = null;
    ResultSet rs = null;

    private boolean validateInputs() throws SQLException {

        if (txtemail.getText().isEmpty()) {
            Alert alert1 = new Alert(Alert.AlertType.WARNING);
            alert1.setTitle("Erreur");
            alert1.setContentText("Veillez saisir votre nom d'utilisateur");
            alert1.setHeaderText("Controle de saisie");
            alert1.show();
            return false;
        }
        if (txtpassword.getText().isEmpty()) {
            Alert alert1 = new Alert(Alert.AlertType.WARNING);
            alert1.setTitle("Erreur");
            alert1.setContentText("Veillez saisir votre mot de passe");
            alert1.setHeaderText("Controle de saisie");
            alert1.show();
            return false;
        }
        return true;
    }
        

    @FXML
    private void loginaccountaction(MouseEvent event) throws SQLException, IOException {       
        labelerror.setText(""); 
        if (validateInputs()) {
            userservices us = new userservices();
            String pseudo = txtemail.getText();
            String password = txtpassword.getText();

            User u1 = user.findByMail(txtemail.getText());
            //System.out.println(u1.getEnabled());
           // System.out.println(txtpassword.getText());
           // System.out.println(txtpassword.getText());
            //System.out.println( u1.getPassword());
           
            if (u1!=null && BCrypt.checkpw(txtpassword.getText(), u1.getPassword())) {
               Main.fos_user = u1;
             Main.user_id = u1.getId();
                  Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Succès");
                alert.setHeaderText("Authentifié");
                alert.setContentText("Vous êtes connecté en tant que :" + u1.getEmail());
                alert.showAndWait();
                              // System.out.println(fos_user.getEmail());

                
                if(u1.getRoles().contains("ROLE_SUPER_ADMIN")){
                    
                     Stage stage1 = (Stage) signinPane.getScene().getWindow();

                    stage1.close();
                    Stage stage = new Stage();
                    Parent root = FXMLLoader.load(getClass().getResource("/gui/BackInterface.fxml"));

                    Scene scene = new Scene(root);

                    stage.setScene(scene);
                    stage.getIcons().add(new Image("/Images/logo.png"));
                    stage.setTitle("T-HUNT");
                    stage.show();
                    
                } else {
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
                }
                
                //AnchorPane root = getRole(u);

                //txtPassword.getScene().setRoot(root);

          }
            else {
               labelerror.setTextFill(Color.TOMATO);
            labelerror.setText("Verify parameters");}
}

        }
    

 
}

    
