package Controllers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import Entities.Lignepanier;
import Entities.Panier;
import Entities.Product;
import Services.LignePanierService;
import Services.PanierService;
import Services.ProductService;
import com.jfoenix.controls.JFXButton;
import com.mashape.unirest.http.async.Callback;
import config.Config;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
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
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import static org.bouncycastle.asn1.cms.CMSObjectIdentifiers.data;

/**
 * FXML Controller class
 *
 * @author cyrine
 */
public class MenuController implements Initializable {
    
   

    
    @FXML
    private TextField  b1;
/*
    @FXML
    private JFXButton btn2;

    @FXML
    private JFXButton btn3;

    @FXML
    private JFXButton btn4;

    @FXML
    private JFXButton btn5;

    @FXML
    private JFXButton btn6;

    @FXML
    private JFXButton btn7;

    @FXML
    private JFXButton btn8;

    @FXML
    private JFXButton btn9;

    @FXML
    private JFXButton btn10;

    @FXML
    private JFXButton btn11;

    @FXML
    private JFXButton btn12;
    */
    
    Connection con; 

    
    @FXML
    private JFXButton btn1;
    @FXML
    private JFXButton btn2;
    @FXML
    private JFXButton btn3;
    @FXML
    private JFXButton btn4;
    @FXML
    private JFXButton btn5;
    @FXML
    private JFXButton btn6;
    @FXML
    private JFXButton btn7;
    @FXML
    private JFXButton btn8;
    @FXML
    private JFXButton btn9;
    @FXML
    private JFXButton btn12;
    @FXML
    private JFXButton btn11;
    @FXML
    private JFXButton btn10;
    
    boolean type;
     @FXML
    private Button delete;
    @FXML
    private Button comm;
    
    
    
    @FXML
    private AnchorPane avr;
    
    public ObservableList<Lignepanier> data = FXCollections.observableArrayList();
    @FXML
    private TableView<Lignepanier> table;
     @FXML
    private TableColumn<Lignepanier, Integer> idlp;
     @FXML
    private TableColumn<Lignepanier, Integer> product_id;
     @FXML
    private TableColumn<Lignepanier, Integer> panier_id; 
    @FXML
    private TableColumn<Lignepanier, Integer> quantite;
    @FXML
    private TableColumn<Lignepanier, String> nompr;
     @FXML
    private TableColumn<Lignepanier, String> descrip; 
    @FXML
    private TableColumn<Lignepanier, Double> prix;
    
    LignePanierService ser = new LignePanierService();
    ProductService prod = new ProductService();
    
    
    
    @FXML
    private TableView<Product> tableP;
     public ObservableList<Product> dataP = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Product, Integer> id1;
     @FXML
    private TableColumn<Product, String> pnompr;
     @FXML
    private TableColumn<Product, String> pdescrip; 
    @FXML
    private TableColumn<Product, Double> pprix;
  

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
 
    public void addToCart1(ActionEvent event) throws IOException,SQLException{  
      LignePanierService lpService = new LignePanierService();
      PanierService panierService = new PanierService();
      int id_produit = 1;
      Panier panier = new Panier();
      Lignepanier lp = new Lignepanier();
        if(ser.trouve(id_produit)==true ){
            lpService.increment_qnt(id_produit,Config.currentpanier);
            new Alert(Alert.AlertType.INFORMATION, "reajout").show();
        }
        else{
      panier = panierService.getCurrentPanierByUserID(Config.currentUser);
      lp.setPanier_id(panier.getIdpan());
      lp.setProduct_id(id_produit);
      lp.setQuantite(1);
      
      lpService.ajouterLigne2(lp);
      new Alert(Alert.AlertType.INFORMATION, "sucess").show();
        }
    }
    
    public void addToCart2(ActionEvent event) throws IOException,SQLException{  
      LignePanierService lpService = new LignePanierService();
      PanierService panierService = new PanierService();
      int id_produit = 2;
      Panier panier = new Panier();
      Lignepanier lp = new Lignepanier();
        if(ser.trouve(id_produit)==true ){
            lpService.increment_qnt(id_produit,Config.currentpanier);
            new Alert(Alert.AlertType.INFORMATION, "reajout").show();
        }
        else{
      panier = panierService.getCurrentPanierByUserID(Config.currentUser);
      lp.setPanier_id(panier.getIdpan());
      lp.setProduct_id(id_produit);
      lp.setQuantite(1);
      
      lpService.ajouterLigne2(lp);
      new Alert(Alert.AlertType.INFORMATION, "sucess").show();
        }
    }
    
    
    
    public void addToCart3(ActionEvent event) throws IOException,SQLException{  
      LignePanierService lpService = new LignePanierService();
      PanierService panierService = new PanierService();
      int id_produit = 3;
      Panier panier = new Panier();
      Lignepanier lp = new Lignepanier();
        if(ser.trouve(id_produit)==true ){
            lpService.increment_qnt(id_produit,Config.currentpanier);
            new Alert(Alert.AlertType.INFORMATION, "reajout").show();
        }
        else{
      panier = panierService.getCurrentPanierByUserID(Config.currentUser);
      lp.setPanier_id(panier.getIdpan());
      lp.setProduct_id(id_produit);
      lp.setQuantite(1);
      
      lpService.ajouterLigne2(lp);
      new Alert(Alert.AlertType.INFORMATION, "sucess").show();
        }
    }
    
    
    public void addToCart4(ActionEvent event) throws IOException,SQLException{  
      LignePanierService lpService = new LignePanierService();
      PanierService panierService = new PanierService();
      int id_produit = 4;
      Panier panier = new Panier();
      Lignepanier lp = new Lignepanier();
        if(ser.trouve(id_produit)==true ){
            lpService.increment_qnt(id_produit,Config.currentpanier);
            new Alert(Alert.AlertType.INFORMATION, "reajout").show();
        }
        else{
      panier = panierService.getCurrentPanierByUserID(Config.currentUser);
      lp.setPanier_id(panier.getIdpan());
      lp.setProduct_id(id_produit);
      lp.setQuantite(1);
      
      lpService.ajouterLigne2(lp);
      new Alert(Alert.AlertType.INFORMATION, "sucess").show();
        }
    }
    
    
    public void addToCart5(ActionEvent event) throws IOException,SQLException{  
      LignePanierService lpService = new LignePanierService();
      PanierService panierService = new PanierService();
      int id_produit = 5;
      Panier panier = new Panier();
      Lignepanier lp = new Lignepanier();
        if(ser.trouve(id_produit)==true ){
            lpService.increment_qnt(id_produit,Config.currentpanier);
            new Alert(Alert.AlertType.INFORMATION, "reajout").show();
        }
        else{
      panier = panierService.getCurrentPanierByUserID(Config.currentUser);
      lp.setPanier_id(panier.getIdpan());
      lp.setProduct_id(id_produit);
      lp.setQuantite(1);
      
      lpService.ajouterLigne2(lp);
      new Alert(Alert.AlertType.INFORMATION, "sucess").show();
        }
    }

    
    public void addToCart6(ActionEvent event) throws IOException,SQLException{  
      LignePanierService lpService = new LignePanierService();
      PanierService panierService = new PanierService();
      int id_produit = 6;
      Panier panier = new Panier();
      Lignepanier lp = new Lignepanier();
        if(ser.trouve(id_produit)==true ){
            lpService.increment_qnt(id_produit,Config.currentpanier);
            new Alert(Alert.AlertType.INFORMATION, "reajout").show();
        }
        else{
      panier = panierService.getCurrentPanierByUserID(Config.currentUser);
      lp.setPanier_id(panier.getIdpan());
      lp.setProduct_id(id_produit);
      lp.setQuantite(1);
      
      lpService.ajouterLigne2(lp);
      new Alert(Alert.AlertType.INFORMATION, "sucess").show();
        }
    }
    
    
    public void addToCart7(ActionEvent event) throws IOException,SQLException{  
      LignePanierService lpService = new LignePanierService();
      PanierService panierService = new PanierService();
      int id_produit = 7;
      Panier panier = new Panier();
      Lignepanier lp = new Lignepanier();
        if(ser.trouve(id_produit)==true ){
            lpService.increment_qnt(id_produit,Config.currentpanier);
            new Alert(Alert.AlertType.INFORMATION, "reajout").show();
        }
        else{
      panier = panierService.getCurrentPanierByUserID(Config.currentUser);
      lp.setPanier_id(panier.getIdpan());
      lp.setProduct_id(id_produit);
      lp.setQuantite(1);
      
      lpService.ajouterLigne2(lp);
      new Alert(Alert.AlertType.INFORMATION, "sucess").show();
        }
    }
    
    
    public void addToCart8(ActionEvent event) throws IOException,SQLException{  
      LignePanierService lpService = new LignePanierService();
      PanierService panierService = new PanierService();
      int id_produit = 8;
      Panier panier = new Panier();
      Lignepanier lp = new Lignepanier();
        if(ser.trouve(id_produit)==true ){
            lpService.increment_qnt(id_produit,Config.currentpanier);
            new Alert(Alert.AlertType.INFORMATION, "reajout").show();
        }
        else{
      panier = panierService.getCurrentPanierByUserID(Config.currentUser);
      lp.setPanier_id(panier.getIdpan());
      lp.setProduct_id(id_produit);
      lp.setQuantite(1);
      
      lpService.ajouterLigne2(lp);
      new Alert(Alert.AlertType.INFORMATION, "sucess").show();
        }
    }
    
    
    public void addToCart9(ActionEvent event) throws IOException,SQLException{  
      LignePanierService lpService = new LignePanierService();
      PanierService panierService = new PanierService();
      int id_produit = 9;
      Panier panier = new Panier();
      Lignepanier lp = new Lignepanier();
        if(ser.trouve(id_produit)==true ){
            lpService.increment_qnt(id_produit,Config.currentpanier);
            new Alert(Alert.AlertType.INFORMATION, "reajout").show();
        }
        else{
      panier = panierService.getCurrentPanierByUserID(Config.currentUser);
      lp.setPanier_id(panier.getIdpan());
      lp.setProduct_id(id_produit);
      lp.setQuantite(1);
      
      lpService.ajouterLigne2(lp);
      new Alert(Alert.AlertType.INFORMATION, "sucess").show();
        }
    }
    
    
    public void addToCart10(ActionEvent event) throws IOException,SQLException{  
      LignePanierService lpService = new LignePanierService();
      PanierService panierService = new PanierService();
      int id_produit = 10;
      Panier panier = new Panier();
      Lignepanier lp = new Lignepanier();
        if(ser.trouve(id_produit)==true ){
            lpService.increment_qnt(id_produit,Config.currentpanier);
            new Alert(Alert.AlertType.INFORMATION, "reajout").show();
        }
        else{
      panier = panierService.getCurrentPanierByUserID(Config.currentUser);
      lp.setPanier_id(panier.getIdpan());
      lp.setProduct_id(id_produit);
      lp.setQuantite(1);
      
      lpService.ajouterLigne2(lp);
      new Alert(Alert.AlertType.INFORMATION, "sucess").show();
        }
    }
    
    
    public void addToCart11(ActionEvent event) throws IOException,SQLException{  
      LignePanierService lpService = new LignePanierService();
      PanierService panierService = new PanierService();
      int id_produit = 11;
      Panier panier = new Panier();
      Lignepanier lp = new Lignepanier();
        if(ser.trouve(id_produit)==true ){
            lpService.increment_qnt(id_produit,Config.currentpanier);
            new Alert(Alert.AlertType.INFORMATION, "reajout").show();
        }
        else{
      panier = panierService.getCurrentPanierByUserID(Config.currentUser);
      lp.setPanier_id(panier.getIdpan());
      lp.setProduct_id(id_produit);
      lp.setQuantite(1);
      
      lpService.ajouterLigne2(lp);
      new Alert(Alert.AlertType.INFORMATION, "sucess").show();
        }
    }
    
    
    public void addToCart12(ActionEvent event) throws IOException,SQLException{  
      LignePanierService lpService = new LignePanierService();
      PanierService panierService = new PanierService();
      int id_produit = 12;
      Panier panier = new Panier();
      Lignepanier lp = new Lignepanier();
        if(ser.trouve(id_produit)==true ){
            lpService.increment_qnt(id_produit,Config.currentpanier);
            new Alert(Alert.AlertType.INFORMATION, "reajout").show();
        }
        else{
      panier = panierService.getCurrentPanierByUserID(Config.currentUser);
      lp.setPanier_id(panier.getIdpan());
      lp.setProduct_id(id_produit);
      lp.setQuantite(1);
      
      lpService.ajouterLigne2(lp);
      new Alert(Alert.AlertType.INFORMATION, "sucess").show();
        }
    }
    
    
    
    public void afficherp(ActionEvent event) throws SQLException {
    idlp.setCellValueFactory(new PropertyValueFactory<Lignepanier,Integer>("idlp"));
    product_id.setCellValueFactory(new PropertyValueFactory<Lignepanier,Integer>("product_id"));
     panier_id.setCellValueFactory(new PropertyValueFactory<Lignepanier,Integer>("panier_id"));
     quantite.setCellValueFactory(new PropertyValueFactory<Lignepanier,Integer>("quantite"));
        
          LignePanierService ser = new LignePanierService();
       
        table.setItems(data);
        System.out.println(data);
    }
    
  

    public void aff(ActionEvent event) throws SQLException {
    id1.setCellValueFactory(new PropertyValueFactory<Product,Integer>("id"));
 
     pdescrip.setCellValueFactory(new PropertyValueFactory<Product,String>("descrip"));
     pprix.setCellValueFactory(new PropertyValueFactory<Product,Double>("prix"));
          ProductService serv = new ProductService();
       dataP =serv.indexActionP();
        tableP.setItems(dataP);
        System.out.println(dataP);
    }
   
}


   /*
      lezmeek tlawej est ce que el produit eli bch tzidou mawjoud deja fi les lignes_paniers mta3 el panier 
      */