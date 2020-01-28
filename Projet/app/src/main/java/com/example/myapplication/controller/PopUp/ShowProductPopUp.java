package com.example.myapplication.controller.PopUp;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.annotation.Nullable;

import com.example.myapplication.R;
import com.example.myapplication.config.ConfigFront;
import com.example.myapplication.controller.PopUp.abstractpopup.AisleSpinnerPopUp;
import com.example.myapplication.controller.PopUp.abstractpopup.DeletePopUp;
import com.example.myapplication.controller.PopUp.abstractpopup.DeleteProductPopUp;
import com.example.myapplication.controller.util.button.listActivity.ButtonPanel;
import com.example.myapplication.dao.RoleDAO;
import com.example.myapplication.model.EntityArticle;
import com.example.myapplication.model.EntityEmployee;

/**
 * Created by Benjamin Vouillon on 28,January,2020
 */
public class ShowProductPopUp extends DeleteProductPopUp {
    private static final String TAG = "ShowProductPopUp";
    EditText mProductName,mProductQuantity, mProductPrice;

    public ShowProductPopUp(EntityEmployee entityEmployee, ButtonPanel activity, EntityArticle selectedArticle) {
        super(entityEmployee, activity, selectedArticle);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mProductName  = (EditText)findViewById(R.id.txtName);
        mProductQuantity = (EditText)findViewById(R.id.txtQuantity);
        mProductPrice = (EditText) findViewById(R.id.txtPrice);
        mProductAisle = (Spinner) findViewById(R.id.txtAisle);

        this.mButtonSubmit = (ImageButton) findViewById(R.id.btnSubmit);
    }

    @Override
    protected void onSubmit() {
        String mq = mProductQuantity.getText().toString();
        int mQuantity = Integer.parseInt(mq);

        String mP = mProductPrice.getText().toString();
        float mPrice = Float.parseFloat(mP);



        //Create Article
        EntityArticle entityArticle = new EntityArticle(0,mProductName.getText().toString(),mPrice,mQuantity,mSelectedAisle);

        //Insert Article
        mArticleDAO.insertUpdate(entityArticle);
        Log.d(TAG, "onItemClick: OnSubmit : article" + entityArticle);
    }


    @Override
    protected boolean isAllFieldsValide() {
        return true;
    }
}
