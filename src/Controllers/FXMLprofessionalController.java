/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import static Controllers.Main.fos_user;
import Entities.Professional;
import Services.ServiceProfessional;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.SQLException;
import java.util.ResourceBundle;
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
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * FXML Controller class
 *
 * @author VENOM
 */
public class FXMLprofessionalController implements Initializable {

    @FXML
    private TextField first;
    @FXML
    private TextField last;
    @FXML
    private TextField email;
    @FXML
    private TextField soft;
    @FXML
    private Button cv;
    @FXML
    private Button ajouterPro;
    @FXML
    private AnchorPane image;
    @FXML
    private Button clear;
    String path = "";
    @FXML
    private ImageView imageViewAdd;


    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajouter(ActionEvent event) throws SQLException, IOException {
        String Last = last.getText();
        String First = first.getText();
        String Email = email.getText();
        String Soft = soft.getText();
        String CV = cv.getText();
        ServiceProfessional sp = new ServiceProfessional();
        Professional p = new Professional(Last, First,Email,Soft,path);
        sp.addProfessional(p);
        System.out.println("ADDED SUCCESSFULLY!");
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Seller added with this e-mail : " +fos_user.getEmail());
                alert.showAndWait();
                image.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("/gui/ChoiceRecrut.fxml"));
        image.getChildren().add(parent);
        image.toFront();
    }
     @FXML
    private void ClearFields(ActionEvent event) {  //Vider les champs **DONE**
         last.clear();
         first.clear();
         email.clear();
         soft.clear();
    }
    
    @FXML
    private void uploadCV(ActionEvent event) throws MalformedURLException {

         BufferedOutputStream stream = null;
    String globalPath="C:\\wamp64\\www\\pidev-java\\Events\\src\\Images\\CV\\";


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
            Logger.getLogger(FXMLprofessionalController.class.getName()).log(Level.SEVERE, null, ex);}


}

  
    
}
