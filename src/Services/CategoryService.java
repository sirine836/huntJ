
package Services;

import Entities.Category;
import Utils.DataBase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MONDHER
 */
public class CategoryService {
     public static int saveCat(Category cat){
        int categ = 0 ;
        Connection cnx = DataBase.getInstance().getCnx();
        PreparedStatement ps;
        
        try {
            ps = cnx.prepareStatement("INSERT INTO category (nomcat,souscat) VALUES(?,?)");
            ps.setString(1 , cat.getNomcat());
            ps.setString(2 , cat.getSouscat());
            categ = ps.executeUpdate();
        } 
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        return categ ;
    }
     
      
     public List<Category> displayAll() { //**DONE**s
        ArrayList <Category> tab = new ArrayList ();
        Connection cnx = DataBase.getInstance().getCnx();
        PreparedStatement ps;
        
        try {
            ps = cnx.prepareStatement("SELECT * FROM category");
            ResultSet rs = ps.executeQuery();
          
            while(rs.next()){
               Category cat = new Category(rs.getInt(1),rs.getString(2),rs.getString(3));
               tab.add(cat);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return tab;
}
}
