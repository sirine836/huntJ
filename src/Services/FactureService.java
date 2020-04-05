/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;


import Entities.Facture;
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
public class FactureService {

   Connection connexion;
   

    public FactureService() {
       connexion=MyDbConnection.getInstance().getConnexion();
    }

    public void ajouterfacture(Facture f) throws SQLException {
        String req = "INSERT INTO `facture` (`panier_id`,`adresse`, `dateDeLivraison`,`numtel`,`etat`) VALUES ( '"+ f.getPanier_id()+"','"+ f.getAdresse()+"','"
                + f.getDatedelivraison() + "','" + f.getNumtel() + "','" + f.getEtat() + "') ";
        Statement stm = connexion.createStatement();
        stm.executeUpdate(req);
    }

    public void ajouterFacture2(Facture f) throws SQLException {
        String req = "INSERT INTO `facture` (`panier_id`,`adresse`, `dateDeLivraison`,`numtel`,`etat`) VALUES (?,?,?,?,?) ";
        PreparedStatement pstm = connexion.prepareStatement(req);
        pstm.setInt(1, f.getPanier_id());
        pstm.setString(2, f.getAdresse());
        pstm.setString(3, f.getDatedelivraison());
        pstm.setString(4, f.getNumtel());
        pstm.setInt(5, f.getEtat());
        pstm.executeUpdate();
    }
    
    public void supprimerFacture(int id) throws SQLException {
        String req = "DELETE FROM `facture` where id= "+id;
        Statement pstm = connexion.createStatement();
        pstm.executeUpdate(req);
        System.out.println("Facture Supprimer");
    }
    
    
    public void modifierFacture(int id,int panier_id,String adresse,String numtel,int etat) throws SQLException {
        String req = "UPDATE `facture` SET  panier_id='"+panier_id
                               +"',adresse='"+adresse
                               +"', numtel='"+numtel
                               +"', etat='"+etat
                               + "' WHERE id="+id;
        System.out.println(req);
        Statement pstm = connexion.createStatement();
       pstm.executeUpdate(req);
        System.out.println("Facture modifier");
    }
    
    
    public void rechercheFacture (int etat) throws SQLException {
        String req = "select * FROM `facture` where etat= '"+etat+"'";
        Statement pstm = connexion.createStatement();
       ResultSet rst = pstm.executeQuery(req);
       rst.last();
       int nbrRow=rst.getRow();
       if (nbrRow != 0 )
       {System.out.println("LignePanier trouver");}
       else{ System.out.println("LignePanier non trouver");
    }
    }
 
    public List<Facture> getAllFactures() throws SQLException {
       List<Facture> Factures = new ArrayList<>();
        
        String req = "select * from facture";
        Statement stm = connexion.createStatement();
        ResultSet result =  stm.executeQuery(req);
        
        while(result.next()){
            Facture p = new Facture(result.getInt(1),result.getInt("panier_id"), result.getString("adresse"), result.getString("dateDeLivraison"),result.getString("numtel"), result.getInt("etat"));
            Factures.add(p);
        }
        
        return Factures;
    }

}
