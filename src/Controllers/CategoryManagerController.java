
package Controllers;

import Entities.Category;
import Services.CategoryService;
import Utils.DataBase;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;


/**
 * FXML Controller class
 *
 * @author MONDHER
 */
public class CategoryManagerController implements Initializable {
    @FXML 
    private TableColumn<Category,Integer> id;
    @FXML 
    private TableColumn<Category,String> nomcat;
    @FXML 
    private TableColumn<Category,String> souscat;
    @FXML
    private TextField ForSearch;
    /*@FXML
    private TextField NameUndCat;
    @FXML
    private TextField NameCat;*/
    @FXML
    private AnchorPane root;
    @FXML
    private TableView<Category> resCateg;
    
    public ObservableList<Category> info = FXCollections.observableArrayList();
    private int ID;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {  
        SearchCategory(); 
    }  
    
    @FXML
    private void ShowCategory(ActionEvent event) { //Afficher les Categories **DONE**
        CategoryService cs = new CategoryService();
        ObservableList info = FXCollections.observableList(cs.displayAll());
        
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomcat.setCellValueFactory(new PropertyValueFactory<>("nomcat"));
        souscat.setCellValueFactory(new PropertyValueFactory<>("souscat"));
        resCateg.setItems(info);
    }
    
     private void SearchCategory() { 
       ForSearch.setOnKeyReleased(e->{
           if(ForSearch.getText().equals("")){
        Connection cnx = DataBase.getInstance().getCnx();
               PreparedStatement aux;
               try {
                   aux = cnx.prepareStatement("SELECT * FROM category");
                   ResultSet rs = aux.executeQuery();
            
            while(rs.next()){
                 info.add(new Category (rs.getInt(1),rs.getString(2),rs.getString(3)));
            }
               } catch (SQLException ex) {
                   Logger.getLogger(CategoryManagerController.class.getName()).log(Level.SEVERE, null, ex);
               }  
           }
           
           else{
               info.clear();
        Connection cnx = DataBase.getInstance().getCnx();
               PreparedStatement ps;
       
        try {
            ps = cnx.prepareStatement("SELECT * FROM category WHERE nomcat LIKE '%" + ForSearch.getText() + "%'");
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                 info.add(new Category (rs.getInt(1),rs.getString(2),rs.getString(3)));
            }
          
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomcat.setCellValueFactory(new PropertyValueFactory<>("nomcat"));
        souscat.setCellValueFactory(new PropertyValueFactory<>("souscat"));
        resCateg.setItems(info);
           }
       });
    }

    public void refreshTable(){
        info.clear();
        Connection cnx = DataBase.getInstance().getCnx();
        PreparedStatement ps;
        
        try{
            ps = cnx.prepareStatement("SELECT * FROM category"); 
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                 info.add(new Category(rs.getInt(1),rs.getString(2),rs.getString(3)));
            }
            resCateg.setItems(info);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
  
}
