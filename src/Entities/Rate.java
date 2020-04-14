/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author youss
 */
public class Rate {
      private int id ;
    private String rate;

    public Rate(String rate) {
        this.rate = rate;
    }

    public Rate(int id, String rate) {
        this.id = id;
        this.rate = rate;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }
    
    
}
