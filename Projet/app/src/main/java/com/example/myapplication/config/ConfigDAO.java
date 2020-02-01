package com.example.myapplication.config;

/**
 * Store all DB config Variable
 */
public class ConfigDAO {
    public static final String DB = "DB";


    /*Table Employee*/
    public static final String TABLE_EMPLOYEE = "employee" ;
    public static final String COLUMN_EMPLOYEE_ID = "idEmployee";
    public static final String COLUMN_EMPLOYEE_NAME = "name";
    public static final String COLUMN_EMPLOYEE_SEX = "sex";
    public static final String COLUMN_EMPLOYEE_PASSWORD = "password";
    public static final String COLUMN_EMPLOYEE_ROLE = "role";
    public static final String COLUMN_EMPLOYEE_AISLE_ID= "idAisleEmployee";


    /*Table Article*/
    public static final String TABLE_ARTICLE= "article" ;
    public static final String COLUMN_ARTICLE_ID = "idArticle" ;
    public static final String COLUMN_ARTICLE_NAME = "name";
    public static final String COLUMN_ARTICLE_QUANTITY= "quantity";
    public static final String COLUMN_ARTICLE_PRICE= "price";
    public static final String COLUMN_ARTICLE_AISLE_ID = "idAisleArticle";

    /*Table Aisle*/
    public static final String TABLE_AISLE = "rayon" ;
    public static final String COLUMN_RAYON_ID = "idRayon" ;
    public static final String COLUMN_RAYON_NAME = "name";


}
