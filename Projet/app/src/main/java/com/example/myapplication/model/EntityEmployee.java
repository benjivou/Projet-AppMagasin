package com.example.myapplication.model;

import static com.example.myapplication.config.ConfigFront.DEFAULT_PASSWORD;

public class EntityEmployee extends SearchebleImplement{

    private int idEmployee;
    private String name;
    private String sex;

    public EntityEmployee(int idEmployee, String name, String sex, String password, String role) {
        this.idEmployee = idEmployee;
        this.name = name;
        this.sex = sex;
        this.password = password;
        this.role = role;
    }

    private String password;

    //private EntityRole role;
    private String role;

    public EntityEmployee(){}

    public EntityEmployee(int idEmployee, String name, String sex,  String role){
        this.idEmployee = idEmployee;
        this.name = name;
        this.sex = sex;
        this.password = DEFAULT_PASSWORD;
        this.role = role;
    }



    /*Getters  & Setters*/
    public int getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(int idEmployee) {
        this.idEmployee = idEmployee;
    }

    public String getName() {
        return name;
    }

    @Override
    public int getUniqueKey() {
        return this.idEmployee;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /*public EntityRole getRole() {
        return role;
    }

    public void setRole(EntityRole role) {
        this.role = role;
    }*/


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
