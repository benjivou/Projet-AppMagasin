package com.example.myapplication.controller;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import android.widget.EditText;
import android.widget.ImageButton;

import com.example.myapplication.R;

import static com.example.myapplication.controller.util.DisplayUtil.displayError;


public class LoginActivity extends Activity  {
    ImageButton b1;
    EditText ed1,ed2;


    int counter = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_frame);

        b1 = (ImageButton)findViewById(R.id.btnSubmit);
        ed1 = (EditText)findViewById(R.id.login);
        ed2 = (EditText)findViewById(R.id.password);




        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ed1.getText().toString().equals("admin") &&
                        ed2.getText().toString().equals("admin")) {
                    displayError("Redirecting...",
                            getBaseContext());
                }else{
                    displayError("Wrong Credentials",
                            getBaseContext());



                }
            }
        });


    }
}