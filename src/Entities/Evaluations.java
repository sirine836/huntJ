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
    private int note;
    private String commentaire;

    public Evaluations() {
    }

    public Evaluations(int id, int note, String commentaire) {
        this.id = id;
        this.note = note;
        this.commentaire = commentaire;
    }


    public Evaluations(int note, String commentaire) {
        this.note = note;
        this.commentaire = commentaire;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

   

    @Override
    public String toString() {
        return "Evaluations{" + "note=" + note + ", commentaire=" + commentaire +'}';
    }
    
    
    
}
