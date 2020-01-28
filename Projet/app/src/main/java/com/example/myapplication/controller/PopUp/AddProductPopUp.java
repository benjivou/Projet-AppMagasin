package com.example.myapplication.controller.PopUp;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import androidx.annotation.Nullable;


import com.example.myapplication.R;
import com.example.myapplication.config.ConfigFront;
import com.example.myapplication.controller.PopUp.abstractpopup.AisleSpinnerPopUp;
import com.example.myapplication.controller.util.button.listActivity.ButtonPanel;
import com.example.myapplication.model.EntityArticle;
import com.example.myapplication.model.EntityEmployee;

public class AddProductPopUp extends AisleSpinnerPopUp {

    // Tag
    private static final String TAG = "AddProduct";

    EditText mProductName,mProductQuantity, mProductPrice;



    public AddProductPopUp(EntityEmployee entityEmployee, ButtonPanel activity) {
        super(entityEmployee, activity);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_add_product);

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
        mArticleDAO.insertArticle(entityArticle);
        Log.d(TAG, "onItemClick: OnSubmit : article" + entityArticle);

    }

    @Override
    protected boolean isAllFieldsValide() {

        // Quantity field wrong
        if(mProductQuantity.getText().toString().isEmpty() || Integer.parseInt(mProductQuantity.getText().toString())<0){

            this.mBtActivity.displayError(ConfigFront.ERROR_PRODUCTFIELD_QUANTITY);
            return false;
        }
        // Price field wrong
        if(mProductPrice.getText().toString().isEmpty() || Float.parseFloat(mProductPrice.getText().toString())<0){
            this.mBtActivity.displayError(ConfigFront.ERROR_PRODUCTFIELD_PRIX);
            return false;
        }

        return true;




    }



}
