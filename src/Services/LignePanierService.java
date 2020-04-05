/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Lignepanier;
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
        String req = "INSERT INTO `ligne_panier` (`product_id`,`panier_id`,`quantite`) VALUES ( ?,?,?) ";
        PreparedStatement pstm = connexion.prepareStatement(req);
        pstm.setInt(1, lp.getProduct_id());
        pstm.setInt(2, lp.getPanier_id());
        pstm.setInt(3, lp.getQuantite());
        pstm.executeUpdate();
    }
    
    public void supprimerLigne(int id) throws SQLException {
        String req = "DELETE FROM `ligne_panier` where id= "+id;
        Statement pstm = connexion.createStatement();
        pstm.executeUpdate(req);
        System.out.println("LignePanier Supprimer");
    }
    
    
    public void modifierLigne(int id,int quantite) throws SQLException {
        String req = "UPDATE `ligne_panier` SET  quantite='"+quantite
                               + "' WHERE id="+id;
        Statement pstm = connexion.createStatement();
       pstm.executeUpdate(req);
        System.out.println("LignePanier modifier");
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
    
  /*---------------autre methode------------------*/  
    
      
    
   public  void rechercheLigne (int quantite) throws SQLException {
        String req = "select * FROM `ligne_panier` where quantite= '"+quantite+"'";
        Statement pstm = connexion.createStatement();
       ResultSet rst = pstm.executeQuery(req);
       rst.last();
       int nbrRow=rst.getRow();
       if (nbrRow != 0 )
       {System.out.println("LignePanier trouver");}
       else{ System.out.println("LignePanier non trouver");
    }
    }
    
    public void increment_qnt(int prodId) throws SQLException{
        PreparedStatement preparedStatement = null ;
        String query="update ligne_panier set quantite=quantite+1 WHERE product_id=?";
        Statement pstm = connexion.createStatement();
       pstm.executeUpdate(query);
    }
    
    public void decrement_qnt(int prodId) throws SQLException{
        PreparedStatement preparedStatement = null ;
        String query="update ligne_panier set quantite=quantite-1 WHERE product_id=?";
        Statement pstm = connexion.createStatement();
       pstm.executeUpdate(query);
    }
    
    
    
    public void calcul_total() throws SQLException{
            String query="SELECT sum(p.prix*quantite) as total FROM ligne_panier l JOIN product p ON p.id = l.product_id";
            ResultSet rs =connexion.createStatement().executeQuery(query);
            while(rs.next()){
            int totalamount=rs.getInt("total");
                System.out.println(totalamount);
            }
    }

}
