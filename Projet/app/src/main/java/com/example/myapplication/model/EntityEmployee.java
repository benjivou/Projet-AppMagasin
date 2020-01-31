package com.example.myapplication.model;

import com.example.myapplication.dao.RoleDAO;

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
    private RoleDAO role;
    /**
     * The employee password
     */
    private String password;


    /**
     * The employee aisle
     */
    private EntityAisle entityAisle;

    /**
     * The copy constructor
     * @param idEmployee : String
     * @param name : String
     * @param sex : String
     * @param password : String
     * @param role : String
     */
    public EntityEmployee(String idEmployee, String name, String sex, String password, String role, EntityAisle entityAisle) {
        this.idEmployee = idEmployee;
        this.name = name;
        this.sex = sex;
        this.password = password;
        this.role = RoleDAO.convertRole(role);
        this.entityAisle = entityAisle;
    }

    public EntityEmployee(String idEmployee, String name, String sex, String password, RoleDAO role, EntityAisle entityAisle) {
        this.idEmployee = idEmployee;
        this.name = name;
        this.sex = sex;
        this.password = password;
        this.role = role;
        this.entityAisle = entityAisle;
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

    @Override
    public String toString() {
        return getName();
    }

    public EntityAisle getEntityAisle() {
        return entityAisle;
    }

    public void setEntityAisle(EntityAisle entityAisle) {
        this.entityAisle = entityAisle;
    }

    public String getRole() {
        return role.getSring();
    }

    public void setRole(String role) {
        this.role = RoleDAO.convertRole(role) ;
    }
    public RoleDAO getRoleEnum(){
        return this.role;
    }




}
