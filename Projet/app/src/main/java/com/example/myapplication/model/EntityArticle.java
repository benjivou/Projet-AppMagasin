package com.example.myapplication.model;

public class EntityArticle extends SearchebleImplement {

    private int idArticle;
    private String name;
    private int quantity;
    private float price;

    public EntityArticle(){}

    public EntityArticle(int idArticle, String name, float price, int quantity){

        this.idArticle = idArticle;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
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

    @Override
    public int getUniqueKey() {
        return this.idArticle;
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



}
