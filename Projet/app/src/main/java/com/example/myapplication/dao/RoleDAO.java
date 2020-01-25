package com.example.myapplication.dao;

import android.util.Log;

/**
 * This class represent a role
 * @<version 1.0
 * @author Leslie Kiav & Benjamin Vouillon
 */
public enum RoleDAO {
    ADMIN("Chef de Magasin"),
    USER("Chef de Rayon");
    private String str;
    private static final String TAG = "RoleDAO";
    RoleDAO(String str){
        this.str = str;
    }


    public String getSring() {
        return str;
    }
    public static RoleDAO convertRole(String role){
        if (role.equals(USER.getSring())){
            return USER;
        }
        if (role.equals(ADMIN.getSring())){
            return ADMIN;
        }

        Log.e(TAG, "convertRole: Pls do something because U have an unexpected role : " + role  );
        return USER;
    }
}
