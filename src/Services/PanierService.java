/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Panier;
import Utils.MyDbConnection;
import java.sql.Connection;
import java.sql.Date;
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
public class PanierService {

   Connection connexion;
   

    public PanierService() {
       connexion=MyDbConnection.getInstance().getConnexion();
    }
    
    /*------------- CRUD---------------*/

    void ajouterPanier(Panier p) throws SQLException {
        String req = "INSERT INTO `panier` (`user`,`datePanier`, `etat`,`archive`, `prixTotal` ) VALUES ( '"+p.getUser()+
               "','" + p.getDatepanier() + "', '" + p.getEtat()+ "','"+p.getArchive()+"','"+p.getPrixtotal()+"') ";
        Statement stm = connexion.createStatement();
        stm.executeUpdate(req);
    }

    public void ajouterPanier2(Panier p) throws SQLException {
        String req = "INSERT INTO `panier` (`user`,`datePanier`, `etat`,`archive`, `prixTotal`)  VALUES ( ?, ?,?,?,?) ";
        PreparedStatement pstm = connexion.prepareStatement(req);
        pstm.setInt(1, p.getUser());
        pstm.setString(2, p.getDatepanier());
        pstm.setInt(3, p.getEtat());
        pstm.setInt(4, p.getArchive());
        pstm.setDouble(5, p.getPrixtotal());
        pstm.executeUpdate();
    }
    
     void supprimerPanier(int id) throws SQLException {
        String req = "DELETE FROM `panier` where id= "+id;
        Statement pstm = connexion.createStatement();
        pstm.executeUpdate(req);
        System.out.println("Panier Supprimer");
    }
    
     public void updatePanierinit(int idp,int user_id) throws SQLException {
         Statement st;
        try {
             st = connexion.createStatement();
             st.executeUpdate( "UPDATE `panier` SET  etat='"+0+ "', archive='"+1
                               + "' WHERE user ='"+user_id
                               +"'and id=(SELECT panier_id FROM `facture` WHERE  panier_id='"+1+"')");
        }   
        catch (SQLException ex) {
            Logger.getLogger(PanierService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
     public void updatePan(int idp,int user_id,double prixtot) throws SQLException {
         Statement st;
        try {
             st = connexion.createStatement();
             st.executeUpdate( "UPDATE `panier` SET  etat='"+1+ "', archive='"+1
                               + "', prixTotal='"+prixtot+ "' WHERE user ='"+user_id
                               +"'and id=(SELECT panier_id FROM `facture` WHERE etat='"+1
                               +"' and panier_id='"+1+"')");
        }   
        catch (SQLException ex) {
            Logger.getLogger(PanierService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    void modifierPanier(int idpan, String datepanier, int etat, int archive, double prixTotal) throws SQLException {
        String req = "UPDATE `panier` SET  datepanier='"+datepanier
                               +"', etat='"+etat
                               +"', archive='"+archive
                               +"', prixTotal='"+prixTotal
                               + "' WHERE id="+idpan;
        System.out.println(req);
        Statement pstm = connexion.createStatement();
       pstm.executeUpdate(req);
        System.out.println("Panier modifier");
    }
    
    
    

   List<Panier> getAllPaniers() throws SQLException {
       List<Panier> Paniers = new ArrayList<>();
        
        String req = "select * from panier ";
        Statement stm = connexion.createStatement();
        ResultSet result =  stm.executeQuery(req);
        
        while(result.next()){
            Panier p = new Panier(result.getInt(1),result.getInt("user"),result.getInt("etat"),result.getInt("archive"), result.getString("datePanier"), result.getDouble("prixTotal"));
            Paniers.add(p);
        }
        
        return Paniers;
    }
   
   /*--------------- chercher panier---------------------------*/
   
   void recherchePanier(Double prix) throws SQLException {
        String req = "select * FROM `panier` where prixTotal= '"+prix+"'";
        Statement pstm = connexion.createStatement();
       ResultSet rst = pstm.executeQuery(req);
       rst.last();
       int nbrRow=rst.getRow();
       if (nbrRow != 0 )
       {System.out.println("Panier trouver");}
       else{ System.out.println("Panier non trouver");
    }
    }
   
   
   /*-------------------affichage des panier valider----------------------*/
   
   void affichagerPanier_valider()throws SQLException{
   String req = "select * FROM `panier` where etat=1 ";
        Statement pstm = connexion.createStatement();
       ResultSet rst = pstm.executeQuery(req);
       rst.last();
       int nbrRow=rst.getRow();
       System.out.println(nbrRow);
    }
   
   /*--------------- chercher le panier disponible ---------------------------*/
   
   public Panier getCurrentPanierByUserID(int id_user) throws SQLException {
        String req = "select * FROM `panier` where user= '"+id_user+"' and etat=0 ";
        //Statement pstm = connexion.createStatement();
        PreparedStatement pstm = connexion.prepareStatement(req);
       ResultSet rst = pstm.executeQuery();
       Panier panier = new Panier();
       while (rst.next()) {
                panier.setIdpan(rst.getInt("id"));
                panier.setUser(rst.getInt("user"));
                panier.setEtat(rst.getInt("etat"));
                panier.setDatepanier(rst.getString("datePanier"));
                panier.setPrixtotal(rst.getDouble("prixTotal"));
                panier.setArchive(rst.getInt("archive"));
       }

            
       
/*       
//rst.last();
       //int nbrRow=rst.getRow();
       
       
       //if (nbrRow != 0 )
       //{
       //System.out.println("Panier trouver");
       
       
          
       
       }
       else{ System.out.println("Panier non trouver");}
       */
       return panier;

    }

}
