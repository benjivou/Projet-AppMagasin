package com.example.myapplication.controller.PopUp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import androidx.annotation.Nullable;

import com.example.myapplication.R;
import com.example.myapplication.model.EntityEmployee;

public class AddProductPopUp extends SubmitControllerPopUp {

    // Tag
    private static final String TAG = "AddProduct";

    EditText mProductName,mProductQuantity, mProductPrice;
    Spinner mProductAisle;
    ImageButton mSubmit;

    public AddProductPopUp(EntityEmployee entityEmployee, Activity activity) {
        super(entityEmployee, activity);
    }

    @Override
    protected void onSubmit() {

    String mq = mProductQuantity.getText().toString();
    int mQuantity = Integer.parseInt(mq);

    String mP = mProductPrice.getText().toString();
    float mPrice = Float.parseFloat(mP);

    


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
}
