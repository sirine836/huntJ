/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Utils.SendMail;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXRippler;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
//import com.maimart.fx.tablefilter.TableFilter;
import Entities.User;
import  interfaces.IUsers;
import java.io.File;
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
import javafx.scene.paint.Paint;
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
    @FXML
    private TableColumn<?, ?> firstname;
    @FXML
    private TableColumn<?, ?> Mail;
    @FXML
    private Label lblName;
    @FXML
    private Label lblEmail;
    @FXML
    private AnchorPane fabPane;
    private Label fabEdit;
    @FXML
    private Button BanUser;
    @FXML
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
    private Label lblprenom;
    @FXML
    private Label lblnom;
    @FXML
    private ImageView profilephoto;
    @FXML
    private JFXRadioButton RDFirstName;
    @FXML
    private TableColumn<?, ?> STATUS;
    @FXML
    private Label lblstatus;
    @FXML
    private JFXRadioButton RDStatus;
    @FXML
    private Button EnableUser;
    @FXML
    private Button DisableUser;
    
    ObservableList<User> userslist1 = FXCollections.observableArrayList();
    IUsers u11=new userservices();

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setRipples();
        buildUsersTable();
        DisableUser.setVisible(false);
        EnableUser.setVisible(false);
        BanUser.setVisible(false);

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
        
//        TableFilter filtertable = new TableFilter(tableUsers);
//        List<Users> userslist = u11.getAll();
//        userslist1.addAll(userslist);
//        
//        IDUser.setCellValueFactory(new PropertyValueFactory<>("ID_USERS"));
//        firstname.setCellValueFactory(new PropertyValueFactory<>("LAST_NAME"));
//        lastname.setCellValueFactory(new PropertyValueFactory<>("FIRST_NAME"));
//        Mail.setCellValueFactory(new PropertyValueFactory<>("EMAIL"));
//        STATUS.setCellValueFactory(new PropertyValueFactory<>("STATUS"));
//        
//        FilteredList<Users> filteredData = new FilteredList<>(userslist1, p -> true);
//        
//        inputSearch.textProperty().addListener((obsVal, oldValue, newValue) -> {
//            filteredData.setPredicate(Users -> Users.getEMAIL().toLowerCase().contains(inputSearch.getText().toLowerCase())                
//            
//            );
//        });
//        
//        SortedList<Users> sortedData = new SortedList<>(filteredData);
//        sortedData.comparatorProperty().bind(tableUsers.comparatorProperty());
//        
//        tableUsers.setItems(sortedData);
    }    

    private void buildUsersTable() {
        
        ObservableList<User> users = FXCollections.observableArrayList();
   
         IUsers u1 = new userservices();
         u1.getAll().forEach((user) ->{ 
                 users.add(user);
                         });
         
         IDUser.setCellValueFactory(new PropertyValueFactory<>("id"));
         firstname.setCellValueFactory(new PropertyValueFactory<>("nom"));
         lastname.setCellValueFactory(new PropertyValueFactory<>("nom"));
         Mail.setCellValueFactory(new PropertyValueFactory<>("email"));
         STATUS.setCellValueFactory(new PropertyValueFactory<>("enabled"));
         tableUsers.getItems().clear();
         tableUsers.getItems().addAll(users);
          
    }

    @FXML
    private void BanUser(ActionEvent event) {
        IUsers u1= new userservices();
        u1.remove(u1.findByMail(lblEmail.getText()));
        setRipples();
        buildUsersTable();
    }

    @FXML
    private void search() throws ParseException{
        if (RDStatus.isSelected() && !"".equals(inputSearch.getText())){
          ObservableList<User> users = FXCollections.observableArrayList();
   
         userservices u1=new userservices();
         u1.getStatus(Integer.parseInt(inputSearch.getText())).forEach((user) ->{ 
                 users.add(user);
                         });          
         IDUser.setCellValueFactory(new PropertyValueFactory<>("id"));
         firstname.setCellValueFactory(new PropertyValueFactory<>("nom"));
         lastname.setCellValueFactory(new PropertyValueFactory<>("nom"));
         Mail.setCellValueFactory(new PropertyValueFactory<>("email"));
         STATUS.setCellValueFactory(new PropertyValueFactory<>("enabled"));
         tableUsers.getItems().clear();
         tableUsers.getItems().addAll(users);          
       }
        else if(RDMail.isSelected() && !"".equals(inputSearch.getText())){
            ObservableList<User> users = FXCollections.observableArrayList();
   
         userservices u1=new userservices();
         u1.getEmail(inputSearch.getText()).forEach((user) ->{ 
                 users.add(user);
                         });              
         IDUser.setCellValueFactory(new PropertyValueFactory<>("id"));
         firstname.setCellValueFactory(new PropertyValueFactory<>("nom"));
         lastname.setCellValueFactory(new PropertyValueFactory<>("nom"));
         Mail.setCellValueFactory(new PropertyValueFactory<>("email"));
         STATUS.setCellValueFactory(new PropertyValueFactory<>("enabled"));
         
          tableUsers.getItems().clear();
          tableUsers.getItems().addAll(users);          
            
        }
        else if ((RDStatus.isSelected() || RDMail.isSelected()) && inputSearch.getText().equals("")){buildUsersTable(); }
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
            lblnom.setText(user.getNom());
            lblEmail.setText(user.getEmail());
            //String ImageUrl = user.getPROFILE_PHOTO();
            //File file = new File(ImageUrl);
            //Image ima = new Image(file.toURI().toString());
            //profilephoto.setImage(ima);
            if (user.getEnabled() == 0){
                DisableUser.setVisible(false);
                EnableUser.setVisible(true);
                BanUser.setVisible(true);
                lblstatus.setText("Etat : Désactivé");}
            else{
                EnableUser.setVisible(false);
                BanUser.setVisible(true);
                DisableUser.setVisible(true);
                lblstatus.setText("Etat : Activé");}
            
            if (user.getId() == 1){
                BanUser.setVisible(false);
                EnableUser.setVisible(false);
                DisableUser.setVisible(false);}
            
                
    }       

    @FXML
    private void EnableUser(ActionEvent event) {
        IUsers u1= new userservices();
        User user = u1.findByMail(lblEmail.getText());
        user.setEnabled(1);
        u1.update(user);
        setRipples();
        buildUsersTable();
        SendMail.send(lblEmail.getText(), "T-Hunter Account", "Hello " + lblprenom.getText() + ",\nWe want to inform you that your account has been activated..\nYou can now sign-in.\nYours,\nT-hunter Team", "thunter.user@gmail.com", "hunt.1234");
        
    }

    @FXML
    private void DisableUser(ActionEvent event) {
        IUsers u1= new userservices();
        User user = u1.findByMail(lblEmail.getText());
        user.setEnabled(0);
        u1.update(user);
        setRipples();
        buildUsersTable();
        SendMail.send(lblEmail.getText(), "T-Hunter Account", "Hello " + lblprenom.getText() + ",\nWe regret to inform you that your account has been deactivated.\nYou cannot use our platform anymore.\nYours,\nT-Hunter Team", "thunter.user@gmail.com", "hunt.1234");
        
    }
}
