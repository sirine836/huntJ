/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;


/**
 *
 * @author VENOM
 */
public class Professional {
    
private int id;
private String proname;
private String prosurname;
private String email;
private String softskills;
private String image; 
private int idUser;
private String username;

    public Professional()
    {

    }

    public Professional(int id, String proname, String prosurname, String email, String softskills, String image, int idUser , String username) {
        this.id = id;
        this.proname = proname;
        this.prosurname = prosurname;
        this.email = email;
        this.softskills = softskills;
        this.image = image;
        this.idUser = idUser;
        this.username = username;
    }

    public Professional(String proname, String prosurname, String email, String softskills, String image, int idUser, String username) {
        this.proname = proname;
        this.prosurname = prosurname;
        this.email = email;
        this.softskills = softskills;
        this.image = image;
        this.idUser = idUser;
        this.username = username;

    }

    public Professional(String proname, String prosurname, String email, String softskills, String image) {
        this.proname = proname;
        this.prosurname = prosurname;
        this.email = email;
        this.softskills = softskills;
        this.image = image;
    }

    public Professional(int id, String proname, String prosurname, String email, String softskills, String image) {
        this.id = id;
        this.proname = proname;
        this.prosurname = prosurname;
        this.email = email;
        this.softskills = softskills;
        this.image = image;
    }

    public Professional(int id, String proname, String prosurname, String email, String softskills) {
        this.id = id;
        this.proname = proname;
        this.prosurname = prosurname;
        this.email = email;
        this.softskills = softskills;
    }

    public Professional(int id, String proname, String prosurname, String email, String softskills, String image, String username) {
        this.id = id;
        this.proname = proname;
        this.prosurname = prosurname;
        this.email = email;
        this.softskills = softskills;
        this.image = image;
        this.username = username;
    }
    
    
    

 
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
   
    public Professional(String email) {
        this.email = email;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProname() {
        return proname;
    }

    public void setProname(String proname) {
        this.proname = proname;
    }

    public String getProsurname() {
        return prosurname;
    }

    public void setProsurname(String prosurname) {
        this.prosurname = prosurname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSoftskills() {
        return softskills;
    }

    public void setSoftskills(String softskills) {
        this.softskills = softskills;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Professional{" + "id=" + id + ", proname=" + proname + ", prosurname=" + prosurname + ", email=" + email + ", softskills=" + softskills + ", image=" + image + ", username=" + username + '}';
    }
  
    

}