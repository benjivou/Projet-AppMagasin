package com.example.myapplication.controller;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.myapplication.R;
import com.example.myapplication.config.ConfigFront;
import com.example.myapplication.controller.util.DisplayUtilActivity;
import com.example.myapplication.dao.EmployeeDAO;
import com.example.myapplication.dao.roleDAO;

import com.example.myapplication.model.EntityEmployee;

public class AddEmployeePopUp extends DisplayUtilActivity implements RadioGroup.OnCheckedChangeListener{

    ImageButton mButton;
    EditText mEmployeeName, mEmployeeMatricule;
    private static final String TAG = "AddEmployee";
    String mPassword;
    RadioGroup radioGroup;
    RadioButton radioButton;
    String mGender;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_add_user);

        mButton = (ImageButton) findViewById(R.id.btnSubmit);
        mEmployeeName = (EditText) findViewById(R.id.name);
        mEmployeeMatricule = (EditText) findViewById(R.id.txtIsbn);
        radioGroup = (RadioGroup)findViewById(R.id.radioGroup);

        mPassword = getIntent().getStringExtra(ConfigFront.DEFAULT_PASSWORD);
        Log.d(TAG, "onCreate: matriculeIntent "+mPassword);


    }

    @Override
    public void onCheckedChanged(RadioGroup group, int i) {

        switch ((i)){

            case R.id.woman:
                mGender = "Woman";
                EntityEmployee entityEmployeeWoman = new EntityEmployee(mEmployeeMatricule.getText().toString(),mEmployeeName.getText().toString(),mGender,mPassword,roleDAO.USER.getSring());

                EmployeeDAO.insertUpdate(entityEmployeeWoman);

                break;

            case R.id.man:
                mGender = "man";
                EntityEmployee entityEmployeeMan = new EntityEmployee(mEmployeeMatricule.getText().toString(),mEmployeeName.getText().toString(),mGender,mPassword,roleDAO.USER.getSring());

                EmployeeDAO.insertUpdate(entityEmployeeMan);

                break;
        }
    }
}
