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
import com.jfoenix.controls.JFXButton;
import config.Config;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import static org.bouncycastle.crypto.tls.ContentType.alert;

/**
 * FXML Controller class
 *
 * @author cyrine
 */
public class CommanderController implements Initializable {

    @FXML
    private JFXButton btnpay;
    @FXML
    private JFXButton btnpan;
  
    @FXML
    private Pane firstpane;
    
    @FXML
    private TableView<Lignepanier> table;
    
    @FXML
    private TableColumn<Lignepanier, String> id_panier;
    
    @FXML
    public TextField num;
    @FXML
    public TextField adr;
    @FXML
    public DatePicker date;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void btnpan(javafx.event.ActionEvent event) throws IOException {
         firstpane.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("Panier.fxml"));
        firstpane.getChildren().add(parent);
        firstpane.toFront();
    }
    

  @FXML
    private void btnpay(ActionEvent event) throws IOException, SQLException {
     
      FactureService fs= new FactureService();
      Facture f = new Facture();
      Panier panier = new Panier();
      PanierService panierService = new PanierService();
      
        String numero = num.getText ();
        String adresse = adr.getText ();
        String dateliv = date.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
      
         if (validateInputs ()==true)
            {
         f.setNumtel(numero);
         f.setAdresse(adresse);
         f.setDatedelivraison(dateliv);

         try {
             panier = panierService.getCurrentPanierByUserID(Config.currentUser);
            f.setPanier_id(Config.currentpanier);
          
         } catch (SQLException ex) {
             ex.getSQLState();
         }
         System.out.println(f.toString());
             Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
             alert.setTitle("Confirmation d'Ajout");
             alert.setHeaderText("Confirmation");
             alert.setContentText("Ajouter Produit ??");
             Optional<ButtonType> result=alert.showAndWait();
             
             
      if(result.get()==ButtonType.OK){
             
            
      fs.ajouterFacture2(f);
           alert.setContentText("Ajouté");
           System.out.println("Nouveaux Produit ");
           firstpane.getChildren().clear();
           Parent parent = FXMLLoader.load(getClass().getResource("paiement.fxml"));
           firstpane.getChildren().add(parent);
           firstpane.toFront();
             }else{
                 
        System.out.println("Annuler");
        firstpane.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("commander.fxml"));
        firstpane.getChildren().add(parent);
        firstpane.toFront();
             }
    
        }
    }
         
    
    
    
    private boolean validateInputs() {
      
        if (num.getText().length() == 0 || adr.getText().length() == 0 ) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("tous les champs doivent être remplis");
            alert.showAndWait();
            return false;
         } else if (num.getText().length() !=8 ) {

            Alert alert2 = new Alert(Alert.AlertType.WARNING);
            alert2.setTitle("Warning Dialog ");
            alert2.setContentText("Numéro de telephone doit etre composer de 8 chiffres");
            alert2.setHeaderText(null);
            alert2.show();
            return false;
      
        } else if (isNotInteger(num.getText()) ) {

            Alert alert1 = new Alert(Alert.AlertType.WARNING);
            alert1.setTitle("Erreur");
            alert1.setContentText("num doivent etre composer de des chiffres");
            alert1.setHeaderText(null);
            alert1.show();
            return false;
        }
        
        return true;
        }
         
          public static boolean isNotInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException | NullPointerException e) {
            return true;
        }

        return false;
    }
        

   
    
}
