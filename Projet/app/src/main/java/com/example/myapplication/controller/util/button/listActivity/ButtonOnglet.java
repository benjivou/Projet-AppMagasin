package com.example.myapplication.controller.util.button.listActivity;

import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.R;
import com.example.myapplication.controller.util.DisplayUtilActivity;

public abstract class ButtonOnglet extends DisplayUtilActivity implements View.OnClickListener{
    private static final String TAG = "ButtonOnglet";
    protected static final String TAG_ONGLET_EMPLOYE = "modeE";
    protected static final String TAG_ONGLET_PRODUCT = "modeP";

    protected Button
            mOngletProduit,
            mOngletEmployers;

    /**
     * Prepare the action of the onCLieck event
     * @param v
     */
    @Override
    public void onClick(View v) {
        // We want a list of employee
        if (TAG_ONGLET_EMPLOYE.equals((String)v.getTag())){
            // Change de button color
            mOngletEmployers.setBackgroundColor(   getColor( R.color.clicked ) );
            mOngletProduit.setBackgroundColor(   getColor( R.color.wait_clicked ) );
            Log.d(TAG, "onClick: Background color done !");
            // Action
            onOngletEmployee();
        }
        // We want a list of Product
        if (TAG_ONGLET_PRODUCT.equals((String)v.getTag())){
            // Change de button color
            mOngletEmployers.setBackgroundColor(   getColor( R.color.wait_clicked ) );
            mOngletProduit.setBackgroundColor(   getColor( R.color.clicked ) );
            // Action
            onOngletProduct();
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
    }

    /**
     * In this function the Son will define what to do
     */
    protected abstract void onOngletEmployee();
    protected abstract void onOngletProduct();



}
