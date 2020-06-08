/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;


import javafx.scene.control.cell.PropertyValueFactory;
import Entities.Lignepanier;
import Entities.Panier;
import Services.LignePanierService;
import Services.PanierService;
import javafx.scene.input.MouseEvent;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javax.swing.JOptionPane;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

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
    private JFXButton btnfacture;
    @FXML
    private JFXButton btndevis;
   
    @FXML
    private TextField somme;
    
    
    @FXML
    private ImageView image;
      
    @FXML
    private Label calc;
    
    @FXML
    private TableColumn<Lignepanier,? > id;
    
     public ObservableList<Lignepanier> data = FXCollections.observableArrayList();
     private Lignepanier selectedid;
    @FXML
    private void btncom(ActionEvent event) throws IOException, SQLException {
         LignePanierService l=new LignePanierService();
        Panier panier = new Panier();
        PanierService panierService = new PanierService();
        panier = panierService.getCurrentPanierByUserID(Main.user_id);
        System.out.println(l.panier_nbligne(panier.getIdpan()));
        if(l.panier_nbligne(panier.getIdpan())>0)
        { 
          firstpane.getChildren().clear();
          Parent parent = FXMLLoader.load(getClass().getResource("/gui/paiement.fxml"));
          firstpane.getChildren().add(parent);
          firstpane.toFront();
        }
        else
        {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Empty cart");
        alert.showAndWait();
        }
    }
    
    
    @FXML
    private void btnfacture(ActionEvent event) {
    try {
        firstpane.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("/gui/AfficherFacture.fxml"));
        firstpane.getChildren().add(parent);
        firstpane.toFront();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }  
    }
    
    @FXML
    private void btndevis(ActionEvent event) {
    try {
        firstpane.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("/gui/devis.fxml"));
        firstpane.getChildren().add(parent);
        firstpane.toFront();
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
          LocalDate d = java.time.LocalDate.now();
          DateTimeFormatter DateTimeFormatter = null;
          Panier panier = new Panier();
          Panier panier1 = new Panier();
       Panier pa = new Panier(Main.user_id,d.toString(),0,0,0);
       Lignepanier lp = new Lignepanier(panier.getIdpan(),1);
       PanierService panierService = new PanierService();
       LignePanierService ser = new LignePanierService();
     
       
    nompr.setCellValueFactory(new PropertyValueFactory<Lignepanier,String>("nompr"));
    descrip.setCellValueFactory(new PropertyValueFactory<Lignepanier,String>("descrip"));
    prix.setCellValueFactory(new PropertyValueFactory<Lignepanier,Double>("prix"));
    quantite.setCellValueFactory(new PropertyValueFactory<Lignepanier,String>("quantite"));
      
    nompr.setStyle("-fx-alignment: CENTER;");
    prix.setStyle("-fx-alignment: CENTER;");
    quantite.setStyle("-fx-alignment: CENTER;");
    
         try
         {
           panier = panierService.getCurrentPanierByUserID(Main.user_id);
           calc.setText(String.valueOf(ser.calcul_total(panier.getIdpan())));
         } catch (SQLException ex) {
             ex.getSQLState();
         }
        data =ser.indexAction(panier.getIdpan());
        System.out.println(data);
        table.setItems(data);
        System.out.println(data);
    }   
    
   @FXML
    private void afficherImage(MouseEvent event) {
        
        selectedid = (Lignepanier) table.getSelectionModel().getSelectedItem();
       
              String img ="http://localhost//projet_3a/symfony/web/images/" +selectedid.getImage();
          
        image.setImage(new Image(img));
     
    } 
 

    @FXML
    private void deleteItem(ActionEvent event) throws SQLException {
        
        LignePanierService l=new LignePanierService();
        Panier panier = new Panier();
        PanierService panierService = new PanierService();
        panier = panierService.getCurrentPanierByUserID(Main.user_id);
        Lignepanier tableIndex = table.getSelectionModel().getSelectedItem();
        if(table.getSelectionModel().getSelectedItems().size()!=0){
             ObservableList<Lignepanier> tableIndexprod3 = table.getSelectionModel().getSelectedItems();
           l.increment_Stock(tableIndexprod3.get(0).getProduct_id(),panier.getIdpan());
           l.deleteL(tableIndexprod3.get(0).getIdlp());
           l.prixupdate(panier.getIdpan(),Main.user_id);
               table.getItems().remove(tableIndex);
               table.refresh();
               table.getSelectionModel().clearSelection();
               
                LignePanierService ser = new LignePanierService();
    nompr.setCellValueFactory(new PropertyValueFactory<Lignepanier,String>("nompr"));
    descrip.setCellValueFactory(new PropertyValueFactory<Lignepanier,String>("descrip"));
    prix.setCellValueFactory(new PropertyValueFactory<Lignepanier,Double>("prix"));
    quantite.setCellValueFactory(new PropertyValueFactory<Lignepanier,String>("quantite"));
         try 
         { 
            calc.setText(String.valueOf(ser.calcul_total(panier.getIdpan()))); 
            l.prixupdate(panier.getIdpan(),Main.user_id);
         } catch (SQLException ex) {
             ex.getSQLState();
         }
        data =ser.indexAction(panier.getIdpan());
        System.out.println(data);
        table.setItems(data);
        System.out.println(data);
        }else{
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("no item has been selected");
        alert.showAndWait();
       }
    
        
   
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
          Panier panier = new Panier();
          PanierService panierService = new PanierService();
          panier = panierService.getCurrentPanierByUserID(Main.user_id);
        int qte = 1;
          Lignepanier tableIndex = table.getSelectionModel().getSelectedItem();
        if(table.getSelectionModel().getSelectedItems().size()!=0)
         {
               String name = null;  
               name = JOptionPane.showInputDialog("Enter quantity ask ");
               if ((name == null) || (name.trim().isEmpty())) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("Enter the quantity to request:");
                    alert.showAndWait();
               } else if (isNotInteger(name)){
                    Alert alert1 = new Alert(Alert.AlertType.WARNING);
                    alert1.setTitle("Erreur");
                    alert1.setContentText("Quantity must be a number");
                    alert1.setHeaderText(null);
                    alert1.showAndWait();
               }else{  
                   if(table.getSelectionModel().getSelectedItems().get(0).getQuantite()>Integer.parseInt(name))
                   {   int diff=Integer.parseInt(name)-table.getSelectionModel().getSelectedItems().get(0).getQuantite();
                       int di=-diff;
                       ObservableList<Lignepanier> tableIndexprod1 = table.getSelectionModel().getSelectedItems();
                       System.out.println("diff inc"+di);
                       l.increment_Stockmodifier(tableIndexprod1.get(0).getProduct_id(),panier.getIdpan(),di);
                       l.updateL(table.getSelectionModel().getSelectedItems().get(0).getIdlp(),Integer.parseInt(name));
                             LignePanierService ser = new LignePanierService();
                               if(Integer.parseInt(name) <=0)
                               { 
                               l.deleteL(table.getSelectionModel().getSelectedItems().get(0).getIdlp());
                               l.prixupdate(panier.getIdpan(),Main.user_id);
                               calc.setText(String.valueOf(ser.calcul_total(panier.getIdpan())));
                      
                               table.getItems().remove(tableIndex);
                               table.refresh();
                               table.getSelectionModel().clearSelection();
                               }       
                              nompr.setCellValueFactory(new PropertyValueFactory<Lignepanier,String>("nompr"));
                              descrip.setCellValueFactory(new PropertyValueFactory<Lignepanier,String>("descrip"));
                              prix.setCellValueFactory(new PropertyValueFactory<Lignepanier,Double>("prix"));
                              quantite.setCellValueFactory(new PropertyValueFactory<Lignepanier,String>("quantite"));

                                try {
                                  l.prixupdate(panier.getIdpan(),Main.user_id);
                                   calc.setText(String.valueOf(ser.calcul_total(panier.getIdpan())));
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
                   else if(table.getSelectionModel().getSelectedItems().get(0).getQuantite()<Integer.parseInt(name))
                   {    ObservableList<Lignepanier> tableIndexprod = table.getSelectionModel().getSelectedItems();  
                       int q=l.prod_qte(tableIndexprod.get(0).getProduct_id(),panier.getIdpan());
                       int diff =Integer.parseInt(name)-table.getSelectionModel().getSelectedItems().get(0).getQuantite(); 
                       int add=q+diff;
                       System.out.println(add);
                            if(add>=Integer.parseInt(name)&& q>=0)
                            {
                             l.decrement_Stockmodifier(tableIndexprod.get(0).getProduct_id(),panier.getIdpan(),diff);
                             l.updateL(table.getSelectionModel().getSelectedItems().get(0).getIdlp(),Integer.parseInt(name));
                             LignePanierService ser = new LignePanierService();
                               if(Integer.parseInt(name) <=0)
                               { 
                               l.deleteL(table.getSelectionModel().getSelectedItems().get(0).getIdlp());
                               l.prixupdate(panier.getIdpan(),Main.user_id);
                               calc.setText(String.valueOf(ser.calcul_total(panier.getIdpan())));
                      
                               table.getItems().remove(tableIndex);
                               table.refresh();
                               table.getSelectionModel().clearSelection();
                               }       
                              nompr.setCellValueFactory(new PropertyValueFactory<Lignepanier,String>("nompr"));
                              descrip.setCellValueFactory(new PropertyValueFactory<Lignepanier,String>("descrip"));
                              prix.setCellValueFactory(new PropertyValueFactory<Lignepanier,Double>("prix"));
                              quantite.setCellValueFactory(new PropertyValueFactory<Lignepanier,String>("quantite"));

                                    try {
                                    l.prixupdate(panier.getIdpan(),Main.user_id);
                                    calc.setText(String.valueOf(ser.calcul_total(panier.getIdpan())));
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
                       else
                       {
                          Alert alert = new Alert(Alert.AlertType.ERROR);
                          alert.setTitle("Oups");
                          alert.setHeaderText(null);
                          alert.setContentText("Out of stock");
                          alert.showAndWait(); 
                       }
                   }
                   
                   }
         }else{
          Alert alert = new Alert(Alert.AlertType.ERROR);
          alert.setTitle("Information Dialog");
          alert.setHeaderText(null);
          alert.setContentText("no item has been selected");
          alert.showAndWait();
       }
        
       }  
        
        
    }
    
    
    
    
    
 
   
    
    
  
    

