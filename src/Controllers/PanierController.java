/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;


import javafx.scene.control.cell.PropertyValueFactory;
import Entities.Lignepanier;
import Entities.Panier;
import Entities.Product;
import Services.LignePanierService;
import Services.ProductService;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author cyrine
 */
public class PanierController implements Initializable {
    
   
   
    
     @FXML
    private AnchorPane avr;

    @FXML
    private TableView<Lignepanier> table;

    @FXML
    private TableColumn<Lignepanier, String> nompr;
    
    @FXML
    private TableColumn<Lignepanier, String> descrip;
    
    @FXML
    private TableColumn<Lignepanier, Double> prix;

    @FXML
    private TableColumn<Lignepanier, String> quantite;

    @FXML
    private Button aff;

    @FXML
    private Label PrixTotal;

    @FXML
    private Button delete;

    @FXML
    private Button comm;
    
    
     public ObservableList<Lignepanier> data = FXCollections.observableArrayList();
  
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void deleteItem(ActionEvent event) throws SQLException {
        LignePanierService l=new LignePanierService();
        if(table.getSelectionModel().getSelectedItems().size()!=0){
            
           l.supprimerLigne(table.getSelectionModel().getSelectedItems().get(0).getId());
        }
       else{
           
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("aucun élément 'a ètè séléctionné");
        alert.showAndWait(); 
       }
    }
    
    
   public void affic(ActionEvent event) throws SQLException {
      
       LignePanierService ser = new LignePanierService();
    nompr.setCellValueFactory(new PropertyValueFactory<Lignepanier,String>("nompr"));
    descrip.setCellValueFactory(new PropertyValueFactory<Lignepanier,String>("descrip"));
     prix.setCellValueFactory(new PropertyValueFactory<Lignepanier,Double>("prix"));
     quantite.setCellValueFactory(new PropertyValueFactory<Lignepanier,String>("quantite"));
        data =ser.indexAction();
        System.out.println(data);
        table.setItems(data);
        System.out.println(data);
       }
    
    
    
    
}
