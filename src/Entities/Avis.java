/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author youss
 */
public class Avis {
    private int id;
    private int produit;
    private int user;
    private String type;
    private String note;
    private String nomproduit;
    private String nomuser;

  
    
    

    public Avis(int id, int produit, int user, String type, String note, String nomproduit) {
        this.id = id;
        this.produit = produit;
        this.user = user;
        this.type = type;
        this.note = note;
        this.nomproduit = nomproduit;
    }

    public Avis(int produit, String type, String note, String nomproduit) {
        this.produit = produit;
        this.type = type;
        this.note = note;
        this.nomproduit = nomproduit;
    }

    public Avis(String type, String note) {
        this.type = type;
        this.note = note;
    }
    
    

  

    public Avis(String type, String note, String nomproduit) {
        this.type = type;
        this.note = note;
        this.nomproduit = nomproduit;
    }
    
    

    
    public Avis(int id, int produit, int user, String type, String note, String nomproduit, String nomuser) {
        this.id = id;
        this.produit = produit;
        this.user = user;
        this.type = type;
        this.note = note;
        this.nomproduit = nomproduit;
        this.nomuser = nomuser;
    }

    public Avis(int produit, int user, String type, String note, String nomproduit, String nomuser) {
        this.produit = produit;
        this.user = user;
        this.type = type;
        this.note = note;
        this.nomproduit = nomproduit;
        this.nomuser = nomuser;
    }

    public Avis(int produit, int user, String type, String note) {
        this.produit = produit;
        this.user = user;
        this.type = type;
        this.note = note;
    }

    public Avis(int id, int produit, int user, String type, String note) {
        this.id = id;
        this.produit = produit;
        this.user = user;
        this.type = type;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
}
