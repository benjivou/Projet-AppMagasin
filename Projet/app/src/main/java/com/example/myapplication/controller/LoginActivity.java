package com.example.myapplication.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.EditText;
import android.widget.ImageButton;

import com.example.myapplication.R;
import com.example.myapplication.config.ConfigFront;

import com.example.myapplication.model.EntityEmployee;

import static com.example.myapplication.config.ConfigFront.USERNAME_SESSION;
import static com.example.myapplication.controller.util.DisplayUtil.displayError;
import static com.example.myapplication.controller.util.DisplayUtil.loginProcess;


public class LoginActivity extends Activity  {
    ImageButton mButton;
    EditText mLogin,mPassword;


    int counter = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_frame);

        mButton = (ImageButton)findViewById(R.id.btnSubmit);
        mLogin = (EditText)findViewById(R.id.login);
        mPassword = (EditText)findViewById(R.id.password);




        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                Step 1 : get the login info
                 */
                boolean[] process = loginProcess(
                        mLogin.getText().toString(),
                        mPassword.getText().toString()
                );

                /*
                Step 2 : result
                 */
                if(process[0] == true) {
                    displayError("Redirecting...",
                            getBaseContext());

                    /*
                    Step 3 : Change Activity
                     */
                    changeActivity(process[1]);
                }else{
                    /*
                      Error : bad pair login / password
                     */
                    displayError(ConfigFront.ERROR_BAD_PAIR_LOGIN_MDP,
                            getBaseContext());



                }
            }
        });


    }

    private void changeActivity(boolean isFirstConnection){

        Intent newActivity;
        EntityEmployee  employee = new EntityEmployee();
        /*
        Step 1 : request the use entity
         */
        // TO-DO


        /*
        Step 2  Prepare the new Activity
         */
        newActivity = isFirstConnection ?
                new Intent(
                        LoginActivity.this,
                        FirstConnexionActivity.class
                        ):
                new Intent(
                        LoginActivity.this,
                        ListeActivity.class
                );

        newActivity.putExtra(USERNAME_SESSION,employee.getIdEmployee());

        startActivity(newActivity);

    }
}