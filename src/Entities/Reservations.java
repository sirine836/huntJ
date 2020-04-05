/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author h^
 */
public class Reservations {
    
    private int id;
    private int quantite;
    private float total_prix;
    private String nom;
    private int Event_id;
    

    public Reservations() {
    }

    public Reservations(int id, int quantite, float total_prix, String nom, int Event_id) {
        this.id = id;
        this.quantite = quantite;
        this.total_prix = total_prix;
        this.nom = nom;
        this.Event_id = Event_id;
    }

    public Reservations(int quantite, float total_prix, String nom) {
        this.quantite = quantite;
        this.total_prix = total_prix;
        this.nom = nom;
    }

    public Reservations(int quantite, float total_prix, int Event_id) {
        this.quantite = quantite;
        this.total_prix = total_prix;
        this.Event_id = Event_id;
    }

    public Reservations(int quantite, float total_prix, String nom, int Event_id) {
        this.quantite = quantite;
        this.total_prix = total_prix;
        this.nom = nom;
        this.Event_id = Event_id;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public float getTotal_prix() {
        return total_prix;
    }

    public void setTotal_prix(float total_prix) {
        this.total_prix = total_prix;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getEvent_id() {
        return Event_id;
    }

    public void setEvent_id(int Event_id) {
        this.Event_id = Event_id;
    }

    @Override
    public String toString() {
        return "Reservations{" + "id=" + id + ", quantite=" + quantite + ", total_prix=" + total_prix + ", nom=" + nom + ", Event_id=" + Event_id + '}';
    }
    
    
    
}
