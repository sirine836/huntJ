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
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
 * @author youss
 */
public class BackInterfaceController implements Initializable {

    @FXML
    private AnchorPane mainPane;
    @FXML
    private JFXButton btnevent;
    @FXML
    private Pane firstpane;
    @FXML
    private FontAwesomeIcon logout;
    @FXML
    private JFXButton logbtn;
    @FXML
    private AnchorPane acDashBord;
    @FXML
    private ScrollPane leftSideBarScroolPan;
    @FXML
    private VBox VBOX1;
    @FXML
    private ImageView imgStoreBtn;
    @FXML
    private ImageView imgEmployeBtn;
    @FXML
    private ImageView imgSellBtn;
    @FXML
    private ImageView imgSettingsBtn;
    private ToggleButton sideMenuToogleBtn;
    private ImageView imgMenuBtn;
    
    
    
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
    Image event = new Image("/icon/sell2.png");
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
    private JFXButton btnSAVb;
    @FXML
    private JFXButton btnProd;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void btnSVAb(ActionEvent event) throws IOException {
         firstpane.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("/gui/AvisReclamationBack.fxml"));
        firstpane.getChildren().add(parent);
        firstpane.toFront();
    }


     @FXML
    private void btnevent(ActionEvent event) throws IOException {
         firstpane.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("/gui/ChoiceBack.fxml"));
        firstpane.getChildren().add(parent);
        firstpane.toFront();
    }
    
    @FXML
    private void recrut(ActionEvent event) throws IOException {
        firstpane.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("/gui/FXMLChoiceBackRecrut.fxml"));
        firstpane.getChildren().add(parent);
        firstpane.toFront();
    }

    
    
    @FXML
    private void logout(MouseEvent event) throws IOException {
         
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

    @FXML
    private void btnStore(ActionEvent event) throws IOException {
         firstpane.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("/gui/HomeProdBackView.fxml"));
        firstpane.getChildren().add(parent);
        firstpane.toFront();
    }


  
    
   

    
}
