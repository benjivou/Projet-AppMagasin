package com.example.myapplication.controller;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.myapplication.R;
import com.example.myapplication.config.ConfigDAO;
import com.example.myapplication.config.ConfigFront;
import com.example.myapplication.controller.util.DisplayUtilActivity;
import com.example.myapplication.model.EntityAisle;
import com.example.myapplication.model.EntityEmployee;

import static com.example.myapplication.config.ConfigFront.DEFAULT_PASSWORD;
import static com.example.myapplication.config.ConfigFront.USERNAME_SESSION;
import static com.example.myapplication.dao.RoleDAO.ADMIN;


public class LoginActivity extends DisplayUtilActivity {

    /*
    Attribut
     */
    ImageButton mButton;
    EditText mLogin,mPassword;

    private static final String TAG = "LoginActivity";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_frame);


        /*
        Connect the View
         */
        mButton = (ImageButton)findViewById(R.id.btnSubmit);
        mLogin = (EditText)findViewById(R.id.login);
        mPassword = (EditText)findViewById(R.id.password);



        mButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                /*
                Step 1 : get the login info
                 */
                boolean[] process =loginProcess(
                        mLogin.getText().toString(),
                        mPassword.getText().toString()
                );

                /*
                Step 2 : result
                 */
                if(process[0] == true) {
                  displayError("Redirecting...");
                    mIdEmployee = mLogin.getText().toString();                    /*
                    Step 3 : Change Activity
                     */
                    changeActivity(process[1]);
                }else{
                    /*
                      Error : bad pair login / password
                     */
                    displayError(ConfigFront.ERROR_BAD_PAIR_LOGIN_MDP);

                }
            }
        });


        /*
        Retrieve the
         */
        initBDD();

    }



    private void changeActivity(boolean isFirstConnection){

        Intent newActivity;




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

        newActivity.putExtra(USERNAME_SESSION,mIdEmployee);

        startActivity(newActivity);

    }

    public void initBDD(){



       //this.getDatabasePath(ConfigDAO.DB).delete(); // Config de Test


        if(  !this.getDatabasePath(ConfigDAO.DB).exists()) {


            // database doesn't exist yet.

            mCurrentUser.insertEmployee(new EntityEmployee(
                    "root",
                    "root",
                    "M",
                    DEFAULT_PASSWORD,
                    ADMIN.getSring(),
                    new EntityAisle()
            ));
            Log.d(TAG, "initBDD: We add the root user");

            Log.d(TAG, "initBDD: first root " + mCurrentUser.getByMatricule("root"));
            for (EntityEmployee e:
                    mCurrentUser.getAllEmployee()) {
                Log.d(TAG, "initBDD: first root " + e.toString());
            }

            Log.d(TAG, "initBDD: first root " + mCurrentUser.countEmployee());

        }


    }


}