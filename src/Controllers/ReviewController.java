/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Avis;
import Entities.Product;
import Services.ServiceRate;
import Services.ServicesAvis;
import Utils.DataBase;
import Utils.NotificationWindows;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.awt.AWTException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author sarra
 */
public class ReviewController implements Initializable {
    
    
    NotificationWindows np = new NotificationWindows();
    @FXML
    private JFXButton deletbnt;
    @FXML
    private JFXButton editbtn;
    @FXML
    private JFXButton addbtn;
    @FXML
    private TableColumn<Avis, String> nompcol;
    private JFXComboBox<String> nompcombo;
    @FXML
    private TableView<Avis> tableviewavis;
    
    ObservableList<Avis> oblist = FXCollections.observableArrayList();
    
    private JFXComboBox<String> cmbnp;
    @FXML
    private JFXTextField textnom;
    @FXML
    private TableView<Product> tableviewproduct;
    @FXML
    private TableColumn<Product, String> c1;
    @FXML
    private TableColumn<Product, String> c2;

    Connection c=DataBase.getInstance().getCnx();
    ServicesAvis srva = new ServicesAvis();
    ObservableList<Product> oblist1 = FXCollections.observableArrayList();
    private Product tab;
    private Avis tab1;
    private JFXComboBox<String> combonote;
    @FXML
    private Rating rate;
    @FXML
    private Label lrate;
    @FXML
    private TableColumn<Avis, String> ratecol;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          ObservableList<Object> data = FXCollections.observableArrayList();
            rate.ratingProperty().addListener(new ChangeListener<Number>() {
            @Override public void changed(ObservableValue<? extends Number> ov, Number t, Number t1) {
             lrate.setText(t1.toString());
            }

         
            
        });
      
    
        
        try {
           ResultSet rs = c.createStatement().executeQuery("SELECT id, nompr, prix  from product ");
            
            while(rs.next()){
                oblist1.add(new Product( rs.getInt("id"),rs.getString("nompr"), rs.getString("prix")));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReviewController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        
        c1.setCellValueFactory(new PropertyValueFactory<>("nompr"));
        c2.setCellValueFactory(new PropertyValueFactory<>("prix"));
        
        
        tableviewproduct.setItems(oblist1);
        
        afficher();
        
    }    
    
    

    @FXML
    private void deletbtn(ActionEvent event) {
       ObservableList<Avis> selectedRows, allPeople;
        allPeople = tableviewavis.getItems();
        selectedRows = tableviewavis.getSelectionModel().getSelectedItems();
        Avis da =  tableviewavis.getSelectionModel().getSelectedItem();
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
          alert.setTitle("Comfirmation");
          alert.setHeaderText(null);
          alert.setContentText("Êtes-vous sûr de supprimer ?");
          Optional<ButtonType> action = alert.showAndWait();
          if (action.get() == ButtonType.OK) {
           try {
               srva.supprimAvis(da.getId());
               tableviewavis.getItems().clear();
               
               clearfields();
               
               np.displayTray("supprimer", "OK");
               afficher();
           } catch (AWTException ex) {
               Logger.getLogger(ReviewController.class.getName()).log(Level.SEVERE, null, ex);
           }
        
    }
    }

    @FXML
    private void update(ActionEvent event) {
      
        
        String x1=lrate.getText();
        Avis da = tableviewavis.getSelectionModel().getSelectedItem();
        //srva.modifieravis(da.getId(), , 0, et, et);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
          alert.setTitle("Comfirmation");
          alert.setHeaderText(null);
          alert.setContentText("Êtes-vous sûr de modifier?");
          Optional<ButtonType> action = alert.showAndWait();
          if (action.get() == ButtonType.OK) {
            try {
                srva.modifieravis(da.getId(),x1);
                tableviewavis.getItems().clear();
                
                clearfields();
                
                np.displayTray("Modifier", "OK");
                afficher();
            } catch (AWTException ex) {
                Logger.getLogger(ReviewController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    }

    @FXML
    private void addbtn(ActionEvent event) throws SQLException {
        
        try {
            String x1=lrate.getText();
            int user_id = Main.user_id ;
            System.out.println(tab.getId());

           Avis a = new Avis(tab.getId(), x1, user_id);
            if(srva.recherche_prod(tab.getId(),user_id)<1)
            {srva.ajouteravis(a);
            tableviewavis.getItems().clear();
            clearfields();     
            np.displayTray("Ajouter", "OK");
            afficher();
            }
            else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("deja avis");
        alert.showAndWait();
            }
            afficher();
        } catch (AWTException ex) {
            Logger.getLogger(ReviewController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            
    }
    
     private void clearfields(){
        //notetext.clear();
        textnom.clear();
        
        
    }
     public void afficher()
    {

        ObservableList obeListe = FXCollections.observableList(srva.displayAll());
            
   
        nompcol.setCellValueFactory(new PropertyValueFactory<>("nomproduit"));
        //notecol.setCellValueFactory(new PropertyValueFactory<>("note"));
        ratecol.setCellValueFactory(new PropertyValueFactory<>("rate"));
       
        tableviewavis.setItems(oblist);
        
        tableviewavis.setItems(obeListe);
        
    }

    @FXML
    private void select(MouseEvent event) {
        
        tab = tableviewproduct.getSelectionModel().getSelectedItem();
        
        if(tab !=null){
            
          //idp.setText(String.valueOf(tab.getId()));
          textnom.setText(String.valueOf(tab.getNompr()));

            
        }
            
    }

    @FXML
    private void select2(MouseEvent event) {
        
        tab1 = tableviewavis.getSelectionModel().getSelectedItem();
        
        if(tab1 !=null){
            
          textnom.setText(String.valueOf(tab1.getNomproduit()));
          //notetext.setText(String.valueOf(tab1.getNote()));
    }
    
}

    private void rate(ActionEvent event) throws SQLException {
        ServiceRate sr = new ServiceRate();
        String x1=lrate.getText();
        sr.RateApp(x1);
         System.err.println("insertion effectue");
        
    }
}
