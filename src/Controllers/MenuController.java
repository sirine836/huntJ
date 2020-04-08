/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Lignepanier;
import Entities.Product;
import Services.LignePanierService;
import Services.ProductService;
import com.jfoenix.controls.JFXButton;
import com.mashape.unirest.http.async.Callback;
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
import javafx.scene.Scene;
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
    
    LignePanierService ser = new LignePanierService();
    
    
    @FXML
    private TableView<Product> tableP;
     public ObservableList<Product> dataP = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Product, Integer> id1;
     @FXML
    private TableColumn<Product, String> nompr;
     @FXML
    private TableColumn<Product, String> descrip; 
    @FXML
    private TableColumn<Product, Double> prix;
  

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
     public void affic(ActionEvent event) throws SQLException {
      
       LignePanierService ser = new LignePanierService();
    nompr.setCellValueFactory(new PropertyValueFactory<Product,String>("nompr"));
     descrip.setCellValueFactory(new PropertyValueFactory<Product,String>("descrip"));
     prix.setCellValueFactory(new PropertyValueFactory<Product,Double>("prix"));
     quantite.setCellValueFactory(new PropertyValueFactory<Lignepanier,Integer>("quantite"));
          ProductService serv = new ProductService();
       dataP =serv.indexActionP();
        tableP.setItems(dataP);
        System.out.println(dataP);
        data =ser.indexAction();
        table.setItems(data);
        System.out.println(data);
       }
     
     
    public void btn1(ActionEvent event){
        int id = (Integer) Integer.parseInt(b1.getText());
        if(ser.trouve(id)==true ){
        
    }
    }

    
    
    
    public void afficher(ActionEvent event) throws SQLException {
    idlp.setCellValueFactory(new PropertyValueFactory<Lignepanier,Integer>("idlp"));
    product_id.setCellValueFactory(new PropertyValueFactory<Lignepanier,Integer>("product_id"));
     panier_id.setCellValueFactory(new PropertyValueFactory<Lignepanier,Integer>("panier_id"));
     quantite.setCellValueFactory(new PropertyValueFactory<Lignepanier,Integer>("quantite"));
        
          LignePanierService ser = new LignePanierService();
       data =ser.indexAction();
        table.setItems(data);
        System.out.println(data);
    }
    
  

    public void aff(ActionEvent event) throws SQLException {
    id1.setCellValueFactory(new PropertyValueFactory<Product,Integer>("id"));
 
     descrip.setCellValueFactory(new PropertyValueFactory<Product,String>("descrip"));
     prix.setCellValueFactory(new PropertyValueFactory<Product,Double>("prix"));
     
        
          ProductService serv = new ProductService();
       dataP =serv.indexActionP();
        tableP.setItems(dataP);
        System.out.println(dataP);
    }
   
}
