/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Seller;
import Services.ServiceSeller;
import Utils.DataBase;
import com.jfoenix.controls.JFXButton;
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
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author VENOM
 */
public class FXMLUpdateSellerController implements Initializable {

    @FXML
    private TextField taxnumber;
    @FXML
    private TextField rcs;
    @FXML
    private TextField sellername;
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

    ServiceSeller ss = new ServiceSeller();

    @FXML
    private Button refresh;
    @FXML
    private Button edit;
    @FXML
    private AnchorPane avr;


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
            Logger.getLogger(FXMLUpdateSellerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /* @FXML
    private void updateSeller(ActionEvent event) {
        
        String Company= sellername.getText();
        String RCS = rcs.getText();
        String TAX = taxnumber.getText();
        String TVA = tva.getText();
        String SIREN = siren.getText();
        String FAX = fax.getText();
        String Phone = phonenumber.getText();
        String Email = email.getText();
        int intTVA = Integer.parseInt(TVA);
        int intSIREN = Integer.parseInt(SIREN);
        int intFAX = Integer.parseInt(FAX);
        int intPHONE = Integer.parseInt(Phone);
        ServiceSeller ss = new ServiceSeller();
        Seller s = new Seller(Company, RCS, TAX, intTVA, intSIREN, intFAX, intPHONE, Email);
        ss.editSeller();
        System.out.println("ADDED SUCCESSFULLY!");
            
    }*/

    @FXML
    void select() {
        Seller selected = table.getSelectionModel().getSelectedItem();
        if (!table.getSelectionModel().getSelectedItems().isEmpty()) {
            sellername.setText(selected.getSellername());
            rcs.setText(selected.getRcs());
            taxnumber.setText(selected.getTaxnumber());
            tva.setText(Integer.toString(selected.getTva()));
            siren.setText(Integer.toString(selected.getSiren()));
            fax.setText(Integer.toString(selected.getFax()));
            phonenumber.setText(Integer.toString(selected.getPhonenumber()));
            email.setText(selected.getEmail());

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("No element selected");
            alert.showAndWait();
        }
    }

    private boolean validateInputs() {
        if (rcs.getText().length() == 0 || taxnumber.getText().length() == 0 || tva.getText().length() == 0 || siren.getText().length() == 0 || fax.getText().length() == 0
                || phonenumber.getText().length() == 0 || email.getText().length() == 0 || sellername.getText().length() == 0) {

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
    void edit() { 
        if (validateInputs()) {

            Seller s = new Seller();
            Seller s2 = table.getSelectionModel().getSelectedItem();
            int tva2 = (Integer) Integer.parseInt(tva.getText()) + 0;
            int siren2 = (Integer) Integer.parseInt(siren.getText()) + 0;
            int fax2 = (Integer) Integer.parseInt(fax.getText()) + 0;
            int phone2 = (Integer) Integer.parseInt(phonenumber.getText()) + 0;

            int id_sel = s2.getId();

            Seller s3 = new Seller(id_sel, sellername.getText(), rcs.getText(), taxnumber.getText(), tva2, siren2, fax2, phone2, email.getText());
               Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Do you confirm updating informations for this candidate?");
                alert.showAndWait();
            ss.maj(s3);

            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Updated successfully");
            alert.showAndWait();

        }
    }

    @FXML
    private void refresh(ActionEvent event) throws SQLException {
    Connection con=DataBase.getInstance().getCnx();

        ObservableList<Seller> data = FXCollections.observableArrayList();
        ServiceSeller es = new ServiceSeller();
        table.setItems(es.getAllSeller());
        System.out.println(es.getAllSeller());

    }

}


