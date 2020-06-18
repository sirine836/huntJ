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
    
    public static EventService su;
    
     public static EventService getInstance() {
        if(su == null )
            su = new EventService();
        return su;
            
    }

    public void ajouter(Events e) {
        String req = "INSERT INTO events (titre, description, date, nbrPlaces, prix, localisation,idPro, nom_image) VALUES ( '"
                + e.getTitre() + "', '" + e.getDescription() + "','" + e.getDate() + "','" + e.getNbrPlaces() + "','" + e.getPrix() + "','" + e.getLocalisation() + "','" + e.getIdPro()+  "','" + e.getNom_image() + "')";
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
            {    Events c = new Events(resultat.getInt("id") , resultat.getString("titre"), resultat.getString("description"),resultat.getString ("date"), resultat.getInt("nbrPlaces"), resultat.getDouble("prix"), resultat.getString("localisation"), resultat.getInt ("idPro"), resultat.getString("nom_image"));
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
                          String nom_image =rs.getString("nom_image");

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
    
        public void increment_nb(int event_id) throws SQLException{
        PreparedStatement preparedStatement = null ;
        String query="update events e INNER JOIN reservation r ON e.id=r.event_id set e.nbrPlaces =e.nbrPlaces+r.quantite WHERE event_id="+event_id;
        Statement pstm = cx.createStatement();
       pstm.executeUpdate(query);
    }
    
    
    
     public void afficher2() {
        try {
            PreparedStatement pt = cx.prepareStatement("SELECT * FROM events");
            ResultSet result = pt.executeQuery();
            while (result.next()) {
        System.out.println(result.getInt(1)+"  "+result.getString(2)+"  "+result.getString(3)+" "+result.getString(4)); 
            }
        } catch (SQLException ex) {
            System.out.println("erreur " + ex.getMessage());
        }
    }
    
    public ResultSet afficherevents() {
                ResultSet rs = null;
        try {
            PreparedStatement pt = cx.prepareStatement("SELECT * FROM events");
            rs = pt.executeQuery();
    
        } catch (SQLException ex) {
            System.out.println("erreur " + ex.getMessage());
        }
            return rs;
    }
    
    
    
     public ResultSet afficherComingevents() {
                ResultSet rs = null;
        try {
            PreparedStatement pt = cx.prepareStatement("SELECT * FROM events  WHERE date > CURRENT_DATE()  ");
            rs = pt.executeQuery();
    
        } catch (SQLException ex) {
            System.out.println("erreur " + ex.getMessage());
        }
            return rs;
    }
     
       public ResultSet afficherPastevents() {
                ResultSet rs = null;
        try {
            PreparedStatement pt = cx.prepareStatement("SELECT * FROM events  WHERE date <= CURRENT_DATE()  ");
            rs = pt.executeQuery();
    
        } catch (SQLException ex) {
            System.out.println("erreur " + ex.getMessage());
        }
            return rs;
    }
    
    
      public Events affichereventparid(int id) {
         Events  evt= new Events();
            try {
            
           
            PreparedStatement pt = cx.prepareStatement("select * from events WHERE id = ?");
            pt.setInt(1, id);
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                evt.setId(rs.getInt(1));
                evt.setTitre(rs.getString(2));
                evt.setLocalisation(rs.getString(7));
                evt.setDate(rs.getString(4));
                evt.setDescription(rs.getString(3));
                evt.setNbrPlaces(rs.getInt(5));
                evt.setPrix(rs.getInt(6));
                evt.setNom_image(rs.getString(8));
                evt.setIdPro(rs.getInt(9));
              //  evt.setIdPro(rs.getInt(10));   

            }
        } catch (SQLException ex) {
            System.out.println("erreur " + ex.getMessage());
        }
        
        return evt;
    }
    
    
       
   

}

