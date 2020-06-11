
package Controllers;

import Entities.Product;
import Services.ProductService;
import Utils.DataBase;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;
import org.controlsfx.control.Notifications;

/**
 *
 * @author MONDHER
 */

public class ProductManagerBackController implements Initializable {
    @FXML
    private Button showProducts;
    @FXML
    private Button ModifyProduct;
    @FXML
    private Button DeleteProduct;
    @FXML
    private Button ajouterPhoto;
    @FXML
    private Button Vider;
    @FXML
    private Button Add;
    @FXML
    private Button Sort;
    @FXML
    private Button btn_QRcode;
    @FXML 
    private AnchorPane mainPane;
    @FXML
    private AnchorPane PaneTab;
    @FXML
    private Label label;
    @FXML
    private TableView<Product> table;
    @FXML 
    private TableColumn<Product,String> nompr;
    @FXML 
    private TableColumn<Product,String> quantity;
    @FXML 
    private TableColumn<Product,String> descrip;
    @FXML 
    private TableColumn<Product,String> prix;
    @FXML 
    private TableColumn<Product,String> image;
    @FXML
    private TableColumn<Product,String> nameCategory;
    @FXML
    private TableColumn<Product,String> idProd;
    @FXML
    private TableColumn<Product,String> QRcode;
    @FXML
    private TextField EntrerName;
    @FXML
    private TextField EntrerQuantite;
    @FXML
    private TextField EntrerDescrip;
    @FXML
    private TextField EntrerPrix;
    @FXML
    private TextField SearchProd;
    @FXML
    private TextField EntrerBarcode;
    @FXML
    private ComboBox<String> chooseCat;
    @FXML
    private ImageView ImageProd2;
    
    Connection cnx = DataBase.getInstance().getCnx();
    final ObservableList<String> choice = FXCollections.observableArrayList();
    public ObservableList<Product> data = FXCollections.observableArrayList();
    private Stage stage;
    Product produit = new Product();
    private int ID;
    String path = "";
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fillComboBox();
        chooseCat.setItems(choice); //Initialiser le ComboBox
        SearchProduct();
    }
   
    @FXML
    private void ShowProducts(ActionEvent event) throws IOException { //Afficher liste des produits **DONE**
        ProductService ps = new ProductService();
        ObservableList aux = FXCollections.observableList(ps.displayAll());
        idProd.setCellValueFactory(new PropertyValueFactory<>("id"));
        nompr.setCellValueFactory(new PropertyValueFactory<>("nompr"));
        quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        descrip.setCellValueFactory(new PropertyValueFactory<>("descrip"));
        prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        image.setCellValueFactory(new PropertyValueFactory<>("image"));
        nameCategory.setCellValueFactory(new PropertyValueFactory<>("namecat"));
        QRcode.setCellValueFactory(new PropertyValueFactory<>("barcode"));
        table.setItems(data);
        table.setItems(aux);
    }
   
    @FXML
    private void addProduct(ActionEvent event) { //Ajouter des produits **DONE**
       ProductService ps = new ProductService();
       String idCat = "";
        String nomProd = EntrerName.getText();
        String Qt = EntrerQuantite.getText();
        String Des = EntrerDescrip.getText();
        String price = EntrerPrix.getText();
         if(chooseCat.getValue().equals("Fishing")){
         idCat = "3";
        }
         if(chooseCat.getValue().equals("Hunting")){
         idCat = "4";
        }
          if(chooseCat.getValue().equals("Clothing")){
         idCat = "5";
        }
        String QRcode = EntrerBarcode.getText();
        
        Product Prod = new Product(nomProd,Qt,Des,price,path,idCat,QRcode);
          
        if(Valid() && ValidateFields()){
         int status = ps.saveProd(Prod);
         if(status == 1){ //AllertAction
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("ADD PRODUCT!!");
            alert.setHeaderText("Information");
            alert.setContentText("PRODUCT HAS BEEN ADDED");
            alert.showAndWait();
        }
        else{
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("ADD PRODUCT");
            alert.setHeaderText("Information");
            alert.setContentText("ERROR");
            alert.showAndWait();
        }
         refreshTable();
    }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Fields Validation");
            alert.setHeaderText("Information");
            alert.setContentText("Please check your Fields!!");
            alert.showAndWait();
        }
    }

   @FXML
    private void setValue(MouseEvent event) { // **DONE**
        Product selected = table.getSelectionModel().getSelectedItem();
        if(selected != null){
        EntrerName.setText(selected.getNompr());
        EntrerQuantite.setText(selected.getQuantity());
        EntrerDescrip.setText(selected.getDescrip());
        EntrerPrix.setText(selected.getPrix());
        Image image = new Image("http://localhost/huntkingdom/web/images/" + selected.getImage());
        ImageProd2.setImage(image);
        EntrerBarcode.setText(selected.getBarcode());
        ID = selected.getId();
        } 
    }
    
    @FXML
     private void UpDateProduct(ActionEvent event){ //Modifier produit **DONE**
        PreparedStatement ps;
        String idCat="";
        Connection cnx = DataBase.getInstance().getCnx();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("CONFIRMATION DIALOG");
            alert.setHeaderText("NOTICE");
            alert.setContentText("ARE YOU SURE TO UPDATE?");
            Optional <ButtonType> action = alert.showAndWait();
          if(Valid() && ValidateFields() && action.get() == ButtonType.OK){
        try {
            ps = cnx.prepareStatement("UPDATE product SET nompr=?, quantity=?, descrip=?, prix=?, image=?, idCategory=?, barcode=? WHERE id="+ID+"");
            ps.setString(1 , EntrerName.getText());
            ps.setString(2 , EntrerQuantite.getText());
            ps.setString(3 , EntrerDescrip.getText());
            ps.setString(4 , EntrerPrix.getText());
            ps.setString(5 , path);
         if(chooseCat.getValue().equals("Fishing")){
                 idCat = "3";
              }
         if(chooseCat.getValue().equals("Hunting")){
                 idCat = "4";
              }
         if(chooseCat.getValue().equals("Clothing")){
                 idCat = "5";
              }
            ps.setString(6 , idCat);
            ps.setString(7 , EntrerBarcode.getText());
            
         int status = ps.executeUpdate();
         if(status == 1){
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("UPDATE PRODUCT");
            alert.setHeaderText("Information");
            alert.setContentText("PRODUCT HAS BEEN UPDATED");
            alert.showAndWait();
            refreshTable();
        }
        else{
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("UPDATE PRODUCT!!");
            alert.setHeaderText("Information");
            alert.setContentText("ERROR!!");
            alert.showAndWait();
        }
        } catch (SQLException ex) {
            Logger.getLogger(ProductManagerBackController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }else{
            alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Fields Validation");
            alert.setHeaderText("Information");
            alert.setContentText("Please check your Fields!!");
            alert.showAndWait();
          }
     }
    
    @FXML
    private void DeleteProduct(ActionEvent event) { //Supprimer produit **DONE**
        PreparedStatement ps;
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("CONFIRMATION DIALOG");
            alert.setHeaderText("NOTICE");
            alert.setContentText("ARE YOU SURE TO DELETE?");
            Optional <ButtonType> action = alert.showAndWait();
            if(action.get() == ButtonType.OK){
        try {
            ps = cnx.prepareStatement("DELETE FROM product WHERE id="+ID+"");
            int status = ps.executeUpdate();
            
            if(status == 1){
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("DELETE PRODUCT");
            alert.setHeaderText("INFORMATION");
            alert.setContentText("PRODUCT HAS BEEN DELETED");
            alert.showAndWait();
        }
        else{
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("DELETE PRODUCT");
            alert.setHeaderText("INFORMATION");
            alert.setContentText("ERROR!!");
            alert.showAndWait();
        }
        } catch (SQLException ex) {
            Logger.getLogger(ProductManagerBackController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        refreshTable();
    }
    
    private void SearchProduct() { //Recherche avancée des produits selon nom du produit **DONE**
        SearchProd.setOnKeyReleased(e->{
            if(SearchProd.getText().equals("")){
                    table.setItems(data);
            }
                else{
            data.clear();
        Connection cnx = DataBase.getInstance().getCnx();
            PreparedStatement ps;
        
        try {
            ps = cnx.prepareStatement("SELECT p.id,p.nompr,p.quantity,p.descrip,p.prix,p.image,c.nomcat,p.barcode FROM product p INNER JOIN category c ON p.idCategory=c.id AND nompr LIKE '%" + SearchProd.getText() + "%'"); 
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                 data.add(new Product(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8)));
            }
          
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        nompr.setCellValueFactory(new PropertyValueFactory<>("nompr"));
        prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        descrip.setCellValueFactory(new PropertyValueFactory<>("descrip"));
        image.setCellValueFactory(new PropertyValueFactory<>("image"));
        nameCategory.setCellValueFactory(new PropertyValueFactory<>("namecat"));
        QRcode.setCellValueFactory(new PropertyValueFactory<>("barcode"));
        table.setItems(data);
            }
    });
                }
    
    @FXML
    private void SortProducts(ActionEvent event) { //Trier Liste produits selon le prix en ordre ASC**DONE**
        data.clear();
        Connection cnx = DataBase.getInstance().getCnx();
        PreparedStatement ps;
        
        try {
            ps = cnx.prepareStatement("SELECT p.id,p.nompr,p.quantity,p.descrip,p.prix,p.image,c.nomcat,p.barcode FROM product p INNER JOIN category c ON p.idCategory=c.id ORDER BY p.prix ASC");
            ResultSet rs = ps.executeQuery();
          
            while(rs.next()){
               data.add(new Product (rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8)));
            }
           
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        nompr.setCellValueFactory(new PropertyValueFactory<>("nompr"));
        quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        descrip.setCellValueFactory(new PropertyValueFactory<>("descrip"));
        prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        image.setCellValueFactory(new PropertyValueFactory<>("image"));
        nameCategory.setCellValueFactory(new PropertyValueFactory<>("namecat"));
        QRcode.setCellValueFactory(new PropertyValueFactory<>("barcode"));
        table.setItems(data);
    }
    
    @FXML
    private void addPhoto(ActionEvent event) { //Ajout image Produit **DONE** 
        BufferedOutputStream stream = null;
        String globalPath="C:\\wamp64\\www\\huntkingdom\\web\\images";


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
            ImageProd2.setImage(image);

        } else if (result == JFileChooser.CANCEL_OPTION) {
            System.out.println("NoData");
        }

                } catch (IOException ex) {
                
                }
           Image notif = new Image("/Images/Error.png");
           Notifications notificationBuilder = Notifications.create()
               .title("DOWNLOAD IMAGE")
               .text("NO IMAGE DOWNLOADED")
               .graphic(new ImageView(notif))
               .hideAfter(Duration.seconds(5))
               .position(Pos.TOP_RIGHT)
               .onAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
               System.out.println("Clicked on Notification");
            }
                   
               });
             notificationBuilder.darkStyle();
             notificationBuilder.show();
        }
  
    @FXML
    private void generateBarcode(ActionEvent event) throws FileNotFoundException, IOException {
         if(!EntrerBarcode.getText().isEmpty() && !EntrerName.getText().isEmpty()){
        String Ind = "Barcode Product :" + " " + EntrerBarcode.getText() + "\n" + "Name Product :" + " " +EntrerName.getText() + "\n" + "Website Link :" + " " +"http://localhost/huntkingdom/web/app_dev.php";
        ByteArrayOutputStream out = QRCode.from(Ind).to(ImageType.JPG).stream();
        File f = new File("C:\\Users\\MONDHER\\Desktop\\Esprit\\pidev-java\\Events\\src\\ProdQRcode\\QRprod.jpg");
        FileOutputStream fos = new FileOutputStream(f);
        fos.write(out.toByteArray());
        out.close();
        fos.close();
        fos.flush();
        Image img = new Image("/Images/Done.png");
        Notifications notificationBuilder = Notifications.create()
               .title("Generation QR code Completed")
               .text("Saved to Prod QRcode File")
               .graphic(new ImageView(img))
               .hideAfter(Duration.seconds(5))
               .position(Pos.TOP_RIGHT)
               .onAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
               System.out.println("Clicked on Notification");
            }
                   
               });
             notificationBuilder.darkStyle();
             notificationBuilder.show();
        }
    } 
    
    public boolean Valid(){ //Controle de saisie du Quantite et le prix du produit **DONE**
         try{
            String val1 = EntrerPrix.getText();
            String val2 = EntrerQuantite.getText();
            Float value1 = Float.parseFloat(val1);
            Integer value2 = Integer.parseInt(val2);
            String val3 = EntrerBarcode.getText();
            Integer value3 = Integer.parseInt(val3);
            if(value1 > 0 && value2 >0 && value3 >0)
                return true;
         }
            catch(NumberFormatException e){
                 System.out.println(e);
            }
         return false;
  }
    
    private boolean ValidateFields(){ //Controle des champs du formulaire **DONE**
        if(EntrerName.getText().isEmpty() | EntrerQuantite.getText().isEmpty() | EntrerDescrip.getText().isEmpty() | EntrerPrix.getText().isEmpty() | chooseCat.getValue().isEmpty() | EntrerBarcode.getText().isEmpty() ){
            return false;
        }
        return true;
    }
    
    public ObservableList fillComboBox(){ //Remplir le comboBox par idCategory **DONE**
        Connection cnx = DataBase.getInstance().getCnx();
        PreparedStatement ps;
        try {
            ps = cnx.prepareStatement("SELECT nomcat FROM category");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                 choice.add((rs.getString(1)));
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProductManagerBackController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return choice;
    }
    
    public void refreshTable(){ //Refresh TableView **DONE** 
        data.clear();
        Connection cnx = DataBase.getInstance().getCnx();
        PreparedStatement ps;
        
        try{
            ps = cnx.prepareStatement("SELECT p.id,p.nompr,p.quantity,p.descrip,p.prix,p.image,c.nomcat,p.barcode FROM product p INNER JOIN category c ON p.idCategory=c.id AND nompr LIKE '%" + SearchProd.getText() + "%'"); 
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                 data.add(new Product(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8)));
            }
            table.setItems(data);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void ClearFields(ActionEvent event) {  //Vider les champs **DONE**
         EntrerName.clear();
         EntrerQuantite.clear();
         EntrerDescrip.clear();
         EntrerPrix.clear();
         chooseCat.setValue(null);
         EntrerBarcode.clear();
    }
   
}
