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
import config.Config;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;



/**
 * FXML Controller class
 *
 * @author cyrine
 */
public class devisController implements Initializable {
     private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.RED);
    private static Font orangeFont = new Font(Font.FontFamily.TIMES_ROMAN, 24, Font.NORMAL, BaseColor.ORANGE);
    private static Font redF = new Font(Font.FontFamily.TIMES_ROMAN, 30, Font.NORMAL, BaseColor.RED);
    private static Font noi = new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.NORMAL, BaseColor.BLACK);
    
    
    

    @FXML
    private Label calc;
    
     @FXML
    private AnchorPane anc;

    @FXML
    private Pane firstpane;
     
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
    private Button btn;
    
    @FXML
    public ObservableList<Lignepanier> data = FXCollections.observableArrayList();
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         LocalDate d = java.time.LocalDate.now();
         DateTimeFormatter DateTimeFormatter = null;
          Panier panier = new Panier();
          Panier panier1 = new Panier();
       Panier pa = new Panier(Main.user_id,d.toString(),0,0,0);
       Lignepanier lp = new Lignepanier(panier.getIdpan(),1);
       Facture f = new Facture();
      PanierService panierService = new PanierService();
        LignePanierService ser = new LignePanierService();
        FactureService fa = new FactureService();
       Lignepanier l= new Lignepanier();
    nompr.setCellValueFactory(new PropertyValueFactory<Lignepanier,String>("nompr"));
    prix.setCellValueFactory(new PropertyValueFactory<Lignepanier,Double>("prix"));
    quantite.setCellValueFactory(new PropertyValueFactory<Lignepanier,String>("quantite"));
      
    nompr.setStyle("-fx-alignment: CENTER;");
    prix.setStyle("-fx-alignment: CENTER;");
    quantite.setStyle("-fx-alignment: CENTER;");
        try { panier = panierService.getCurrentPanierByUserID(Main.user_id);
             calc.setText(String.valueOf(ser.calcul_total(panier.getIdpan())));
        } catch (SQLException ex) {
            Logger.getLogger(devisController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        data =ser.indexActiondevis(panier.getIdpan());
        System.out.println("aaaaa"+data);
        tabled.setItems(data);
        System.out.println(data);

    }  
    
    
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
    

    

    @FXML
    private void PDF(ActionEvent event) throws DocumentException, FileNotFoundException {
        try {
            LignePanierService es = new LignePanierService();
            Panier panier = new Panier();
            PanierService panierService = new PanierService();
            panier = panierService.getCurrentPanierByUserID(Main.user_id);

            String file_name = "C:\\wamp64\\www\\projet_3a\\pidev-java\\Events\\src\\pdf\\Devis.pdf";
            Document document = new Document();

            PdfWriter.getInstance(document, new FileOutputStream(file_name));

            document.open();
             Paragraph p = new Paragraph("  ");
             Image img = Image.getInstance("C:\\wamp64\\www\\projet_3a\\pidev-java\\Events\\src\\Images\\thunt.png");
            
             img.scaleAbsolute(200, 200);
            img.setAbsolutePosition(10, 630);
            document.add(img);
            
            document.add(p);
            document.add(p);
            document.add(p);
            Paragraph par = new Paragraph("     La liste des devis", orangeFont);
            Paragraph parr = new Paragraph("    ___________________", orangeFont);
            par.setAlignment(Element.ALIGN_CENTER);
            parr.setAlignment(Element.ALIGN_CENTER);

           
            document.add(par);
            document.add(parr);
            document.add(p);
            document.add(p);
            document.add(p);
           
            

            Connection connexion = DataBase.getInstance().getCnx();
            PreparedStatement ps = null;
            ResultSet rs = null;
            String query = "select l.id,p.nompr,P.prix,l.quantite from product p JOIN ligne_panier l ON p.id = l.product_id and panier_id="+panier.getIdpan()+"";
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
            
            Paragraph parrr = new Paragraph("____________________________________", noi);
            parrr.setAlignment(Element.ALIGN_CENTER);
            document.add(parrr);
            
            double a= ser.calcul_total(panier.getIdpan());
               Paragraph p1 = new Paragraph("Prix Total :  "+a + "  DT" ,redF);
               
               p1.setAlignment(Element.ALIGN_CENTER);
               
                document.add(p1);
                

            document.close();
            System.out.println("finished");
        } catch (Exception e) {
            System.err.println(e);
        }

    }
    
   
    }    
    

