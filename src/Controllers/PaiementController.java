/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Facture;
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
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
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
    private JFXButton btnpan;
    @FXML
    private Pane firstpane;
    
    @FXML
    private Label calc;
    
   @FXML
    private JFXButton retourcom;
   
   @FXML
    private TextField tel;
    @FXML
    private TextField address;
     @FXML
    public DatePicker date;
    @FXML
    private AnchorPane anc;
    @FXML
    private Label openHomeBtn;
    @FXML
    private Label openPanierBtn;
    @FXML
    private Label openStoreBtn;
    @FXML
    private Label openCommandeBtn;
    @FXML
    private JFXTextField numeroCarte;
    @FXML
    private JFXTextField MoisValidite;
    @FXML
    private JFXTextField AnneeValidite;
    @FXML
    private JFXPasswordField ccvTextField;
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
            calcl.setText(String.valueOf(ser.calcul_total(Config.currentpanier)));
             System.out.println(ser.calcul_total(Config.currentpanier));
         } catch (SQLException ex) {
             Logger.getLogger(PaiementController.class.getName()).log(Level.SEVERE, null, ex);
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
    
    
    
    
    
     public void commander() throws IOException, SQLException {

//        FXMLLoader userLoader = new FXMLLoader(getClass().getResource("/Khrouf/Views/User.fxml"));
//        Parent root = (Parent)userLoader.load();
//        UserController userController =  userLoader.getController();
//        User currentUser = userController.getConnectedUser();
        FactureService fs= new FactureService();
      Facture f = new Facture();
      Panier panier = new Panier();
      PanierService panierService = new PanierService();

//      TODO replace UserID
//      Panier panier = pans.getPanierByUser(User.getId());
        panier = panierService.getCurrentPanierByUserID(Config.currentUser);

      /*  FXMLLoader commandeLoader = new FXMLLoader(getClass().getResource("/Achat/Views/Commander.fxml"));
        Parent root = (Parent) commandeLoader.load();
        CommandeController commandeController = commandeLoader.getController();

        commandeController.ajouterCommande(panier, address.getText(), tel.getText());
        System.out.println("ajouterCommande is called");
        tgt.Entities.SendEmailTLS.sendCommandeConfrimationMail(userMail,panier);*/
      
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
             }
    
        }
    }
     
     
     private boolean validateInputs() {
      
        if (tel.getText().length() == 0 || tel.getText().length() == 0 ) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
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
         
          public static boolean isNotInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException | NullPointerException e) {
            return true;
        }

        return false;
    }



    @FXML
    public void validerFunction(ActionEvent event) throws SQLException, IOException {
        
        try {
            int mois = Integer.parseInt(MoisValidite.getText());
            int annee = Integer.parseInt(AnneeValidite.getText());
            FactureService fs= new FactureService();
            PanierService panierService = new PanierService();
      Facture f = new Facture();
      Panier panier = new Panier();
      

//      TODO replace UserID
//      Panier panier = pans.getPanierByUser(User.getId());
            panier = panierService.getCurrentPanierByUserID(Config.currentUser);
            System.out.println("payment controller : " + panier);

            

            if (tel.getText().length() != 8) {

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
                   double amount=ser.calcul_total(Config.currentpanier);

                    Charge ch = StripePayment.ChargePayement("rk_test_oGfrFNOjpnRPklUVzjelPHgf", "usd", "tok_visa", amount, "sk_test_sM3fCA57AXRf30HBdPYXDmY80083NDeCJu", numeroCarte.getText(), mois, annee, ccvTextField.getText(), Config.userMail);
                    String tit = "Paiement réussi";
                    String message = "Votre paiement a été traité avec succès";
                    System.out.println(message);
//            NotificationType notification = NotificationType.SUCCESS;

//            TrayNotification tray = new TrayNotification(tit, message, notification);          
//            tray.setAnimationType(AnimationType.POPUP);
//            tray.showAndDismiss(javafx.util.Duration.seconds(2));
//        HomeController.afficherprofile=1;
//        ProfileController.affichercommandes=1;
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Achat/Views/Commande.fxml"));
//        AnchorPane root = (AnchorPane) loader.load();
//         Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//         stage.setScene(new Scene(loader));
//         stage.setTitle("Panier");
//       sendEmail();
//        email.getScene().setRoot(root);
//      t3ayet lel fonction showCommande ta3 controller le5er
                    commander();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success");
                    alert.setContentText("Votre Commande est en Cours de traitement !");
                    alert.setHeaderText(null);
                    alert.showAndWait();
                    /*Parent commandePage = FXMLLoader.load(getClass().getResource("/Achat/Views/Commande.fxml"));
                    anc.getChildren().clear();
                    anc.getChildren().add(commandePage);
                    anc.toFront();*/

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
    public void AnnulerFunction(ActionEvent event) {
        try {
           firstpane.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("panier.fxml"));
        firstpane.getChildren().add(parent);
        firstpane.toFront();
        } catch (IOException ex) {
            Logger.getLogger(PanierController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

           
    }    
    

