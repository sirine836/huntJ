/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sarra;

import Entities.Avis;
import Services.ServicesAvis;
import Utils.ConnectionBD;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author sarra
 */
public class ReviewBackController implements Initializable {

    @FXML
    private TableView<Avis> tableavisback;
    @FXML
    private TableColumn<Avis, String> C1;
    @FXML
    private TableColumn<Avis, String> C2;
    @FXML
    private TableColumn<Avis, String> C4;
    
        ObservableList<Avis> oblist = FXCollections.observableArrayList();

     Connection c=ConnectionBD.getInstance().getCnx();
    ServicesAvis srva = new ServicesAvis();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        afficher();
    }    
    
    
     public void afficher()
    {

        ObservableList obeListe = FXCollections.observableList(srva.displayAll2());
            
   
        C1.setCellValueFactory(new PropertyValueFactory<>("nomuser"));
        C2.setCellValueFactory(new PropertyValueFactory<>("nomproduit"));
       
        C4.setCellValueFactory(new PropertyValueFactory<>("rate"));
       
        tableavisback.setItems(oblist);
        
        tableavisback.setItems(obeListe);
        
    }
    
}
