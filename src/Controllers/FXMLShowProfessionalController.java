/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Professional;
import Services.ServiceProfessional;
import Utils.Mailing;
import Utils.DataBase;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javax.mail.MessagingException;

/**
 * FXML Controller class
 *
 * @author VENOM
 */
public class FXMLShowProfessionalController implements Initializable {

    @FXML
    private JFXButton show;
      @FXML
    private Button Delete;
    @FXML
    private TableView<Professional> table;
    @FXML
    private TableColumn<Professional, String> C1;
    @FXML
    private TableColumn<Professional, String> C2;
    @FXML
    private TableColumn<Professional, String> C3;
    @FXML
    private TableColumn<Professional, String> C4;
    
    Connection con=DataBase.getInstance().getCnx();
     	     ServiceProfessional sp = new ServiceProfessional();
    @FXML         
    private AnchorPane avr;
    @FXML
    private Button Update;
    private  Professional  selectedid;
    @FXML
    private ImageView imagePro;
    @FXML
    private Button accept;


   /* 
    @FXML
        void showData(ActionEvent event) throws SQLException, ClassNotFoundException {

        Connection con=MyDbConnection.getInstance().getConnexion();
 	    	
	    ObservableList<Professional> data = FXCollections.observableArrayList();
	     ServiceProfessional sp = new ServiceProfessional();
             table.setItems(sp.getAllPro());
             System.out.println(sp.getAllPro());
	   }
        
        */
        
        
         @FXML
    private void Delete(ActionEvent event) throws SQLException {
        
        if(!table.getSelectionModel().getSelectedItems().isEmpty()){
            ServiceProfessional sp=new ServiceProfessional();
             Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Do you confirm deleting this candidate?");
                alert.showAndWait();
           sp.deleteProfessional(table.getSelectionModel().getSelectedItems().get(0).getId());
        }
       else{
           
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("aucun élément 'a ètè séléctionné");
        alert.showAndWait();

           
        
        }
    }
  
    
    /*@FXML
    void update (ActionEvent event) throws  IOException{
        avr.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("/gui/UpdateProfessional.fxml"));
        avr.getChildren().add(parent);
        avr.toFront();
    }*/
    
      @FXML
    void Update(ActionEvent event) throws  IOException{
         avr.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("/gui/UpdateProfessional.fxml"));
        avr.getChildren().add(parent);
        avr.toFront();
    }
    
    @FXML
    public void select(){
        selectedid = (Professional) table.getSelectionModel().getSelectedItem();
        Image image = new Image("http://localhost//pidev-java/Events/src/Images/CV/" + selectedid.getImage());
        imagePro.setImage(image);
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ObservableList<Professional> Professional = FXCollections.observableArrayList();
        try {
            for (Professional p : sp.getAllPro()) {
                Professional.add(p);
                
                C1.setCellValueFactory(data -> {
                    return new ReadOnlyStringWrapper(data.getValue().getProname());
                });
                C2.setCellValueFactory(data -> {
                    return new ReadOnlyStringWrapper(data.getValue().getProsurname());
                });
                C3.setCellValueFactory(data -> {
                    return new ReadOnlyStringWrapper(data.getValue().getEmail());
                });
                C4.setCellValueFactory(data -> {
                    return new ReadOnlyStringWrapper(data.getValue().getSoftskills());
                });
                table.setItems(Professional);
                
            }       } catch (SQLException ex) {
            Logger.getLogger(FXMLShowProfessionalController.class.getName()).log(Level.SEVERE, null, ex);
        }
}

    @FXML
    private void accept(ActionEvent event) throws IOException {
         avr.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("/gui/FXMLMail.fxml"));
        avr.getChildren().add(parent);
        avr.toFront();
    }
}