package com.example.myapplication.controller.PopUp.abstractpopup;

import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.myapplication.R;
import com.example.myapplication.controller.util.button.listActivity.ButtonPanel;
import com.example.myapplication.model.EntityEmployee;

/**
 * Created by Benjamin Vouillon on 28,January,2020
 */
public abstract class SexFieldPopUp extends DeleteEmployeePopUp implements RadioGroup.OnCheckedChangeListener{
    protected RadioButton mRadioButton;
    protected RadioGroup mRadioGroup;

    protected String mSex;

    public SexFieldPopUp(EntityEmployee entityEmployee, ButtonPanel activity, EntityEmployee selectedEmployee) {
        super(entityEmployee, activity, selectedEmployee);
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
}
