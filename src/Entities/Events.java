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

    private int id;
    private String titre;
    private String description;
    private Date date;
    private int nbrPlaces;
    private double prix;
    private String localisation;
    private String nom_image;
    private Set<Reservations> listReservation = new HashSet<Reservations>();

    public Events() {
    }

    public Events(int id, String titre, String description, Date date, int nbrPlaces, double prix, String localisation, String nom_image) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.date = date;
        this.nbrPlaces = nbrPlaces;
        this.prix = prix;
        this.localisation = localisation;
        this.nom_image = nom_image;
    }

    public Events(String titre, String description, Date date, int nbrPlaces, double prix, String localisation, String nom_image, boolean accesGratuit) {
        this.titre = titre;
        this.description = description;
        this.date = date;
        this.nbrPlaces = nbrPlaces;
        this.prix = prix;
        this.localisation = localisation;
        this.nom_image = nom_image;
    }

    public Events(String titre, String description, Date date, int nbrPlaces, double prix, String localisation) {
        this.titre = titre;
        this.description = description;
        this.date = date;
        this.nbrPlaces = nbrPlaces;
        this.prix = prix;
        this.localisation = localisation;
    }

    public Events(String titre, String description) {
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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

    public String getNom_image() {
        return nom_image;
    }

    public void setNom_image(String nom_image) {
        this.nom_image = nom_image;
    }
    
    public Set<Reservations> getListReservation() {
    return listReservation;
  }

  public void setListReservation(Set<Reservations> listReservation) {
    this.listReservation= listReservation;
  }

  public void addReservation(Reservations reservation){
    this.listReservation.add(reservation);
  }

  public void removeReservation(Reservations reservation){
    this.listReservation.remove(reservation);
  }

    @Override
    public String toString() {
        return "Events{" + "titre=" + titre + ", description=" + description + '}';
    }

}
