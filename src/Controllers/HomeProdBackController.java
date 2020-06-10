
package Controllers;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author MONDHER
 */
public class HomeProdBackController implements Initializable {
    @FXML
    private JFXButton btnProd;
    @FXML
    private JFXButton btnCateg;
    @FXML
    private JFXButton Mailing;
    @FXML
    private AnchorPane rootPane;
    
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void prodManag(ActionEvent event) throws IOException {
        rootPane.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("/gui/ProductManagerBackView.fxml"));
        rootPane.getChildren().add(parent);
        rootPane.toFront();
    }

    @FXML
    private void categManag(ActionEvent event) throws IOException {
        rootPane.getChildren().clear();
            Parent parent = FXMLLoader.load(getClass().getResource("/gui/CategoryManagerBackView.fxml"));
        rootPane.getChildren().add(parent);
        rootPane.toFront();
    }   

    @FXML
    private void GetMail(ActionEvent event) throws IOException {
        rootPane.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("/gui/SendMail.fxml"));
        rootPane.getChildren().add(parent);
        rootPane.toFront();
    }
}
