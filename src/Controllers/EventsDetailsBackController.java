/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import static Controllers.DetailEventController.id;
import Entities.Events;
import Services.EventService;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author h^
 */
public class EventsDetailsBackController implements Initializable {

    @FXML
    private AnchorPane holderPane;
    @FXML
    private ImageView image1;
    @FXML
    private Label nomevent;
    @FXML
    private Label lieuxevent;
    @FXML
    private Label datedebe;
    @FXML
    private ImageView image;
    @FXML
    private Label prix;
    @FXML
    private Label nb;
    @FXML
    private Text description;
    @FXML
    private JFXButton Back;
    @FXML
    private ImageView adress;
    @FXML
    private ImageView adress1;
    @FXML
    private ImageView adress11;
    @FXML
    private ImageView adress111;
    @FXML
    private Label prix1;
    EventService evt = new EventService();
    Events e = evt.affichereventparid(id);
    public static int id;
    private static int idEvent;
    @FXML
    private Button Delete;
    @FXML
    private Button Update;
    @FXML
    private JFXButton eva;
    @FXML
    private JFXButton res2;



    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
  DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        nomevent.setText(e.getTitre());
        lieuxevent.setText(e.getLocalisation());
        prix.setText(Double.toString(e.getPrix()));
        description.setText(e.getDescription());
        nb.setText(Integer.toString(e.getNbrPlaces()));
        datedebe.setText(e.getDate());
        //date_fine.setValue(e.getDate_fine().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        String img = "http://localhost//projet_3a/symfony/web/images/" + e.getNom_image();
        image.setImage(new Image(img));
        idEvent = e.getId();
     
         Date d1 = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String d2 = dateFormat.format(d1);
        System.out.println("nous somme le : " + d2);

        if (e.getDate().compareTo(d2) < 0) {
            res2.setVisible(false);

        }

        if (e.getDate().compareTo(d2) > 0) {
            eva.setVisible(false);
        }

    }    

    @FXML
    private void retourner(ActionEvent event) throws IOException {
        AnchorPane page1 = FXMLLoader.load(getClass().getResource("/gui/EventsBack.fxml"));
        setNode(page1);

    }
    
     public void setNode(Node node) {
        holderPane.getChildren().clear();
        holderPane.getChildren().add((Node) node);

        FadeTransition ft = new FadeTransition(Duration.millis(1000));
        ft.setNode(node);
        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();
    }

    @FXML
    private void Delete(ActionEvent event) {
            EventService es = new EventService();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("WARNING");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to delete event ?");
            alert.showAndWait();
            es.supprimer(e.getId());
    }

    @FXML
    private void Update(ActionEvent event) throws IOException {
        UpdateEventController.id = e.getId();
        holderPane.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("/gui/UpdateEvent.fxml"));
        holderPane.getChildren().add(parent);
        holderPane.toFront();
    }

    @FXML
    private void ShowEvaluations(ActionEvent event) throws IOException {
        
         ShowEvaluationsEventController.id = e.getId();
        holderPane.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("/gui/ShowEvaluationsEvent.fxml"));
        holderPane.getChildren().add(parent);
        holderPane.toFront();
    }

    @FXML
    private void ShowReservations(ActionEvent event) throws IOException {
        ShowReservationEventController.id = e.getId();
        holderPane.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("/gui/SowReservationEvent.fxml"));
        holderPane.getChildren().add(parent);
        holderPane.toFront();
    }
    
    
}
