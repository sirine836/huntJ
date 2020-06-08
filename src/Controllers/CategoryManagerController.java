
package Controllers;

import Entities.Category;
import Services.CategoryService;
import Utils.DataBase;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
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
    @FXML
    private TextField NameUndCat;
    @FXML
    private TextField NameCat;
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
        SearchCategory(); //Appeler Fonction Search **n'est pas liÃ©e Ã  un button_Action**
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
    
    private void AddCategory(ActionEvent event) { //Ajout Categorie **DONE**
        String nameCat = NameCat.getText();
        String nomScat = NameUndCat.getText();
        
        Category Cat = new Category();
        Cat.setNomcat(nameCat);
        Cat.setSouscat(nomScat);
        if(ValidateFields()){
         CategoryService cs = new CategoryService();
         int status = CategoryService.saveCat(Cat);
         if(status == 1){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ADD CATEGORY");
            alert.setHeaderText("INFORMATION");
            alert.setContentText("CATEGORY HAS BEEN ADDED");
            alert.showAndWait();
            refreshTable();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ADD CATEGORY");
            alert.setHeaderText("INFORMATION");
            alert.setContentText("ERROR!!");
            alert.showAndWait();
        }
         
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Fields Validation");
            alert.setHeaderText("INFORMATION");
            alert.setContentText("Please check your Fields!!");
            alert.showAndWait();
        }
    }

     private void SearchCategory() { //Recherche avancee des Categories **DONE**
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

   private Category categ;
   @FXML
    private void setCategory(MouseEvent event) { // **DONE**
        Category selected = resCateg.getSelectionModel().getSelectedItem();
        if(selected != null){
        NameCat.setText(selected.getNomcat());
        NameUndCat.setText(selected.getSouscat());
        ID = selected.getId();
        } 
        } 
    
    private void UpdateCategory(ActionEvent event) { //Modifier les categories **DONE**
        PreparedStatement ps;
        Connection cnx = DataBase.getInstance().getCnx();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("CONFIRMATION DIALOG");
            alert.setHeaderText("NOTICE");
            alert.setContentText("ARE YOU SURE TO UPDATE?");
            Optional <ButtonType> action = alert.showAndWait();
        if(ValidateFields() && action.get() == ButtonType.OK){
        try {
            ps = cnx.prepareStatement("UPDATE category SET nomcat=?, souscat=? WHERE id="+ID+"");
            ps.setString(1 , NameCat.getText());
            ps.setString(2 , NameUndCat.getText());
            int status = ps.executeUpdate();
            
         if(status == 1){
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("UPDATE CATEGORY");
            alert.setHeaderText("INFORMATION");
            alert.setContentText("CATEGORY HAS BEEN UPDATED");
            alert.showAndWait();
        }
        else{
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("UPDATE CATEGORY");
            alert.setHeaderText("INFORMATION");
            alert.setContentText("ERROR!!");
            alert.showAndWait();
        }
        } catch (SQLException ex) {
            Logger.getLogger(ProductManagerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        refreshTable();
        }else{
            alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Fields Validation");
            alert.setHeaderText("INFORMATION");
            alert.setContentText("Please Check Your Fields!!");
            alert.showAndWait();
        }
     }

    private void DeleteCategory(ActionEvent event) { //Supprimer les categories **DONE**
        Connection cnx = DataBase.getInstance().getCnx();
        PreparedStatement ps;
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("CONFIRMATION DIALOG");
            alert.setHeaderText("NOTICE");
            alert.setContentText("ARE YOU SURE TO DELETE?");
            Optional <ButtonType> action = alert.showAndWait();
          if(action.get() == ButtonType.OK){
        try {
            ps = cnx.prepareStatement("DELETE FROM category WHERE id="+ID+"");
            int status = ps.executeUpdate();
            
            if(status == 1){
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("DELETE CATEGORY");
            alert.setHeaderText("INFORMATION");
            alert.setContentText("CATEGORY HAS BEEN DELETED");
            alert.showAndWait();
        }
        else{
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("DELETE CATEGORY");
            alert.setHeaderText("INFORMATION");
            alert.setContentText("ERROR!!");
            alert.showAndWait();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductManagerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        refreshTable();
    }
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
    
    private boolean ValidateFields(){ //Controlee des champs du formulaire **DONE**
        if(NameCat.getText().isEmpty() | NameUndCat.getText().isEmpty()){
            return false;
        }
        return true;
    }
               
     @FXML
    private void ClearFields(ActionEvent event) { //Vider les champs **DONE**
        NameCat.clear();
        NameUndCat.clear();
    }
}
