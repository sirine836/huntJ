
package Services;

import Entities.Product;
import Utils.DataBase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author cyrine
 */
public class ProductService {
    
    Connection connexion = DataBase.getInstance().getCnx();
    final ObservableList<String> choice = FXCollections.observableArrayList();
  
    public ProductService() {
       
    }
    
    /*------------- CRUD---------------*/

     public ObservableList<Product> indexActionP() 
     { 
        ObservableList<Product> prod=FXCollections.observableArrayList();
        String req= " select p.nompr,P.descrip,P.prix from Product ";
        Statement st;
        try {
            st=connexion.createStatement();
            ResultSet result=st.executeQuery(req);
            while(result.next())
            {    Product p = new Product(result.getInt(1),result.getString("nompr"),result.getString("descrip"),result.getString("prix"));
            prod.add(p);
                    }
        } catch (SQLException ex) {
            Logger.getLogger(LignePanierService.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("raaaa");
        }
          return  prod;
     }
    
     public List<Product> displayAll() { //**DONE**
        ArrayList <Product> tab = new ArrayList ();
        Connection cnx = DataBase.getInstance().getCnx();
        PreparedStatement ps;
        
        try {
            ps = cnx.prepareStatement("SELECT p.id,p.nompr,p.quantity,p.descrip,p.prix,p.image,c.nomcat,p.barcode FROM product p INNER JOIN category c ON p.idCategory=c.id");
            ResultSet rs = ps.executeQuery();
          
            while(rs.next()){
               Product prods = new Product(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8));
               tab.add(prods);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return tab;
    }
     
      public static int saveProd(Product p){ //**DONE**
        int pr = 0 ;
        Connection cnx = DataBase.getInstance().getCnx();
        String req = "INSERT INTO product (nompr,quantity,descrip,prix,image,idCategory,barcode) VALUES ( '"
                + p.getNompr() + "', '" + p.getQuantity() + "','" + p.getDescrip() + "','" + p.getPrix() + "','" + p.getImage() + "','" + p.getIdCategory() +  "','" + p.getBarcode() + "')";
        Statement st;
        try {
            st = cnx.createStatement();
            pr = st.executeUpdate(req);
        } catch (SQLException ex) {
           System.out.println(ex);
        }
     
        return pr;
      }
         
       /*-----------------------------------------------*/
      
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
            System.out.println(ex);
        }
        return choice;
    }
    
}
