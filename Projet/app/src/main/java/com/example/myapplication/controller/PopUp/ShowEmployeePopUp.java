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
 * This class allow us to show all the information about the employee
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
    /**
     * This method allow us to bind the attribute with the view
     * @param savedInstanceState
     */
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

    /**
     * This method allow us to show all informations about employee when the user click on the name
     * @param v
     */
    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (TAG_BUTTON_AISLE.equals(v.getTag())){
            this.setContentOfSpinner(this.mAisleDAO.getAll());
            setSpinnerToAisle(this.mSelectedEmployee.getEntityAisle());
            Log.d(TAG, "onClick: Choose your Aisle");
        }
    }

    /**
     * This method delete the employee in the dabatase when the user click on the delete button
     */
    @Override
    protected void onDelete() {
        this.mEmployeeDAO.deleteEmployeeById(this.mSelectedEmployee.getIdEmployee());
    }
    /**
     * This method contain all action will do once you submit
     */
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
        this.mCOwner.refreshAll();
    }

    @Override
    protected boolean isAllFieldsValide() {
        // always true because there is no possibilities to send faulty info
        return true;
    }

    /**
     * This method initialize the view with all the information about the employee name clicked.
     */
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
