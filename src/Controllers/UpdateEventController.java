/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import static Controllers.NewEventController.isNotInteger;
import Entities.Events;
import Services.EventService;
import Utils.DataBase;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author h^
 */
public class UpdateEventController implements Initializable {

    @FXML
    private Button edit;
    @FXML
    private Button Back;
    @FXML
    private Button refresh;
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
    private AnchorPane Fields;
    @FXML
    private TextField Titrech;
    @FXML
    private TextField Descriptionch;
    EventService es = new EventService();
        private Events selectedid;
    @FXML
    private ImageView ImgEvent;
    @FXML
    private JFXButton Browser;


    @FXML
    void Back(ActionEvent event) throws IOException {
        avr.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("/gui/EventsBack.fxml"));
        avr.getChildren().add(parent);
        avr.toFront();
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
    void select() {
        Events selected = table.getSelectionModel().getSelectedItem();
        if (!table.getSelectionModel().getSelectedItems().isEmpty()) {
            Titrech.setText(selected.getTitre());
            Descriptionch.setText(selected.getDescription());
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
    void edit() {
        /*if (validateInputs()) { 
            String titre = Titrech.getText();
            String description = Descriptionch.getText();
       
        EventService es = new EventService();
        Events e = new Events(titre, description);
        es.maj(e);
        System.out.println("Evenement modifié");
         */

        if (validateInputs()) {

            Events e = new Events();
            Events e2 = table.getSelectionModel().getSelectedItem();
            int id_ev = e2.getId();

            Events e3 = new Events(id_ev, Titrech.getText(), Descriptionch.getText());
            es.maj(e3);

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Updated successfully");
            alert.showAndWait();

        }

    }

    private boolean validateInputs() {
        if (Titrech.getText().length() == 0 || Descriptionch.getText().length() == 0) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("tous les champs doivent etre remplis");
            alert.showAndWait();
            return false;
        }

        return true;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<Events> event = FXCollections.observableArrayList();
        for (Events e : es.afficher()) {
            event.add(e);
            C1.setCellValueFactory(data -> {
                return new ReadOnlyStringWrapper(data.getValue().getTitre());
            });
            C2.setCellValueFactory(data -> {
                return new ReadOnlyStringWrapper(data.getValue().getDescription());
            });

            C3.setCellValueFactory(new PropertyValueFactory<Events, String>("prix"));

            C4.setCellValueFactory(new PropertyValueFactory<Events, String>("date"));
            table.setItems(event);

            //	delete.setCellValueFactory(new PropertyValueFactory<>("true"));

            /*C2.setCellValueFactory(new PropertyValueFactory<Events,String>("description"));
		C3.setCellValueFactory(new PropertyValueFactory<Events,String>("prix"));
                C4.setCellValueFactory(new PropertyValueFactory<Events,String>("date"));
             */
        }
    }
}
