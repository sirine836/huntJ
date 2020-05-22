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
public class questions {
    
   
    private int id;
    private String wording;

    public questions() {
    }

    public questions(int id, String wording) {
        this.id = id;
        this.wording = wording;
    }

    public questions(String wording) {
        this.wording = wording;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWording() {
        return wording;
    }

    public void setWording(String wording) {
        this.wording = wording;
    }

    @Override
    public String toString() {
        return wording;
    }

   
    
}
