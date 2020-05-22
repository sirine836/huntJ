
package Entities;

import java.util.Objects;

/**
 *
 * @author MONDHER
 */
public class Category {
    private int id;
    private String nomcat;
    private String souscat;

    public Category() {
    }

    public Category(String nomcat, String souscat) {
        this.nomcat = nomcat;
        this.souscat = souscat;
    }
    
    public Category(int id, String nomcat, String souscat) {
        this.id = id;
        this.nomcat = nomcat;
        this.souscat = souscat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomcat() {
        return nomcat;
    }

    public void setNomcat(String nomcat) {
        this.nomcat = nomcat;
    }

    public String getSouscat() {
        return souscat;
    }

    public void setSouscat(String souscat) {
        this.souscat = souscat;
    }

    @Override
    public String toString() {
        return "Category{" + "id=" + id + ", nomcat=" + nomcat + ", souscat=" + souscat + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + this.id;
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
        final Category other = (Category) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.nomcat, other.nomcat)) {
            return false;
        }
        return true;
    }
}
