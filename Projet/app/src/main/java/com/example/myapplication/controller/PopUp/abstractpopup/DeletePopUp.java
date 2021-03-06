package com.example.myapplication.controller.PopUp.abstractpopup;

import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.example.myapplication.controller.util.button.listActivity.ControlOnglet;
import com.example.myapplication.model.EntityEmployee;

/**
 * Created by Benjamin Vouillon on 28,January,2020
 */
public abstract class DeletePopUp extends AisleSpinnerPopUp {
    private static final String TAG = "DeletePopUp";
    public static final String DELETE_BTN ="delete";

    protected ImageButton mDeleteBtn; // represent the btn Delete

    public DeletePopUp(EntityEmployee entityEmployee, ControlOnglet activity) {
        super(entityEmployee, activity);
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Tag the button
        mDeleteBtn.setTag(DELETE_BTN);
        mDeleteBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if(DELETE_BTN.equals(v.getTag())){
            Log.d(TAG, "onClick: U want to delete someOne");
            onDelete();
            // refresh the selection before killing popup
            this.mCOwner.refreshAll();
            dismiss();

        }
    }

    protected abstract void onDelete();
}
