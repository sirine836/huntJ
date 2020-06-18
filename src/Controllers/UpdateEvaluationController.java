/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import static Controllers.EvaluationDetailController.id;
import static Controllers.NewEvaluationController.isInteger;
import Entities.Evaluations;
import Entities.Events;
import Services.EvaluationService;
import Services.EventService;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXTextField;
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

/**
 * FXML Controller class
 *
 * @author h^
 */
public class UpdateEvaluationController implements Initializable {

    @FXML
    private AnchorPane avr;
    @FXML
    private AnchorPane avr2;
    @FXML
    private AnchorPane Fields;
    @FXML
    private Button Back;
    @FXML
    private ImageView ImgEvent;
    @FXML
    private JFXSlider Notech;
    @FXML
    private JFXTextField Commentch;
    @FXML
    private Label Titre;
    @FXML
    private Button edit;
    public static int id;
    public static int idEvent;
    EvaluationService evs = new EvaluationService();
    Evaluations ev = evs.afficherevaluationparidEventidUser(idEvent, Main.fos_user.getId());
    EventService es = new EventService();
    Events e = es.affichereventparid(idEvent);
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Commentch.setText(ev.getCommentaire());
        Notech.setValue(ev.getNote());
        Image image = new Image("http://localhost//projet_3a/symfony/web/images/" + e.getNom_image());
        ImgEvent.setImage(image);
        idEvent = e.getId();
        Titre.setText(e.getTitre());
    }    

    @FXML
    private void Back(ActionEvent event) throws IOException {
             EvaluationDetailController.id = idEvent;
            EvaluationDetailController.idUser = Main.fos_user.getId();
            avr.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("/gui/EvaluationDetail.fxml"));
        avr.getChildren().add(parent);
        avr.toFront();
    }

    @FXML
    private void edit(ActionEvent event) {
          if (validateInputs()) {

            int id_ev = ev.getId();

            Evaluations e3 = new Evaluations(id_ev, Notech.getValue(), Commentch.getText(), e.getId(), Main.fos_user.getId());
            evs.maj(e3);

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Updated successfully");
            alert.showAndWait();
            
            try {
                EvaluationDetailController.id = idEvent;
            EvaluationDetailController.idUser=Main.fos_user.getId();
                avr.getChildren().clear();
                Parent parent = FXMLLoader.load(getClass().getResource("/gui/EvaluationDetail.fxml"));
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
        } else if (isInteger(Commentch.getText())){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Comment must be a string");
            alert.showAndWait();
                        return false;
        }

        return true;
    }
}
