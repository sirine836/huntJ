
package Entities;

/**
 *
 * @author MONDHER
 */
public class Product {
    private int id;
    private String nompr;
    private String quantity;
    private String descrip;
    private String prix;
    private String image;
    private String idCategory;
    private String namecat;

    public Product() {
    }

    public Product(String nompr, String quantity, String descrip, String prix, String image, String idCategory) {
        this.nompr = nompr;
        this.quantity = quantity;
        this.descrip = descrip;
        this.prix = prix;
        this.image = image;
        this.idCategory= idCategory;
    }

    public Product(int id, String nompr, String quantity, String descrip, String prix, String image,String namecat) {
        this.id = id;
        this.nompr = nompr;
        this.quantity = quantity;
        this.descrip = descrip;
        this.prix = prix;
        this.image = image;
        this.namecat = namecat;
        //this.idCategory = idCategory;
    }

    public String getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(String idCategory) {
        this.idCategory = idCategory;
    }
    
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getNamecat() {
        return namecat;
    }

    public void setNamecat(String namecat) {
        this.namecat = namecat;
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

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    public String getPrix() {
        return prix;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", nompr=" + nompr + ", quantity=" + quantity + ", descrip=" + descrip + ", prix=" + prix + ", image=" + image + ", idCategory=" + idCategory + ", namecat=" + namecat + '}';
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + this.id;
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
        final Product other = (Product) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
      
}
