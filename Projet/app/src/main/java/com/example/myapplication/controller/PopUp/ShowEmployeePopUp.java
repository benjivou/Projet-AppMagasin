package com.example.myapplication.controller.PopUp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.myapplication.R;
import com.example.myapplication.controller.PopUp.abstractpopup.DeletePopUp;
import com.example.myapplication.controller.util.button.listActivity.ControlOnglet;
import com.example.myapplication.model.EntityAisle;
import com.example.myapplication.model.EntityEmployee;

import java.util.ArrayList;

/**
 * Created by Benjamin Vouillon on 30,January,2020
 */
public class ShowEmployeePopUp extends DeletePopUp {
    private static final String TAG = "ShowEmployeePopUp";
    private static final String TAG_BUTTON_AISLE = "aisle";
    EntityEmployee mSelectedEmployee;

    /*
    Front
     */
    TextView mTextName,mTextSex,mTextRef;
    ImageButton mBtnAisle;
    /*
    Attributes
     */
    String mSex,mName,mAisle,mRef;

    public ShowEmployeePopUp(EntityEmployee entityEmployee, ControlOnglet activity,EntityEmployee target) {
        super(entityEmployee, activity);
        mSelectedEmployee = target;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_show_user);

        mTextName = findViewById(R.id.txtName);
        mTextRef = findViewById(R.id.txtId);
        mTextSex = findViewById(R.id.txtSex);
        this.mProductAisle = findViewById(R.id.txtPrice);
        mBtnAisle = findViewById(R.id.btnModifyPrice);
        this.mDeleteBtn = findViewById(R.id.btnDelete);
        this.mButtonSubmit = findViewById(R.id.btnSubmit);

    }

    @Override
    protected void onStart() {
        super.onStart();
        init();
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (TAG_BUTTON_AISLE.equals(v.getTag())){
            this.setContentOfSpinner(this.mAisleDAO.getAll());
            setSpinnerToAisle(this.mSelectedEmployee.getEntityAisle());
            Log.d(TAG, "onClick: Choose your Aisle");
        }
    }

    @Override
    protected void onDelete() {
        this.mEmployeeDAO.deleteEmployeeById(this.mSelectedEmployee.getIdEmployee());
    }

    @Override
    protected void onSubmit() {
        try{
            this.mSelectedEmployee.setEntityAisle(this.mSelectedAisle);

            //Insert Article
            mEmployeeDAO.insertUpdate(this.mSelectedEmployee);
            Log.d(TAG, "onItemClick: OnSubmit : employee " + mSelectedEmployee);
            this.mCOwner.refreshEmployeeList();
        }
        catch(Exception e ){ // if the formular is not valid don't do anything just remove the pop-up
            Log.e(TAG, "onSubmit: error when submit employeePopup ",e );
            Log.d(TAG, "onSubmit: invalid formular");
        }
    }

    @Override
    protected boolean isAllFieldsValide() {
        // always true because there is no possibilities to send faulty info
        return true;
    }

    private void init(){


        // fill the fields
        this.mTextRef.setText(this.mSelectedEmployee.getIdEmployee());
        this.mTextName.setText(this.mSelectedEmployee.getName());
        this.mTextSex.setText(this.mSelectedEmployee.getSex());
        ArrayList<EntityAisle>listAisle = new ArrayList<EntityAisle>();
        if (! this.mSelectedEmployee.getIdEmployee().equals(("root"))){
            listAisle.add(this.mSelectedEmployee.getEntityAisle());
            // Prepare the modif button
            this.mBtnAisle.setTag(TAG_BUTTON_AISLE);
            this.mBtnAisle.setOnClickListener(this);
        }
        else{
            // because no aisle for the admin and no posibilities to kill him
            this.mBtnAisle.setVisibility(View.INVISIBLE);
            this.mDeleteBtn.setVisibility(View.INVISIBLE);
        }

        this.setContentOfSpinner(listAisle);
    }
}
