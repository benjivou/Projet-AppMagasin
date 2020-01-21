package com.example.myapplication.controller.util;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.example.myapplication.dao.EmployeeDAO;
import com.example.myapplication.model.EntityEmployee;

import static com.example.myapplication.config.ConfigFront.DEFAULT_PASSWORD;
import static com.example.myapplication.config.ConfigFront.DURATION_ERROR_MESSAGES;


/**
 * Prepare some methods use in the front
 */
public abstract class DisplayUtilActivity extends Activity {
    private  final String TAG = "DisplayUtil";
    protected String mIdEmployee;




    /**
     * Display the error message
     * @param msg the error message
     */
    public  void displayError(String msg){
        for (int i = 0 ; i < DURATION_ERROR_MESSAGES; i ++ ) {
            Toast.makeText(
                   this,
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
    public  boolean[] loginProcess(String login, String password){
        boolean[] resultat = {false,false};
        String truePassword = "";

        /*
        Step 1 : get the code from the bdd
         */
        EntityEmployee employee = EmployeeDAO.getByMatricule(login,this);
        if (employee == null)
            return resultat;
        truePassword = employee.getPassword();
        /*
        Step 2 : check the password
         */
        resultat[0] =  !(truePassword.equals("")) &&  password.equals(truePassword);
        resultat[1] = truePassword.equals(DEFAULT_PASSWORD);


        return resultat;
    }

    public String displayUsername(EntityEmployee entityEmployee){
        return entityEmployee.getIdEmployee()+", " + entityEmployee.getRole();
    }




}
