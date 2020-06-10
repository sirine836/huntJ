
package Controllers;

import Entities.Lignepanier;
import Entities.Panier;
import Entities.Product;
import Services.LignePanierService;
import Services.PanierService;
import Services.ProductService;
import Utils.DataBase;
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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author MONDHER
 */

public class ProductManagerMemberController implements Initializable {

    @FXML
    private AnchorPane mainPane;
    @FXML
    private Label label;
    @FXML
    private AnchorPane PaneTab;
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
    private Button showProducts;
    @FXML
    private Button panier;
    @FXML
    private Button Sort;
    @FXML
    private ImageView ImageProd;

   public ObservableList<Product> data = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        SearchProduct();
    }
    
    @FXML
    private void ShowProducts(ActionEvent event) {
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
    

