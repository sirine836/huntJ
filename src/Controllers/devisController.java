/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Lignepanier;
import Entities.Panier;
import Services.LignePanierService;
import Services.PanierService;
import config.Config;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;



/**
 * FXML Controller class
 *
 * @author cyrine
 */
public class devisController implements Initializable {
    
    @FXML
    private Label calc;
    

    
     
      @FXML
    private AnchorPane avr;

    @FXML
    private TableView<Lignepanier> tabled;
    
    @FXML
    private TableColumn<Lignepanier, String> idlp;

    @FXML
    private TableColumn<Lignepanier, String> nompr;
    
    @FXML
    private TableColumn<Lignepanier, String> descrip;
    
    @FXML
    private TableColumn<Lignepanier, Double> prix;

    @FXML
    private TableColumn<Lignepanier, String> quantite;
    @FXML
    private Label setnom;
    @FXML
    public ObservableList<Lignepanier> data = FXCollections.observableArrayList();
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         LignePanierService ser = new LignePanierService();
       Lignepanier l= new Lignepanier();
    nompr.setCellValueFactory(new PropertyValueFactory<Lignepanier,String>("nompr"));
    prix.setCellValueFactory(new PropertyValueFactory<Lignepanier,Double>("prix"));
    quantite.setCellValueFactory(new PropertyValueFactory<Lignepanier,String>("quantite"));
      
    nompr.setStyle("-fx-alignment: CENTER;");
    prix.setStyle("-fx-alignment: CENTER;");
    quantite.setStyle("-fx-alignment: CENTER;");
        try {
            calc.setText(String.valueOf(ser.calcul_total(Config.currentpanier)));
        } catch (SQLException ex) {
            Logger.getLogger(devisController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        data =ser.indexActiondevis(Config.currentpanier);
        System.out.println("aaaaa"+data);
        tabled.setItems(data);
        System.out.println(data);

    }  
    
    
     @FXML
    private void backImageV(ActionEvent event) throws IOException {
         try {
        javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("Panier.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }  
    }
    

    
   
    }    
    

