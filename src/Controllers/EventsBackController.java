/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import static Controllers.AllEventsController.value;
import Entities.Events;
import Services.EventService;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import Utils.DataBase;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXTextField;
import com.mysql.jdbc.PreparedStatement;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author fedi
 */
public class EventsBackController implements Initializable {

    private TableView<Events> table;
    private TableColumn<Events, String> C1;
    private TableColumn<Events, String> C2;
    private TableColumn<Events, String> C3;
    private TableColumn<Events, String> C4;
    @FXML
    private Button PDF;
    private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.RED);
    private static Font orangeFont = new Font(Font.FontFamily.TIMES_ROMAN, 24, Font.NORMAL, BaseColor.ORANGE);

    java.sql.PreparedStatement pst;
    @FXML
    private AnchorPane holderPane;
    Connection cx = DataBase.getInstance().getCnx();
    EventService es = new EventService();
    @FXML
    private Button New;
    @FXML
    private ImageView image1;
    @FXML
    private Label label1;
    @FXML
    private Button btn1_event;
    @FXML
    private ImageView image3;
    @FXML
    private Label label3;
    @FXML
    private Button btn3_event;
    @FXML
    private ImageView image4;
    @FXML
    private Label label4;
    @FXML
    private Button btn4_event;
    @FXML
    private ImageView eventsuivant;
    @FXML
    private ImageView eventprecedent;
    @FXML
    private JFXTextField rechercheBar;
    @FXML
    private ImageView image2;
    @FXML
    private Label label2;
    @FXML
    private Button btn2_event;
   private static int a, b, c, d, i, idevent1, idevent2, idevent3, idevent4;
    private ObservableList<Events> data;
    public static String value;


   /* private void Delete(ActionEvent event) throws SQLException {

        if (table.getSelectionModel().getSelectedItems().size() != 0) {
            EventService es = new EventService();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("WARNING");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to delete event ?");
            alert.showAndWait();
            es.supprimer(table.getSelectionModel().getSelectedItems().get(0).getId());

        } else {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("aucun élément 'a ètè séléctionné");
            alert.showAndWait();

        }
    }*/

    @FXML
    private void Add(ActionEvent event) throws IOException {
        holderPane.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("/gui/NewEvent.fxml"));
        holderPane.getChildren().add(parent);
        holderPane.toFront();
    }

   /* private void Update(ActionEvent event) throws IOException {
        holderPane.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("/gui/UpdateEvent.fxml"));
        holderPane.getChildren().add(parent);
        holderPane.toFront();
    }*/

    @FXML
    private void PDF(ActionEvent event) throws DocumentException, FileNotFoundException {
        try {
            EventService es = new EventService();

            String file_name = "C:\\wamp64\\www\\projet_3a\\pidev-java\\Events\\src\\pdf\\Events.pdf";
            Document document = new Document();

            PdfWriter.getInstance(document, new FileOutputStream(file_name));

            document.open();
            Paragraph par = new Paragraph("La liste des evenements", orangeFont);
            Paragraph parr = new Paragraph("___________________", orangeFont);
            par.setAlignment(Element.ALIGN_CENTER);
            parr.setAlignment(Element.ALIGN_CENTER);

            Paragraph p = new Paragraph("  ");
            document.add(par);
            document.add(parr);
            document.add(p);
            document.add(p);
            document.add(p);
            Image img = Image.getInstance("C:\\wamp64\\www\\projet_3a\\pidev-java\\Events\\src\\Images\\logo.png");
            Image img2 = Image.getInstance("C:\\wamp64\\www\\projet_3a\\pidev-java\\Events\\src\\Images\\unnamed.png");
            img.scaleAbsolute(50f, 50f);
            img.setAbsolutePosition(450f, 775f);
            img2.setAbsolutePosition(100f, 775f);
            img2.scaleAbsolute(50f, 50f);

            document.add(img);
            document.add(img2);
            document.add(p);
            document.add(p);
            document.add(p);

            Connection cx = DataBase.getInstance().getCnx();
            PreparedStatement ps = null;
            ResultSet rs = null;
            String query = "Select * from events";
            ps = (PreparedStatement) cx.prepareStatement(query);
            rs = ps.executeQuery();
            int i = 1;
            while (rs.next()) {
                Paragraph p2 = new Paragraph("Evenement n°" + i, redFont);
                document.add(p2);

                Paragraph para = new Paragraph("Titre   " + rs.getString("titre") + " \n Descriptioon   " + rs.getString("description") + " \n Date   " + rs.getString("date"));
                Image imageEvent = Image.getInstance("http://localhost//projet_3a/symfony/web/images/" + rs.getString("nom_image"));
                imageEvent.scaleAbsolute(400f, 150f);

                document.add(para);
                document.add(imageEvent);
                document.add(new Paragraph("  "));

                i++;
            }

            document.close();
            
             String tit = "PDF";
            String message = "PDF telechargé avec succés.";
            NotificationType notification = NotificationType.SUCCESS;
    
            TrayNotification tray = new TrayNotification(tit, message, notification);          
            tray.setAnimationType(AnimationType.POPUP);
            tray.showAndDismiss(javafx.util.Duration.seconds(10));
            System.out.println("finished");
        } catch (Exception e) {
            System.err.println(e);
        }

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         a = 0;
        b = 1;
        c = 2;
        d = 3;
        eventprecedent.setVisible(false);
        buildData();
        rechercheBar.textProperty().addListener((observable, oldValue, newValue) -> {
            value = rechercheBar.getText();
            buildData();
        });

    }
    
    
     private void buildData() {
        data = FXCollections.observableArrayList();
        EventService su = EventService.getInstance();
        btn1_event.setVisible(false);
        label1.setVisible(false);
        image1.setVisible(false);
        btn2_event.setVisible(false);
        label2.setVisible(false);
        image2.setVisible(false);
        btn3_event.setVisible(false);
        label3.setVisible(false);
        image3.setVisible(false);
        btn4_event.setVisible(false);
        label4.setVisible(false);
        image4.setVisible(false);
        i = 0;
        int j = 0;
        try {
            ResultSet rs = su.afficherevents();
            while (rs.next()) {

                Events evt = new Events();
                evt.setId(rs.getInt(1));
                evt.setTitre(rs.getString(2));
                evt.setLocalisation(rs.getString(7));
                evt.setDate(rs.getString(4));
                evt.setDescription(rs.getString(3));
                evt.setNbrPlaces(rs.getInt(5));
                evt.setPrix(rs.getInt(6));
                evt.setNom_image(rs.getString(8));
                evt.setIdPro(rs.getInt(9));

                if (value == null) {
                    data.add(evt);
                    j++;
                } ////////recherche ///////// 
                else if (evt.getTitre().toLowerCase().contains(value.toLowerCase()) || evt.getDescription().toLowerCase().contains(value.toLowerCase())) {
                    data.add(evt);
                    j++;

                }

                while (i < j) {
                    if (i == a) { 
                        btn1_event.setVisible(true);
                        label1.setVisible(true);
                        image1.setVisible(true);
                        label1.setText(data.get(i).getTitre());
                        String img = "http://localhost//projet_3a/symfony/web/images/" + data.get(i).getNom_image();
                        idevent1 = data.get(i).getId();
                        image1.setImage(new javafx.scene.image.Image(img));
                    } else if (i == b) {
                        btn2_event.setVisible(true);
                        label2.setVisible(true);
                        image2.setVisible(true);
                        label2.setText(data.get(i).getTitre());
                        String img = "http://localhost//projet_3a/symfony/web/images/" + data.get(i).getNom_image();
                        idevent2 = data.get(i).getId();
                        image2.setImage(new javafx.scene.image.Image(img));
                    } else if (i == c) {
                        btn3_event.setVisible(true);
                        label3.setVisible(true);
                        image3.setVisible(true);
                        label3.setText(data.get(i).getTitre());
                        String img = "http://localhost//projet_3a/symfony/web/images/" + data.get(i).getNom_image();
                        idevent3 = data.get(i).getId();
                        image3.setImage(new javafx.scene.image.Image(img));
                    } else if (i == d) {
                        btn4_event.setVisible(true);
                        label4.setVisible(true);
                        image4.setVisible(true);
                        label4.setText(data.get(i).getTitre());
                        String img = "http://localhost//projet_3a/symfony/web/images/" + data.get(i).getNom_image();
                        idevent4 = data.get(i).getId();
                        image4.setImage(new javafx.scene.image.Image(img));
                    }

                    i++;
                }

            }

        } catch (SQLException ex) {
            Logger.getLogger(EventsBackController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @FXML
    private void affichage_1(ActionEvent event) throws IOException {
        EventsDetailsBackController.id = idevent1;
        AnchorPane page1 = FXMLLoader.load(getClass().getResource("/gui/EventsDetailsBack.fxml"));
        setNode(page1);
    }

    @FXML
    private void affichage_3(ActionEvent event) throws IOException {
        EventsDetailsBackController.id = idevent3;
        AnchorPane page1 = FXMLLoader.load(getClass().getResource("/gui/EventsDetailsBack.fxml"));
        setNode(page1);
    }

    @FXML
    private void affichage_4(ActionEvent event) throws IOException {
        EventsDetailsBackController.id = idevent4;
        AnchorPane page1 = FXMLLoader.load(getClass().getResource("/gui/EventsDetailsBack.fxml"));
        setNode(page1);
    }
  @FXML
    private void suivant_event(MouseEvent event) {
        a = a + 4;
        b = b + 4;
        c = c + 4;
        d = d + 4;
        eventprecedent.setVisible(true);
        buildData();
        if (b == i || a == i || c == i || d == i) {
            eventsuivant.setVisible(false);
        }
    }

    @FXML
    private void event_precedent(MouseEvent event) {
        a = a - 4;
        b = b - 4;
        c = c - 4;
        d = d - 4;
        eventsuivant.setVisible(true);

        if (a == 0) {
            eventprecedent.setVisible(false);
        }
        buildData();
    }
    
     public void setNode(Node node) {
        holderPane.getChildren().clear();
        holderPane.getChildren().add((Node) node);

        FadeTransition ft = new FadeTransition(Duration.millis(1000));
        ft.setNode(node);
        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();
    }

    @FXML
    private void affichage_2(ActionEvent event) throws IOException {
        EventsDetailsBackController.id = idevent2;
        AnchorPane page1 = FXMLLoader.load(getClass().getResource("/gui/EventsDetailsBack.fxml"));
        setNode(page1);
    }
}
