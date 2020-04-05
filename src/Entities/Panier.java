/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;



/**
 *
 * @author gogo-
 */
public class Panier {
    private int idpan,user;
    private int etat,archive;
    private String datepanier;
    private double prixtotal;
    
    
    /*-------------------------------------------------------*/

    public Panier() {
    }

    public Panier(int idpan,int user, int etat, int archive, String datepanier, double prixtotal) {
        this.idpan = idpan;
        this.user = user;
        this.etat = etat;
        this.archive = archive;
        this.datepanier = datepanier;
        this.prixtotal = prixtotal;
    }

   

    public Panier(int idpan, String datepanier, int etat, int archive, double prixtotal) {
        this.idpan = idpan;
        this.etat = etat;
        this.archive = archive;
        this.datepanier = datepanier;
        this.prixtotal = prixtotal;
    }

    public Panier(int idpan,int etat, int archive, double prixtotal) {
         this.idpan = idpan;
        this.etat = etat;
        this.archive = archive;
        this.prixtotal = prixtotal;
    }

  

  
    /*---------------------------------------------------------------*/ 

    public int getIdpan() {
        return idpan;
    }

    public void setIdpan(int idpan) {
        this.idpan = idpan;
    }

    

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public int getArchive() {
        return archive;
    }

    public void setArchive(int archive) {
        this.archive = archive;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public String getDatepanier() {
        return datepanier;
    }

    public void setDatepanier(String datepanier) {
        this.datepanier = datepanier;
    }

    

    public double getPrixtotal() {
        return prixtotal;
    }

    public void setPrixtotal(double prixtotal) {
        this.prixtotal = prixtotal;
    }

   
    
    
    
    /*------------------------------------------------------------------------*/

    @Override
    public String toString() {
        return "Panier{" + "idpan=" + idpan + ", user=" + user + ", etat=" + etat + ", archive=" + archive + ", datepanier=" + datepanier + ", prixtotal=" + prixtotal + '}';
    }

   

    
}
