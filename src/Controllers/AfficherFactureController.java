/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Facture;
import Entities.Lignepanier;
import Entities.Panier;
import Services.FactureService;
import Services.LignePanierService;
import Services.PanierService;
import Utils.DataBase;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXButton;
import config.Config;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
    private JFXButton calculp;
    @FXML
    private Label calc1;

    @FXML
    private Label calc;
   
    

    @FXML
    private TableView<Facture> tablef;
    
    @FXML
    private TableColumn<Facture, Integer> idfact;

    @FXML
    private TableColumn<Facture, String> adresse;
    @FXML
    private TableColumn<Facture, String> numtel;
    
     @FXML
    private TableColumn<Facture, String> dateDeLivraison;
    
    @FXML
    private TableColumn<Facture, Integer> etat;
   
    
   
   @FXML
    private JFXButton btn;
    
    public ObservableList<Facture> data = FXCollections.observableArrayList();
    
    private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.RED);
    private static Font orangeFont = new Font(Font.FontFamily.TIMES_ROMAN, 24, Font.NORMAL, BaseColor.ORANGE);
    private static Font noi = new Font(Font.FontFamily.TIMES_ROMAN, 24, Font.NORMAL, BaseColor.BLACK);
    

    /**
     * Initializes the controller class.
     */
   @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        LignePanierService ser = new LignePanierService();
        FactureService fer = new FactureService();
        PanierService panierService = new PanierService();
        
        Lignepanier l= new Lignepanier();
        Facture tableI = new Facture();
        Panier panier = new Panier();
       
    idfact.setCellValueFactory(new PropertyValueFactory<Facture,Integer>("idfact"));
    adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
    numtel.setCellValueFactory(new PropertyValueFactory<>("numtel"));
    dateDeLivraison.setCellValueFactory(new PropertyValueFactory<>("dateDeLivraison"));
    etat.setCellValueFactory(new PropertyValueFactory<Facture,Integer>("etat"));
      
    idfact.setStyle("-fx-alignment: CENTER;");
    dateDeLivraison.setStyle("-fx-alignment: CENTER;");
    etat.setStyle("-fx-alignment: CENTER;");
        try 
        { 
         tableI = tablef.getSelectionModel().getSelectedItem();
           if(tablef.getSelectionModel().getSelectedItems().size()!=0)
           { 
               int id_fact = tablef.getSelectionModel().getSelectedItems().get(0).getIdfact();
               panier = panierService.getCurrentPanierByUserID(Main.user_id);
               double amount = ser.calcul_total(panier.getIdpan());
               calc.setText(String.valueOf(amount+(amount/100*18)+5));
           }
        } catch (SQLException ex) {
            Logger.getLogger(AfficherFactureController.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(panier.getIdpan());
        System.out.println(panier.getIdpan());
    
      data =fer.indexActionfacture(Main.user_id);
      tablef.setItems(data);
       System.out.println(data);
      tablef.refresh();
      tablef.getSelectionModel().clearSelection();
      
    }  
    
    
    /* calculer ligne de facture tableau " 9ardou"*/
    @FXML
    private void calculp(ActionEvent event) throws IOException, SQLException 
    {   PanierService panierService = new PanierService();
        LignePanierService ser = new LignePanierService();
        FactureService fer = new FactureService();
        
        Panier panier = new Panier();
        Lignepanier l= new Lignepanier();
        
        Facture tableIndex = tablef.getSelectionModel().getSelectedItem();
          if(tablef.getSelectionModel().getSelectedItems().size()!=0)
          {panier = panierService.getCurrentPanierByUserID(Main.user_id);
            double amount=ser.calcul_total(panier.getIdpan());
            calc.setText(String.valueOf(amount+(amount/100*18)+5));
              
          }
    }
    
    /* retourner a la page precedente*/
     @FXML
    private void backImageV(ActionEvent event) throws IOException {
         try {
        firstpane.getChildren().clear();
           Parent parent = FXMLLoader.load(getClass().getResource("/gui/Panier.fxml"));
           firstpane.getChildren().add(parent);
           firstpane.toFront();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }  
    }
    
    
}
