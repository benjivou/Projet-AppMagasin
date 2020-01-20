package com.example.myapplication.controller;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.myapplication.R;

public class FirstConnexionActivity extends Activity {

    private DatabaseQueryClass databaseQueryClass;
    ImageButton mButton;
    EditText mLogin,mPassword;
    private static final String TAG = "FirstLoginActivity";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_password);

         /*
        Connect the View
         */
        mButton = (ImageButton)findViewById(R.id.btnSubmit);
        mLogin = (EditText)findViewById(R.id.login);
        mPassword = (EditText)findViewById(R.id.password);
    }

    protected void firstConnection(String username, String password){


    }
}
