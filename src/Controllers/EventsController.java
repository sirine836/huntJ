/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Events;
import Services.EventService;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import com.jfoenix.controls.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import Utils.DataBase;
import java.io.IOException;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author h^
 */
public class EventsController implements Initializable {

    @FXML
    private TextField recherchech;
    @FXML
    private Button Reserver;
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

    private Events selectedid;

    java.sql.PreparedStatement pst;
    @FXML
    private AnchorPane avr;
    Connection c = DataBase.getInstance().getCnx();
    EventService es = new EventService();
    @FXML
    private Button Evaluer;
    @FXML
    private Button btnRefresh;
    @FXML
    private Label NbrPlacesLa;
    @FXML
    private Label DateLa;
    @FXML
    private Label LocalisationLa;
    @FXML
    private Label titreLa;
    @FXML
    private ImageView ImgEvent;
    @FXML
    private Button BTDescription;
    @FXML
    private Label txtNBP;


    /*@FXML
     void select() {
        int nbrPlaces = table.getSelectionModel().getSelectedItem().getNbrPlaces();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("This event has " + nbrPlaces + " places");
        alert.showAndWait();

    }
     */
    
        @FXML
        public void select(){
        String titre = table.getSelectionModel().getSelectedItem().getTitre();
        String description = table.getSelectionModel().getSelectedItem().getDescription();
        int nbrPlaces = table.getSelectionModel().getSelectedItem().getNbrPlaces();
        double prix = table.getSelectionModel().getSelectedItem().getPrix();
        String date = table.getSelectionModel().getSelectedItem().getDate();
        int pro = table.getSelectionModel().getSelectedItem().getIdPro();
        String local = table.getSelectionModel().getSelectedItem().getLocalisation();
        titreLa.setText(titre);
        DateLa.setText(date);
        LocalisationLa.setText(local);
        NbrPlacesLa.setText(Integer.toString(nbrPlaces));
        selectedid = (Events) table.getSelectionModel().getSelectedItem();
        Image image = new Image("http://localhost//projet_3a/symfony/web/images/" + selectedid.getNom_image());
        ImgEvent.setImage(image);
        
        BTDescription.setOnAction(
        new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setTitle("Description");
            alert1.setContentText(description+" Aleez reservez votre place dès maintenant dans l'interface Réservation");
            alert1.setHeaderText(null);
            alert1.show();
            }
         });
        

    }
     
        
     
     
 /*@
    void Search(ActionEvent event) throws IOException{
        String titre = Searchch.getText();
        ObservableList<Events> data = FXCollections.observableArrayList();
        Events e = new Events(titre);
        table.setItems(es.rechercheEvent(titre));
        System.out.println(es.rechercheEvent(titre));
    }*/
     
    @FXML
    private void Refresh(ActionEvent event) {

        ObservableList<Events> data = FXCollections.observableArrayList();
        table.setItems(es.afficher());
        System.out.println(es.afficher());

    }
     
    private void recherche(ActionEvent event) {
        ObservableList<Events> events= FXCollections.observableArrayList();
            events= es.recherche(recherchech.getText());
     
         C1.setCellValueFactory(new PropertyValueFactory<>("titre"));
         C2.setCellValueFactory(new PropertyValueFactory<>("description"));
         C3.setCellValueFactory(new PropertyValueFactory<>("prix"));

         C4.setCellValueFactory(new PropertyValueFactory<>("date"));
  
         
   
       
        table.setItems(events);
        
    
    }
     
     
     
     
    @FXML
    private void Evaluate(ActionEvent event) throws IOException {
        avr.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("/gui/NewEvaluation.fxml"));
        avr.getChildren().add(parent);
        avr.toFront();
    }

   /* private void imageViewAdd(MouseEvent event) {

        selectedid = (Events) table.getSelectionModel().getSelectedItem();
        Image image = new Image("http://localhost//Events/" + selectedid.getImage());
        imageViewAdd.setImage(image);

    }
*/
    @FXML
    private void Reserver(ActionEvent event) throws IOException {
        avr.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("/gui/NewReservation.fxml"));
        avr.getChildren().add(parent);
        avr.toFront();
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

    @FXML
    private void Rechercher(KeyEvent event) {
         ObservableList<Events> events= FXCollections.observableArrayList();
            events= es.recherche(recherchech.getText());
     
         C1.setCellValueFactory(new PropertyValueFactory<>("titre"));
         C2.setCellValueFactory(new PropertyValueFactory<>("description"));
         C3.setCellValueFactory(new PropertyValueFactory<>("prix"));

         C4.setCellValueFactory(new PropertyValueFactory<>("date"));
  
         
   
       
        table.setItems(events);
    }

    
}
