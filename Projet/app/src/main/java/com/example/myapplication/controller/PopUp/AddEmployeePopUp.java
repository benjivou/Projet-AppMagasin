package com.example.myapplication.controller.PopUp;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.myapplication.R;
import com.example.myapplication.config.ConfigFront;
import com.example.myapplication.controller.util.DisplayUtilActivity;
import com.example.myapplication.dao.EmployeeDAO;
import com.example.myapplication.dao.RoleDAO;

import com.example.myapplication.model.EntityAisle;
import com.example.myapplication.model.EntityEmployee;

public class AddEmployeePopUp extends MainPopUp implements RadioGroup.OnCheckedChangeListener{

    ImageButton mButton;
    EditText mEmployeeName, mEmployeeMatricule;
    Spinner mAisle;
    private static final String TAG = "AddEmployee";
    String mPassword;
    RadioGroup radioGroup;
    RadioButton radioButton;
    String mGender;

    public AddEmployeePopUp(EntityEmployee entityEmployee) {
        super(entityEmployee);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_add_user);

        mButton = (ImageButton) findViewById(R.id.btnSubmit);
        mEmployeeName = (EditText) findViewById(R.id.name);
        mEmployeeMatricule = (EditText) findViewById(R.id.txtIsbn);
        radioGroup = (RadioGroup)findViewById(R.id.radioGroup);

        mAisle = (Spinner) findViewById(R.id.txtPublisher);

        mPassword = getIntent().getStringExtra(ConfigFront.DEFAULT_PASSWORD);
        Log.d(TAG, "onCreate: matriculeIntent "+mPassword);


    }

    @Override
    public void onCheckedChanged(RadioGroup group, int i) {

        switch ((i)){

            case R.id.woman:
                mGender = "Woman";

               // EntityEmployee entityEmployeeWoman = new EntityEmployee(mEmployeeMatricule.getText().toString(),mEmployeeName.getText().toString(),mGender,mPassword,RoleDAO.USER.toString(),mAisle.getSelectedItem());

              // EmployeeDAO.insertUpdate(entityEmployeeWoman);

                break;

            case R.id.man:
                mGender = "man";
               // EntityEmployee entityEmployeeMan = new EntityEmployee(mEmployeeMatricule.getText().toString(),mEmployeeName.getText().toString(),mGender,mPassword,roleDAO.USER.getSring());

                //EmployeeDAO.insertUpdate(entityEmployeeMan);

                break;
        }
    }
}
