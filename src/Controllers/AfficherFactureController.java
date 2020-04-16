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
    private TableView<Facture> tablef;
    
    @FXML
    private TableColumn<Facture, Integer> idfact;

    @FXML
    private TableColumn<Facture, String> adresse;
    
    @FXML
    private TableColumn<Facture, Integer> etat;
    @FXML
    private TableColumn<Facture, String> date;
    
   
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
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        LignePanierService ser = new LignePanierService();
         FactureService fer = new FactureService();
       Lignepanier l= new Lignepanier();
       
      
    idfact.setCellValueFactory(new PropertyValueFactory<Facture,Integer>("idfact"));
    adresse.setCellValueFactory(new PropertyValueFactory<Facture,String>("adresse"));
    date.setCellValueFactory(new PropertyValueFactory<Facture,String>("dateDeLivraison"));
    etat.setCellValueFactory(new PropertyValueFactory<Facture,Integer>("etat"));
      
    idfact.setStyle("-fx-alignment: CENTER;");
    date.setStyle("-fx-alignment: CENTER;");
    etat.setStyle("-fx-alignment: CENTER;");
        try {
            
        Facture tableIndex = tablef.getSelectionModel().getSelectedItem();
        if(tablef.getSelectionModel().getSelectedItems().size()!=0){
            double amount=ser.calcul_total(Config.currentpanier);
            calc.setText(String.valueOf(amount+(amount/100*18)+5));
        }
        } catch (SQLException ex) {
            Logger.getLogger(AfficherFactureController.class.getName()).log(Level.SEVERE, null, ex);
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
            FactureService es = new FactureService();

            String file_name = "C:\\Users\\cyrine\\Desktop\\Facture.pdf";
            Document document = new Document();

            PdfWriter.getInstance(document, new FileOutputStream(file_name));

            document.open();
            Paragraph p = new Paragraph("  ");
             Image img = Image.getInstance("C:\\Users\\cyrine\\Documents\\NetBeansProjects\\huntJ\\src\\Images\\thunt.png");
            
             img.scaleAbsolute(200, 200);
            img.setAbsolutePosition(10, 630);
            document.add(img);
            
            document.add(p);
            document.add(p);
            document.add(p);
            Paragraph par = new Paragraph("La liste des Factures  ", orangeFont);
            Paragraph parr = new Paragraph("___________________", orangeFont);
            par.setAlignment(Element.ALIGN_CENTER);
            parr.setAlignment(Element.ALIGN_CENTER);

            
            document.add(par);
            document.add(parr);
            document.add(p);
            document.add(p);
            document.add(p);
         
            Connection connexion = MyDbConnection.getInstance().getConnexion();
            PreparedStatement ps = null;
            ResultSet rs = null;
            String query = "Select f.id,f.adresse,f.dateDeLivraison,f.etat from facture f join panier p where p.id=f.panier_id ";
            ps = (PreparedStatement) connexion.prepareStatement(query);
            rs = ps.executeQuery();
            int i = 1;
            LignePanierService ser = new LignePanierService();
            while (rs.next()) {
                Paragraph p2 = new Paragraph("Article n°" + i, redFont);
                document.add(p2);

                Paragraph para = new Paragraph("Numéro de la Facture  : " + rs.getInt("idfact") +
                        " \n Adresse   :  " + rs.getString("adresse") + " \n Date de livraison  : " 
                        + rs.getString("dateDeLivraison")+ " \n Etat de la facture   :  " + rs.getInt("etat"));
                document.add(para);
              
                document.add(new Paragraph("  "));

                i++;
            }
            Paragraph parrz = new Paragraph("_____________________________________", redFont);
            parrz.setAlignment(Element.ALIGN_CENTER);
            document.add(parrz);
            double a= ser.calcul_total(Config.currentpanier);
               Paragraph p1 = new Paragraph("Prix HT :  "+a + "  DT" ,noi);
               p1.setAlignment(Element.ALIGN_CENTER);
               document.add(p1);
            int tva= 18;
               Paragraph tv = new Paragraph("TVA :  "+tva+ "  %" ,noi);
               tv.setAlignment(Element.ALIGN_CENTER);
               document.add(tv);
            int frais= 5;
               Paragraph f = new Paragraph("frais :  "+frais+ "  DT" ,noi);
               f.setAlignment(Element.ALIGN_CENTER);
               document.add(f);
               Paragraph parrzz = new Paragraph("_________________", redFont);
            parrzz.setAlignment(Element.ALIGN_CENTER);
            document.add(parrzz); 
            
           double b= ser.calcul_total(Config.currentpanier);
           double x= b+(b/100*18)+5;
               Paragraph p1t = new Paragraph("Prix Total a payer :  "+x + "  DT" ,noi);
               p1t.setAlignment(Element.ALIGN_CENTER);
               document.add(p1t);

            document.close();
            System.out.println("finished");
        } catch (Exception e) {
            System.err.println(e);
        }

    }
    
    
}
