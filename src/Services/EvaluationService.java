/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Evaluations;
import Utils.DataBase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.ArrayList;

/**
 *
 * @author fedi
 */
public class EvaluationService {

    Connection cx = DataBase.getInstance().getCnx();

    public void ajouter(Evaluations  ev) {
        String req = "INSERT INTO evaluations (note, commentaire, event_id, user_id) VALUES ( '"
                + ev.getNote() + "', '" + ev.getCommentaire() + "','"+ev.getEvent_id()+ "','" + ev.getUser_id()+ "')";
        Statement st;
        try {
            st = cx.createStatement();
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(EvaluationService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void supprimer(int id){
        String req="delete from evaluations where Id= '"+id+"'";
        Statement st;
         try {
            st = cx.createStatement();
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(EvaluationService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void maj(Evaluations e){
       
        Statement st;
        try {
             st = cx.createStatement();
             st.executeUpdate("UPDATE evaluations SET note='"+e.getNote()+"',commentaire='"+e.getCommentaire()+
                        "' WHERE id= "+e.getId());
                    }   
        catch (SQLException ex) {
            Logger.getLogger(EvaluationService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
      public void rechercheEvaluation(int id) throws SQLException {
        String req = "select * FROM evaluations where id= '"+id+"'";
        Statement pstm = cx.createStatement();
       ResultSet rst = pstm.executeQuery(req);
       rst.last();
       int nbrRow=rst.getRow();
       if (nbrRow != 0 )
       {System.out.println("Evaluation trouver");}
       else{ System.out.println("Evaluation non trouver");
    }
    }
    
    public ObservableList<Evaluations> afficher()
             
     {  
        ObservableList<Evaluations> mylist=FXCollections.observableArrayList();
        String req= " SELECT ev.id,ev.note,ev.commentaire,e.titre FROM evaluations ev INNER JOIN events e ON ev.event_id=e.id ";
        Statement st;
        try {
            st=cx.createStatement();
            ResultSet resultat=st.executeQuery(req);
            while(resultat.next())
            {    Evaluations c = new Evaluations(resultat.getString("titre"),resultat.getString("commentaire"),resultat.getInt("note"));
               /*  c.setTitre(resultat.getString(2));
                 c.setDescription(resultat.getString(3));*/
                 mylist.add(c);
                    }
            
        } catch (SQLException ex) {
            Logger.getLogger(EvaluationService.class.getName()).log(Level.SEVERE, null, ex);
        }
          return mylist;
     }
    
    public ObservableList<Evaluations> afficherUsers(){
                ObservableList<Evaluations> mylist=FXCollections.observableArrayList();
                String req="Select ev.note from evaluations ev INNER JOIN fos_user u ON ev.user_id=u.id";
                Statement st;
        try {
            st=cx.createStatement();
            ResultSet resultat=st.executeQuery(req);
            while(resultat.next())
            {    Evaluations c = new Evaluations(resultat.getDouble("note"));
               /*  c.setTitre(resultat.getString(2));
                 c.setDescription(resultat.getString(3));*/
                 mylist.add(c);
                    }
            
        } catch (SQLException ex) {
            Logger.getLogger(EvaluationService.class.getName()).log(Level.SEVERE, null, ex);
        }
          return mylist;
     }
    
     public List<Evaluations> FindByUserID(int id)  {
        
        
            
        List<Evaluations> listEV = new ArrayList<>();
        
        try {
        String req = "SELECT * FROM `evaluations` WHERE user_id = ?";
        PreparedStatement pre = cx.prepareStatement(req);
        pre.setInt(1,id);
        ResultSet rs = pre.executeQuery();
        while (rs.next()) {
            
            
              listEV.add(new Evaluations(
                         rs.getInt("id"),
                      rs.getDouble("note"),
                      rs.getString("commentaire"),
                         rs.getInt("event_id"),
                         rs.getInt("user_id")
                ));
     
        }
        
              } catch (SQLException ex) {
            Logger.getLogger(EvaluationService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listEV;
    }
     
     
     public ObservableList<Evaluations> afficherEvaluationsparidev(int id) {
        ObservableList<Evaluations> mylist=FXCollections.observableArrayList();
            try {
            
           
            PreparedStatement pt = cx.prepareStatement("SELECT ev.note,ev.commentaire,u.username FROM evaluations ev INNER JOIN fos_user u ON ev.user_id=u.id  WHERE ev.event_id = ?");
            pt.setInt(1, id);
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                Evaluations c = new Evaluations(rs.getDouble("note"), rs.getString("commentaire"), rs.getString("username"));
              //  evt.setIdPro(rs.getInt(10));   

             mylist.add(c);
                    }
        } catch (SQLException ex) {
            System.out.println("erreur " + ex.getMessage());
        }
        
        return mylist;
    }
     
     
      public Evaluations afficherevaluationparidEventidUser(int event_id, int user_id) {
         Evaluations  evt= new Evaluations();
            try {
            
           
            PreparedStatement pt = cx.prepareStatement("select * from evaluations WHERE event_id = ? AND user_id=?");
            pt.setInt(1, event_id);
                        pt.setInt(2, user_id);

            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                evt.setId(rs.getInt(1));
               evt.setEvent_id(rs.getInt(2));
               evt.setUser_id(rs.getInt(3));
               evt.setNote(rs.getInt(4));
               evt.setCommentaire(rs.getString(5));
              //  evt.setIdPro(rs.getInt(10));   

            }
        } catch (SQLException ex) {
            System.out.println("erreur " + ex.getMessage());
        }
        
        return evt;
    }
     
     public double eval(int id) throws SQLException{
            String query="select avg(note) from evaluations where event_id=?" ;
            PreparedStatement pre = cx.prepareStatement(query);
             pre.setInt(1,id);
             ResultSet rs = pre.executeQuery();
            while(rs.next()){
            Double notecours=rs.getDouble("note");
                return notecours;
                
            }
       return 0;
    }
     
     
                


    
                
}


//SELECT ev.id,ev.note,ev.commentaire,e.titre FROM evaluations ev INNER JOIN events e ON ev.event_id=e.id

