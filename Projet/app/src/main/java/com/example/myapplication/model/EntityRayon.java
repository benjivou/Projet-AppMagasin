package com.example.myapplication.model;

import android.app.SearchableInfo;

import java.util.ArrayList;

public class EntityRayon extends SearchebleImplement<Integer> {

    private int idRayon;
    private String name;
    private EntityEmployee employee;
    private ArrayList<EntityArticle> listArticle;


    public EntityRayon(){}

    public EntityRayon(int idRayon, String name){
        this.idRayon = idRayon;
        this.name = name;
        this.listArticle = new  ArrayList<EntityArticle>();
    }

    public EntityRayon(int idRayon, String name, EntityEmployee employee, ArrayList<EntityArticle> listArticle){
        this.idRayon = idRayon;
        this.name = name;
        this.employee = employee;
        this.listArticle = new  ArrayList<EntityArticle>();
        this.listArticle = listArticle;

    }

    /*Getters & Setters*/
    public int getIdRayon() {
        return idRayon;
    }

    public void setIdRayon(int idRayon) {
        this.idRayon = idRayon;
    }

    public String getName() {
        return name;
    }

    @Override
    public Integer getUniqueKey() {
        return this.idRayon;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EntityEmployee getEmployee() {
        return employee;
    }

    public void setEmployee(EntityEmployee employee) {
        this.employee = employee;
    }


    public ArrayList<EntityArticle> getListArticle() {
        return listArticle;
    }

    public void setListArticle(ArrayList<EntityArticle> listArticle) {
        this.listArticle = listArticle;
    }


}
