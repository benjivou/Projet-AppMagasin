package com.example.myapplication.model;

import static com.example.myapplication.config.ConfigFront.DEFAULT_PASSWORD;

/**
 * This class represent a employee
 * @<version 1.0
 * @author Leslie Kiav & Benjamin Vouillon
 */
public class EntityEmployee{

    /**
     * The employee id
     */
    private String idEmployee;
    /**
     * The employee name
     */
    private String name;
    /**
     * The employee sex
     */
    private String sex;
    /**
     * The employee role in the store
     */
    private String role;
    /**
     * The employee password
     */
    private String password;

    /**
     * The copy constructor
     * @param idEmployee : String
     * @param name : String
     * @param sex : String
     * @param password : String
     * @param role : String
     */
    public EntityEmployee(String idEmployee, String name, String sex, String password, String role) {
        this.idEmployee = idEmployee;
        this.name = name;
        this.sex = sex;
        this.password = password;
        this.role = role;
    }

    /**
     * This is the default constructor
     */
    public EntityEmployee(){}

    /**
     * The copy constructor
     * @param idEmployee
     * @param name
     * @param sex
     * @param role
     */
    public EntityEmployee(String idEmployee, String name, String sex,  String role){
        this.idEmployee = idEmployee;
        this.name = name;
        this.sex = sex;
        this.password = DEFAULT_PASSWORD;
        this.role = role;
    }

    /*Getters  & Setters*/

    public String getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(String idEmployee) {
        this.idEmployee = idEmployee;
    }

    public String getName() {
        return name;
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


    public void setRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    @Override
    public String toString() {
        return getName();
    }
}
