package com.example.myapplication.controller.PopUp;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Spinner;

import androidx.annotation.Nullable;

import com.example.myapplication.R;
import com.example.myapplication.controller.util.button.listActivity.ButtonPanel;
import com.example.myapplication.model.EntityEmployee;

public class AddEmployeePopUp extends SubmitControllerPopUp {

    // Tag
    private static final String TAG = "AddEmployee";

    EditText mEmployeeName,mEmployeeMatricule;
    RadioButton mWoman, mMan;
    Spinner mEmployeeAisle;

    public AddEmployeePopUp(EntityEmployee entityEmployee, ButtonPanel activity) {
        super(entityEmployee, activity);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_add_user);

        mEmployeeName = (EditText)findViewById(R.id.name);
        mEmployeeAisle = (Spinner) findViewById(R.id.txtPublisher);
        mEmployeeMatricule = (EditText)findViewById(R.id.txtIsbn);
        mWoman = (RadioButton) findViewById(R.id.woman);
        mMan = (RadioButton) findViewById(R.id.man);
        this.mButtonSubmit = (ImageButton) findViewById(R.id.btnSubmit);

    }



    @Override
    protected void onSubmit() {

    }
}
