package com.example.myapplication.controller.util.button.listActivity;

import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.R;
import com.example.myapplication.controller.util.DisplayUtilActivity;

public abstract class ButtonOnglet extends SessionManagerActivity implements View.OnClickListener{
    private static final String TAG = "ButtonOnglet";
    private static final String TAG_ONGLET_EMPLOYE = "modeE";
    private static final String TAG_ONGLET_PRODUCT = "modeP";

    protected static final boolean MODE_PRODUCT_IS_CLICKED = true;

    private boolean isModeProductIsClicked;

    protected Button
            mOngletProduit,
            mOngletEmployers;

    /**
     * Prepare the action of the onCLieck event
     * @param v
     */
    @Override
    public void onClick(View v) {
        Log.d(TAG, "onClick: button clicked");
        // We want a list of employee
        if (TAG_ONGLET_EMPLOYE.equals((String)v.getTag())){
            modifOnglet( !MODE_PRODUCT_IS_CLICKED);
            Log.d(TAG, "onClick: button Employee");

        }
        // We want a list of Product
        if (TAG_ONGLET_PRODUCT.equals((String)v.getTag())){

            modifOnglet(MODE_PRODUCT_IS_CLICKED);
            Log.d(TAG, "onClick: button Product");
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        // Tag the buttons
        mOngletEmployers.setTag(TAG_ONGLET_EMPLOYE);
        mOngletProduit.setTag(TAG_ONGLET_PRODUCT);

        mOngletEmployers.setOnClickListener(this);
        mOngletProduit.setOnClickListener(this);


        isModeProductIsClicked = false;
        modifOnglet( MODE_PRODUCT_IS_CLICKED);



    }

    private void modifOnglet(boolean isProductClick){

        // Active the button
        mOngletProduit.setClickable(!isProductClick);
        mOngletEmployers.setClickable(isProductClick);

        // Change de button color
        if(isProductClick){
            mOngletEmployers.setBackgroundColor(   getColor( R.color.clicked ) );
            mOngletProduit.setBackgroundColor(   getColor( R.color.wait_clicked ) );
            // Action
            onOngletProduct();
        }
        else{
            mOngletEmployers.setBackgroundColor(   getColor( R.color.wait_clicked ) );
            mOngletProduit.setBackgroundColor(   getColor( R.color.clicked ) );
            // Action
            onOngletEmployee();
        }

        // set the product mode
        isModeProductIsClicked = ! isModeProductIsClicked;
    }
    /**
     * In this function the Son will define what to do
     */
    protected abstract void onOngletEmployee();
    protected abstract void onOngletProduct();

    protected boolean isModeProductIsClicked() {
        return isModeProductIsClicked;
    }
}
