/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Evaluations;
import Entities.Events;
import Services.EvaluationService;
import Services.EventService;
import Utils.DataBase;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class NewEvaluationController implements Initializable {

    @FXML
    private AnchorPane btnAjouter;
    @FXML
    private Button refresh;
    @FXML
    private TextField Commentch;
    @FXML
    private JFXSlider Notech;
    @FXML
    private JFXTextField Titrech;
    @FXML
    private Button consulter;
    @FXML
    private TableView<Events> table;
    @FXML
    private TableColumn<Events, String> C1;
    @FXML
    private TableColumn<Events, String> C2;
    @FXML
    private TableColumn<Events, String> C3;
    @FXML
    private TableColumn<Events, String> C4;

    @FXML
    private AnchorPane avr;
    @FXML
    private Button btnAjout;
    @FXML
    private ImageView ImgEvent;
    
        private Events selectedid;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        C1.setCellValueFactory(data -> {
            return new ReadOnlyStringWrapper(data.getValue().getTitre());
        });
        C2.setCellValueFactory(data -> {
            return new ReadOnlyStringWrapper(data.getValue().getDescription());
        });

        C3.setCellValueFactory(new PropertyValueFactory<Events, String>("prix"));

        C4.setCellValueFactory(new PropertyValueFactory<Events, String>("date"));

        ObservableList<Events> data = FXCollections.observableArrayList();
    }

    @FXML
    void select() {
        Events selected = table.getSelectionModel().getSelectedItem();
        if (!table.getSelectionModel().getSelectedItems().isEmpty()) {
            Titrech.setText(selected.getTitre());
            selectedid = (Events) table.getSelectionModel().getSelectedItem();
        Image image = new Image("http://localhost//pidev-java/Events/src/Images/Upload/" + selectedid.getImage());
        ImgEvent.setImage(image);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("No element selected");
            alert.showAndWait();
        }
    }

    @FXML
    private void ajouter(ActionEvent event) {
        if (validateInputs()) {

            String titre = Titrech.getText();
            int id = table.getSelectionModel().getSelectedItem().getId();
            String commentaire = Commentch.getText();
            double note = Notech.getValue();
            int user_id = Main.user_id;

            
            EvaluationService evs = new EvaluationService();
            Evaluations ev = new Evaluations(id, note, commentaire, user_id);
            EventService es = new EventService();            
        
            evs.ajouter(ev);
            System.out.println("Evaluation ajouté");
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Evaluation bien ajouté");

            alert.showAndWait();
            table.setItems(es.afficher());

        }
    }

    @FXML
    private void refresh(ActionEvent event) {
        Connection c = DataBase.getInstance().getCnx();

        ObservableList<Events> data = FXCollections.observableArrayList();
        EventService es = new EventService();
        table.setItems(es.afficher());
        System.out.println(es.afficher());

    }

    @FXML
    private void consulter(ActionEvent event) {
        {
            try {
                avr.getChildren().clear();
                Parent parent = FXMLLoader.load(getClass().getResource("/gui/Events.fxml"));
                avr.getChildren().add(parent);
                avr.toFront();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    private boolean validateInputs() {
        if (Commentch.getText().length() == 0) {

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
