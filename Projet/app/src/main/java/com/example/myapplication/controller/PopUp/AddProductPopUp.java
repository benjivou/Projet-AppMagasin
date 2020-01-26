package com.example.myapplication.controller.PopUp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.myapplication.R;
import com.example.myapplication.controller.util.button.listActivity.ButtonPanel;
import com.example.myapplication.model.EntityAisle;
import com.example.myapplication.model.EntityArticle;
import com.example.myapplication.model.EntityEmployee;

import java.util.ArrayList;
import java.util.List;

public class AddProductPopUp extends SubmitControllerPopUp {

    // Tag
    private static final String TAG = "AddProduct";

    EditText mProductName,mProductQuantity, mProductPrice;
    Spinner mProductAisle;

    public AddProductPopUp(EntityEmployee entityEmployee, ButtonPanel activity) {
        super(entityEmployee, activity);
    }

    @Override
    protected void onSubmit() {

        String mq = mProductQuantity.getText().toString();
        final int mQuantity = Integer.parseInt(mq);

        String mP = mProductPrice.getText().toString();
        final float mPrice = Float.parseFloat(mP);

        List<EntityAisle> list = new ArrayList<EntityAisle>();
        ArrayAdapter <EntityAisle> arrayAdapter = new ArrayAdapter<EntityAisle>(this.mActivity,android.R.layout.simple_list_item_1,list);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mProductAisle.setAdapter(arrayAdapter);
        mProductAisle.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //getAll le spinner
                for (EntityAisle ea:
                        mAisleDAO.getAll()
                ) {
                    Log.d(TAG, "onSubmit: aisle " + ea.getName());
                }
                //get selected item
                EntityAisle entityAisle = new EntityAisle(0,mProductAisle.getAdapter().getItem(position).toString());

                //Create Article
                EntityArticle entityArticle = new EntityArticle(0,mProductName.getText().toString(),mPrice,mQuantity,entityAisle);
                
                //Insert Article
                mArticleDAO.insertArticle(entityArticle);
                Log.d(TAG, "onItemClick: OnSubmit : article" + entityArticle);

            }
        });


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
