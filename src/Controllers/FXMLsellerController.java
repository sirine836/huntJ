/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import static Controllers.Main.fos_user;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import Entities.Seller;
import Services.ServiceSeller;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * FXML Controller class
 *
 * @author VENOM
 */
public class FXMLsellerController implements Initializable {
    @FXML
    private AnchorPane avr;
    @FXML
    private TextField sellername;
    @FXML
    private TextField taxnumber;
    @FXML
    private TextField rcs;
    @FXML
    private TextField tva;
    @FXML
    private TextField siren;
    @FXML
    private TextField fax;
    @FXML
    private TextField phonenumber;
    @FXML
    private TextField email;
    @FXML
    private Button license;
    @FXML
    private Button add;
    String path = "";
    @FXML
    private ImageView imageViewAdd;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @FXML
    private void addSeller(ActionEvent event) throws SQLException, IOException {
        Parent root;
        Stage primaryStage = new Stage();
        String Company= sellername.getText();
        String RCS = rcs.getText();
        String TAX = taxnumber.getText();
        String TVA = tva.getText();
        String SIREN = siren.getText();
        String FAX = fax.getText();
        String Phone = phonenumber.getText();
        String Email = email.getText();
        String License = license.getText();
        int intTVA = Integer.parseInt(TVA);
        int intSIREN = Integer.parseInt(SIREN);
        int intFAX = Integer.parseInt(FAX);
        int intPHONE = Integer.parseInt(Phone);
        ServiceSeller ss = new ServiceSeller();
        Seller s = new Seller(Company, RCS, TAX, intTVA, intSIREN, intFAX, intPHONE, Email, path);
        ss.addSeller(s);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Seller added with this e-mail : " +fos_user.getEmail());
                alert.showAndWait();
                avr.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("/gui/ChoiceRecrut.fxml"));
        avr.getChildren().add(parent);
        avr.toFront();
        
                
    }
    
    
    
    @FXML
    private void ClearFields(ActionEvent event) {  //Vider les champs **DONE**
         sellername.clear();
         rcs.clear();
         taxnumber.clear();
         tva.clear();
         siren.clear();
         fax.clear();
         phonenumber.clear();
         email.clear();
         
    }
    
    @FXML
    private void uploadLicense(ActionEvent event) throws MalformedURLException {

         BufferedOutputStream stream = null;
    String globalPath="C:\\wamp64\\www\\pidev-java\\Events\\src\\Images\\Licence\\";


        try {

        JFileChooser fileChooser = new JFileChooser(); 
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.IMAGE", "jpg", "gif", "png");
        fileChooser.addChoosableFileFilter(filter);
        int result = fileChooser.showSaveDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {

            File selectedFile = fileChooser.getSelectedFile();
            path = selectedFile.getName();

            Path p = selectedFile.toPath();
            byte[] bytes = Files.readAllBytes(p); 
            File dir = new File(globalPath);

            File serverFile = new File(dir.getAbsolutePath()+File.separator + path);
            stream = new BufferedOutputStream(new FileOutputStream(serverFile));
            stream.write(bytes);
            stream.close();


            String path2 = selectedFile.toURI().toURL().toString();
            Image image = new Image(path2);
            imageViewAdd.setImage(image);

        } else if (result == JFileChooser.CANCEL_OPTION) {
            System.out.println("NoData");
        }

                } catch (IOException ex) {
            Logger.getLogger(FXMLsellerController.class.getName()).log(Level.SEVERE, null, ex);}


}

  
 /*
    @FXML
    private void back(ActionEvent event){
   
         Stage primaryStage = new Stage();
         Parent root;
         
        try {
            root = FXMLLoader.load(getClass().getResource("/gui/FXMLprofessional.fxml"));
        
             Scene scene = new Scene(root);
            
            primaryStage.setTitle("Sign Up Page");
            primaryStage.setScene(scene);
            primaryStage.show();}
        catch (IOException ex) {
            Logger.getLogger(FXMLsellerController.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
*/

}
