/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Facture;
import Entities.Lignepanier;
import Services.FactureService;
import Services.LignePanierService;
import com.jfoenix.controls.JFXButton;
import config.Config;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author cyrine
 */
public class AfficherFactureController implements Initializable {

    @FXML
    private AnchorPane firstpane;
    
    @FXML
    private JFXButton backImageV;
    @FXML
    private Label calc1;

    @FXML
    private Label calc;
   
    
     @FXML
    private AnchorPane anc;

     
      @FXML
    private AnchorPane avr;

    @FXML
    private TableView<Facture> tablef;
    
    @FXML
    private TableColumn<Facture, Integer> numf;

    @FXML
    private TableColumn<Facture, String> adresse;
    
    @FXML
    private TableColumn<Facture, Integer> etat;
    @FXML
    private TableColumn<Facture, ? > date;
   
   @FXML
    private Button btn;
    
    @FXML
    public ObservableList<Facture> data = FXCollections.observableArrayList();
    

    /**
     * Initializes the controller class.
     */
@Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        LignePanierService ser = new LignePanierService();
         FactureService fer = new FactureService();
       Lignepanier l= new Lignepanier();
       
       String dateliv = date.getText();
    numf.setCellValueFactory(new PropertyValueFactory<Facture,Integer>("id"));
    adresse.setCellValueFactory(new PropertyValueFactory<Facture,String>("adresse"));
   // date.setCellValueFactory(new PropertyValueFactory<Facture,DateTimeFormatter>("dateDeLivraison"));
    etat.setCellValueFactory(new PropertyValueFactory<Facture,Integer>("etat"));
      
    numf.setStyle("-fx-alignment: CENTER;");
    date.setStyle("-fx-alignment: CENTER;");
    etat.setStyle("-fx-alignment: CENTER;");
        try {
            calc.setText(String.valueOf(ser.calcul_total(Config.currentpanier)));
        } catch (SQLException ex) {
            Logger.getLogger(devisController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        data =fer.indexActionfacture(Config.currentpanier);
        System.out.println("aaaaa"+data);
        tablef.setItems(data);
        System.out.println(data);

    }  
    
    
     @FXML
    private void backImageV(ActionEvent event) throws IOException {
         try {
        firstpane.getChildren().clear();
           Parent parent = FXMLLoader.load(getClass().getResource("Panier.fxml"));
           firstpane.getChildren().add(parent);
           firstpane.toFront();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }  
    }
    
}
