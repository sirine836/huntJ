/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author sarra
 */
public class Avis {
    private int id;
    private int produit;
    private int user;
    private String note;
    private String nomproduit;
    private String nomuser;
    private String rate;

    public Avis(int id, int produit, int user, String nomproduit, String rate) {
        this.id = id;
        this.produit = produit;
        this.user = user;
        this.nomproduit = nomproduit;
        this.rate = rate;
    }
    

    

    public Avis(int produit, String rate, int user) {
        this.produit = produit;
        this.rate = rate;
        this.user=user;
    }

    public Avis(int id, int produit, int user, String nomproduit, String nomuser, String rate) {
        this.id = id;
        this.produit = produit;
        this.user = user;
        this.nomproduit = nomproduit;
        this.nomuser = nomuser;
        this.rate = rate;
    }

    

    

   
    
    
    

    public Avis(int produit, int user, String note, String nomproduit, String nomuser) {
        this.produit = produit;
        this.user = user;
        this.note = note;
        this.nomproduit = nomproduit;
        this.nomuser = nomuser;
    }

    public Avis(int id, int produit, int user, String note) {
        this.id = id;
        this.produit = produit;
        this.user = user;
        this.note = note;
    }

    public Avis(int produit, int user, String note) {
        this.produit = produit;
        this.user = user;
        this.note = note;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProduit() {
        return produit;
    }

    public void setProduit(int produit) {
        this.produit = produit;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getNomproduit() {
        return nomproduit;
    }

    public void setNomproduit(String nomproduit) {
        this.nomproduit = nomproduit;
    }

    public String getNomuser() {
        return nomuser;
    }

    public void setNomuser(String nomuser) {
        this.nomuser = nomuser;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }
    

  
    
    
}
