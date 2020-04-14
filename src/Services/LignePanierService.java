/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Lignepanier;
import Entities.Product;
import Utils.MyDbConnection;
import java.sql.Connection;
import java.sql.DriverManager;
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
import javafx.scene.control.TableColumn;

/**
 *
 * @author gogo-
 */
public class LignePanierService {

   Connection connexion;
   

    public LignePanierService() {
       connexion=MyDbConnection.getInstance().getConnexion();
    }

    public void ajouterLigne(Lignepanier lp) throws SQLException {
        String req = "INSERT INTO `ligne_panier` (`product_id`,`panier_id`,`quantite`) VALUES ( '"+ lp.getProduct_id()
                                             + "','" + lp.getPanier_id()+ "','" + lp.getQuantite() + "') ";
        Statement stm = connexion.createStatement();
        stm.executeUpdate(req);
    }

    public void ajouterLigne2(Lignepanier lp) throws SQLException {
        String req = "INSERT INTO ligne_panier ( product_id, panier_id , quantite ) VALUES (?,?,?) ";
      try {  PreparedStatement pstm = connexion.prepareStatement(req);
        pstm.setInt(1, lp.getProduct_id());
        pstm.setInt(2, lp.getPanier_id());
        pstm.setInt(3, lp.getQuantite());
        pstm.executeUpdate();
      } catch (SQLException ex) {
            Logger.getLogger(LignePanierService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
 
    public void deleteL(int idlp) throws SQLException {
        String req = "DELETE FROM `ligne_panier` WHERE id=? ";
        PreparedStatement pstm = connexion.prepareStatement(req);
        pstm.setInt(1, idlp);
        pstm.executeUpdate();
    }
    
    
    public void updateL(int idlp,int quantite) throws SQLException {
         Statement st;
        try {
             st = connexion.createStatement();
             st.executeUpdate( "UPDATE `ligne_panier` SET  quantite='"+quantite
                               + "' WHERE id="+idlp);
        }   
        catch (SQLException ex) {
            Logger.getLogger(LignePanierService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  

    public List<Lignepanier> getAllLignes() throws SQLException {
       List<Lignepanier> ligne = new ArrayList<>();
        
        String req = "select * from ligne_panier";
        Statement stm = connexion.createStatement();
        ResultSet result =  stm.executeQuery(req);
        
        while(result.next()){
            Lignepanier p = new Lignepanier(result.getInt(1),result.getInt("product_id"),result.getInt("panier_id"),result.getInt("quantite"));
            ligne.add(p);
        }
        
        return ligne;
    }
    
    
    
    public ObservableList<Lignepanier> getAllnomLignes(int panierID) 
     { 
        ObservableList<Lignepanier> ligne=FXCollections.observableArrayList();
        String req= " select l.nompr,l.prix,l.quantite from product p JOIN ligne_panier l ON p.id = l.product_id and panier_id='"+panierID+"'";
        Statement st;
        try {
            st=connexion.createStatement();
            ResultSet result=st.executeQuery(req);
            while(result.next())
            {    Lignepanier p = new Lignepanier(result.getString("nompr"),result.getDouble("prix"),result.getInt("quantite"));
            ligne.add(p);
                    }
        } catch (SQLException ex) {
            Logger.getLogger(LignePanierService.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("rrrr");
        }
          return  ligne;
     }
    
  /*---------------autre methode------------------*/  
    
      
    
   public  void rechercheLigne (int id) throws SQLException {
        String req = "select * FROM `ligne_panier` where id= ?'";
        Statement pstm = connexion.createStatement();
       ResultSet rst = pstm.executeQuery(req);
       rst.last();
       int nbrRow=rst.getRow();
       if (nbrRow != 0 )
       {System.out.println("LignePanier trouver");}
       else{ System.out.println("LignePanier non trouver");
    }
    }
    
    public void increment_qnt(int prodId,int panier) throws SQLException{
        PreparedStatement preparedStatement = null ;
        String query="update ligne_panier set quantite=quantite+1 WHERE product_id='"+prodId+"' and panier_id='"+panier+"'";
        Statement pstm = connexion.createStatement();
       pstm.executeUpdate(query);
    }
    
    public void decrement_qnt(int prodId) throws SQLException{
        PreparedStatement preparedStatement = null ;
        String query="update ligne_panier set quantite=quantite-1 WHERE product_id=?";
        Statement pstm = connexion.createStatement();
       pstm.executeUpdate(query);
    }
    
    

    
    
    public double calcul_total(int panierID) throws SQLException{
            String query="SELECT sum(p.prix*quantite) as total FROM ligne_panier l JOIN product p ON p.id = l.product_id and panier_id='"+panierID+"'" ;
            ResultSet rs =connexion.createStatement().executeQuery(query);
            while(rs.next()){
            int totalamount=rs.getInt("total");
                return totalamount;
                
            }
       return 0;
    }

    
    
   public boolean trouve(int id) {    
        String req= " select l.id,p.nompr,P.descrip,P.prix from ligne_panier l JOIN product p ON "+id+"=  p.id and p.id=l.product_id";
        try {
            Statement pstm = connexion.createStatement();
       ResultSet rst = pstm.executeQuery(req);
       rst.last();
       int nbrRow=rst.getRow();
       if (nbrRow != 0 )
       {return true;}
       
        } catch (SQLException ex) {
            Logger.getLogger(LignePanierService.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("rrrr");
        }
          return  false;
     }
   

    
    
    public ObservableList<Lignepanier> indexAction(int panierID) 
     { 
        ObservableList<Lignepanier> ligne=FXCollections.observableArrayList();
        String req= " select l.id,p.nompr,P.descrip,P.prix,l.quantite from product p JOIN ligne_panier l ON p.id = l.product_id and panier_id='"+panierID+"'";
        Statement st;
        try {
            st=connexion.createStatement();
            ResultSet result=st.executeQuery(req);
            while(result.next())
            {    Lignepanier p = new Lignepanier(result.getInt("id"),result.getString("nompr"),result.getString("descrip"),result.getDouble("prix"),result.getInt("quantite"));
            ligne.add(p);
                    }
        } catch (SQLException ex) {
            Logger.getLogger(LignePanierService.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("rrrr");
        }
          return  ligne;
     }

    public ObservableList<Lignepanier> indexActiondevis(int panierID) 
     { 
        ObservableList<Lignepanier> ligne=FXCollections.observableArrayList();
        String req= " select l.id,p.nompr,P.prix,l.quantite from product p JOIN ligne_panier l ON p.id = l.product_id and panier_id='"+panierID+"'";
        Statement st;
        try {
            st=connexion.createStatement();
            ResultSet result=st.executeQuery(req);
            while(result.next())
            {    Lignepanier p = new Lignepanier(result.getInt("id"),result.getString("nompr"),result.getDouble("prix"),result.getInt("quantite"));
            ligne.add(p);
                    }
        } catch (SQLException ex) {
            Logger.getLogger(LignePanierService.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("rrrr");
        }
          return  ligne;
     }

   
}
