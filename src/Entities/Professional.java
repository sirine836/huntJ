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

    public Professional()
    {

    }
    public Professional(int id, String proname, String prosurname, String email, String softskills, String image) {
        this.id = id;
        this.proname = proname;
        this.prosurname = prosurname;
        this.email = email;
        this.softskills = softskills;
        this.image = image;
    }

    public Professional(String proname, String prosurname, String email, String softskills, String image) {
        this.proname = proname;
        this.prosurname = prosurname;
        this.email = email;
        this.softskills = softskills;
        this.image = image;
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
        return "Candidate{" + "id=" + id + ", Last Name=" + proname + ", First Name=" + prosurname + ", Email=" + email + ", Softskills=" + softskills + ", CV =" + image + '}';
    }

}