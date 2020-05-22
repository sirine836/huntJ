/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Events;
import Services.EventService;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import com.jfoenix.controls.*;
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
import com.mysql.jdbc.PreparedStatement;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author youss
 */
public class EventsBackController implements Initializable {

    @FXML
    private Button Delete;
    @FXML
    private Button Update;
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
    private Button PDF;
    private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.RED);
    private static Font orangeFont = new Font(Font.FontFamily.TIMES_ROMAN, 24, Font.NORMAL, BaseColor.ORANGE);

    java.sql.PreparedStatement pst;
    @FXML
    private AnchorPane avr;
    Connection c = DataBase.getInstance().getCnx();
    EventService es = new EventService();
    @FXML
    private Button New;

    @FXML
    private void Delete(ActionEvent event) throws SQLException {

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
    }

    @FXML
    private void Add(ActionEvent event) throws IOException {
        avr.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("/gui/NewEvent.fxml"));
        avr.getChildren().add(parent);
        avr.toFront();
    }

    @FXML
    private void Update(ActionEvent event) throws IOException {
        avr.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("/gui/UpdateEvent.fxml"));
        avr.getChildren().add(parent);
        avr.toFront();
    }

    @FXML
    private void PDF(ActionEvent event) throws DocumentException, FileNotFoundException {
        try {
            EventService es = new EventService();

            String file_name = "C:\\wamp64\\www\\pidev-java\\Events\\src\\pdf\\Events.pdf";
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
            Image img = Image.getInstance("C:\\wamp64\\www\\pidev-java\\Events\\src\\Images\\logo.png");
            Image img2 = Image.getInstance("C:\\wamp64\\www\\pidev-java\\Events\\src\\Images\\unnamed.png");
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
                Image imageEvent = Image.getInstance("http://localhost//pidev-java/Events/src/Images/Upload/" + rs.getString("Image"));
                imageEvent.scaleAbsolute(400f, 150f);

                document.add(para);
                document.add(imageEvent);
                document.add(new Paragraph("  "));

                i++;
            }

            document.close();
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
}
