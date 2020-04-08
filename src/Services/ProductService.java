/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;


import Entities.Product;
import Utils.MyDbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author cyrine
 */
public class ProductService {
    
    Connection connexion;
   

    public ProductService() {
       connexion=MyDbConnection.getInstance().getConnexion();
    }
    
    /*------------- CRUD---------------*/

    void ajouterProduit(Product p) throws SQLException {
        String req = "INSERT INTO `product` (`nompr`,`descrip`, `prix` ) VALUES ( '"+p.getNompr()+
               "','" + p.getDescrip()+ "', '" + p.getPrix()+"') ";
        Statement stm = connexion.createStatement();
        stm.executeUpdate(req);
    }

     public ObservableList<Product> indexActionP() 
     { 
        ObservableList<Product> prod=FXCollections.observableArrayList();
        String req= " select * from Product ";
        Statement st;
        try {
            st=connexion.createStatement();
            ResultSet result=st.executeQuery(req);
            while(result.next())
            {    Product p = new Product(result.getInt(1),result.getString("nompr"),result.getString("descrip"),result.getDouble("prix"));
            prod.add(p);
                    }
        } catch (SQLException ex) {
            Logger.getLogger(LignePanierService.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("raaaa");
        }
          return  prod;
     }
    
    
}
