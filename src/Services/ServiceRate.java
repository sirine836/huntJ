/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Utils.ConnectionBD;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author youss
 */
public class ServiceRate {
      Connection c=ConnectionBD.getInstance().getCnx();
       Statement ste;
        public void RateApp(String rate) throws SQLException{
         
        try {
            ste = c.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
         String req1="INSERT INTO `rate` (`rate`) VALUES ('"+rate+"');";
            ste.executeUpdate(req1);
            System.out.println("elment insert");
    }
}
           

    


