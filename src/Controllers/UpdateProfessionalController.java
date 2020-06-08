/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Professional;
import Services.ServiceProfessional;
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
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author VENOM
 */
public class UpdateProfessionalController implements Initializable {

    @FXML
    private AnchorPane avr;
    @FXML
    private TextField proname;
    @FXML
    private TextField prosurname;
    @FXML
    private TextField email;
    @FXML
    private TextField softskills;
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
    @FXML
    private Button refresh;
    @FXML
    private Button edit;
    ServiceProfessional sp = new ServiceProfessional();

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

            }
        } catch (SQLException ex) {
            Logger.getLogger(FXMLShowProfessionalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void select() {
        Professional selected = table.getSelectionModel().getSelectedItem();
        if (!table.getSelectionModel().getSelectedItems().isEmpty()) {
            proname.setText(selected.getProname());
            prosurname.setText(selected.getProsurname());
            email.setText(selected.getEmail());
            softskills.setText(selected.getSoftskills());

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("No element selected");
            alert.showAndWait();
        }
    }

    private boolean validateInputs() {
        if (proname.getText().length() == 0 || prosurname.getText().length() == 0 || email.getText().length() == 0 || softskills.getText().length() == 0) {

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
    private void refresh(ActionEvent event) throws SQLException {

        Connection con = DataBase.getInstance().getCnx();

        ObservableList<Professional> data = FXCollections.observableArrayList();
        ServiceProfessional es = new ServiceProfessional();
        table.setItems(es.getAllPro());
        System.out.println(es.getAllPro());
    }

    @FXML
    private void edit(ActionEvent event) {

        if (validateInputs()) {

            Professional p = new Professional();
            Professional p2 = table.getSelectionModel().getSelectedItem();

            int id_pro = p2.getId();

            Professional p3 = new Professional(id_pro, proname.getText(), prosurname.getText(), email.getText(), softskills.getText());
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Do you confirm updating informations for this candidate?");
            alert.showAndWait();
            sp.maj(p3);

        }
    }

}
