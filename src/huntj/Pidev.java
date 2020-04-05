/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huntj;

import Entities.Professional;
import Entities.Seller;
import Services.ServiceProfessional;
import Services.ServiceSeller;
import java.sql.SQLException;

/**
 *
 * @author VENOM matmesouch el main mte3iiiiiiiiiiiiii bisous bye
 */
public class Pidev {

    /**
     * @param args the command line arguments
     * @throws java.sql.SQLException
     */
    public static void main(String[] args) throws SQLException {
        Professional p = new Professional("cyrine", "Jlassi","cyrine@gmail.com","skills","cv cv");
        Seller s = new Seller("Decathlon",71100200,"decathlon@org.tn");
        ServiceProfessional sp = new ServiceProfessional();
        ServiceSeller ss = new ServiceSeller();
        
        System.out.println("CRUD Professional");
        //sp.showProfessional();
        //System.out.println(sp.getAllProfessional());
        //sp.addProfessional(p);
        //sp.editProfessional(43,"Koukoud","Ouerghemmi","khouloud.ouerghemmi@esprit.tn","lorem","cv");
        //sp.deleteProfessional(43);
        System.out.println("CRUD Seller");
        ss.showSeller();
        //ss.addSeller(s);
        //ss.editSeller(33, "HAHAH", "rcs", "taxnumber", 0, 0, 0, 0,"haha april fool", "");
        ss.deleteSeller(36);

        
    }
    
}