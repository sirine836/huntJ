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
public class Evaluations {

    private int id;
    private double note;
    private String commentaire;
    private int event_id;
    private int user_id;
    private String titre;
    private String user_nom;

    public Evaluations() {
    }

    public Evaluations(double note) {
        this.note = note;
    }
    
    

    public Evaluations(int id, double note, String commentaire, int event_id, int user_id) {
        this.id = id;
        this.note = note;
        this.commentaire = commentaire;
        this.event_id = event_id;
        this.user_id = user_id;
    }

    public Evaluations( int event_id, double note, String commentaire) {
        this.note = note;
        this.commentaire = commentaire;
        this.event_id = event_id;
    }

    public Evaluations(int event_id,double note, String commentaire,  int user_id) {
        this.note = note;
        this.commentaire = commentaire;
        this.event_id = event_id;
        this.user_id = user_id;
    }
    
    
    
    

    public Evaluations(double note, String commentaire, String titre) {
        this.note = note;
        this.commentaire = commentaire;
        this.titre = titre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getNote() {
        return note;
    }

    public void setNote(double note) {
        this.note = note;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public int getEvent_id() {
        return event_id;
    }

    public void setEvent_id(int event_id) {
        this.event_id = event_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }


    public String getUser_nom() {
        return user_nom;
    }

    public void setUser_nom(String user_nom) {
        this.user_nom = user_nom;
    }
    
    
     @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + this.id;
        return hash;
    }
    
     @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Evaluations other = (Evaluations) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    @Override
    public String toString() {
        return "Evaluations{" + "note=" + note + ", commentaire=" + commentaire + ", titre=" + titre + '}';
    }

   

   

   

}
