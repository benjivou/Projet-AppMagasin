package com.example.myapplication.controller.PopUp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import androidx.annotation.Nullable;

import com.example.myapplication.R;
import com.example.myapplication.controller.PopUp.abstractpopup.DeleteProductPopUp;
import com.example.myapplication.controller.util.button.listActivity.ControlOnglet;
import com.example.myapplication.model.EntityAisle;
import com.example.myapplication.model.EntityArticle;
import com.example.myapplication.model.EntityEmployee;

import java.util.ArrayList;

/**
 * Created by Benjamin Vouillon on 28,January,2020
 */
public class ShowProductPopUp extends DeleteProductPopUp {
    private static final String TAG = "ShowProductPopUp";
    private static final String TAG_BUTTON_NAME = "name";
    private static final String TAG_BUTTON_QUANTITY = "quantity";
    private static final String TAG_BUTTON_PRICE = "price";
    private static final String TAG_BUTTON_AISLE = "aisle";

    EditText mProductName,mProductQuantity, mProductPrice,mProductRef;

    ImageButton mButtonName, mButtonQuantity,mButtonPrice,mButtonAisle;

    public ShowProductPopUp(EntityEmployee entityEmployee, ControlOnglet activity, EntityArticle selectedArticle) {
        super(entityEmployee, activity, selectedArticle);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_show_product);

        mProductName  = (EditText)findViewById(R.id.txtName);
        mProductQuantity = (EditText)findViewById(R.id.txtQuantity);
        mProductPrice = (EditText) findViewById(R.id.txtPrice);
        mProductAisle = (Spinner) findViewById(R.id.txtAisle);
        mProductRef = findViewById(R.id.txtRef);

        mButtonAisle = findViewById(R.id.btnModifyAisle);
        mButtonPrice = findViewById(R.id.btnModifyPrice);
        mButtonName = findViewById(R.id.btnModifyName);
        mButtonQuantity = findViewById(R.id.btnModifyQuantity);

        this.mButtonSubmit = (ImageButton) findViewById(R.id.btnSubmit);
        this.mDeleteBtn = (ImageButton) findViewById(R.id.btnDelete);


    }
    @Override
    protected void onStart() {
        super.onStart();

        init();
    }

    @Override
    protected void onSubmit() {
        try{
            String mq = mProductQuantity.getText().toString();
            int mQuantity = Integer.parseInt(mq);

            String mP = mProductPrice.getText().toString();
            float mPrice = Float.parseFloat(mP);



            //Create Article
            EntityArticle entityArticle = new EntityArticle(
                    Integer.valueOf(mProductRef.getText().toString()),
                    mProductName.getText().toString(),
                    mPrice,
                    mQuantity,
                    mSelectedAisle);

            //Insert Article
            mArticleDAO.insertUpdate(entityArticle);
            Log.d(TAG, "onItemClick: OnSubmit : article " + entityArticle);
            this.mCOwner.refreshAll();
        }
        catch(Exception e ){ // if the formular is not valid don't do anything just remove the pop-up
            Log.e(TAG, "onSubmit: error when submit productPopup ",e );
            Log.d(TAG, "onSubmit: invalid formular");
        }
    }


    @Override
    protected boolean isAllFieldsValide() {
        return true;
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (TAG_BUTTON_AISLE.equals(v.getTag())){
            this.setContentOfSpinner(this.mAisleDAO.getAll());
            Log.d(TAG, "onClick: Choose your Aisle");
        }
        if (TAG_BUTTON_NAME.equals((v.getTag()))){
            this.mProductName.setFocusableInTouchMode(true);
            this.mProductName.setEnabled(true);
            this.mProductName.setFocusable(true);
            Log.d(TAG, "onClick: choose your name");
        }
        if (TAG_BUTTON_PRICE.equals(v.getTag())){
            this.mProductPrice.setFocusableInTouchMode(true);
            this.mProductPrice.setEnabled(true);
            this.mProductPrice.setFocusable(true);
        }
        if (TAG_BUTTON_QUANTITY.equals(v.getTag())){
            this.mProductQuantity.setFocusableInTouchMode(true);
            this.mProductQuantity.setEnabled(true);
            this.mProductQuantity.setFocusable(true);
        }
    }

    /**
     * The goal is to init the pop up
     */
    private void init(){
        /*
        foreach user : init the visual
         */
        Float price  = this.mSelectedArticle.getPrice();
        ArrayList<EntityAisle> entityAisleArrayLists = new ArrayList<EntityAisle>();
        entityAisleArrayLists.add( this.mSelectedArticle.getEntityAisle());

        Log.d(TAG, "init: "+ this.mSelectedArticle.getQuantity());
        this.mProductName.setText(this.mSelectedArticle.getName());
        this.mProductQuantity.setText(Integer.toString(this.mSelectedArticle.getQuantity()));
        this.mProductPrice.setText(price.toString());
        this.mProductRef.setText((Integer.toString(this.mSelectedArticle.getIdArticle())));
        setContentOfSpinner(entityAisleArrayLists);

        /*
        for the Admin and the Aisle Chief : prepare the button
         */
        if(this.mEmployeeDAO.modifyArticlesRules(this.mSelectedArticle)){
            Log.d(TAG, "init: U have the rigth to modify this item");

            /*
            Set the tag
             */
            this.mButtonName.setTag(TAG_BUTTON_NAME);
            this.mButtonPrice.setTag(TAG_BUTTON_PRICE);
            this.mButtonQuantity.setTag(TAG_BUTTON_QUANTITY);
            this.mButtonAisle.setTag(TAG_BUTTON_AISLE);

            /*
            Set the listener
             */
            this.mButtonName.setOnClickListener(this);
            this.mButtonPrice.setOnClickListener(this);
            this.mButtonQuantity.setOnClickListener(this);
            this.mButtonAisle.setOnClickListener(this);

        }

        else {
            /*
            Hide the buttons
             */
            this.mButtonName.setVisibility(View.INVISIBLE);
            this.mButtonPrice.setVisibility(View.INVISIBLE);
            this.mButtonQuantity.setVisibility(View.INVISIBLE);
            this.mButtonAisle.setVisibility(View.INVISIBLE);
            this.mDeleteBtn.setVisibility(View.INVISIBLE);
        }

        /*
         if you are a normal user U could not modify the aisle
         */
        if (!this.mCOwner.isAdmin()){
            this.mButtonAisle.setVisibility(View.INVISIBLE);
        }
    }
}
