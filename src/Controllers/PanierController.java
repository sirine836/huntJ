/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;


import javafx.scene.control.cell.PropertyValueFactory;
import Entities.Lignepanier;
import Entities.Panier;
import Entities.Product;
import Services.LignePanierService;
import Services.PanierService;
import Services.ProductService;
import com.jfoenix.controls.JFXButton;
import config.Config;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author cyrine
 */
public class PanierController implements Initializable {
    
   
    
     @FXML
    private AnchorPane avr;
     @FXML
    private Pane firstpane;

    @FXML
    private TableView<Lignepanier> table;
    
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
    private Button aff;

    @FXML
    private Label PrixTotal;

    @FXML
    private Button delete;

    @FXML
    private Button btncom;
   
    @FXML
    private TextField somme;
    
    @FXML
    private Label calc;
    
    @FXML
    private TableColumn<Lignepanier,? > id;
    
     public ObservableList<Lignepanier> data = FXCollections.observableArrayList();
  
    @FXML
    private void btncom(ActionEvent event) throws IOException {
         firstpane.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("paiement.fxml"));
        firstpane.getChildren().add(parent);
        firstpane.toFront();
    }
    
    
    @FXML
    private void btndevis(ActionEvent event) {
    try {
        javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("devis.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }  
    }
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
        LignePanierService ser = new LignePanierService();
       
    nompr.setCellValueFactory(new PropertyValueFactory<Lignepanier,String>("nompr"));
    descrip.setCellValueFactory(new PropertyValueFactory<Lignepanier,String>("descrip"));
    prix.setCellValueFactory(new PropertyValueFactory<Lignepanier,Double>("prix"));
    quantite.setCellValueFactory(new PropertyValueFactory<Lignepanier,String>("quantite"));
      
    nompr.setStyle("-fx-alignment: CENTER;");
    prix.setStyle("-fx-alignment: CENTER;");
    quantite.setStyle("-fx-alignment: CENTER;");
    
      Panier panier = new Panier();
      PanierService panierService = new PanierService();
         try {
             panier = panierService.getCurrentPanierByUserID(Config.currentUser);
            calc.setText(String.valueOf(ser.calcul_total(Config.currentpanier)));
          
         } catch (SQLException ex) {
             ex.getSQLState();
         }
        data =ser.indexAction(panier.getIdpan());
        System.out.println(data);
        table.setItems(data);
        System.out.println(data);
        
    }   
    
    
   
    
       
         
    
   
 
    
    @FXML
    private void deleteItem(ActionEvent event) throws SQLException {
        LignePanierService l=new LignePanierService();
        Lignepanier tableIndex = table.getSelectionModel().getSelectedItem();
        if(table.getSelectionModel().getSelectedItems().size()!=0){
           l.deleteL(table.getSelectionModel().getSelectedItems().get(0).getIdlp());
           
               table.getItems().remove(tableIndex);
               table.refresh();
               table.getSelectionModel().clearSelection();
        }else{
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("aucun élément 'a ètè séléctionné");
        alert.showAndWait();
       }
        
        LignePanierService ser = new LignePanierService();
       
    nompr.setCellValueFactory(new PropertyValueFactory<Lignepanier,String>("nompr"));
    descrip.setCellValueFactory(new PropertyValueFactory<Lignepanier,String>("descrip"));
    prix.setCellValueFactory(new PropertyValueFactory<Lignepanier,Double>("prix"));
    quantite.setCellValueFactory(new PropertyValueFactory<Lignepanier,String>("quantite"));
      
      Panier panier = new Panier();
      PanierService panierService = new PanierService();
         try {
             panier = panierService.getCurrentPanierByUserID(Config.currentUser);
             System.out.println(Config.currentpanier);
              System.out.println(String.valueOf(ser.calcul_total(Config.currentpanier)));
            calc.setText(String.valueOf(ser.calcul_total(Config.currentpanier)));
           
             
         } catch (SQLException ex) {
             ex.getSQLState();
         }
        data =ser.indexAction(panier.getIdpan());
        System.out.println(data);
        table.setItems(data);
        System.out.println(data);
    }
    
     @FXML
    private void updateqte(ActionEvent event) throws SQLException {
        LignePanierService l=new LignePanierService();
        Lignepanier lp=new Lignepanier();
        int qte = 1;
        Lignepanier tableIndex = table.getSelectionModel().getSelectedItem();
        if(table.getSelectionModel().getSelectedItems().size()!=0){
           System.out.println(table.getSelectionModel().getSelectedItems().get(0).getIdlp());
           l.updateL(table.getSelectionModel().getSelectedItems().get(0).getIdlp(),qte);
           
               table.getItems().remove(tableIndex);
               table.refresh();
               table.getSelectionModel().clearSelection();
          
        }else{
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("aucun élément n'a été séléctionné");
        alert.showAndWait();
       }
        
        LignePanierService ser = new LignePanierService();
       
    nompr.setCellValueFactory(new PropertyValueFactory<Lignepanier,String>("nompr"));
    descrip.setCellValueFactory(new PropertyValueFactory<Lignepanier,String>("descrip"));
    prix.setCellValueFactory(new PropertyValueFactory<Lignepanier,Double>("prix"));
    quantite.setCellValueFactory(new PropertyValueFactory<Lignepanier,String>("quantite"));
      
      Panier panier = new Panier();
      PanierService panierService = new PanierService();
         try {
             panier = panierService.getCurrentPanierByUserID(Config.currentUser);
             System.out.println(Config.currentpanier);
              System.out.println(String.valueOf(ser.calcul_total(Config.currentpanier)));
            calc.setText(String.valueOf(ser.calcul_total(Config.currentpanier)));
           
             
         } catch (SQLException ex) {
             ex.getSQLState();
         }
        data =ser.indexAction(panier.getIdpan());
        System.out.println(data);
        table.setItems(data);
        System.out.println(data);
    }
    
    public static boolean isNotInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException | NullPointerException e) {
            return true;
        }
        return false;
    }
    
     @FXML
    private void editqte(ActionEvent event) throws SQLException, IOException {
         LignePanierService l=new LignePanierService();
        int qte = 1;
        Lignepanier tableIndex = table.getSelectionModel().getSelectedItem();
        if(table.getSelectionModel().getSelectedItems().size()!=0)
         {
               String name = null;  
               name = JOptionPane.showInputDialog("Modifier quantité ? ");
               if ((name == null) || (name.trim().isEmpty())) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("Veuillez saisir la quantité!");
                    alert.show();
               } else if (isNotInteger(name)){
                    Alert alert1 = new Alert(Alert.AlertType.WARNING);
                    alert1.setTitle("Erreur");
                    alert1.setContentText("Quantité doit être un nombre");
                    alert1.setHeaderText(null);
                    alert1.show();
               }else{   
                   l.updateL(table.getSelectionModel().getSelectedItems().get(0).getIdlp(),Integer.parseInt(name));
                             LignePanierService ser = new LignePanierService();
                               if(Integer.parseInt(name) <=0)
           { l.deleteL(table.getSelectionModel().getSelectedItems().get(0).getIdlp());
           calc.setText(String.valueOf(ser.calcul_total(Config.currentpanier)));
               table.getItems().remove(tableIndex);
               table.refresh();
               table.getSelectionModel().clearSelection();
           }       
                              nompr.setCellValueFactory(new PropertyValueFactory<Lignepanier,String>("nompr"));
                              descrip.setCellValueFactory(new PropertyValueFactory<Lignepanier,String>("descrip"));
                              prix.setCellValueFactory(new PropertyValueFactory<Lignepanier,Double>("prix"));
                              quantite.setCellValueFactory(new PropertyValueFactory<Lignepanier,String>("quantite"));

                              Panier panier = new Panier();
                              PanierService panierService = new PanierService();
                                try {
                                  panier = panierService.getCurrentPanierByUserID(Config.currentUser);
                                   calc.setText(String.valueOf(ser.calcul_total(Config.currentpanier)));
                                    } catch (SQLException ex) {
                                          ex.getSQLState();
                                   }
                              data =ser.indexAction(panier.getIdpan());
                              System.out.println(data);
                              table.setItems(data);
                              System.out.println(data);
        
                    table.refresh();
                    table.getSelectionModel().clearSelection();
              
               }
         }else{
          Alert alert = new Alert(Alert.AlertType.ERROR);
          alert.setTitle("Information Dialog");
          alert.setHeaderText(null);
          alert.setContentText("aucun élément n'a été séléctionné");
          alert.showAndWait();
       }
        
       }  
        
        
    }
    
    
    
    
    
 
   
    
    
  
    

