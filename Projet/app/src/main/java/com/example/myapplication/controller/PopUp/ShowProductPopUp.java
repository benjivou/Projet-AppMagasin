package com.example.myapplication.controller.PopUp;

import android.widget.ImageButton;

import com.example.myapplication.controller.PopUp.abstractpopup.AisleSpinnerPopUp;
import com.example.myapplication.controller.PopUp.abstractpopup.DeletePopUp;
import com.example.myapplication.controller.PopUp.abstractpopup.DeleteProductPopUp;
import com.example.myapplication.controller.util.button.listActivity.ButtonPanel;
import com.example.myapplication.model.EntityArticle;
import com.example.myapplication.model.EntityEmployee;

/**
 * Created by Benjamin Vouillon on 28,January,2020
 */
public class ShowProductPopUp extends DeleteProductPopUp {


    public ShowProductPopUp(EntityEmployee entityEmployee, ButtonPanel activity, EntityArticle selectedArticle) {
        super(entityEmployee, activity, selectedArticle);
    }



    @Override
    protected void onSubmit() {

    }


    @Override
    protected boolean isAllFieldsValide() {
        return false;
    }
}
