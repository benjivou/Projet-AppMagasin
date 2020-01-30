package com.example.myapplication.config;

import com.example.myapplication.dao.RoleDAO;
import com.example.myapplication.model.EntityAisle;

public class ConfigFront {

    public static final int DURATION_ERROR_MESSAGES  = 1; // 3 *2 sec long

    // Error messages
    public static final String ERROR_CREATION_PASSWORD_MISSING_MAJ="Il faut 1 Majuscule";
    public static final String ERROR_CREATION_PASSWORD_MISSING_MIN="Il faut 1 minuscule";
    public static final String ERROR_CREATION_PASSWORD_MISSING_SPECIAL="Il faut 1 caractère autre qu’une lettre de l’alphabet";
    public static final String ERROR_CREATION_PASSWORD_LENGTH_PROBLEM="Il faut entre 8 et 20 caractères";
    public static final String ERROR_CREATION_PASSWORD_CORRESPONDANCE_PROBLEM="les 2 mots de passe doivent correspondre";

    public static final String DEFAULT_PASSWORD = "root";                    // Default password
    public static final String ERROR_BAD_PAIR_LOGIN_MDP = "Mauvaise combinaison login/mdp";


    public static final String  USERNAME_SESSION = " Username";
    public static final RoleDAO SYSTEM_ROLE = RoleDAO.ADMIN;
    public static final EntityAisle SYSTEM_AISLE = new EntityAisle() ;

    public static final String ERROR_USERFIELD_NAME = "Nom à définir ";
    public static final String ERROR_USERFIELD_ID = "Matricule à définir ";
    public static final String ERROR_PRODUCTFIELD_QUANTITY = "Quantité négative ";
    public static final String ERROR_PRODUCTFIELD_PRIX = "Prix négatif";
    public static final String ERROR_FIELD_NO_AISLE = "Créez une allée avant tout";


}
