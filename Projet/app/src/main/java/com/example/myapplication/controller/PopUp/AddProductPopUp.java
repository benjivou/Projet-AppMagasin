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

    // back-end attribute
    EntityAisle mSelectedAisle;
    private ArrayList<EntityAisle> mListAisle;
    private ArrayList<String> mListNameAisle;

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



        List<EntityAisle> list = new ArrayList<EntityAisle>();

        //getAll le spinner
        mListAisle = mAisleDAO.getAll();
        mListNameAisle = modifyType(mListAisle);




        ArrayAdapter<EntityAisle> arrayAdapter = new ArrayAdapter<EntityAisle> (this.mActivity,android.R.layout.simple_list_item_1,list);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mProductAisle.setAdapter(arrayAdapter);
        mProductAisle.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //get selected item
                mSelectedAisle = (EntityAisle) parent.getSelectedItem();

                Log.d(TAG, "onItemClick: Selected item is : " + mSelectedAisle);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }


        });

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

}
