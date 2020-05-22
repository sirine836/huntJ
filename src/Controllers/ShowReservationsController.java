/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Evaluations;
import Entities.Reservations;
import Services.EvaluationService;
import Services.EventService;
import Services.ReservationService;
import Utils.DataBase;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXButton;
import com.mysql.jdbc.PreparedStatement;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author h^
 */
public class ShowReservationsController implements Initializable {

    @FXML
    private Button PDF;
    @FXML
    private TableView<Reservations> table;
    @FXML
    private TableColumn<Reservations, String> C1;
    @FXML
    private TableColumn<Reservations, String> C2;
    @FXML
    private TableColumn<Reservations, String> C3;
    @FXML
    private TableColumn<Reservations, String> C4;
    Connection c = DataBase.getInstance().getCnx();
    ReservationService rs = new ReservationService();

    private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.RED);
    private static Font orangeFont = new Font(Font.FontFamily.TIMES_ROMAN, 24, Font.NORMAL, BaseColor.ORANGE);

    @FXML
    private void PDF(ActionEvent event) throws DocumentException, FileNotFoundException {
        try {
            EventService es = new EventService();

            String file_name = "C:\\wamp64\\www\\pidev-java\\Events\\src\\pdf\\Reservations.pdf";
            Document document = new Document();

            PdfWriter.getInstance(document, new FileOutputStream(file_name));

            document.open();
            Paragraph par = new Paragraph("La liste des Reservations", orangeFont);
            Paragraph parr = new Paragraph("___________________");
            par.setAlignment(Element.ALIGN_CENTER);
            parr.setAlignment(Element.ALIGN_CENTER);
            Paragraph p = new Paragraph("  ");
            document.add(par);
            document.add(parr);
            document.add(p);
            document.add(p);
            document.add(p);
            Image img = Image.getInstance("C:\\wamp64\\www\\pidev-java\\Events\\src\\Images\\logo.png");
            Image img2 = Image.getInstance("C:\\wamp64\\www\\pidev-java\\Events\\src\\Images\\RESERVATION-ICON.png");
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
            String query = "SELECT r.id, r.quantite, r.prixpaye, u.username, e.titre , e.Image FROM reservation r INNER JOIN events e ON r.event_id=e.id INNER JOIN fos_user u ON r.user_id=u.id ";
            ps = (PreparedStatement) cx.prepareStatement(query);
            rs = ps.executeQuery();
            int i = 1;
            while (rs.next()) {

                Paragraph p2 = new Paragraph("Reservation n°" + i, redFont);
                document.add(p2);

                Paragraph para = new Paragraph("quantite " + rs.getInt("quantite") + " \n prixpaye =" + rs.getFloat("prixpaye") + " \n Event=" + rs.getString("titre") + " \n User=" + rs.getString("username"));
                para.setAlignment(Element.ALIGN_CENTER);

                document.add(para);
                Image imageEvent = Image.getInstance("http://localhost//pidev-java/Events/src/Images/Upload/" + rs.getString("Image"));
                imageEvent.scaleAbsolute(400f, 150f);
                imageEvent.setAlignment(Element.ALIGN_CENTER);

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
        ObservableList<Reservations> reservation = FXCollections.observableArrayList();
        for (Reservations r : rs.afficher()) {
            reservation.add(r);

            C1.setCellValueFactory(new PropertyValueFactory<Reservations, String>("titre"));
            C2.setCellValueFactory(new PropertyValueFactory<Reservations, String>("quantite"));
            C3.setCellValueFactory(new PropertyValueFactory<Reservations, String>("prixpaye"));
            C4.setCellValueFactory(new PropertyValueFactory<Reservations, String>("usename"));

            table.setItems(reservation);
        }
    }
}
