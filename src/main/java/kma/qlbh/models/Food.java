package kma.qlbh.models;

/**
 * @createAt Dec 2, 2020
 * @author Tran Duc Cuong<clonebmn2itt@gmail.com>
 */
public class Food {

    protected int id, unitPrice, idCategory;
    protected String name, description, urlImage, unitName;

    public Food() {
    }

    public Food(int id, String name, String description, String urlImage, String unitName, int unitPrice, int idCategory) {
        this.id = id;
        this.unitPrice = unitPrice;
        this.idCategory = idCategory;
        this.name = name;
        this.description = description;
        this.urlImage = urlImage;
        this.unitName = unitName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    @Override
    public String toString() {
        return "Food{" + "id=" + id + ", unitPrice=" + unitPrice + ", idCategory=" + idCategory + ", name=" + name + ", description=" + description + ", urlImage=" + urlImage + ", unitName=" + unitName + '}';
    }

}
