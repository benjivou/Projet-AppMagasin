package com.example.myapplication.model;

import java.util.ArrayList;
import java.util.Objects;


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





    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EntityAisle that = (EntityAisle) o;
        return idAisle == that.idAisle;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAisle);
    }
}
