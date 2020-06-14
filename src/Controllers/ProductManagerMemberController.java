
package Controllers;

import Entities.Lignepanier;
import Entities.Panier;
import Entities.Product;
import Services.LignePanierService;
import Services.PanierService;
import Services.ProductService;
import Utils.DataBase;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
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
import javafx.util.Duration;
import static org.bouncycastle.crypto.tls.ContentType.alert;
import org.controlsfx.control.Notifications;

/**
 *
 * @author MONDHER
 */

public class ProductManagerMemberController implements Initializable {

    @FXML
    private AnchorPane mainPane;
    @FXML
    private AnchorPane PaneTab;
    @FXML
    private Label label;
    @FXML
    private Button showProducts;
    @FXML
    private Button panier;
    @FXML
    private Button Sort;
    @FXML
    private Button FilterProds;
    @FXML
    private TableView<Product> table;
    @FXML
    private TableColumn<Product , String> nompr;
    @FXML
    private TableColumn<Product , String> quantity;
    @FXML
    private TableColumn<Product , String> descrip;
    @FXML
    private TableColumn<Product , String> prix;
    @FXML
    private TableColumn<Product , String> image;
    @FXML
    private TableColumn<Product , String> nameCategory;
    @FXML
    private TableColumn<Product , String> QRcode;
    @FXML
    private TableColumn<Product , String> idProd;
    @FXML
    private TextField SearchProd;
    @FXML
    private TextField MAX_PRICE;
    @FXML
    private TextField MIN_PRICE;
    @FXML
    private ImageView ImageProd;
    @FXML
    private ComboBox<String> filterProds;

    ProductService ps = new ProductService();
    ObservableList listCat = FXCollections.observableList(ps.fillComboBox());
    public ObservableList<Product> data = FXCollections.observableArrayList();
    final ObservableList<String> choice = FXCollections.observableArrayList();
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        filterProds.setItems(listCat); //Initialiser le ComboBox
        SearchProduct(); 
    }
    
    @FXML
    private void ShowProducts(ActionEvent event) {
        ObservableList aux = FXCollections.observableList(ps.displayAll());
        idProd.setCellValueFactory(new PropertyValueFactory<>("id"));
        nompr.setCellValueFactory(new PropertyValueFactory<>("nompr"));
        quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        descrip.setCellValueFactory(new PropertyValueFactory<>("descrip"));
        prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        image.setCellValueFactory(new PropertyValueFactory<>("image"));
        nameCategory.setCellValueFactory(new PropertyValueFactory<>("namecat"));
        QRcode.setCellValueFactory(new PropertyValueFactory<>("barcode"));
        table.setItems(aux);
    }

      @FXML
    private void SetImageProd(MouseEvent event) {
        Product selected = table.getSelectionModel().getSelectedItem();
        Image image = new Image("http://localhost/huntkingdom/web/images/" + selected.getImage());
        ImageProd.setImage(image);
    }
    
    private void SearchProduct() { 
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
    private void SortProducts(ActionEvent event) {
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
    private void filterByCat(ActionEvent event) { //Filtrer Les Produits Selon Category **DONE**
        data.clear();
        Connection cnx = DataBase.getInstance().getCnx();
        PreparedStatement ps;
        
        try {
            ps = cnx.prepareStatement("SELECT p.id,p.nompr,p.quantity,p.descrip,p.prix,p.image,c.nomcat FROM product p JOIN category c ON p.idCategory=c.id AND c.nomcat LIKE '%" + filterProds.getValue() + "%'");
            ResultSet rs = ps.executeQuery();
          
            while(rs.next()){
               data.add(new Product (rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7)));
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
        table.setItems(data);
        
        String url = "";
        if(filterProds.getValue().equals("Hunting")){
            url = "/Images/hunt.png";
        }
        if(filterProds.getValue().equals("Fishing")){
            url = "/Images/fish.png";
        }
        if(filterProds.getValue().equals("Clothing")){
            url = "/Images/clothes.png";
        }
        
         Image notif = new Image(url);
           Notifications notificationBuilder = Notifications.create()
               .title("FILTER PRODUCTS BY CATEGORY NAME")
               .text("List Product Has Been Filtred By :" + " " + filterProds.getValue())
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
    private void FilterByPrice(ActionEvent event) {
        data.clear();
        Connection cnx = DataBase.getInstance().getCnx();
        PreparedStatement ps;
        if(ValidFields()){
        try {
            ps = cnx.prepareStatement("SELECT p.id,p.nompr,p.quantity,p.descrip,p.prix,p.image,c.nomcat FROM product p JOIN category c ON p.idCategory=c.id AND p.prix BETWEEN '" + MIN_PRICE.getText() + "' AND '" + MAX_PRICE.getText() + "' ");
            ResultSet rs = ps.executeQuery();
          
            while(rs.next()){
               data.add(new Product(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7)));
            }
           
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        nompr.setCellValueFactory(new PropertyValueFactory<>("nompr"));
        quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        descrip.setCellValueFactory(new PropertyValueFactory<>("descrip"));
        prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        image.setCellValueFactory(new PropertyValueFactory<>("image"));
        nameCategory.setCellValueFactory(new PropertyValueFactory<>("namecat"));
        table.setItems(data);
        
         Image notif = new Image("/Images/dollar.png");
           Notifications notificationBuilder = Notifications.create()
               .title("FILTER PRODUCTS BY PRICE")
               .text("List Products With Price Between " + " " + MIN_PRICE.getText() + " " + "And" + " " + MAX_PRICE.getText())
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
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Fields Validation");
            alert.setHeaderText("Information");
            alert.setContentText("Please check your Fields!!");
            alert.showAndWait();
        }
    }
   
     public boolean ValidFields(){
        String val1 = MIN_PRICE.getText();
        String val2 = MAX_PRICE.getText();
        Float value1 = Float.parseFloat(val1);
        Float value2 = Float.parseFloat(val2);
        if(value1 < value2 && !(MIN_PRICE.getText().isEmpty()) && !(MAX_PRICE.getText().isEmpty())){
            return true;
        } 
        return false;
     }
    
    @FXML
    private void addToCart(ActionEvent event) throws SQLException {
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
    

