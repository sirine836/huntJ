/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;


/**
 *
 * @author h^
 */
public class Events {

    public int id;
    private String titre;
    private String description;
    private String date;
    public int nbrPlaces;
    private double prix;
    private String localisation;
    private int idPro;
    private String Image;

    public Events(int id, String titre, String description, int nbrPlaces, double prix, String localisation) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.nbrPlaces = nbrPlaces;
        this.prix = prix;
        this.localisation = localisation;
    }
    
    
    
    
    
    public Events() {
    }

    public Events(String titre, String description, String date, int nbrPlaces, double prix, String localisation, int idPro) {
        this.titre = titre;
        this.description = description;
        this.date = date;
        this.nbrPlaces = nbrPlaces;
        this.prix = prix;
        this.localisation = localisation;
        this.idPro = 1;
    }
    
    public Events (String titre) {
        this.titre = titre;
    }

    public Events(int id, int nbrPlaces) {
        
        this.nbrPlaces=nbrPlaces;
        this.id = id;
    }
    
    
    

    public Events(int id, String titre, String description, String date, int nbrPlaces, double prix, String localisation, String Image) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.date = date;
        this.nbrPlaces = nbrPlaces;
        this.prix = prix;
        this.localisation = localisation;
        this.Image = Image;
    }

    public Events(String titre, String description, String date, int nbrPlaces, double prix, String localisation, int idPro, String Image) {
        this.titre = titre;
        this.description = description;
        this.date = date;
        this.nbrPlaces = nbrPlaces;
        this.prix = prix;
        this.localisation = localisation;
        this.idPro = idPro;
        this.Image = Image;
    }

    public Events(int id, String titre, String description, String date, int nbrPlaces, double prix, String localisation, int idPro, String Image) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.date = date;
        this.nbrPlaces = nbrPlaces;
        this.prix = prix;
        this.localisation = localisation;
        this.idPro = idPro;
        this.Image = Image;
    }
    
    

    
    public Events(int id ,String titre, String description, double prix, String date, int nbrPlaces, String Image) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.date = date;
        this.prix = prix;
        this.nbrPlaces = nbrPlaces;
        this.Image = Image;
    }

    public Events(String titre, String description, String date, int nbrPlaces, double prix, String localisation, String Image) {
        this.titre = titre;
        this.description = description;
        this.date = date;
        this.nbrPlaces = nbrPlaces;
        this.prix = prix;
        this.localisation = localisation;
        this.Image = Image;
    }

    public Events(String titre, String description, String date, int nbrPlaces, double prix, String localisation) {
        this.titre = titre;
        this.description = description;
        this.date = date;
        this.nbrPlaces = nbrPlaces;
        this.prix = prix;
        this.localisation = localisation;
    }

    public Events(int id ,String titre, String description) {
        this.id = id;
        this.titre = titre;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getNbrPlaces() {
        return nbrPlaces;
    }

    public void setNbrPlaces(int nbrPlaces) {
        this.nbrPlaces = nbrPlaces;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }
    
    public String getImage() {
        return Image;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }

    public int getIdPro() {
        return idPro;
    }

    public void setIdPro(int idPro) {
        this.idPro = idPro;
    }
    
    

    @Override
    public String toString() {
        return   titre  ;
    }
    

}
