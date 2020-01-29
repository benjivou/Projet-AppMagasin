package com.example.myapplication.controller.PopUp;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.annotation.Nullable;

import com.example.myapplication.R;
import com.example.myapplication.config.ConfigFront;
import com.example.myapplication.controller.PopUp.abstractpopup.AisleSpinnerPopUp;
import com.example.myapplication.controller.util.button.listActivity.ButtonPanel;
import com.example.myapplication.dao.RoleDAO;
import com.example.myapplication.model.EntityEmployee;

public class AddEmployeePopUp extends AisleSpinnerPopUp implements RadioGroup.OnCheckedChangeListener {


    EditText mEmployeeName,mEmployeeMatricule;
    RadioButton mRadioButton;
    RadioGroup mRadioGroup;



    String mSex,mName,mPassword,mId;


    public AddEmployeePopUp(EntityEmployee entityEmployee, ButtonPanel activity) {
        super(entityEmployee, activity);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_add_user);
        mRadioButton = findViewById(R.id.man);
        mEmployeeName = (EditText)findViewById(R.id.name);
        this.mProductAisle = (Spinner) findViewById(R.id.txtPublisher);
        mEmployeeMatricule = (EditText)findViewById(R.id.txtIsbn);
        this.mButtonSubmit = (ImageButton) findViewById(R.id.btnSubmit);
        mRadioGroup = findViewById(R.id.radioGroup);
        mRadioGroup.setOnCheckedChangeListener(this);

        // Set the default value to Man
        mRadioButton.performClick();
    }

    @Override
    protected void onSubmit() {
        mName = mEmployeeName.getText().toString();
        mPassword = ConfigFront.DEFAULT_PASSWORD;
        mId = mEmployeeMatricule.getText().toString();
        EntityEmployee employee = new EntityEmployee(
                mId,
                mName,
                mPassword,
                mSex,
                RoleDAO.USER,
                mSelectedAisle
        );

        this.mEmployeeDAO.insertEmployee(employee);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        RadioButton rEnCours=(RadioButton)findViewById(checkedId);
        // generate the Sex
        if(rEnCours.getId() == R.id.woman){
            this.mSex = "F";
        }
        if(rEnCours.getId() == R.id.man){
            this.mSex = "M";
        }
    }

    @Override
    protected boolean isAllFieldsValide() {
        if(mEmployeeName.getText().toString().isEmpty()){
            this.mCOwner.displayError(ConfigFront.ERROR_USERFIELD_NAME);
            return false;
        }
        if(mEmployeeMatricule.getText().toString().isEmpty()){
            this.mCOwner.displayError(ConfigFront.ERROR_USERFIELD_ID);
            return false;
        }
        return true;


    }
}
