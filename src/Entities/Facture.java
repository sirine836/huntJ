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
public class Facture {
    private int idfact,panier_id;
    private String adresse, numtel, datedelivraison;
    private int etat;
    
    
    /*-------------------------------------------------------*/

    public Facture() {
    }

    public Facture(int idfact, int panier_id, String adresse, String numtel, String datedelivraison, int etat) {
        this.idfact = idfact;
        this.panier_id = panier_id;
        this.adresse = adresse;
        this.numtel = numtel;
        this.datedelivraison = datedelivraison;
        this.etat = etat;
    }

   

    public Facture(String adresse, String numtel, int etat) {
        this.adresse = adresse;
        this.numtel = numtel;
        this.etat = etat;
    }

   

    
    
  
    /*---------------------------------------------------------------*/ 

    public int getIdfact() {
        return idfact;
    }

    public void setIdfact(int idfact) {
        this.idfact = idfact;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getNumtel() {
        return numtel;
    }

    public void setNumtel(String numtel) {
        this.numtel = numtel;
    }

    public int getPanier_id() {
        return panier_id;
    }

    public void setPanier_id(int panier_id) {
        this.panier_id = panier_id;
    }

    public String getDatedelivraison() {
        return datedelivraison;
    }

    public void setDatedelivraison(String datedelivraison) {
        this.datedelivraison = datedelivraison;
    }

    

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    

    
    
    
    /*------------------------------------------------------------------------*/

    @Override
    public String toString() {
        return "Facture{" + "idfact=" + idfact + ", panier_id=" + panier_id + ", adresse=" + adresse + ", numtel=" + numtel + ", datedelivraison=" + datedelivraison + ", etat=" + etat + '}';
    }

    

    
    
}
