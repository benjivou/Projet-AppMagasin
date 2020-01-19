package com.example.myapplication.model;

<<<<<<< HEAD
public class EntityArticle extends SearchebleImplement<Integer> {

=======
/**
 * This class represent a article
 * @<version 1.0
 * @author Leslie Kiav & Benjamin Vouillon
 */
public class EntityArticle{

    /**
     * The article id
     */
>>>>>>> Leslie
    private int idArticle;
    /**
     * The article name
     */
    private String name;
    /**
     * The article quantity
     */
    private int quantity;
    /**
     * The article price
     */
    private float price;

    /**
     * This is the default constructor
     */
    public EntityArticle(){}

    /**
     * The copy constructor
     * @param idArticle : int
     * @param name : String
     * @param price : float
     * @param quantity : int
     */
    public EntityArticle(int idArticle, String name, float price, int quantity){

        this.idArticle = idArticle;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    /*Getters & Setters*/
    public int getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(int idArticle) {
        this.idArticle = idArticle;
    }

    public String getName() {
        return name;
    }

<<<<<<< HEAD
    @Override
    public Integer getUniqueKey() {
        return this.idArticle;
    }

=======
>>>>>>> Leslie
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



}
