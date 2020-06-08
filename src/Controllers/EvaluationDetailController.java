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
import com.jfoenix.controls.JFXButton;
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
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import tray.notification.TrayNotification;
import tray.notification.NotificationType;
import tray.animations.AnimationType;

/**
 * FXML Controller class
 *
 * @author h^
 */
public class EvaluationDetailController implements Initializable {

    public static int id;
    public static int idUser;
    EvaluationService evs = new EvaluationService();
    Evaluations eva = evs.afficherevaluationparidEventidUser(id, idUser);
    EventService evt = new EventService();
    Events e = evt.affichereventparid(id);
    @FXML
    private ImageView image1;
    @FXML
    private Label nomUser;
    @FXML
    private Label nomevent;
    @FXML
    private ImageView image;
    @FXML
    private Text commentaire;
    @FXML
    private JFXButton Back;
    @FXML
    private ImageView adress11;
    @FXML
    private ImageView image2;
    @FXML
    private ImageView image3;
    @FXML
    private ImageView image4;
    @FXML
    private ImageView image5;
    @FXML
    private ImageView image6;
    @FXML
    private ImageView image61;
    @FXML
    private AnchorPane avr;
    @FXML
    private Button Delete;
    @FXML
    private Button Update;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nomUser.setText(Main.fos_user.getNom());
        nomevent.setText(e.getTitre());
        commentaire.setText(eva.getCommentaire());
        if (eva.getNote() == 1) {
            image2.setVisible(true);
            image3.setVisible(false);
            image4.setVisible(false);
            image5.setVisible(false);
            image6.setVisible(false);
        } else if (eva.getNote() == 2) {
            image2.setVisible(false);
            image3.setVisible(true);
            image4.setVisible(false);
            image5.setVisible(false);
            image6.setVisible(false);
        } else if (eva.getNote() == 3) {
            image2.setVisible(false);
            image3.setVisible(false);
            image4.setVisible(true);
            image5.setVisible(false);
            image6.setVisible(false);
        } else if (eva.getNote() == 4) {
            image2.setVisible(false);
            image3.setVisible(false);
            image4.setVisible(false);
            image5.setVisible(true);
            image6.setVisible(false);
        } else if (eva.getNote() == 5) {
            image2.setVisible(false);
            image3.setVisible(false);
            image4.setVisible(false);
            image5.setVisible(false);
            image6.setVisible(true);
        }

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
    private void Delete(ActionEvent event) throws IOException {
        EvaluationService evs = new EvaluationService();
          Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("WARNING");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to delete your evaluation ?");
            alert.showAndWait();
             evs.supprimer(eva.getId());
                
              String tit = "Evaluation deleted";
            String message = "Evaluation deleted successfully";
            NotificationType notification = NotificationType.SUCCESS;

            TrayNotification tray = new TrayNotification(tit, message, notification);
            tray.setAnimationType(AnimationType.POPUP);
            tray.showAndDismiss(javafx.util.Duration.seconds(10));
            
            DetailEventController.id = id;
            
            avr.getChildren().clear();
            Parent parent = FXMLLoader.load(getClass().getResource("/gui/DetailEvent.fxml"));
            avr.getChildren().add(parent);
            avr.toFront();
            

    }

    @FXML
    private void Update(ActionEvent event) throws IOException {
         UpdateEvaluationController.idEvent = e.getId();
         UpdateEvaluationController.id = eva.getId();

        avr.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("/gui/UpdateEvaluation.fxml"));
        avr.getChildren().add(parent);
        avr.toFront();
    }

}
