package com.example.myapplication.controller.util;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;


import static com.example.myapplication.config.ConfigFront.*;


/**
 * Prepare some methods use in the front
 */
public class DisplayUtil {
    private static final String TAG = "DisplayUtil";

    /**
     * Display the error message
     * @param msg the error message
     * @param context the Activity context
     */
    public static void displayError(String msg, Context context){
        for (int i = 0 ; i < DURATION_ERROR_MESSAGES; i ++ ) {
            Toast.makeText(
                    context,
                    msg,
                    Toast.LENGTH_LONG
            ).show();
        }
    }

    /**
     * Log the the user to the Activity
     * @param login
     * @param password
     * @return First : isIt the good pair login password ?
     *          Second : is it the first connexion ?
     */
    public static boolean[] loginProcess(String login, String password){
        boolean[] resultat = {false,false};
        String truePassword = "";

        /*
        Step 1 : get the code from the bdd
         */
        // TO-DO
        if (login.equals("root"))
            truePassword = DEFAULT_PASSWORD;


        /*
        Step 2 : check the password
         */
        resultat[0] =  !(truePassword.equals("")) &&  password.equals(truePassword);
        resultat[1] = truePassword.equals(DEFAULT_PASSWORD);


        return resultat;
    }




}
