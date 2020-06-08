/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Product;
import Entities.Reclamation;
import Services.ServiceReclamation;
import Utils.DataBase;
import Utils.Mail;
import Utils.NotificationWindows;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.awt.AWTException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;


/**
 * FXML Controller class
 *
 * @author sarra
 */
public class TroubleShootingController implements Initializable {
        
    Mail m =new Mail();
    NotificationWindows np = new NotificationWindows();
    @FXML
    private JFXButton deletbnt;
    @FXML
    private JFXButton editbtn;
    @FXML
    private JFXButton addbtn;
    @FXML
    private JFXTextArea commentairetxt;
    @FXML
    private JFXTextField nomtext;
    @FXML
    private TableView<Product> tableprod;
    @FXML
    private TableColumn<Product, String> c1;
     ObservableList<Product> oblist1 = FXCollections.observableArrayList();
    @FXML
    private TableView<Reclamation> tablerec;
    @FXML
    private TableColumn<Reclamation, String> C2;
    @FXML
    private TableColumn<Reclamation, String> C4;
  
    
    ObservableList<Reclamation> oblist = FXCollections.observableArrayList();
    
            

    
    ServiceReclamation srvr = new ServiceReclamation();
    Connection c=DataBase.getInstance().getCnx();
    private Product tab;
    private Reclamation tab1;
    @FXML
    private TableColumn<?, ?> C5;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
    
    
        
        try {
           ResultSet rs = c.createStatement().executeQuery("SELECT id, nompr  from product ");
            
            while(rs.next()){
                oblist1.add(new Product( rs.getInt("id"),rs.getString("nompr")));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(TroubleShootingController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        
        c1.setCellValueFactory(new PropertyValueFactory<>("nompr"));
       
        
        tableprod.setItems(oblist1);
        
        afficher();
       
    }    
    
    

    @FXML
    private void deletbtn(ActionEvent event) {
        
        ObservableList<Reclamation> selectedRows, allPeople;
        allPeople = tablerec.getItems();
        selectedRows = tablerec.getSelectionModel().getSelectedItems();
        Reclamation da =  tablerec.getSelectionModel().getSelectedItem();
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
          alert.setTitle("Comfirmation");
          alert.setHeaderText(null);
          alert.setContentText("Êtes-vous sûr de supprimer ?");
          Optional<ButtonType> action = alert.showAndWait();
          if (action.get() == ButtonType.OK) {
            try {
                srvr.supprimreclamation(da.getId());
                tablerec.getItems().clear();
                
                clearfields();
                
                np.displayTray("Supprimer","Ok");
                afficher();
            } catch (AWTException ex) {
                Logger.getLogger(TroubleShootingController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
    }
    }
    @FXML
    private void update(ActionEvent event) {
        
       
        
        Reclamation da = tablerec.getSelectionModel().getSelectedItem();
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
          alert.setTitle("Comfirmation");
          alert.setHeaderText(null);
          alert.setContentText("Êtes-vous sûr de modifier?");
          Optional<ButtonType> action = alert.showAndWait();
          if (action.get() == ButtonType.OK) {
            try {
                srvr.modifierreclamation(da.getId(), commentairetxt.getText());
                tablerec.getItems().clear();
                
                clearfields();
                 np.displayTray("modifier","Ok");
               
                afficher();
            } catch (AWTException ex) {
                Logger.getLogger(TroubleShootingController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
          }
    }

    @FXML
    private void addbtn(ActionEvent event) throws SQLException {
        
        try {
           
            int user_id = Main.user_id ;

            
          
            Reclamation reec = new Reclamation(tab.getId(), commentairetxt.getText(), user_id);
            srvr.ajouterreclamation(reec);
            tablerec.getItems().clear();
            
            
            m.sendMail("sarra.dekhili@esprit.tn", "Reclamation","reclamation de user sur le produit  "+nomtext.getText()+"");
            np.displayTray("Ajouter et mail envoyer avec succes ","Ok");
            clearfields();
            afficher();
       } 
catch (AWTException ex) {
            Logger.getLogger(TroubleShootingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    private void clearfields(){
        commentairetxt.clear();
        nomtext.clear();
        
        
    }

    @FXML
    private void select(MouseEvent event) {
        
        tab = tableprod.getSelectionModel().getSelectedItem();
        
        if(tab !=null){
            
          //idp.setText(String.valueOf(tab.getId()));
          nomtext.setText(String.valueOf(tab.getNompr()));

            
        }
        
    }

    @FXML
    private void select2(MouseEvent event) {
        
        tab1 = tablerec.getSelectionModel().getSelectedItem();
        
        if(tab1 !=null){
            
          nomtext.setText(String.valueOf(tab1.getNomproduit()));
          commentairetxt.setText(String.valueOf(tab1.getProbleme()));
    }
        
    }
    
    public void afficher()
    {
    
        ObservableList obeListe = FXCollections.observableList(srvr.displayAll());
            
   
        C2.setCellValueFactory(new PropertyValueFactory<>("nomproduit"));

        C4.setCellValueFactory(new PropertyValueFactory<>("probleme"));
       
        tablerec.setItems(oblist);
        
        tablerec.setItems(obeListe);
    }
    
}
