package com.example.myapplication.controller;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.Nullable;

import com.example.myapplication.R;
import com.example.myapplication.config.ConfigFront;
import com.example.myapplication.config.passwordvalidator.PasswordValidator;
import com.example.myapplication.config.passwordvalidator.ValidationResult;
import com.example.myapplication.controller.util.DisplayUtilActivity;
import com.example.myapplication.dao.EmployeeDAO;
import com.example.myapplication.model.EntityEmployee;

import static com.example.myapplication.config.ConfigFront.USERNAME_SESSION;

public class FirstConnexionActivity extends DisplayUtilActivity {

    ImageButton mButton;
    EditText mPassword1,mPassword2;
    private static final String TAG = "FisrtLoginActivity";
    String mMatricule;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_password);

        /*
        Connect the View
         */
        mButton    = (ImageButton)findViewById(R.id.btnSubmit);
        mPassword1 = (EditText)findViewById(R.id.password);
        mPassword2 = (EditText)findViewById(R.id.password2);

        mMatricule = getIntent().getStringExtra(ConfigFront.USERNAME_SESSION);
        Log.d(TAG, "onCreate: matriculeIntent "+mMatricule);

        mButton.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                String p1,p2;
                /*
                Step 1 : get the two passwords
                 */
                p1 = mPassword1.getText().toString();
                p2 = mPassword2.getText().toString();


                /*
                Step 2 : Compare the two passwords
                 */

                if(p1.equals(p2)){
                    /*
                     Step 3 : Validation passwords
                    */
                    PasswordValidator passwordValidator = new PasswordValidator();
                    ValidationResult validationResult =  passwordValidator.validateWithMessages(p2);

                    if(validationResult.isValid() == false){
                        displayError(validationResult.getMessages().get(0));
                    }else{
                        changeActivity();
                    }


                }else{
                    displayError(ConfigFront.ERROR_CREATION_PASSWORD_CORRESPONDANCE_PROBLEM);
                }

            }
        });


    }

    private void changeActivity(){

        Intent newActivity;

        /*
        Step 1 : register password
         */

        EntityEmployee entityEmployee = EmployeeDAO.getByMatricule(mMatricule,this);
        entityEmployee.setPassword(mPassword2.getText().toString());
        EmployeeDAO.insertUpdate(entityEmployee,this);
        Log.d(TAG, "changeActivity: DBContent" + EmployeeDAO.getByMatricule(mMatricule,this));

        /*
        Step 2 : changeActivity + give user id
         */
        newActivity = new Intent( FirstConnexionActivity.this,ListeActivity.class);

        newActivity.putExtra(USERNAME_SESSION,mMatricule);

        startActivity(newActivity);
    }
}
