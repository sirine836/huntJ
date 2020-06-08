/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Seller;
import Services.ServiceSeller;
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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javax.mail.MessagingException;

/**
 * FXML Controller class
 *
 * @author VENOM
 */
public class MailsellerController implements Initializable {

    @FXML
    private AnchorPane avr;
    @FXML
    private TableView<Seller> table;
    @FXML
    private TableColumn<Seller, String> C1;
    @FXML
    private TableColumn<Seller, String> C2;
    @FXML
    private TableColumn<Seller, String> C3;
    @FXML
    private TableColumn<Seller, String> C4;
    @FXML
    private TableColumn<Seller, String> C5;
    @FXML
    private TableColumn<Seller, String> C6;
    @FXML
    private TableColumn<Seller, String> C7;
    @FXML
    private TableColumn<Seller, String> C8;
    
    ServiceSeller ss= new ServiceSeller();
    Seller s = new Seller();
    @FXML
    private JFXButton send;
    @FXML
    private TextField email;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     ObservableList<Seller> Seller = FXCollections.observableArrayList();
        try {
            for (Seller s : ss.getAllSeller()) {
                Seller.add(s);

                C1.setCellValueFactory(data -> {
                    return new ReadOnlyStringWrapper(data.getValue().getSellername());
                });
                C2.setCellValueFactory(data -> {
                    return new ReadOnlyStringWrapper(data.getValue().getRcs());
                });
                C3.setCellValueFactory(data -> {
                    return new ReadOnlyStringWrapper(data.getValue().getTaxnumber());
                });
                C4.setCellValueFactory(new PropertyValueFactory<Seller, String>("tva"));
                C5.setCellValueFactory(new PropertyValueFactory<Seller, String>("siren"));
                C6.setCellValueFactory(new PropertyValueFactory<Seller, String>("fax"));
                C7.setCellValueFactory(new PropertyValueFactory<Seller, String>("phonenumber"));
                C8.setCellValueFactory(data -> {
                    return new ReadOnlyStringWrapper(data.getValue().getEmail());
                });
                table.setItems(Seller);

            }
        } catch (SQLException ex) {
            Logger.getLogger(FXMLShowSellerController.class.getName()).log(Level.SEVERE, null, ex);
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

    @FXML
    private void select(MouseEvent event) {
          
        String email1= table.getSelectionModel().getSelectedItem().getEmail();
        email.setText(email1);
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

}
