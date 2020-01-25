package com.example.myapplication.model;

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

    private int idAisle;
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
     * @param idAisle : int
     * @param name : String
     */
    public EntityAisle(int idAisle, String name){
        this.idAisle = idAisle;
        this.name = name;
        this.listArticle = new  ArrayList<EntityArticle>();
    }

    /**
     * The copy constructor
     * @param idAisle : int
     * @param name : String
     * @param employee : EntityEnmployee
     * @param listArticle : EntityArticle
     */
    public EntityAisle(int idAisle, String name, EntityEmployee employee, ArrayList<EntityArticle> listArticle){
        this.idAisle = idAisle;
        this.name = name;
        this.employee = employee;
        this.listArticle = new  ArrayList<EntityArticle>();
        this.listArticle = listArticle;

    }

    /*Getters & Setters*/
    public int getIdAisle() {
        return idAisle;
    }

    public void setIdAisle(int idAisle) {
        this.idAisle = idAisle;
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
