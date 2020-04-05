/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Lignepanier;
import Services.LignePanierService;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
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
    private Button delete;
    @FXML
    private TableView<Lignepanier> table;
    LignePanierService cs =new LignePanierService();
    @FXML
    private TableView<Lignepanier> TableId;
    @FXML
    private AnchorPane avr;
    @FXML
    private TableColumn<?, ?> nompr;
    @FXML
    private TableColumn<?, ?> descrip;
    @FXML
    private TableColumn<?, ?> prix;
    @FXML
    private TableColumn<?, ?> quantity;
    @FXML
    private Label PrixTotal;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void deleteItem(ActionEvent event) throws SQLException {
        
        if(TableId.getSelectionModel().getSelectedItems().size()!=0){
            LignePanierService l=new LignePanierService();
           l.supprimerLigne(TableId.getSelectionModel().getSelectedItems().get(0).getId());
        }
       else{
           
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("aucun élément 'a ètè séléctionné");
        alert.showAndWait();

           
        
       }
    }
    
   
    
}
