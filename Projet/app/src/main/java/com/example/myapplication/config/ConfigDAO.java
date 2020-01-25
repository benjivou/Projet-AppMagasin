package com.example.myapplication.config;

public class ConfigDAO {
    public static final String DB = "DB";
    public static final String AISLE = "Aisle.txt";
    public static final String EMPLOYEE ="Employee.txt";
    public static final String ARTICLE = "Article.txt";


    public static final String TABLE_EMPLOYEE = "employee" ;
    public static final String COLUMN_EMPLOYEE_ID = "idEmployee";
    public static final String COLUMN_EMPLOYEE_NAME = "name";
    public static final String COLUMN_EMPLOYEE_SEX = "sex";
    public static final String COLUMN_EMPLOYEE_PASSWORD = "password";
    public static final String COLUMN_EMPLOYEE_ROLE = "role";
    public static final String COLUMN_EMPLOYEE_AISLE_ID= "idAisleEmployee";



    public static final String TABLE_ARTICLE= "article" ;
    public static final String COLUMN_ARTICLE_ID = "idArticle" ;
    public static final String COLUMN_ARTICLE_NAME = "name";
    public static final String COLUMN_ARTICLE_QUANTITY= "quantity";
    public static final String COLUMN_ARTICLE_PRICE= "price";
    public static final String COLUMN_AISLE_ID= "idAisleArticle";

    public static final String TABLE_AISLE = "rayon" ;
    public static final String COLUMN_RAYON_ID = "idRayon" ;
    public static final String COLUMN_RAYON_NAME = "name";
    public static final String COLUMN_RAYON_EMPLOYEE= "employee";


}
