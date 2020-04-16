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
import Services.StripePayment;
import Services.PanierService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.stripe.model.Charge;
import com.stripe.model.Token;
import config.Config;
import static config.Config.currentUser;
import static config.Config.userMail;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author cyrine
 */
public class PaiementController implements Initializable {

    /**
     * Initializes the controller class.
     * 
     * 
     */
    @FXML
    private AnchorPane anc;
     @FXML
    private AnchorPane avr;
   @FXML
    private AnchorPane mainPane;
    @FXML
    private JFXButton btnprod;
    @FXML
    private JFXButton btnclient;
    @FXML
    private JFXButton backImageV;
    @FXML
    private JFXButton logbtn;
    @FXML
    private FontAwesomeIcon logout;
    
    
    @FXML
    private Pane firstpane;
    @FXML
    private JFXButton retourcom;
    @FXML
    private TextField tel;
    @FXML
    private TextField address;
     @FXML
    public DatePicker date;
     @FXML
    private TextField numeroCarte;
     @FXML
    private TextField nom;
    @FXML 
     private TextField MoisValidite;
    @FXML
    private TextField AnneeValidite;
    @FXML
    private PasswordField ccvTextField;
    @FXML
    private JFXButton btnValider;
    @FXML
    private JFXButton btnAnnuler;
    
   
   
    
    @FXML
    private ImageView imgValidationPanier;
    @FXML
    private ImageView imgLivraison;

    @FXML
    private Label calcl;
  
  
    
   

    /**
     * Initializes the controller class.
     */
    
    LignePanierService ser = new LignePanierService();
     Panier panier = new Panier();
      PanierService panierService = new PanierService();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
         try { 
            calcl.setText(String.valueOf(ser.calcul_total(Config.currentpanier)+5));
             System.out.println(ser.calcul_total(Config.currentpanier));
         } catch (SQLException ex) {
             Logger.getLogger(PaiementController.class.getName()).log(Level.SEVERE, null, ex);
         }
    }    

     private boolean validateInputs() 
     {
      if (tel.getText().length() == 0 || tel.getText().length() == 0 ||
                numeroCarte.getText().length()==0 ) 
      {   Alert alert = new Alert(Alert.AlertType.ERROR);
          alert.setTitle("Information Dialog");
          alert.setHeaderText(null);
          alert.setContentText("tous les champs doivent être remplis");
          alert.showAndWait();
          return false;
      } else if (tel.getText().length() !=8 ) {
            Alert alert2 = new Alert(Alert.AlertType.WARNING);
            alert2.setTitle("Warning Dialog ");
            alert2.setContentText("Numéro de telephone doit etre composer de 8 chiffres");
            alert2.setHeaderText(null);
            alert2.show();
            return false;
      } else if (isNotInteger(tel.getText()) ) {
        Alert alert1 = new Alert(Alert.AlertType.WARNING);
        alert1.setTitle("Erreur");
        alert1.setContentText("num doivent etre composer de des chiffres");
        alert1.setHeaderText(null);
        alert1.show();
        return false;
        }
        return true;
     }
         
          public static boolean isNotInteger(String s) 
        {
        try {  Integer.parseInt(s);} 
        catch (NumberFormatException | NullPointerException e) {
            return true;}
        return false;
        }
   
    private void btnpay() throws IOException, SQLException {
      FactureService fs= new FactureService();
      Facture f = new Facture();
      Panier panier = new Panier();
      PanierService panierService = new PanierService();
            
        String numero = tel.getText ();
        String adresse = address.getText ();
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
             alert.setTitle("Confirmation de Validation ");
             alert.setHeaderText("Confirmation");
             alert.setContentText("Valider !");
             Optional<ButtonType> result=alert.showAndWait();
             
             
      if(result.get()==ButtonType.OK){
          if(f.getPanier_id()!=Config.currentpanier ) 
          {
          fs.ajouterFacture2(f);
          }else {fs.modifierFacture(f.getIdfact(), Config.currentpanier,numero,
               adresse,dateliv, 1);
          }
           alert.setContentText("Ajouté");
           System.out.println("Nouveaux Produit ");
           firstpane.getChildren().clear();
           Parent parent = FXMLLoader.load(getClass().getResource("Panier.fxml"));
           firstpane.getChildren().add(parent);
           firstpane.toFront();
             }else{
                 
        System.out.println("Annuler");
        firstpane.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("paiement.fxml"));
        firstpane.getChildren().add(parent);
        firstpane.toFront();
             }
    
        }
    }
    
    private void btnpayinit() throws IOException, SQLException {
      FactureService fs= new FactureService();
      Facture f = new Facture();
      Panier panier = new Panier();
      PanierService panierService = new PanierService();
            
        String numero = tel.getText ();
        String adresse = address.getText ();
        String dateliv = date.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
       
       if (validateInputs ()==true)
            {
               f.setNumtel(numero);
               f.setAdresse(adresse);
               f.setDatedelivraison(dateliv);
               try {
                    panier = panierService.getCurrentPanierByUserID(Config.currentUser);
                    f.setPanier_id(Config.newpanier);
               } catch (SQLException ex) {
             ex.getSQLState();
             }
             System.out.println(f.toString());
             Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
             alert.setTitle("Confirmation de Validation ");
             alert.setHeaderText("Confirmation");
             alert.setContentText("Valider !");
             Optional<ButtonType> result=alert.showAndWait();
             
             
      if(result.get()==ButtonType.OK){
          if(f.getPanier_id()!=Config.newpanier ) 
          {
          fs.ajouterFacture2(f);
          }else {fs.modifierFacture(f.getIdfact(), Config.newpanier,numero,
               adresse,dateliv, 1);
          }
           alert.setContentText("Ajouté");
           System.out.println("Nouveaux Produit ");
           firstpane.getChildren().clear();
           Parent parent = FXMLLoader.load(getClass().getResource("Panier.fxml"));
           firstpane.getChildren().add(parent);
           firstpane.toFront();
             }else{
                 
        System.out.println("Annuler");
        firstpane.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("paiement.fxml"));
        firstpane.getChildren().add(parent);
        firstpane.toFront();
             }
    
        }
    }
           
    


    @FXML
    private void AnnulerFunction(javafx.event.ActionEvent event) throws IOException, SQLException{
        
      try {
           firstpane.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("panier.fxml"));
        firstpane.getChildren().add(parent);
        firstpane.toFront();
        } catch (IOException ex) {
            Logger.getLogger(PanierController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
   
    
    
    @FXML
    private void validerFunction(javafx.event.ActionEvent event) throws IOException, SQLException {
        PanierService p = new PanierService();
        FactureService fs= new FactureService();
        LignePanierService lps=new LignePanierService();
        
      Facture f = new Facture();
      Panier panier = new Panier();
      String formater=null;
         try {
            int mois = Integer.parseInt(MoisValidite.getText());
            int annee = Integer.parseInt(AnneeValidite.getText());
            

//      TODO replace UserID
//      Panier panier = pans.getPanierByUser(User.getId());
             panier = panierService.getCurrentPanierByUserID(Config.currentUser);
            System.out.println("payment controller : " + panier);

            if (tel.getText().length() == 0 || tel.getText().length() == 0 ||
                numeroCarte.getText().length()==0 ) 
      {   Alert alert = new Alert(Alert.AlertType.ERROR);
          alert.setTitle("Information Dialog");
          alert.setHeaderText(null);
          alert.setContentText("tous les champs doivent être remplis");
          alert.showAndWait();
           
      } else if (isNotInteger(tel.getText()) ) {
        Alert alert1 = new Alert(Alert.AlertType.WARNING);
        alert1.setTitle("Erreur");
        alert1.setContentText("num doivent etre composer de des chiffres");
        alert1.setHeaderText(null);
        alert1.show();
        
        }else  if (tel.getText().length() != 8) {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Alert");
                alert.setContentText("numero Tel incorrect !");
                alert.setHeaderText(null);
                alert.showAndWait();

        } else if (address.getText().length() < 5) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Alert");
                alert.setContentText("adresse incorrecte !");
                alert.setHeaderText(null);
                alert.showAndWait();
            } else {
               

                 Token token = StripePayment.getToken("pk_test_nBZ1PBy1Q0GzbbioWDBJqxv200SDGaJUNq", numeroCarte.getText(), mois, annee, ccvTextField.getText(), Config.userMail);

                if (token != null) {
//                TODO change amount by the real Panier Prix Total
//                TODO change UserMail By Real User mail
                    Double amount = ser.calcul_total(Config.currentpanier);

                    Charge ch = StripePayment.ChargePayement("rk_test_oGfrFNOjpnRPklUVzjelPHgf", "usd", "tok_visa", amount, "sk_test_sM3fCA57AXRf30HBdPYXDmY80083NDeCJu", numeroCarte.getText(), mois, annee, ccvTextField.getText(), Config.userMail);
                    String tit = "Paiement réussi";
                    String message = "Votre paiement a été traité avec succès";
                    System.out.println(message);
                btnpay();
                  p.updatePan(Config.currentpanier,Config.currentUser,amount+(amount/100*18)+5);
                   
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success");
                    alert.setContentText("Votre Commande est en Cours de traitement !");
                    alert.setHeaderText(null);
                    alert.showAndWait();
                    
                    /*   if(f.getEtat()==1)
                        {Date aujourdhui = new Date();
                         formater = new SimpleDateFormat("dd-MM-yy").toString();
                         Panier pan=new Panier(Config.currentUser,formater);
                          Facture fp=new Facture(Config.newpanier);
                         Lignepanier l = new Lignepanier();
                          p.updatePanierinit(Config.newpanier,Config.currentUser);
                         p.ajouterPanier2(pan);
                         System.out.println(pan);
                        
                         lps.ajouterLigne(l);
                         btnpay();
                        }
                       
                      */

                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Alert");
                    alert.setContentText("carte invalide !");
                    alert.setHeaderText(null);
                    alert.showAndWait();
                }
            }

        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Alert");
            alert.setContentText("carte invalide !");
            alert.setHeaderText(null);
            alert.showAndWait();
        }

   
         
    }
         
    
   
           
          
          
      
          
          @FXML
    private void btnpan(javafx.event.ActionEvent event) throws IOException {
         firstpane.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("Panier.fxml"));
        firstpane.getChildren().add(parent);
        firstpane.toFront();
    }
    
    @FXML
    private void btnprod(javafx.event.ActionEvent event) throws IOException {
         firstpane.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("Menu.fxml"));
        firstpane.getChildren().add(parent);
        firstpane.toFront();
    }
    
    
    @FXML
    private void btnclient(javafx.event.ActionEvent event) throws IOException {
         firstpane.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("AvisReclamation.fxml"));
        firstpane.getChildren().add(parent);
        firstpane.toFront();
    }
    
    @FXML
    private void retourcom(javafx.event.ActionEvent event) throws IOException {
         firstpane.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("panier.fxml"));
        firstpane.getChildren().add(parent);
        firstpane.toFront();
    }
 
    @FXML
    private void logout(MouseEvent event) throws IOException {
    }

    @FXML
    private void logbtn(javafx.event.ActionEvent event)  throws IOException {
         Stage stage1 = (Stage) mainPane.getScene().getWindow();
            
        stage1.close();
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.getIcons().add(new Image("/Images/logo.png"));
        stage.setTitle("T-HUNT");
        stage.show();
    }
    
    }    
    

