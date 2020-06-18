/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import static Controllers.EvaluationDetailController.id;
import Entities.Events;
import Entities.Reservations;
import java.util.Optional;
import java.awt.AWTException;
import Services.EventService;
import Services.ReservationService;
import com.itextpdf.text.log.Level;
import com.itextpdf.text.log.Logger;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;



/**
 * FXML Controller class
 *
 * @author h^
 */
public class ReservationDetailController implements Initializable {

    @FXML
    private AnchorPane avr;
    @FXML
    private ImageView image1;
    @FXML
    private Label nomUser;
    @FXML
    private Label nomevent;
    @FXML
    private ImageView image;
    @FXML
    private JFXButton Back;
    @FXML
    private Button Delete;
    @FXML
    private Text quantite;
     public static int id;
    public static int idUser;
     ReservationService evs = new ReservationService();
    Reservations eva = evs.afficherreservationparidEventidUser(id, idUser);
    EventService evt = new EventService();
    Events e = evt.affichereventparid(id);
    private int quantitee;
    @FXML
    private Label price;
    private int prix;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          nomUser.setText(Main.fos_user.getNom());
        nomevent.setText(e.getTitre());
        quantitee=(int)eva.getQuantite();
        prix = (int)eva.getPrixpaye();
        quantite.setText(Integer.toString(quantitee));
        price.setText(Integer.toString(prix));
        
       

        String img = "http://localhost//projet_3a/symfony/web/images/" + e.getNom_image();
        image.setImage(new Image(img));
    }    

    @FXML
    private void retourner(ActionEvent event) throws IOException {
         DetailEventController.id = id;
        avr.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("/gui/DetailEvent.fxml"));
        avr.getChildren().add(parent);
        avr.toFront();
    }

    @FXML
    private void Delete(ActionEvent event) throws IOException, SQLException {
          ReservationService evs = new ReservationService();
          Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("WARNING");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to delete your reservation ?");
            
            Optional<ButtonType> action = alert.showAndWait();
          if (action.get() == ButtonType.OK) {
            try {
               EventService es = new EventService();
             es.increment_nb(id);
             evs.supprimer(eva.getId());
              String tit = "Reservation deleted";
            String message = "Reservation deleted successfully";
            NotificationType notification = NotificationType.SUCCESS;

            TrayNotification tray = new TrayNotification(tit, message, notification);
            tray.setAnimationType(AnimationType.POPUP);
            tray.showAndDismiss(javafx.util.Duration.seconds(10));
            
            DetailEventController.id = id;
            
            avr.getChildren().clear();
            Parent parent = FXMLLoader.load(getClass().getResource("/gui/DetailEvent.fxml"));
            avr.getChildren().add(parent);
            avr.toFront();
            } catch (Exception ex) {
                java.util.logging.Logger.getLogger(ReservationDetailController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
          }       
    }
    
}
