package com.example.myapplication.dao;

public enum roleDAO {
    ADMIN("Chef de Magasin"),
    USER("Chef de Rayon");
    private String str;

    roleDAO(String str){
        this.str = str;
    }


    public String getSring() {
        return str;
    }
}
