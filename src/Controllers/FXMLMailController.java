/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Professional;
import Services.ServiceProfessional;
import Utils.Mailing;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javax.mail.MessagingException;

/**
 * FXML Controller class
 *
 * @author VENOM
 */
public class FXMLMailController implements Initializable {

    @FXML
    private AnchorPane avr;
    @FXML
    private TextField email;
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
    ServiceProfessional sp = new ServiceProfessional();
    @FXML
    private JFXButton send;

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
    void send() throws MessagingException{
       // Professional selected = table.getSelectionModel().getSelectedItem();
        // if (!table.getSelectionModel().getSelectedItems().isEmpty()) {
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Do you confirm sending acceptation mail?");
                alert.showAndWait();
            String email2= email.getText();
            Mailing.sendMail(email2);


       /* } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("No element selected");
            alert.showAndWait();
        }*/
    }
    
    
    private boolean validateInputs() {
        if (email.getText().length() == 0) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("tous les champs doivent etre remplis");
            alert.showAndWait();
            return false;
        }

        return true;
    }

    @FXML
    private void select(MouseEvent event) {
        
        String email1= table.getSelectionModel().getSelectedItem().getEmail();
        email.setText(email1);
    }
    
}
