/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author cyrine
 */
public class User {
    
     private int idu;
    private String nom, prenom,email,password,last_login,roles;
    
    
    
    /*-------------------------------------------*/

    public User() {
    }

    public User(int idu, String nom, String prenom, String email, String password, String last_login, String roles) {
        this.idu = idu;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.last_login = last_login;
        this.roles = roles;
    }

   
    
    
    /*----------------------------------------------*/

    public int getIdu() {
        return idu;
    }

    public void setIdu(int idu) {
        this.idu = idu;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLast_login() {
        return last_login;
    }

    public void setLast_login(String last_login) {
        this.last_login = last_login;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }
    
    
    
    /*-------------------------------------------------*/

    @Override
    public String toString() {
        return "User{" + "idu=" + idu + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", password=" + password + ", last_login=" + last_login + ", roles=" + roles + '}';
    }

  
    
    
    
}
