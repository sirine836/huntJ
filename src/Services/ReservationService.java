/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Controllers.Main;
import Entities.Events;
import Entities.Reservations;
import Utils.DataBase;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author h^
 */
public class ReservationService {

    Connection cx = DataBase.getInstance().getCnx();

    public void ajouter(Reservations  r) {
        String req = "INSERT INTO reservation (id , quantite , prixpaye, event_id, user_id) VALUES ( '"
                + r.getId() + "', '" + r.getQuantite() +"', '" + r.getPrixpaye()+"','"+r.getEvent_id()+ "','" + r.getUser_id()+ "')";
        Statement st;
        try {
            st = cx.createStatement();
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(ReservationService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void supprimer(int id){
        String req="delete from reservations where Id= '"+id+"'";
        Statement st;
         try {
            st = cx.createStatement();
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(ReservationService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
   /* public void maj(Reservations r){
        String req= " UPDATE reservations SET (nom , quantite, total_prix, Event_id)='"+ r.getNom() + "', '" + r.getQuantite() + "', '" + r.getTotal_prix()+"', '" + r.getEvent_id()+"' where id= '"+r.getId()+"' " ;
        Statement st;
        try {
             st = cx.createStatement();
             st.executeUpdate(req);
                    }   
        catch (SQLException ex) {
            Logger.getLogger(ReservationService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/
    
      public void rechercheReservation(int prixpaye) throws SQLException {
        String req = "select * FROM reservation where prixpaye= '"+prixpaye+"'";
        Statement pstm = cx.createStatement();
       ResultSet rst = pstm.executeQuery(req);
       rst.last();
       int nbrRow=rst.getRow();
       if (nbrRow != 0 )
       {System.out.println("Reservation trouver");}
       else{ System.out.println("Reservation non trouver");
    }
    }
    
    public ObservableList<Reservations> afficher()
             
     {  
        ObservableList<Reservations> mylist=FXCollections.observableArrayList();
        String req= " SELECT r.id, r.quantite, r.prixpaye, u.username, e.titre , e.Image FROM reservation r INNER JOIN events e ON r.event_id=e.id INNER JOIN fos_user u ON r.user_id=u.id ";
        Statement st;
        try {
            st=cx.createStatement();
            ResultSet resultat=st.executeQuery(req);
            while(resultat.next())
            {    Reservations r = new Reservations(resultat.getDouble("quantite"), resultat.getDouble("prixpaye"), resultat.getString("titre"), resultat.getString("username"));
               /*  c.setTitre(resultat.getString(2));
                 c.setDescription(resultat.getString(3));*/
                 mylist.add(r);
                    }
            
        } catch (SQLException ex) {
            Logger.getLogger(ReservationService.class.getName()).log(Level.SEVERE, null, ex);
        }
          return mylist;
     }
       
    public void afficherfront(Reservations r)
             
     {  
        ObservableList<Reservations> mylist=FXCollections.observableArrayList();
        String req= " SELECT r.id, r.quantite,r.prixpaye,e.titre FROM reservation r INNER JOIN events e  ON r.event_id=e.id WHERE r.user_id="+Main.user_id;
        Statement st;
        try {
            st=cx.createStatement();
            ResultSet resultat=st.executeQuery(req);
            while(resultat.next())
            {   r = new Reservations(resultat.getDouble("quantite"), resultat.getDouble("prixpaye"), resultat.getString("titre"));
               /*  c.setTitre(resultat.getString(2));
                 c.setDescription(resultat.getString(3));*/
                 mylist.add(r);
                    }
            
        } catch (SQLException ex) {
            Logger.getLogger(ReservationService.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
}


//SELECT r.id,r.quantite,r.prixpaye,e.titre FROM reservation r INNER JOIN events e ON r.event_id=e.id
