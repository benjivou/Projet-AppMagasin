package com.example.myapplication.model;

import android.app.SearchableInfo;

import java.util.ArrayList;

/**
 * This class represent a aisle
 * @<version 1.0
 * @author Leslie Kiav & Benjamin Vouillon
 */
public class EntityAisle {

    /**
     * The aisle id
     */
    private int idRayon;
    /**
     * The aisle name
     */
    private String name;
    /**
     * The employee responsible of the aisle
     */
    private EntityEmployee employee;
    /**
     * The article list
     */
    private ArrayList<EntityArticle> listArticle;

    /**
     * This is the default constructor
     */
    public EntityAisle(){}

    /**
     * The copy constructor
     * @param idRayon : int
     * @param name : String
     */
    public EntityAisle(int idRayon, String name){
        this.idRayon = idRayon;
        this.name = name;
        this.listArticle = new  ArrayList<EntityArticle>();
    }

    /**
     * The copy constructor
     * @param idRayon : int
     * @param name : String
     * @param employee : EntityEnmployee
     * @param listArticle : EntityArticle
     */
    public EntityAisle(int idRayon, String name, EntityEmployee employee, ArrayList<EntityArticle> listArticle){
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
