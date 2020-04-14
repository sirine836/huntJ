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
public class Product {
    
    private int id;
    private String nompr;
    private double prix;

    public Product(int id, String nompr, double prix) {
        this.id = id;
        this.nompr = nompr;
        this.prix = prix;
    }

    public Product(int id, String nompr) {
        this.id = id;
        this.nompr = nompr;
    }
    

    
    public Product(String nompr, double prix) {
        this.nompr = nompr;
        this.prix = prix;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNompr() {
        return nompr;
    }

    public void setNompr(String nompr) {
        this.nompr = nompr;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }
   
    
    
}
