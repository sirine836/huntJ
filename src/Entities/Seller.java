/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 *
 * @author VENOM
 */
public class Seller {
    public int id;
    public String sellername;
    public String rcs;
    public String taxnumber;
    public int tva;
    public int siren;
    public int fax;
    public int phonenumber;
    public String email;
    public String image;
    private int idUser;
    private String username;
    
    public Seller (){
    }

    public Seller(String sellername, int phonenumber, String email) {
        this.sellername = sellername;
        this.phonenumber = phonenumber;
        this.email = email;
    }
    
    public Seller(int id, String sellername, String rcs, String taxnumber, int tva, int siren, int fax, int phonenumber, String email, String image) {
        this.id = id;
        this.sellername = sellername;
        this.rcs = rcs;
        this.taxnumber = taxnumber;
        this.tva = tva;
        this.siren = siren;
        this.fax = fax;
        this.phonenumber = phonenumber;
        this.email = email;
        this.image = image;
    }

    public Seller(String sellername, String rcs, String taxnumber, int tva, int siren, int fax, int phonenumber, String email, String image) {
        this.sellername = sellername;
        this.rcs = rcs;
        this.taxnumber = taxnumber;
        this.tva = tva;
        this.siren = siren;
        this.fax = fax;
        this.phonenumber = phonenumber;
        this.email = email;
        this.image = image;
    }

    public Seller(String sellername, String rcs, String taxnumber, int tva, int siren, int fax, int phonenumber, String email) {
        this.sellername = sellername;
        this.rcs = rcs;
        this.taxnumber = taxnumber;
        this.tva = tva;
        this.siren = siren;
        this.fax = fax;
        this.phonenumber = phonenumber;
        this.email = email;
    }

    public Seller(int id, String sellername, String rcs, String taxnumber, int tva, int siren, int fax, int phonenumber, String email) {
        this.id = id;
        this.sellername = sellername;
        this.rcs = rcs;
        this.taxnumber = taxnumber;
        this.tva = tva;
        this.siren = siren;
        this.fax = fax;
        this.phonenumber = phonenumber;
        this.email = email;
    }

    public Seller(int id, String sellername, String rcs, String taxnumber, int tva, int siren, int fax, int phonenumber, String email, String image, String username) {
        this.id = id;
        this.sellername = sellername;
        this.rcs = rcs;
        this.taxnumber = taxnumber;
        this.tva = tva;
        this.siren = siren;
        this.fax = fax;
        this.phonenumber = phonenumber;
        this.email = email;
        this.image = image;
        this.username = username;
    }

    public Seller(int id, String sellername, String rcs, String taxnumber, int tva, int siren, int fax, int phonenumber, String email, String image, int idUser, String username) {
        this.id = id;
        this.sellername = sellername;
        this.rcs = rcs;
        this.taxnumber = taxnumber;
        this.tva = tva;
        this.siren = siren;
        this.fax = fax;
        this.phonenumber = phonenumber;
        this.email = email;
        this.image = image;
        this.idUser = idUser;
        this.username = username;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSellername() {
        return sellername;
    }

    public void setSellername(String sellername) {
        this.sellername = sellername;
    }

    public String getRcs() {
        return rcs;
    }

    public void setRcs(String rcs) {
        this.rcs = rcs;
    }

    public String getTaxnumber() {
        return taxnumber;
    }

    public void setTaxnumber(String taxnumber) {
        this.taxnumber = taxnumber;
    }

    public int getTva() {
        return tva;
    }

    public void setTva(int tva) {
        this.tva = tva;
    }

    public int getSiren() {
        return siren;
    }

    public void setSiren(int siren) {
        this.siren = siren;
    }

    public int getFax() {
        return fax;
    }

    public void setFax(int fax) {
        this.fax = fax;
    }

    public int getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(int phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Seller{" + "id=" + id + ", sellername=" + sellername + ", rcs=" + rcs + ", taxnumber=" + taxnumber + ", tva=" + tva + ", siren=" + siren + ", fax=" + fax + ", phonenumber=" + phonenumber + ", email=" + email + ", image=" + image + ", username=" + username + '}';
    }

   
    
    
}
