package com.example.myapplication.model;

public class EntityRole {

    private int idRole;
    private String name;

    public EntityRole(){}

    public EntityRole(int idRole, String name){
        this.idRole = idRole;
        this.name = name;
    }

    public int getIdRole() {
        return idRole;
    }

    public void setIdRole(int idRole) {
        this.idRole = idRole;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



}
