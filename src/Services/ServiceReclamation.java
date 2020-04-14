/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;



import Entities.Reclamation;
import Utils.ConnectionBD;
import java.sql.Connection;
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
 * @author sarra
 */
public class ServiceReclamation {
    
    Connection c=ConnectionBD.getInstance().getCnx();
    private Statement ste;
    
    public void ajouterreclamation(Reclamation rc) throws SQLException
    {
        
        try{
    PreparedStatement pt= c.prepareStatement(" insert into reclamation (produit,Probleme)"
        + " values ( ?, ?)");
            
           pt.setInt(1, rc.getProduit());
           pt.setString(2,rc.getProbleme());
         
            
          
            
           pt.execute();
            
        }catch (SQLException ex)
            {
            Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Reclamation> displayAll() {
      // L'id du freelancer dans la requette est statique il faut le changer (session) !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        ArrayList <Reclamation> tab = new ArrayList ();     
        
    
         try {
             PreparedStatement pt =c.prepareStatement("select  reclamation.id, product.id, probleme , product.nompr from reclamation inner join product on product.id=reclamation.produit");
  
             ResultSet res= pt.executeQuery();
             while(res.next())
                 
             {
                 Reclamation rec = new Reclamation(res.getInt(1), res.getInt(2), res.getString(3), res.getString(4));
     
                 tab.add(rec);
                 
             }
         } catch (SQLException ex) {
             Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
         }
        
       return tab;
    }
    
    
    public List<Reclamation> displayAll2() {
        ArrayList <Reclamation> tab = new ArrayList ();     
        
    
         try {
             PreparedStatement pt =c.prepareStatement("select reclamation.id ,fos_user.id ,product.id, probleme,product.nompr,fos_user.username ,etat  from reclamation inner join product on product.id=reclamation.produit inner join fos_user on fos_user.id=reclamation.user");
  
             ResultSet res= pt.executeQuery();
             while(res.next())
                 
             {
                 //Reclamation rec = new Reclamation(res.getInt(1), res.getInt(2), res.getString(3), res.getString(4), res.getString(5));
                 //Reclamation rr = new Reclamation(res.getInt(1), res.getInt(2), res.getInt(3), res.getInt(4), res.getString(5), res.getString(6), res.getString(7), res.getString(8));
                 Reclamation rr = new Reclamation(res.getInt(1),res.getInt(2), res.getInt(3), res.getString(4), res.getString(5), res.getString(6), res.getString(7));
                 tab.add(rr);
                 
             }
         } catch (SQLException ex) {
             Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
         }
        
       return tab;
    }
    
     public void affecterrec(int id,String nometat) 
    {
        
       try {
            PreparedStatement pt= c.prepareStatement("update reclamation set  etat= ?  where id=?");
           
            
            pt.setString(1,nometat);
            pt.setInt(2,id);
            
            pt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
      public void supprimreclamation(int ide)
    {
        try {
            PreparedStatement pt =c.prepareStatement("delete from reclamation where id=?" );
            pt.setInt(1,ide);
            pt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
        }
    
}
      
       public void modifierreclamation (int id,String probleme)
    {
        try {
            PreparedStatement pt= c.prepareStatement("update reclamation set  Probleme= ?  where id=?");
           
            
            pt.setString(1,probleme);
            pt.setInt(2,id);
            
            pt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
    
    }
    
    
}
