package Model;

public class EntityArticle {

    private int idArticle;
    private String name;
    private int quantity;
    private float price;
    private EntityRayon rayon;

    public EntityArticle(){}

    public EntityArticle(int idArticle, String name, float price, int quantity, EntityRayon rayon){

        this.idArticle = idArticle;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.rayon = rayon;
    }

    public int getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(int idArticle) {
        this.idArticle = idArticle;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }


    public EntityRayon getRayon() {
        return rayon;
    }

    public void setRayon(EntityRayon rayon) {
        this.rayon = rayon;
    }


}
