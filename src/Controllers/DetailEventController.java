/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Evaluations;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import Entities.Events;
import Entities.Reservations;
import Services.EvaluationService;
import Services.EventService;
import Services.ReservationService;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Fedi
 */
public class DetailEventController implements Initializable {

    private ObservableList<Evaluations> data;
    @FXML
    private Label nomevent;
    @FXML
    private Label lieuxevent;
    @FXML
    private Label datedebe;
    @FXML
    private Text description;
    private TextArea commentaires;
    @FXML
    private Label prix;
    private static int idEvent;

    private static int idUser = Main.fos_user.getId();
    @FXML
    private ImageView image;
    @FXML
    private AnchorPane avr;
    @FXML
    private Label nb;
    public static int id;
    @FXML
    private JFXButton btnevalua;
    @FXML
    private JFXButton btnreser;
    @FXML
    private JFXButton Back;
    @FXML
    private AnchorPane res1;
    @FXML
    private AnchorPane ev;
    EventService evt = new EventService();
    Events e = evt.affichereventparid(id);
    @FXML
    private ImageView image1;
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
    EvaluationService evs = new EvaluationService();

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
            res1.setVisible(false);

        }

        if (e.getDate().compareTo(d2) > 0) {
            ev.setVisible(false);
        }

    }

    @FXML
    private void btnreser(ActionEvent event) throws IOException {
        if (e.getNbrPlaces() == 0) {

            System.out.println("Reservation conceled");

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Oups ! not enough places");
            alert.showAndWait();

        } else {
            if (!exist2()) {
                NewReservationController.id = idEvent;
                avr.getChildren().clear();
                Parent parent = FXMLLoader.load(getClass().getResource("/gui/NewReservation.fxml"));
                avr.getChildren().add(parent);
                avr.toFront();
            } else {
                Alert alert1 = new Alert(Alert.AlertType.WARNING);
                alert1.setTitle("Accée réfusée");
                alert1.setContentText("You've already booked for this event! Ok to see your reservation's detail");
                alert1.setHeaderText(null);
                alert1.showAndWait();
                ReservationDetailController.id = idEvent;
                ReservationDetailController.idUser = idUser;
                avr.getChildren().clear();
                Parent parent = FXMLLoader.load(getClass().getResource("/gui/ReservationDetail.fxml"));
                avr.getChildren().add(parent);
                avr.toFront();
            }
        }
    }

    @FXML
    private void btnevalua(ActionEvent event) throws IOException {
        if (!exist()) {
            NewEvaluationController.id = idEvent;

            avr.getChildren().clear();
            Parent parent = FXMLLoader.load(getClass().getResource("/gui/NewEvaluation.fxml"));
            avr.getChildren().add(parent);
            avr.toFront();

        } else {
            Alert alert1 = new Alert(Alert.AlertType.WARNING);
            alert1.setTitle("Accée réfusée");
            alert1.setContentText("You've already evaluated this event!  Ok to see your evaluation's detail");
            alert1.setHeaderText(null);
            alert1.showAndWait();
            EvaluationDetailController.id = idEvent;
            EvaluationDetailController.idUser = idUser;
            avr.getChildren().clear();
            Parent parent = FXMLLoader.load(getClass().getResource("/gui/EvaluationDetail.fxml"));
            avr.getChildren().add(parent);
            avr.toFront();

        }
    }

    @FXML
    private void retourner(ActionEvent event) throws IOException {
        Date d1 = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String d2 = dateFormat.format(d1);
        System.out.println("nous somme le : " + d2);

        if (e.getDate().compareTo(d2) < 0) {
        AnchorPane page1 = FXMLLoader.load(getClass().getResource("/gui/AllEvents.fxml"));
        setNode(page1);
        }

        if (e.getDate().compareTo(d2) > 0) {
        AnchorPane page1 = FXMLLoader.load(getClass().getResource("/gui/AllComingEvents.fxml"));
        setNode(page1);        }
        
       

    }

    public void setNode(Node node) {
        avr.getChildren().clear();
        avr.getChildren().add((Node) node);

        FadeTransition ft = new FadeTransition(Duration.millis(1000));
        ft.setNode(node);
        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();
    }

    public boolean exist() {

        EvaluationService uvs = new EvaluationService();
        List<Evaluations> listUV = new ArrayList<>();
        listUV = uvs.FindByUserID(Main.user_id);

        if (listUV != null) {
            for (Evaluations uv : listUV) {
                if (uv.getEvent_id() == e.getId()) {
                    return true;
                }
            }
        }
        return false;

    }

    public boolean exist2() {
        ReservationService uvs = new ReservationService();
        List<Reservations> listUV = new ArrayList<>();
        listUV = uvs.FindByUserID(Main.user_id);

        if (listUV != null) {
            for (Reservations uv : listUV) {
                if (uv.getEvent_id() == e.getId()) {
                    return true;
                }
            }
        }
        return false;
    }

}
