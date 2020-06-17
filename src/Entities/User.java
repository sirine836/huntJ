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

    private int id;
    public String nom;;
    private String PROFILE_PHOTO;;
    private String email;
    private String password;
    private String roles;
    private int enabled;
    
    
    
    
    /*-------------------------------------------*/

    public User() {
    }

    public User(int id, String nom, String email, String password, String roles) {
        this.id = id;
        this.nom = nom;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }
    public User( String nom, String email, String password) {
        
        this.nom = nom;
        this.email = email;
        this.password = password;
        this.roles="user";
        }

    public User(int id, String nom, String email, String password, String roles, int enabled) {
        this.id = id;
        this.nom = nom;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.enabled = enabled;
    }

    public User(String FIRST_NAME, String EMAIL, String PASSWORD, String PROFILE_PHOTO) {
        this.nom = FIRST_NAME;
        
        this.email = EMAIL;
        this.password = PASSWORD;
        this.PROFILE_PHOTO = PROFILE_PHOTO;
    }
   
    
    
    /*----------------------------------------------*/

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
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

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public String getPROFILE_PHOTO() {
        return PROFILE_PHOTO;
    }

    public void setPROFILE_PHOTO(String PROFILE_PHOTO) {
        this.PROFILE_PHOTO = PROFILE_PHOTO;
    }
    
    
   
   
    
    /*-------------------------------------------------*/

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", nom=" + nom + ", email=" + email + ", password=" + password + ", roles=" + roles + '}';
    }

    

  
    
    
    
}
