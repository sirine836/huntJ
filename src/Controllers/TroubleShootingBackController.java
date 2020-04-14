/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sarra;

import Entities.Reclamation;
import Services.ServiceReclamation;
import Utils.ConnectionBD;
import Utils.NotificationWindows;
import Utils.PDFconvert;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.awt.AWTException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author sarra
 */
public class TroubleShootingBackController implements Initializable {
    
    NotificationWindows np = new NotificationWindows();
    PDFconvert pdf = new PDFconvert();
    @FXML
    private TableView<Reclamation> tabletat;
    @FXML
    private TableColumn<Reclamation, String> c1;
    @FXML
    private TableColumn<Reclamation, String> c2;
    @FXML
    private TableColumn<Reclamation, String> c4;

    @FXML
    private JFXButton affbtn;
    @FXML
    private JFXComboBox<String> comboetat;
    @FXML
    private JFXTextField usertext;
    @FXML
    private JFXTextField prtext;
Connection c=ConnectionBD.getInstance().getCnx();
 ObservableList<Reclamation> oblist = FXCollections.observableArrayList();
     ServiceReclamation srvr = new ServiceReclamation();
    private Reclamation tab1;
    @FXML
    private JFXButton IMPBTN;
    @FXML
    private TableColumn<Reclamation, String> c3;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        comboetat.getItems().addAll("En attente","Traiter","En cours","Refuser");
        
        afficher();
        
    }    


    @FXML
    private void affcter(ActionEvent event) {
        
        String et="";
            et=comboetat.getSelectionModel().getSelectedItem();
        Reclamation da = tabletat.getSelectionModel().getSelectedItem();
        srvr.affecterrec(da.getId(), et);
                tabletat.getItems().clear();
                afficher();
        
        
    }

    @FXML
    private void select(MouseEvent event) {
        
        tab1 = tabletat.getSelectionModel().getSelectedItem();
        
        if(tab1 !=null){
            
          usertext.setText(String.valueOf(tab1.getNomuser()));
          prtext.setText(String.valueOf(tab1.getNomproduit()));
    }
        
        
    }
    
    public void afficher()
    {
    
        ObservableList obeListe = FXCollections.observableList(srvr.displayAll2());
            
   
        c3.setCellValueFactory(new PropertyValueFactory<>("nomuser"));
        c4.setCellValueFactory(new PropertyValueFactory<>("nomproduit"));
        c1.setCellValueFactory(new PropertyValueFactory<>("probleme"));
        c2.setCellValueFactory(new PropertyValueFactory<>("nometat"));

       
        tabletat.setItems(oblist);
        
        tabletat.setItems(obeListe);
    }

    @FXML
    private void ADD1(MouseEvent event) {
    }

    @FXML
    private void impbtn(ActionEvent event) {
        try {
            pdf.CreatePdf();
            np.displayTray("PDF Genere ! ", "OK");
        } catch (AWTException ex) {
            Logger.getLogger(TroubleShootingBackController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
