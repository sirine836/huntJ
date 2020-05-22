/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Utils.DataBase;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author h^
 */
public class StatsEvaluationController {

    @FXML
    private AnchorPane avr;
    @FXML
    private BarChart<String, Double> BarChart;
    @FXML
    private JFXButton LOAD;

    @FXML
    private void LOAD(ActionEvent event) throws SQLException {
        String query = "SELECT ev.note,e.titre FROM evaluations ev INNER JOIN events e ON ev.event_id=e.id ";
        XYChart.Series<String,Double> series = new XYChart.Series<>();
        try {
         Connection cx = DataBase.getInstance().getCnx();
            ResultSet rs=cx.createStatement().executeQuery(query);
            while (rs.next()) {
             series.getData().add(new XYChart.Data<>(rs.getString(2), rs.getDouble(1)));
            }
            BarChart.getData().add(series);

        } catch (Exception e){
            
        }
    }
    
   /**
     * Initializes the controller class.
     */
    public void initialize(URL url, ResourceBundle rb){
        //TODO
    }
    
}
