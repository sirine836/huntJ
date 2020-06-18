/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Evaluations;
import Services.EvaluationService;
import Utils.DataBase;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author h^
 */
public class ShowEvaluationsController implements Initializable {

    @FXML
    private TableView<Evaluations> table;
    @FXML
    private TableColumn<Evaluations, String> C1;
    @FXML
    private TableColumn<Evaluations, String> C2;
    @FXML
    private TableColumn<Evaluations, String> C3;
    EvaluationService evs = new EvaluationService();
    Connection c = DataBase.getInstance().getCnx();
    @FXML
    private Button stats;
    @FXML
    private AnchorPane avr;

    /*@FXML
    private void PDF(ActionEvent event) throws DocumentException, FileNotFoundException {
        try {
            EventService es = new EventService();

            String file_name = "C:\\Users\\h^\\Desktop\\pdf\\Evaluations.pdf";
            Document document = new Document();

            PdfWriter.getInstance(document, new FileOutputStream(file_name));

            document.open();
            Paragraph par = new Paragraph("                                                            La liste des evaluations");
            Paragraph parr = new Paragraph("                                                            ___________________");
            Paragraph p = new Paragraph("  ");
            document.add(par);
            document.add(parr);
            document.add(p);
            document.add(p);
            document.add(p);
            document.add(p);
            document.add(p);
            document.add(p);

            Connection cx = DataBase.getInstance().getCnx();
            PreparedStatement ps = null;
            ResultSet rs = null;
            String query = "SELECT ev.id,ev.note,ev.commentaire,e.titre FROM evaluations ev INNER JOIN events e ON ev.event_id=e.id ";
            ps = (PreparedStatement) cx.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {

                Paragraph para = new Paragraph("Note" + rs.getInt("note") + " / Commentaire =" + rs.getString("commentaire") + " / Event=" + rs.getString("titre"));
                document.add(para);
                document.add(new Paragraph("  "));

            }

            document.close();
            System.out.println("finished");
        } catch (Exception e) {
            System.err.println(e);
        }

    }*/
    
    
    
    @FXML
    void stats (ActionEvent event) throws IOException{
        avr.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("/gui/StatsEvaluation.fxml"));
        avr.getChildren().add(parent);
        avr.toFront();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ObservableList<Evaluations> evaluation = FXCollections.observableArrayList();
        for (Evaluations ev : evs.afficher()) {
            evaluation.add(ev);
            C1.setCellValueFactory(new PropertyValueFactory<>("note"));
            C2.setCellValueFactory(new PropertyValueFactory<>("commentaire"));
            C3.setCellValueFactory(new PropertyValueFactory<>("titre"));

            table.setItems(evaluation);

        }

    }

}
