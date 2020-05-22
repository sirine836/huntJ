/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Objects;

/**
 *
 * @author VENOM
 */
public class proposition {
    private int id ;
    public int idQuestion;
    private  String name;
    private int state;
    String wording;

    public proposition() {
    }

    
    public proposition(int id, String name, int state, String wording, int idQuestion) {
        this.id = id;
        this.name = name;
        this.state = state;
        this.wording = wording;
        this.idQuestion =idQuestion;
    }

    public proposition(int id, int idQuestion, String name, int state) {
        this.id = id;
        this.idQuestion = idQuestion;
        this.name = name;
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdQuestion() {
        return idQuestion;
    }

    public void setIdQuestion(int idQuestion) {
        this.idQuestion = idQuestion;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getWording() {
        return wording;
    }

    public void setWording(String wording) {
        this.wording = wording;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + this.id;
        hash = 17 * hash + this.idQuestion;
        hash = 17 * hash + Objects.hashCode(this.name);
        hash = 17 * hash + this.state;
        hash = 17 * hash + Objects.hashCode(this.wording);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final proposition other = (proposition) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.idQuestion != other.idQuestion) {
            return false;
        }
        if (this.state != other.state) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.wording, other.wording)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "proposition{" + "id=" + id + ", idQuestion=" + idQuestion + ", name=" + name + ", state=" + state + ", wording=" + wording + '}';
    }
    
    
    
    
}
