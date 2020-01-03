package com.example.myapplication.controller.util;

import android.content.Context;
import android.widget.Toast;

import com.example.myapplication.BuildConfig;

import static com.example.myapplication.config.ConfigFront.*;

/**
 * Prepare some methods use in the front
 */
public class DisplayUtil {

    /**
     * Display the error message
     * @param msg the error message
     * @param context the Activity context
     */
    public static void displayError(String msg, Context context){
        for (int i = 0 ; i < DURATION_ERROR_MESSAGES-1; i ++ ) {
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
     * @return
     */
    public static boolean loginProcess(String login, String password){
        boolean resultat = false;

        return resultat;
    }
}
