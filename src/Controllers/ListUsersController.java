/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXRippler;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
// import com.maimart.fx.tablefilter.TableFilter;
import Entities.User;
import  interfaces.IUsers;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import Services.userservices;

/**
 * FXML Controller class
 *
 * @author thepoet
 */
public class ListUsersController implements Initializable {

    @FXML
    private AnchorPane holderAnchor;
    @FXML
    private TableView<User> tableUsers;
    @FXML
    private TableColumn<?, ?> IDUser;
    @FXML
    private TableColumn<?, ?> lastname;
    // @FXML
    // private TableColumn<?, ?> firstname;
    @FXML
    private TableColumn<?, ?> Mail;
    @FXML
    private Label lblName;
    @FXML
    private Label lblEmail;
    @FXML
    private AnchorPane fabPane;
    private Label fabEdit;
    private JFXRadioButton RDUserName;
    @FXML
    private ToggleGroup filter;
    @FXML
    private JFXRadioButton RDMail;
    @FXML
    private JFXTextField inputSearch;
    @FXML
    private JFXButton Search;
    
    @FXML
    private JFXButton logoutbtn;
    @FXML
    private Label lblprenom;
    @FXML
    private Label lblnom;
    @FXML
    private ImageView profilephoto;
    @FXML
    private TableColumn<?, ?> LOGIN;
    @FXML
    private Label lbllogin;
    @FXML
    private JFXRadioButton RDlogin;
    
    ObservableList<User> userslist1 = FXCollections.observableArrayList();
    IUsers u11=new userservices();

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
	 @FXML
	    private void logout(MouseEvent event) {
		 logoutbtn.getScene().getWindow().hide();
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
private void delete() {
    IUsers u1= new userservices();
    User user = u1.findByMail(lblEmail.getText());
    u1.remove(user);
    tableUsers.getItems().removeAll(tableUsers.getSelectionModel().getSelectedItem());
	
}

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setRipples();
        buildUsersTable();
       

        tableUsers.getSelectionModel().selectedItemProperty().addListener(
                (
                        ObservableValue<? extends User> observable,
                        User oldValue,
                        User newValue) -> {
                    if (newValue == null) {
                                
                        return;
                    }
                    getUserInfo(newValue.getEmail()); 
                   

                }); 
      
    }    

    private void buildUsersTable() {
        
        ObservableList<User> users = FXCollections.observableArrayList();
   
         IUsers u1 = new userservices();
         u1.getAll().forEach((user) ->{ 
                 users.add(user);
                         });
         
         IDUser.setCellValueFactory(new PropertyValueFactory<>("idu"));
         
         lastname.setCellValueFactory(new PropertyValueFactory<>("nom"));
         Mail.setCellValueFactory(new PropertyValueFactory<>("email"));
         tableUsers.refresh();
          tableUsers.getItems().clear();
         tableUsers.getItems().addAll(users);
          
    }


    @FXML
    private void search() throws ParseException{
        if (RDlogin.isSelected() && !"".equals(inputSearch.getText())){
          ObservableList<User> users = FXCollections.observableArrayList();
   
         userservices u1=new userservices();
         u1.getLogin(inputSearch.getText()).forEach((user) ->{ 
                 users.add(user);
                         });          
         IDUser.setCellValueFactory(new PropertyValueFactory<>("idu"));
         lastname.setCellValueFactory(new PropertyValueFactory<>("nom"));
         Mail.setCellValueFactory(new PropertyValueFactory<>("email"));
         
          tableUsers.getItems().clear();
          tableUsers.getItems().addAll(users);          
       }
        else if(RDMail.isSelected() && !"".equals(inputSearch.getText())){
            ObservableList<User> users = FXCollections.observableArrayList();
   
         userservices u1=new userservices();
         u1.getEmail(inputSearch.getText()).forEach((user) ->{ 
                 users.add(user);
                         });              
         IDUser.setCellValueFactory(new PropertyValueFactory<>("idu"));
         lastname.setCellValueFactory(new PropertyValueFactory<>("nom"));
         Mail.setCellValueFactory(new PropertyValueFactory<>("email"));
         
         
          tableUsers.getItems().clear();
          tableUsers.getItems().addAll(users);          
            
        }
        else if ((RDlogin.isSelected() || RDMail.isSelected()) && inputSearch.getText().equals("")){buildUsersTable(); }
    }
    
    private void setRipples() {
        JFXRippler fXRippler = new JFXRippler(fabEdit);
        fXRippler.setMaskType(JFXRippler.RipplerMask.CIRCLE);
        fXRippler.setRipplerFill(Paint.valueOf("#F05537"));
        fabPane.getChildren().add(fXRippler);

    }
    
    private void getUserInfo(String mail) {
    
            if (mail == null) {
                return;
            }
            IUsers u1 = new userservices();
            User user = u1.findByMail(mail); 
            
            lblprenom.setText(user.getNom());
           
            lblEmail.setText(user.getEmail());
           
            
  
            
                
    }       

}
