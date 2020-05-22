/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author h^
 */
public class MainInterfaceController implements Initializable {

    @FXML
    private AnchorPane mainPane;
    @FXML
    private Pane firstpane;
    @FXML
    private JFXButton btnevent;
    private ToggleButton sideMenuToogleBtn;
    private ImageView imgMenuBtn;
    @FXML
    private ScrollPane leftSideBarScroolPan;
     @FXML
    private JFXButton logbtn;
    @FXML
    private FontAwesomeIcon logout;
    @FXML
    private AnchorPane acDashBord;
    @FXML
    private VBox VBOX1;
    @FXML
    private JFXButton btnHome;
    @FXML
    private ImageView imgHomeBtn;
    @FXML
    private JFXButton btnStore;
    @FXML
    private ImageView imgStoreBtn;
    @FXML
    private ImageView imgEmployeBtn;
    @FXML
    private ImageView imgSellBtn;
    @FXML
    private JFXButton btnSAV;
    @FXML
    private ImageView imgSettingsBtn;
    @FXML
    private JFXButton btnAbout;
    
    @FXML
    private ImageView imgAboutBtn;
    @FXML
    private  Label emailtxt;
    
   
   

  
    


    Image menuImage = new Image("/icon/menu.png");
    Image menuImageRed = new Image("/icon/menuRed.png");
    Image image;

    String defultStyle = "-fx-border-width: 0px 0px 0px 5px;"
            + "-fx-border-color:none";

    String activeStyle = "-fx-border-width: 0px 0px 0px 5px;"
            + "-fx-border-color:#FF4E3C";

    Image home = new Image("/icon/home.png");
    Image homeRed = new Image("/icon/homeRed.png");
    Image store = new Image("/icon/stock.png");
    Image storered = new Image("/icon/stockRed.png");
    Image event = new Image("/icon/sell.png");
    Image eventred = new Image("/icon/sellRed.png");
    Image recruit = new Image("/icon/employe.png");
    Image recruitRed = new Image("/icon/employeRed.png");
    Image SAV = new Image("/icon/settings.png");
    Image SAVRed = new Image("/icon/settingsRed.png");
    Image about = new Image("/icon/about.png");
    Image aboutRed = new Image("/icon/aboutRed.png");
    @FXML
    private JFXButton recrut;
    @FXML
    private JFXButton btnpan;
    @FXML
    private JFXButton btnuser;
   
  
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
          firstpane.getChildren().clear();
        Parent parent;
        try {
            parent = FXMLLoader.load(getClass().getResource("/gui/Menu.fxml"));
        
        firstpane.getChildren().add(parent);
        firstpane.toFront();
        } catch (IOException ex) {
            Logger.getLogger(MainInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    
    private Button backtologin;
    private void gotologininterface(MouseEvent event) {
	 backtologin.getScene().getWindow().hide();
        try {
            Stage dashboardStage = new Stage();
            dashboardStage.setTitle("");
            Parent root = FXMLLoader.load(getClass().getResource("/gui/Login.fxml"));
            Scene scene = new Scene(root);
            dashboardStage.setScene(scene);
            dashboardStage.show();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

     @FXML
    public void showprofile() {
        try {
            Stage profileStage = new Stage();
            profileStage.setTitle("");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/profile.fxml"));
            Parent  root= loader.load();
            profileController pController = loader.getController();
            pController.setUserInfo(emailtxt.getText());
            Scene scene = new Scene(root);
            profileStage.setScene(scene);
            profileStage.show();
        } catch (IOException ex) {
            Logger.getLogger(Dashboard_UserController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    


    
    
    
  

    @FXML
    private void logout(MouseEvent event) {
    }

  




    /*@FXML
    private void btnclient(ActionEvent event) throws IOException {
         firstpane.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("AvisReclamation.fxml"));
        firstpane.getChildren().add(parent);
        firstpane.toFront();
    }*/
    @FXML
    private void btnevent(ActionEvent event) throws IOException {
        
        EventsActive();
        firstpane.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("/gui/Choice.fxml"));
        firstpane.getChildren().add(parent);
        firstpane.toFront();
    }
    
    @FXML
    private void btnpan(ActionEvent event) throws IOException {
         firstpane.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("/gui/Panier.fxml"));
        firstpane.getChildren().add(parent);
        firstpane.toFront();
    }
    
     @FXML
    private void btnSAV(ActionEvent event) throws IOException {
        SavActive();
         firstpane.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("/gui/AvisReclamation.fxml"));
        firstpane.getChildren().add(parent);
        firstpane.toFront();
    }
      

    @FXML
    private void logbtn(ActionEvent event) throws IOException {
        Stage stage1 = (Stage) mainPane.getScene().getWindow();

        stage1.close();
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/gui/Login.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.getIcons().add(new Image("/Images/logo.png"));
        stage.setTitle("T-HUNT");
        stage.show();
    }

   
    
     private void EventsActive() {
        imgHomeBtn.setImage(home);
        imgStoreBtn.setImage(store);
        imgSellBtn.setImage(eventred);
        imgEmployeBtn.setImage(recruit);
        imgSettingsBtn.setImage(SAV);
        imgAboutBtn.setImage(about);
        btnHome.setStyle(defultStyle);
        btnStore.setStyle(defultStyle);
        btnevent.setStyle(activeStyle);
        recrut.setStyle(defultStyle);
        btnSAV.setStyle(defultStyle);
        btnAbout.setStyle(defultStyle);
    }
    
    
     private void RecrutActive() {
        imgHomeBtn.setImage(home);
        imgStoreBtn.setImage(store);
        imgSellBtn.setImage(event);
        imgEmployeBtn.setImage(recruitRed);
        imgSettingsBtn.setImage(SAV);
        imgAboutBtn.setImage(about);
        btnHome.setStyle(defultStyle);
        btnStore.setStyle(defultStyle);
        btnevent.setStyle(defultStyle);
        recrut.setStyle(activeStyle);
        btnSAV.setStyle(defultStyle);
        btnAbout.setStyle(defultStyle);
    }
     
     
     private void StoreActive() {
        imgHomeBtn.setImage(home);
        imgStoreBtn.setImage(storered);
        imgSellBtn.setImage(event);
        imgEmployeBtn.setImage(recruit);
        imgSettingsBtn.setImage(SAV);
        imgAboutBtn.setImage(about);
        btnHome.setStyle(defultStyle);
        btnStore.setStyle(activeStyle);
        btnevent.setStyle(defultStyle);
        recrut.setStyle(defultStyle);
        btnSAV.setStyle(defultStyle);
        btnAbout.setStyle(defultStyle);
    }

          
     private void SavActive() {
        imgHomeBtn.setImage(home);
        imgStoreBtn.setImage(store);
        imgSellBtn.setImage(event);
        imgEmployeBtn.setImage(recruit);
        imgSettingsBtn.setImage(SAVRed);
        imgAboutBtn.setImage(about);
        btnHome.setStyle(defultStyle);
        btnStore.setStyle(defultStyle);
        btnevent.setStyle(defultStyle);
        recrut.setStyle(defultStyle);
        btnSAV.setStyle(activeStyle);
        btnAbout.setStyle(defultStyle);
    }
     
       private void AboutActive() {
        imgHomeBtn.setImage(home);
        imgStoreBtn.setImage(store);
        imgSellBtn.setImage(event);
        imgEmployeBtn.setImage(recruit);
        imgSettingsBtn.setImage(SAV);
        imgAboutBtn.setImage(aboutRed);
        btnHome.setStyle(defultStyle);
        btnStore.setStyle(defultStyle);
        btnevent.setStyle(defultStyle);
        recrut.setStyle(defultStyle);
        btnSAV.setStyle(defultStyle);
        btnAbout.setStyle(activeStyle);
    }


    private void logout(ActionEvent event) throws IOException {
         Stage stage1 = (Stage) mainPane.getScene().getWindow();

        stage1.close();
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/gui/Login.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.getIcons().add(new Image("/Images/logo.png"));
        stage.setTitle("T-HUNT");
        stage.show();
    }

   @FXML
    private void recrut(ActionEvent event) throws IOException {
        RecrutActive();
         firstpane.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("/gui/ChoiceRecrut.fxml"));
        firstpane.getChildren().add(parent);
        firstpane.toFront();
    }

    @FXML
    private void OpenStore(ActionEvent event) throws IOException {
            StoreActive();
        firstpane.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("/gui/HomeProdView.fxml"));
        firstpane.getChildren().add(parent);
        firstpane.toFront();
    }
    
    public void transferMessage(String text) {
		// TODO Auto-generated method stub
		emailtxt.setText(text);
    }

    @FXML
    private void btnAbout(ActionEvent event) throws IOException {
        AboutActive();
        firstpane.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("/gui/about.fxml"));
        firstpane.getChildren().add(parent);
        firstpane.toFront();
        
    }

    @FXML
    private void btnHome(ActionEvent event) throws IOException {
         firstpane.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("/gui/Menu.fxml"));
        firstpane.getChildren().add(parent);
        firstpane.toFront();
    }

  

}
