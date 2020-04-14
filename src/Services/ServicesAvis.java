/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Avis;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author sarra
 */
public class ServicesAvis {
    
    Connection c=ConnectionBD.getInstance().getCnx();
    private Statement ste;
    
    public void ajouteravis(Avis a) throws SQLException
    {
        
        try{
    PreparedStatement pt= c.prepareStatement(" insert into avis (produit,rate)"
        + " values ( ?, ?)");
            
           pt.setInt(1, a.getProduit());
           
            pt.setString(2,a.getRate());
          
            
           pt.execute();
            
        }catch (SQLException ex)
            {
            Logger.getLogger(ServicesAvis.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
 public List<String> displayP()
       {
             List<String> list = new ArrayList<String>();
      
        ObservableList obList = FXCollections.observableList(list);
        
       
         try {
            PreparedStatement pt =c.prepareStatement("select * from product");
            ResultSet rs= pt.executeQuery();
            
            while(rs.next())
            {
               list.add(rs.getString(2));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServicesAvis.class.getName()).log(Level.SEVERE, null, ex);
        }
         return list;
       }
 
 public int id_product(String m) {
        
                Connection c=ConnectionBD.getInstance().getCnx();
                 int k=0;

        try {
            PreparedStatement pt =c.prepareStatement("select id from product where nompr=?");
            pt.setString(1,m);
            ResultSet rs= pt.executeQuery();
            
            while(rs.next())
            {
               k = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServicesAvis.class.getName()).log(Level.SEVERE, null, ex);
        }
        return k;
    }
 
  public List<Avis> displayAll() {
      // L'id du freelancer dans la requette est statique il faut le changer (session) !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        ArrayList <Avis> tab = new ArrayList ();     
        
    
         try {
           
             PreparedStatement pt =c.prepareStatement("select  avis.id, product.id, user , product.nompr, rate from avis inner join product on product.id=avis.produit");
                          
  
             ResultSet res= pt.executeQuery();
             while(res.next())
                 
             {
                
                // Avis a = new Avis(res.getInt(1), res.getInt(2), res.getInt(3), res.getString(4), res.getString(5));
                 Avis a = new Avis(res.getInt(1), res.getInt(2), res.getInt(3), res.getString(4), res.getString(5));
             
               
     
                 tab.add(a);
                 
             }
         } catch (SQLException ex) {
             Logger.getLogger(ServicesAvis.class.getName()).log(Level.SEVERE, null, ex);
         }
        
       return tab;
    }
  
  
  
  public List<Avis> displayAll2() {
        ArrayList <Avis> tab = new ArrayList ();     
        
    
         try {
             PreparedStatement pt =c.prepareStatement("select  avis.id, product.id, fos_user.id , product.nompr, fos_user.username,rate from avis inner join product on product.id=avis.produit inner join fos_user on fos_user.id=avis.user");
  
             ResultSet res= pt.executeQuery();
             while(res.next())
                 
             {
                 //Avis a = new Avis(res.getInt(1),res.getInt(2), res.getInt(3), res.getString(4), res.getString(5), res.getString(6));
                 Avis a =new Avis(res.getInt(1), res.getInt(2), res.getInt(3), res.getString(4), res.getString(5), res.getString(6));
                 tab.add(a);
                 
             }
         } catch (SQLException ex) {
             Logger.getLogger(ServicesAvis.class.getName()).log(Level.SEVERE, null, ex);
         }
        
       return tab;
    }
  
  
  
  
       public void modifieravis (int id,String rate)
    {
        try {
            PreparedStatement pt= c.prepareStatement("update avis set  rate= ?  where id=?");
           
            
            pt.setString(1,rate);
            pt.setInt(2,id);
            
            pt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ServicesAvis.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
    
    }
       
       public void supprimAvis(int ide)
    {
        try {
            PreparedStatement pt =c.prepareStatement("delete from avis where id=?" );
            pt.setInt(1,ide);
            pt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ServicesAvis.class.getName()).log(Level.SEVERE, null, ex);
        }
    
}
}
