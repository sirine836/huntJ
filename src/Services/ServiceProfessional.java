/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Controllers.Main;
import Utils.DataBase;
import Entities.Professional;
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
 * @author VENOM
 */
public class ServiceProfessional {
Connection con=DataBase.getInstance().getCnx();
    public void addProfessional(Professional p) throws SQLException {
        Statement st;
            st = con.createStatement();
            String req = "insert into professional (`proname`,`prosurname`,`email`,`softskills`,`image`, idUser) values ('"+ p.getProname()+"','"+p.getProsurname()+"','"+p.getEmail()+"','"+p.getSoftskills()+"','"+p.getImage() + "','"+ Main.fos_user.getId() + "')";
            st.executeUpdate(req);
       

        }

    public List<Professional>getAllProfessional()throws SQLException 
    {
        List<Professional> g = new ArrayList<>();
        Statement st;
        st = con.createStatement();
        String req = "select * from professional";
        Statement stm = con.createStatement();
        ResultSet result =  stm.executeQuery(req);
        
        while(result.next()){
            Professional p = new Professional(result.getInt(43), result.getString("proname"),result.getString("prosurname"), result.getString("email"), result.getString("sofskills"),result.getString("image"));
            g.add(p);
        }
        
        return g;
    }

    public void showProfessional() throws SQLException {
        PreparedStatement pt;

            pt = con.prepareStatement("select * from professional");
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                System.out.println("Professional [id :" + rs.getInt(1) + ",Name" + rs.getString(2) + ",Surname" + rs.getString(3) +", Email" +rs.getString(4)+", Sofskills"+rs.getString(5)+",CV"+rs.getString(6));
            }


    }
    
    public void editProfessional(int id, String proname, String prosurname, String email, String softskills, String image)throws SQLException {
        String req = "UPDATE `professional` SET  proname='"+proname
                               +"', prosurname='"+prosurname
                               +"', email='"+email
                               +"',softskills='"+softskills 
                               +"',image='"+image 
                               + "' WHERE id="+id;
        Statement pstm = con.createStatement();
       pstm.executeUpdate(req);
        System.out.println("Candidate updated");
    }

    public void deleteProfessional(int id) throws SQLException  {
    
        String req = "DELETE FROM `professional` where id="+id;
        System.out.println(req);
        Statement pstm = con.createStatement();
        pstm.executeUpdate(req);
        System.out.println("Candidate deleted");

    }

    public ObservableList<Professional>getAllPro()throws SQLException 
    {
        ObservableList<Professional> mylist=FXCollections.observableArrayList();
        Statement st;
        st = con.createStatement();
        String req = "select p.id , p.proname,p.prosurname, p.email , p.softskills ,p.image , u.username  from professional p INNER JOIN fos_user u ON p.idUser=u.id";
        Statement stm = con.createStatement();
        ResultSet result =  stm.executeQuery(req);
        
        while(result.next()){
            Professional p = new Professional(result.getInt("id"),result.getString("proname"),result.getString("prosurname"),result.getString("email"),result.getString("softskills"),result.getString("image"),result.getString("username"));
            mylist.add(p);
        }
        
        return mylist;
    }
    
    
     public void maj(Professional p) {
        Statement st;
        try {
            st = con.createStatement();
            st.executeUpdate("UPDATE `professional` SET  proname='" + p.getProname()
                    + "', prosurname='" + p.getProsurname()
                    + "',email='" + p.getEmail()
                    + "', softskills='" + p.getSoftskills()
                    + "' WHERE id=" + p.getId());
        } catch (SQLException ex) {
            Logger.getLogger(ServiceProfessional.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
    
}
