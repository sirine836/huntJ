/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Events;
import Services.EventService;
import Utils.DataBase;
import com.jfoenix.controls.JFXButton;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class NewEventController implements Initializable {

    @FXML
    private AnchorPane btnAjouter;
    @FXML
    private JFXButton refresh;
    @FXML
    private TextField Titrech;
    @FXML
    private TextField Descriptionch;
    @FXML
    private DatePicker Datech;
    @FXML
    private TextField Placesch;
    @FXML
    private TextField Prixch;
    @FXML
    private TextField Locationch;
    @FXML
    private TextField Proch;
    @FXML
    private Button consulter;
    @FXML
    private Button image;
    String path = "";
     @FXML
    private ImageView imageViewAdd;
    
    @FXML
    private Button btnAjout;
    @FXML
    private TableView<Events> table;
    @FXML
    private TableColumn<Events, String> C1;
    @FXML
    private TableColumn<Events, String> C2;
    @FXML
    private TableColumn<Events, String> C3;
    @FXML
    private TableColumn<Events, String> C4;

    @FXML
    private AnchorPane avr;
    Connection c = DataBase.getInstance().getCnx();
    EventService es = new EventService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<Events> event = FXCollections.observableArrayList();
        for (Events e : es.afficher()) {
            event.add(e);
            C1.setCellValueFactory(data -> {
                return new ReadOnlyStringWrapper(data.getValue().getTitre());
            });
            C2.setCellValueFactory(data -> {
                return new ReadOnlyStringWrapper(data.getValue().getDescription());
            });

            C3.setCellValueFactory(new PropertyValueFactory<Events, String>("prix"));

            C4.setCellValueFactory(new PropertyValueFactory<Events, String>("date"));
            table.setItems(event);

            //	delete.setCellValueFactory(new PropertyValueFactory<>("true"));

            /*C2.setCellValueFactory(new PropertyValueFactory<Events,String>("description"));
		C3.setCellValueFactory(new PropertyValueFactory<Events,String>("prix"));
                C4.setCellValueFactory(new PropertyValueFactory<Events,String>("date"));
             */
        }
    }

    @FXML
    private void ajouter(ActionEvent event) {
        if (validateInputs()) {
            String titre = Titrech.getText();
            String description = Descriptionch.getText();
            String date = Datech.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            int nbrPlaces = (Integer) Integer.parseInt(Placesch.getText()) + 0;
            int prix = (Integer) Integer.parseInt(Prixch.getText()) + 0;
            String localisation = Locationch.getText();
            int idPro = (Integer) Integer.parseInt(Proch.getText()) + 0;

            EventService es = new EventService();
            Events e = new Events(titre, description, date, nbrPlaces, prix, localisation, idPro,path);
            es.ajouter(e);
            System.out.println("Evenement ajouté");
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Evenement bien ajouté");

            alert.showAndWait();
            table.setItems(es.afficher());

        }

    }

    @FXML
    private void refresh(ActionEvent event) {

        ObservableList<Events> data = FXCollections.observableArrayList();
        table.setItems(es.afficher());
        System.out.println(es.afficher());

    }

    @FXML
    private void consulter(ActionEvent event) {
        {
            try {
                avr.getChildren().clear();
                Parent parent = FXMLLoader.load(getClass().getResource("/gui/EventsBack.fxml"));
                avr.getChildren().add(parent);
                avr.toFront();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    /*@FXML
    private void image(ActionEvent event) throws IOException {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", ".png", ".jpg", "*.gif"));

        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            Upload u = new Upload();
            u.Upload(selectedFile);
            image.setText(selectedFile.getName());

        }
    }*/
    
    
    @FXML
    private void image(ActionEvent event) throws MalformedURLException {

         BufferedOutputStream stream = null;
    String globalPath="C:\\wamp64\\www\\pidev-java\\Events\\src\\Images\\Upload\\";


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
            Logger.getLogger(NewEventController.class.getName()).log(Level.SEVERE, null, ex);}


}

    public static boolean isNotInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException | NullPointerException e) {
            return true;
        }

        return false;
    }

    private boolean validateInputs() {
        if (Titrech.getText().length() == 0 || Descriptionch.getText().length() == 0
                || Locationch.getText().length() == 0) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("tous les champs doivent etre remplis");
            alert.showAndWait();
            return false;
        } else if (isNotInteger(Prixch.getText()) || isNotInteger(Placesch.getText())) {

            Alert alert1 = new Alert(Alert.AlertType.WARNING);
            alert1.setTitle("Erreur");
            alert1.setContentText("nombre de places ,prix doivent etre des nombres");
            alert1.setHeaderText(null);
            alert1.show();
            return false;

        } 

        return true;
    }

}
