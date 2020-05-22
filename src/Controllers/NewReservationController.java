/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import static Controllers.Main.fos_user;
import Entities.Events;
import Entities.Reservations;
import Services.EventService;
import Services.ReservationService;
import Utils.DataBase;
import Utils.MailFedy;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
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
import javax.mail.MessagingException;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class NewReservationController implements Initializable {

    @FXML
    private AnchorPane btnAjouter;
    @FXML
    private Button refresh;
    @FXML
    private TextField PrixPayech;
    @FXML
    private JFXSlider Quantitech;
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
    double prixapaye;
    EventService es = new EventService();
    ReservationService rs = new ReservationService();
    Events e = new Events();
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
            PrixPayech.setText(Double.toString(prixapaye = (Quantitech.getValue() * selected.getPrix())));
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
    private void ajouter(ActionEvent event) throws SQLException, MessagingException {
        if (validateInputs()) {

            String titre = Titrech.getText();
            int id_ev = table.getSelectionModel().getSelectedItem().getId();
            int nbrPlaces = table.getSelectionModel().getSelectedItem().getNbrPlaces();
            double prix = (Double) Double.parseDouble(PrixPayech.getText()) + 0;
            double quantite = Quantitech.getValue();
            int user_id = Main.user_id ;

            if (nbrPlaces == 0) {
                System.out.println("Reservation conceled");

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Oups ! not enough places");
                alert.showAndWait();

            } else {

               
                ReservationService rs = new ReservationService();
                Reservations r = new Reservations(quantite, prix, id_ev, user_id);
                rs.ajouter(r);
                System.out.println("Reservation added");
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Reservation made, You'll pay on the day of the event , a mail is sent to " +fos_user.getEmail());
                alert.showAndWait();
                sendMail();
                
                es.decrement_nb(id_ev);
                table.setItems(es.afficher());

            }
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
  private void sendMail() throws MessagingException{
        MailFedy.sendMail(Main.fos_user.getEmail());
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
        if (PrixPayech.getText().length() == 0) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Empty field");
            alert.showAndWait();
            return false;
        }

        return true;
    }

   /* private void res(ActionEvent event) {
         {
            try {
                avr.getChildren().clear();
                Parent parent = FXMLLoader.load(getClass().getResource("/gui/ShowReservationFront.fxml"));
                avr.getChildren().add(parent);
                avr.toFront();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }*/

}
