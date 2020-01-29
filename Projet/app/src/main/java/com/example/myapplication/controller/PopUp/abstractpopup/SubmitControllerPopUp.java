package com.example.myapplication.controller.PopUp.abstractpopup;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

import com.example.myapplication.controller.util.button.listActivity.ButtonPanel;
import com.example.myapplication.controller.util.button.listActivity.ControlOnglet;
import com.example.myapplication.model.EntityEmployee;

/**
 * Created by Benjamin Vouillon on 25,January,2020
 */
public abstract class SubmitControllerPopUp extends MainPopUp implements OnClickListener {

    private static final String TAG_BUTTON_SUBMIT = "submit";

    // Front
    protected ImageButton mButtonSubmit;



    public SubmitControllerPopUp(EntityEmployee entityEmployee, ControlOnglet activity) {
        super(entityEmployee, activity);

    }

    @Override
    protected void onStart() {
        super.onStart();
        mButtonSubmit.setTag(TAG_BUTTON_SUBMIT);
        mButtonSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(TAG_BUTTON_SUBMIT.equals(v.getTag())){
            if(isAllFieldsValide())
            {
               
                    onSubmit();
                    dismiss();
                
            }

        }
    }
    protected abstract void onSubmit();

    /**
     * Check if we can submit this formular
     * @return
     */
    protected abstract boolean isAllFieldsValide();

}
