/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Utils.MyDbConnection;
import Entities.Professional;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author VENOM
 */
public class ServiceProfessional {
Connection con=MyDbConnection.getInstance().getConnexion();
    public void addProfessional(Professional p) throws SQLException {
        Statement st;
            st = con.createStatement();
            String req = "insert into professional (`proname`,`prosurname`,`email`,`softskills`,`image`) values ('"+ p.getProname()+"','"+p.getProsurname()+"','"+p.getEmail()+"','"+p.getSoftskills()+"','"+p.getImage()+"')";
            st.executeUpdate(req);
       Statement stm = con.createStatement();
        stm.executeUpdate(req);

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
    
}
