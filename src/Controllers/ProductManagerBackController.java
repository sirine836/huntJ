
package Controllers;

import Entities.Lignepanier;
import Entities.Panier;
import Entities.Product;
import Services.LignePanierService;
import Services.PanierService;
import Services.ProductService;
import Utils.DataBase;
import config.Config;
//import static com.sun.jmx.mbeanserver.Util.cast;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;

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
    private Label label;
    @FXML
    private TableView<Product> table;
    @FXML
    private Button Add;
    @FXML
    private Button Sort;
    @FXML 
    private AnchorPane mainPane;
    @FXML
    private AnchorPane PaneTab;
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
    private TextField EntrerName;
    @FXML
    private TextField EntrerQuantite;
    @FXML
    private TextField EntrerDescrip;
    @FXML
    private TextField EntrerPrix;
    @FXML
    private TextField EntrerImage;
    @FXML
    private Button Vider;
    @FXML
    private ComboBox<String> chooseId;
    @FXML
    private Button ajouterPhoto;
    @FXML
    private TextField SearchProd;
    @FXML
    private TextField EntrerBarcode;
    @FXML
    private TableColumn<Product,String> QRcode;
    @FXML
    private Button btn_QRcode;
    
    Connection cnx = DataBase.getInstance().getCnx();
    final ObservableList<String> choice = FXCollections.observableArrayList();
    public ObservableList<Product> data = FXCollections.observableArrayList();
    private FileChooser fileChooser;
    private File dos;
    private Stage stage;
    Product produit = new Product();
    private int ID;
    @FXML
    private Button addToCart;
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fillComboBox();
        chooseId.setItems(choice); //Initialiser le ComboBox
        //data = FXCollections.observableArrayList();
        SearchProduct();
        fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                   new FileChooser.ExtensionFilter("All files","*."),
                   new FileChooser.ExtensionFilter("images","*.png","*.jpg","*.gif"),
                   new FileChooser.ExtensionFilter("Text File","*.txt"));
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
   
    /*public static int saveProd(Product p){ //**DONE**
        int pr = 0 ;
        Connection cnx = DataBase.getInstance().getCnx();
        PreparedStatement ps;
        try {
            ps = cnx.prepareStatement("INSERT INTO product (nompr,quantity,descrip,prix,image,idCategory,barcode) VALUES(?,?,?,?,?,?,?)");
            //InsÃ©rer les nouvelles informations dans la base de donnÃ©es selon l'ordre
            ps.setString(1 , p.getNompr());
            ps.setString(2 , p.getQuantity());
            ps.setString(3 , p.getDescrip());
            ps.setString(4 , p.getPrix());
            ps.setString(5 , p.getImage());
            ps.setString(6 , p.getIdCategory());
            ps.setString(7 , p.getBarcode());
            pr = ps.executeUpdate();
        } 
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        return pr;
    }*/
    
    @FXML
    private void addProduct(ActionEvent event) { //Ajouter des produits **DONE**
        ProductService ps = new ProductService();
        String nomProd = EntrerName.getText();
        String Qt = EntrerQuantite.getText();
        String Des = EntrerDescrip.getText();
        String price = EntrerPrix.getText();
        String img = EntrerImage.getText();
        String QRcode = EntrerBarcode.getText();
        Product Prod = new Product();
        Prod.setNompr(nomProd);
        Prod.setQuantity(Qt);
        Prod.setDescrip(Des);
        Prod.setPrix(price);
        Prod.setImage(img);
        Prod.setBarcode(QRcode);
        Prod.setIdCategory(chooseId.getValue());
                
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
        EntrerImage.setText(selected.getImage());
        EntrerBarcode.setText(selected.getBarcode());
        ID = selected.getId();
        } 
    }
    
    @FXML
     private void UpDateProduct(ActionEvent event){ //Modifier produit **DONE**
        PreparedStatement ps;
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
            ps.setString(5 , EntrerImage.getText());
            ps.setString(6 , chooseId.getValue());
            ps.setString(7 , EntrerBarcode.getText());
            
         int status = ps.executeUpdate();
         if(status == 1){
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("UPDATE PRODUCT");
            alert.setHeaderText("Information");
            alert.setContentText("PRODUCT HAS BEEN UPDATED");
            alert.showAndWait();
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
        refreshTable();
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
         refreshTable();
    }
    }
    
    private void SearchProduct() { //Recherche avancÃ©e des produits selon nom du produit **DONE**
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
           stage = (Stage) mainPane.getScene().getWindow();
           dos = fileChooser.showOpenDialog(stage);
           String path = dos.getAbsolutePath();
           EntrerImage.setText(path);  
        }
  
    @FXML
    private void generateBarcode(ActionEvent event) throws FileNotFoundException, IOException {
        if(!EntrerBarcode.getText().isEmpty()){
        String Ind = EntrerBarcode.getText();
        ByteArrayOutputStream out = QRCode.from(Ind).to(ImageType.JPG).stream();
        File f = new File("C:\\wamp64\\www\\pidev-java\\Events\\src\\ProdQRcode\\QRprod.jpg");
        FileOutputStream fos = new FileOutputStream(f);
        fos.write(out.toByteArray());
        out.close();
        fos.close();
        fos.flush();
        Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("GENERATE BARCODE");
            alert.setHeaderText("Information");
            alert.setContentText("BARCODE HAS BEEN GENERATED");
            alert.showAndWait();
        }
        else{
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("GENERATE BARCODE");
            alert.setHeaderText("Information");
            alert.setContentText("ERROR");
            alert.showAndWait(); 
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
        if(EntrerName.getText().isEmpty() | EntrerQuantite.getText().isEmpty() | EntrerDescrip.getText().isEmpty() | EntrerPrix.getText().isEmpty() | EntrerImage.getText().isEmpty() | chooseId.getValue().isEmpty() | EntrerBarcode.getText().isEmpty() ){
            return false;
        }
        return true;
    }
    
    public ObservableList fillComboBox(){ //Remplir le comboBox par idCategory **DONE**
        Connection cnx = DataBase.getInstance().getCnx();
        PreparedStatement ps;
        try {
            ps = cnx.prepareStatement("SELECT id FROM category");
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
         EntrerImage.clear();
         chooseId.setValue(null);
         EntrerBarcode.clear();
    }

   @FXML
    public void addToCart(ActionEvent event) throws IOException,SQLException{ 
         Product tableIndex = table.getSelectionModel().getSelectedItem();
              if(table.getSelectionModel().getSelectedItems().size()!=0){
      LocalDate d = java.time.LocalDate.now();
         DateTimeFormatter DateTimeFormatter = null;
         
      LignePanierService lpService = new LignePanierService();
      PanierService panierService = new PanierService();
      Panier pa = new Panier(Main.user_id,d.toString(),0,0,0);
      int id_produit = table.getSelectionModel().getSelectedItems().get(0).getId();
      Panier panier = new Panier();
      Panier panier1 = new Panier();
             panier = panierService.getCurrentPanierByUserID(Main.user_id);
             panier1 = panierService.getCurPanierByUserIDetat(Main.user_id);
             System.out.println(panier.getIdpan());
             System.out.println(panier1.getIdpan());
             Lignepanier lp = new Lignepanier(id_produit,panier.getIdpan(),1);
            
                if(panier.getIdpan()>panier1.getIdpan())
                {
                    System.out.println(lpService.trouve(id_produit,Main.user_id,panier.getIdpan()));
                    System.out.println(panier.getIdpan());
                  if(lpService.trouve(id_produit,Main.user_id,panier.getIdpan())==true )
                    {
                        
                      lpService.increment_qnt(id_produit,panier.getIdpan());
                      new Alert(Alert.AlertType.INFORMATION, "reajout").show();
                    }
                    else
                    { 
                      lp.setPanier_id(panier.getIdpan());
                      lp.setProduct_id(id_produit);
                      lp.setQuantite(1);
                      lpService.ajouterLigne2(lp);
                      new Alert(Alert.AlertType.INFORMATION, "sucess").show();
                    }
                }
                else
                {
                  panierService.ajouterPanier2(pa);
                // lpService.ajouterLigne2(lp);
                
                }
              }
              else{
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("aucun élément 'a ètè séléctionné");
        alert.showAndWait();
       }  
         table.refresh();
                    table.getSelectionModel().clearSelection();
  }
    
}
