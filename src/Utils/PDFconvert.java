/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import Utils.ConnectionBD;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.ResultSet;
import java.sql.Statement;


/**
 *
 * @author sarra
 */
public class PDFconvert {
    
    private Connection con = ConnectionBD.getInstance().getCnx();
    

   public void CreatePdf() {
        
        try {
         Document document = new Document();
        PdfWriter.getInstance(document,new FileOutputStream("C:/Users\\sarra\\Desktop\\javafinal\\Sarra.pdf"));
       document.open();
       PdfPTable table=new PdfPTable(5);
      
       table.setWidthPercentage(100);
		table.getDefaultCell().setBorder(1);
         table.addCell("Nom User");
         table.addCell("Nom Produit");
         table.addCell("Type");
         table.addCell("Probleme");
         table.addCell("Etat");
    
        com.itextpdf.text.Paragraph p = new com.itextpdf.text.Paragraph();
            p.add("T-HUNT");
       
            p.setAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            document.add(p);
            
                   document.add(com.itextpdf.text.Image.getInstance("C:/Users\\sarra\\Desktop\\javafinal\\SarraJava\\Sarra\\src\\Images\\logo.png"));

       Class.forName("com.mysql.jdbc.Driver");
       Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/huntkinkdom", "root", "");
       Statement st=con.createStatement();
     
       ResultSet rs=st.executeQuery("select user,  produit , Type , Probleme , etat from reclamation");
      
       while(rs.next()){
       table.addCell(rs.getString("user"));
        table.addCell(rs.getString("produit"));
         table.addCell(rs.getString("Type"));
          table.addCell(rs.getString("Probleme"));
          table.addCell(rs.getString("etat"));
       }
    
       document.add(table);
     
        
               document.close();
           
            

        } catch (Exception e) {
            
         
            System.out.println("Error PDF");
            System.out.println(e.getStackTrace());
            System.out.println(e.getMessage());
        }
    }
   
   
    
    
    
   
}
