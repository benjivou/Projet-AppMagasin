package com.example.myapplication.model;


import androidx.annotation.NonNull;

/**
 * This class represent a article
 * @<version 1.0
 * @author Leslie Kiav & Benjamin Vouillon
 */
public class EntityArticle{

    /**
     * The article id
     */
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
     * The Aisle Name of the article
     */
    private String aisleName;

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
     * @param aisleName : String
     */
    public EntityArticle(int idArticle, String name, float price, int quantity,String aisleName ){

        this.idArticle = idArticle;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.aisleName = aisleName;
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

    @NonNull
    @Override
    public String toString() {
        return getName();
    }

    public String getAisleName() {
        return this.aisleName;
    }
}
