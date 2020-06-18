/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Events;
import Services.EventService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author fedi
 */
public class UpdateEventController implements Initializable {

    @FXML
    private Button edit;
    @FXML
    private Button Back;
    @FXML
    private AnchorPane avr;
    @FXML
    private AnchorPane Fields;
    @FXML
    private TextField Titrech;
    @FXML
    private TextField Descriptionch;
    EventService es = new EventService();
    @FXML
    private ImageView ImgEvent;
    public static int id;
    private static int idEvent;
    EventService evt = new EventService();

    Events e = evt.affichereventparid(id);

    @FXML
    void Back(ActionEvent event) throws IOException {
        avr.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("/gui/EventsBack.fxml"));
        avr.getChildren().add(parent);
        avr.toFront();
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

            int id_ev = e.getId();

            Events e3 = new Events(id_ev, Titrech.getText(), Descriptionch.getText());
            es.maj(e3);

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Updated successfully");
            alert.showAndWait();
            
            try {
                avr.getChildren().clear();
                Parent parent = FXMLLoader.load(getClass().getResource("/gui/EventsBack.fxml"));
                avr.getChildren().add(parent);
                avr.toFront();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }

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
        Titrech.setText(e.getTitre());
        Descriptionch.setText(e.getDescription());
        Image image = new Image("http://localhost//projet_3a/symfony/web/images/" + e.getNom_image());
        ImgEvent.setImage(image);
        idEvent = e.getId();

    }
}
