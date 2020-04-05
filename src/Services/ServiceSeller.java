/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Utils.MyDbConnection;
import Entities.Seller;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author VENOM
 */
public class ServiceSeller {
    Connection con=MyDbConnection.getInstance().getConnexion();
    public void addSeller(Seller p) throws SQLException{
     Statement st;
            st = con.createStatement();
            String req = "insert into seller (`sellername`,`rcs`,`taxnumber`,`tva`,`siren`,`fax`,`phonenumber`,`email`,`image`) values ('"+ p.getSellername()+"','"+p.getRcs()+"','"+p.getTaxnumber()+"','"+p.getTva()+"','"+p.getSiren()+"','"+p.getFax()+"','"+p.getPhonenumber()+"','"+p.getEmail()+"','"+p.getImage()+"')";
            st.executeUpdate(req);
       Statement stm = con.createStatement();
        stm.executeUpdate(req);
    }
    public void showSeller() throws SQLException {
        PreparedStatement pt;

            pt = con.prepareStatement("select * from seller");
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                System.out.println("Seller [id :" + rs.getInt(1) + ",Name" + rs.getString(2) + ",RCS" + rs.getString(3) + ",Tax" + rs.getString(4) + ",TVA" + rs.getInt(5) + ",SIREN" + rs.getInt(6) + ",FAX" + rs.getInt(7) + ",Phone" + rs.getInt(8) +", Email" +rs.getString(9)+",Licence"+rs.getString(10));
            }
    }
    public void editSeller(int id, String sellername, String rcs, String taxnumber, int tva, int siren, int fax, int phonenumber, String email, String image) throws SQLException {
        String req = "UPDATE `seller` SET  sellername='"+sellername
                               +"', rcs='"+rcs
                               +"', taxnumber='"+taxnumber
                               +"',tva='"+tva 
                               +"',siren='"+siren 
                               +"',fax='"+fax 
                               +"',phonenumber='"+phonenumber
                               +"',email='"+email
                               +"',image='"+image 
                               + "' WHERE id="+id;
        Statement pstm = con.createStatement();
       pstm.executeUpdate(req);
        System.out.println("Candidate updated");
    }
    public void deleteSeller(int id) throws SQLException  {
    
        String req = "DELETE FROM `seller` where id="+id;
        System.out.println(req);
        Statement pstm = con.createStatement();
        pstm.executeUpdate(req);
        System.out.println("Candidate deleted");
    }
    
}
