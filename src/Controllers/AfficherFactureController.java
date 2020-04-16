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
import Utils.MyDbConnection;
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
    private JFXButton calculp;
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
    private Button btn1;
   
   @FXML
    private Button btn;
    
    @FXML
    public ObservableList<Facture> data = FXCollections.observableArrayList();
    
     private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.RED);
    private static Font orangeFont = new Font(Font.FontFamily.TIMES_ROMAN, 24, Font.NORMAL, BaseColor.ORANGE);
    

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
            
        Facture tableIndex = tablef.getSelectionModel().getSelectedItem();
        if(tablef.getSelectionModel().getSelectedItems().size()!=0){
            double amount=ser.calcul_total(Config.currentpanier);
            calc.setText(String.valueOf(amount+(amount/100*18)+5));
        }
        } catch (SQLException ex) {
            Logger.getLogger(devisController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        data =fer.indexActionfacture(Config.currentpanier);
        System.out.println("aaaaa"+data);
        tablef.setItems(data);
        System.out.println(data);

    }  
    
    @FXML
    private void calculp(ActionEvent event) throws IOException, SQLException {
         LignePanierService ser = new LignePanierService();
         FactureService fer = new FactureService();
       Lignepanier l= new Lignepanier();
       Facture tableIndex = tablef.getSelectionModel().getSelectedItem();
        if(tablef.getSelectionModel().getSelectedItems().size()!=0){
            double amount=ser.calcul_total(Config.currentpanier);
            calc.setText(String.valueOf(amount+(amount/100*18)+5));
        }
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
    
     @FXML
    private void PDF(ActionEvent event) throws DocumentException, FileNotFoundException {
        try {
            LignePanierService es = new LignePanierService();

            String file_name = "C:\\Users\\cyrine\\Desktop\\Avis.pdf";
            Document document = new Document();

            PdfWriter.getInstance(document, new FileOutputStream(file_name));

            document.open();
            Paragraph par = new Paragraph("La liste des devis", orangeFont);
            Paragraph parr = new Paragraph("___________________", orangeFont);
            par.setAlignment(Element.ALIGN_CENTER);
            parr.setAlignment(Element.ALIGN_CENTER);

            Paragraph p = new Paragraph("  ");
            document.add(par);
            document.add(parr);
            document.add(p);
            document.add(p);
            document.add(p);
            Image img = Image.getInstance("C:\\Users\\cyrine\\Documents\\NetBeansProjects\\huntJ\\src\\Images\\thunt.png");
            Image img2 = Image.getInstance("C:\\Users\\cyrine\\Documents\\NetBeansProjects\\huntJ\\src\\Images\\cod.png");
            img.scaleAbsolute(50f, 50f);
            img.setAbsolutePosition(450f, 775f);
            img2.setAbsolutePosition(100f, 775f);
            img2.scaleAbsolute(50f, 50f);

            document.add(img);
            document.add(img2);
            document.add(p);
            document.add(p);
            document.add(p);

            Connection connexion = MyDbConnection.getInstance().getConnexion();
            PreparedStatement ps = null;
            ResultSet rs = null;
            String query = "Select p.nompr,p.prix,lp.quantite from ligne_panier lp join product p where p.id=lp.product_id";
            ps = (PreparedStatement) connexion.prepareStatement(query);
            rs = ps.executeQuery();
            int i = 1;
            LignePanierService ser = new LignePanierService();
            while (rs.next()) {
                Paragraph p2 = new Paragraph("Article n°" + i, redFont);
                document.add(p2);

                Paragraph para = new Paragraph("Nom Article  : " + rs.getString("nompr") + " \n Prix Unitaire   :" + rs.getDouble("prix") + " \n Quantité  : " + rs.getInt("quantite"));
                document.add(para);
              
                document.add(new Paragraph("  "));

                i++;
            }
            
            double a= ser.calcul_total(Config.currentpanier);
               Paragraph p1 = new Paragraph("Prix Total :  "+a + "  DT" ,redFont);
               
               p1.setAlignment(Element.ALIGN_CENTER);
               
                document.add(p1);
                

            document.close();
            System.out.println("finished");
        } catch (Exception e) {
            System.err.println(e);
        }

    }
    
    
}
