/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Seller;
import Services.ServiceSeller;
import Utils.DataBase;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author VENOM
 */
public class FXMLShowSellerController implements Initializable {

    @FXML
    private Button Delete;
    @FXML
    private TableView<Seller> table;
    @FXML
    private TableColumn<Seller, String> C1;
    @FXML
    private TableColumn<Seller, String> C2;
    @FXML
    private TableColumn<Seller, String> C3;
    @FXML
    private TableColumn<Seller, String> C4;
    @FXML
    private TableColumn<Seller, String> C5;
    @FXML
    private TableColumn<Seller, String> C6;
    @FXML
    private TableColumn<Seller, String> C7;
    @FXML
    private TableColumn<Seller, String> C8;
    Connection con=DataBase.getInstance().getCnx();
    ServiceSeller ss = new ServiceSeller();
    @FXML
    private AnchorPane avr;
    private  Seller selectedid; 
    @FXML
    private ImageView imageSel;
    @FXML
    private Button Update;
    

    /* @FXML
    private void showData(ActionEvent event) throws SQLException, ClassNotFoundException {

        Connection con = MyDbConnection.getInstance().getConnexion();

        ObservableList<Seller> data = FXCollections.observableArrayList();
        ServiceSeller ss = new ServiceSeller();
        table.setItems(ss.getAllSeller());
        System.out.println(ss.getAllSeller());
    }
     */
    @FXML
    private void Delete(ActionEvent event) throws SQLException {

        if (!table.getSelectionModel().getSelectedItems().isEmpty()) {
            ServiceSeller ss = new ServiceSeller();
             Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Do you confirm deleting this candidate?");
                alert.showAndWait();
            ss.deleteSeller(table.getSelectionModel().getSelectedItems().get(0).getId());
        } else {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("aucun élément 'a ètè séléctionné");
            alert.showAndWait();

        }
    }
    
    @FXML
    public void select(){
        selectedid = (Seller) table.getSelectionModel().getSelectedItem();
        Image image = new Image("http://localhost//pidev-java/Events/src/Images/Licence/" + selectedid.getImage());
        imageSel.setImage(image);
    }

   /* @FXML
    private void update(ActionEvent event) throws SQLException {
        if (!table.getSelectionModel().getSelectedItems().isEmpty()) {
            Stage primaryStage = new Stage();
            Parent root;

            try {
                root = FXMLLoader.load(getClass().getResource("/gui/FXMLUpdateSeller.fxml"));

                Scene scene = new Scene(root);

                primaryStage.setTitle("Update informations.");
                primaryStage.setScene(scene);
                primaryStage.show();
            } catch (IOException ex) {
                Logger.getLogger(FXMLsellerController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("aucun élément 'a ètè séléctionné");
            alert.showAndWait();

        }
    }*/
    
    
    @FXML
    void Update (ActionEvent event) throws  IOException{
         avr.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("/gui/FXMLUpdateSeller.fxml"));
        avr.getChildren().add(parent);
        avr.toFront();
    }
    
    /*   
    @FXML
    private void modifT(ActionEvent event) {
            String input5 = idT.getText();
            int iid5 = Integer.parseInt(input5);            //iid = integer id
            String inputphone5 = phoneT.getText();        
            int iphone5 = Integer.parseInt(inputphone5);      //iphone = integer phone

            
            String FirstNT = FnameT.getText();
            String LastNT = LnameT.getText();
            String emailTr = emailT.getText();
            String pwdT = passwordT.getText();
            String picT = pictureT.getText();
            String Diplome = diploma.getText();
            crudform spf = new crudform();
            gform pef = new gform(iid5, FirstNT, LastNT, emailTr, pwdT, picT, iphone5, Diplome);
            spf.modifierPersonne(iid5,pef);
            
    }*/
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<Seller> Seller = FXCollections.observableArrayList();
        try {
            for (Seller s : ss.getAllSeller()) {
                Seller.add(s);

                C1.setCellValueFactory(data -> {
                    return new ReadOnlyStringWrapper(data.getValue().getSellername());
                });
                C2.setCellValueFactory(data -> {
                    return new ReadOnlyStringWrapper(data.getValue().getRcs());
                });
                C3.setCellValueFactory(data -> {
                    return new ReadOnlyStringWrapper(data.getValue().getTaxnumber());
                });
                C4.setCellValueFactory(new PropertyValueFactory<Seller, String>("tva"));
                C5.setCellValueFactory(new PropertyValueFactory<Seller, String>("siren"));
                C6.setCellValueFactory(new PropertyValueFactory<Seller, String>("fax"));
                C7.setCellValueFactory(new PropertyValueFactory<Seller, String>("phonenumber"));
                C8.setCellValueFactory(data -> {
                    return new ReadOnlyStringWrapper(data.getValue().getEmail());
                });
                table.setItems(Seller);

            }
        } catch (SQLException ex) {
            Logger.getLogger(FXMLShowSellerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void accept(ActionEvent event) throws IOException {
         avr.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("/gui/mailseller.fxml"));
        avr.getChildren().add(parent);
        avr.toFront();
    }

 
}
