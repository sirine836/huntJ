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
public class Reclamation {
    private int id;
    private int produit;
    private int user;
    private int etat=0;
    private String probleme;
    private String nomproduit;
    private String nomuser;
    private String nometat;

    
    
    
    public Reclamation(int id, int produit, String probleme, String nomproduit) {
        this.id = id;
        this.produit = produit;
        this.probleme = probleme;
    
        this.nomproduit = nomproduit;
    }

    public Reclamation(int produit, String probleme, String nomproduit) {
        this.produit = produit;
        this.probleme = probleme;
        this.nomproduit = nomproduit;
    }

    public Reclamation(int produit, String probleme) {
        this.produit = produit;
        this.probleme = probleme;
    }
    
    public Reclamation(int id, int produit, int user, String probleme, String nomproduit, String nomuser, String nometat) {
        this.id = id;
        this.produit = produit;
        this.user = user;
        this.probleme = probleme;
        this.nomproduit = nomproduit;
        this.nomuser = nomuser;
        this.nometat = nometat;
    }
    

    public Reclamation(int id, int produit, int user, int etat, String probleme, String nomproduit, String nomuser, String nometat) {
        this.id = id;
        this.produit = produit;
        this.user = user;
        this.etat = etat;
        this.probleme = probleme;
        this.nomproduit = nomproduit;
        this.nomuser = nomuser;
        this.nometat = nometat;
    }

    
    public Reclamation(int id, int produit, int user, int etat, String probleme) {
        this.id = id;
        this.produit = produit;
        this.user = user;
        this.etat = etat;
        this.probleme = probleme;
    
    }

    public Reclamation(int produit, int user, int etat, String probleme) {
        this.produit = produit;
        this.user = user;
        this.etat = etat;
        this.probleme = probleme;
      
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

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public String getProbleme() {
        return probleme;
    }

    public void setProbleme(String probleme) {
        this.probleme = probleme;
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

    public String getNometat() {
        return nometat;
    }

    public void setNometat(String nometat) {
        this.nometat = nometat;
    }
    
    
}
