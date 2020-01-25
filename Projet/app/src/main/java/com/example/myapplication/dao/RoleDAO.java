package com.example.myapplication.dao;

/**
 * This class represent a role
 * @<version 1.0
 * @author Leslie Kiav & Benjamin Vouillon
 */
public enum RoleDAO {
    ADMIN("Chef de Magasin"),
    USER("Chef de Rayon");
    private String str;

    RoleDAO(String str){
        this.str = str;
    }


    public String getSring() {
        return str;
    }
}
