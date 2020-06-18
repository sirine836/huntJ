/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Evaluations;
import Entities.Events;
import Entities.Reservations;
import Services.EvaluationService;
import Services.EventService;
import Services.ReservationService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author h^
 */
public class ShowReservationEventController implements Initializable {

    @FXML
    private TableColumn<Reservations, String> C1;
    @FXML
    private TableColumn<Reservations, String> C2;
    @FXML
    private TableColumn<Reservations, String> C3;
    EventService evt = new EventService();
    Events e = evt.affichereventparid(id);
    public static int id;
    private static int idEvent;
    ReservationService evs= new ReservationService();
    @FXML
    private TableView<Reservations> table;
    @FXML
    private ImageView image;
    @FXML
    private Label titre;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<Reservations> reservation = FXCollections.observableArrayList();
        for (Reservations ev : evs.afficherReservationparidev(id)) {
            reservation.add(ev);
            C1.setCellValueFactory(new PropertyValueFactory<>("username"));
            C2.setCellValueFactory(new PropertyValueFactory<>("quantite"));
            C3.setCellValueFactory(new PropertyValueFactory<>("prixpaye"));

            table.setItems(reservation);
           


        }
          String img = "http://localhost//projet_3a/symfony/web/images/" + e.getNom_image();
            image.setImage(new Image(img));
            titre.setText(e.getTitre());
    }    
    
}
