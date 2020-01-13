package com.example.myapplication.controller;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;

import android.widget.EditText;
import android.widget.ImageButton;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.myapplication.Database.DatabaseQueryClass;
import com.example.myapplication.R;
import com.example.myapplication.config.ConfigFront;

import com.example.myapplication.dao.EmployeeDAO;
import com.example.myapplication.model.EntityEmployee;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;


import static com.example.myapplication.config.ConfigDAO.EMPLOYEE;

import static com.example.myapplication.config.ConfigFront.USERNAME_SESSION;
import static com.example.myapplication.controller.util.DisplayUtil.displayError;

import static com.example.myapplication.controller.util.DisplayUtil.loginProcess;
import static com.example.myapplication.dao.roleDAO.ADMIN;


public class LoginActivity extends Activity  {
    private DatabaseQueryClass databaseQueryClass = new DatabaseQueryClass(this);


    ImageButton mButton;
    EditText mLogin,mPassword;
    private static final String TAG = "LoginActivity";


    int counter = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_frame);

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        }
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);
        }
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

        /*
        Retrieve the
         */
        initBDD();
    }

    @Override
    protected void onStart() {
        super.onStart();
       // initBDD();
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
    public void initBDD(){

        for (EntityEmployee o :
             databaseQueryClass.getAllEmployee())
        {
            Log.d(TAG, "initBDD: " + o.toString());
        }
        /*
        checkFolder();
        EmployeeDAO employeeDAO = new EmployeeDAO();
        Log.d(TAG, "initBDD: " + employeeDAO.toString());
        /*
        First Use of the APP
         *//*
        if (employeeDAO.getSize() == 0 ){
            employeeDAO.add(
                    new EntityEmployee(
                            0,
                            "root",
                            "M",
                            ADMIN.getSring()
                    )
            );
        }*/

    }

    public void checkFolder() {
        String path =  Environment.getExternalStorageDirectory().getPath() + "/Android/data/ " +
                getPackageName() + "/files/"   ;
        File dir = new File(path);
        boolean isDirectoryCreated = dir.exists();
        if (!isDirectoryCreated) {
            isDirectoryCreated = dir.mkdir();
        }
        if (isDirectoryCreated) {
            // do something\
            Log.d("Folder", "Already Created");
        }
    }
}