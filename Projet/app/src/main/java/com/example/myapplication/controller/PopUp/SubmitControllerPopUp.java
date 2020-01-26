package com.example.myapplication.controller.PopUp;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

import com.example.myapplication.model.EntityEmployee;

/**
 * Created by Benjamin Vouillon on 25,January,2020
 */
public abstract class SubmitControllerPopUp extends MainPopUp implements OnClickListener {

    private static final String TAG_BUTTON_SUBMIT = "submit";

    // Front
    protected ImageButton mButtonSubmit;

    public SubmitControllerPopUp(EntityEmployee entityEmployee, Activity activity) {
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
            onSubmit();
            dismiss();
        }
    }
    protected abstract void onSubmit();
}
