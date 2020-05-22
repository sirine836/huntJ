/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Events;
import Utils.DataBase;
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
 * @author h^
 */
public class EventService {

    Connection cx = DataBase.getInstance().getCnx();

    public void ajouter(Events e) {
        String req = "INSERT INTO events (titre, description, date, nbrPlaces, prix, localisation,idPro, Image) VALUES ( '"
                + e.getTitre() + "', '" + e.getDescription() + "','" + e.getDate() + "','" + e.getNbrPlaces() + "','" + e.getPrix() + "','" + e.getLocalisation() + "','" + e.getIdPro()+  "','" + e.getImage() + "')";
        Statement st;
        try {
            st = cx.createStatement();
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void supprimer(int id){
        String req="delete from events where Id= '"+id+"'";
        Statement st;
         try {
            st = cx.createStatement();
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void maj(Events e){
       
        Statement st;
        try {
             st = cx.createStatement();
             st.executeUpdate("UPDATE events SET titre='"+e.getTitre()+"',description='"+e.getDescription()+
                        "' WHERE id= "+e.getId());
                    }   
        catch (SQLException ex) {
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public void rechercheEvent(String Titre) throws SQLException {
        String req = "select * FROM events where titre= '"+Titre+"'";
        Statement pstm = cx.createStatement();
       ResultSet rst = pstm.executeQuery(req);
       rst.last();
       int nbrRow=rst.getRow();
       if (nbrRow != 0 )
       {System.out.println("Evenement trouver");}
       else{ System.out.println("Evenement non trouver");
    }
    }

    
    public ObservableList<Events> afficher()
             
     {  
        ObservableList<Events> mylist=FXCollections.observableArrayList();
        String req= " select * from events ";
        Statement st;
        try {
            st=cx.createStatement();
            ResultSet resultat=st.executeQuery(req);
            while(resultat.next())
            {    Events c = new Events(resultat.getInt("id") , resultat.getString("titre"), resultat.getString("description"),resultat.getString ("date"), resultat.getInt("nbrPlaces"), resultat.getDouble("prix"), resultat.getString("localisation"), resultat.getInt ("idPro"), resultat.getString("Image"));
               /*  c.setTitre(resultat.getString(2));
                 c.setDescription(resultat.getString(3));*/
                 mylist.add(c);
                    }
            
        } catch (SQLException ex) {
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
        }
          return mylist;
     }
    
    

   
public ObservableList<Events>recherche(String titre) {
        
        String requete = "SELECT * FROM  events  where titre = '"+titre+"' " ;
        PreparedStatement pst;
        ObservableList<Events> events= FXCollections.observableArrayList();
            

        try {
            pst = cx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while(rs.next())
            {
                
            int id=rs.getInt(1);
            titre =rs.getString("titre");

            String description =rs.getString("description");
              Double prix =rs.getDouble("prix");
              String date =rs.getString("date");
              int nbrPlaces= rs.getInt("nbrPlaces");
                          String nom_image =rs.getString("Image");

               Events e = new Events(id, titre, description, prix, date, nbrPlaces, nom_image);
           events.add(e);
            }
            
        
        } catch (SQLException ex) {
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
        }
       return events;
          
    }

    public void decrement_nb(int event_id) throws SQLException{
        PreparedStatement preparedStatement = null ;
        String query="update events e INNER JOIN reservation r ON e.id=r.event_id set e.nbrPlaces =e.nbrPlaces-r.quantite WHERE event_id="+event_id;
        Statement pstm = cx.createStatement();
       pstm.executeUpdate(query);
    }

}

