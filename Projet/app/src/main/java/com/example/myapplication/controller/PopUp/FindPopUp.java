package com.example.myapplication.controller.PopUp;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.myapplication.R;
import com.example.myapplication.controller.PopUp.abstractpopup.MainPopUp;
import com.example.myapplication.controller.util.button.listActivity.ButtonPanel;
import com.example.myapplication.model.EntityEmployee;

/**
 * This class allow us to manage the find pop-up
 * Created by Benjamin Vouillon on 26,January,2020
 */
public class FindPopUp extends MainPopUp {
    private static final String TAG = "FindPopUp";
    private static String TITLE_PRODUCT = "Recherche par nom de produit";
    private static String TITLE_EMPLOYEE = "Recherche par nom d’utilisateur";

    TextView mTextView ;
    EditText mEditText ;


    public FindPopUp(EntityEmployee entityEmployee, ButtonPanel activity) {
        super(entityEmployee, activity);

    }
    /**
     * This method allow us to bind the attribute with the view
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_find);

        // Binding
        mTextView = findViewById(R.id.title);
        mEditText = findViewById(R.id.txtId);
        mButtonSubmit = findViewById(R.id.btnSubmit);

        // set the text of the app
        if(this.mCOwner.isModeProductIsClicked()){
            mTextView.setText(TITLE_PRODUCT);
        }else {
            mTextView.setText(TITLE_EMPLOYEE);
        }

    }
    /**
     * This method contain all action will do once you submit
     */
    @Override
    protected void onSubmit() {
        // refresh the list of elements
        if(this.mCOwner.isModeProductIsClicked()){
            this.mCOwner.setArticleArrayAdapter(
                    mArticleDAO.getByName(this.mEditText.getText().toString())
            );
            Log.d(TAG, "onSubmit: the research of Product is done");
        }else {
            this.mCOwner.setEmployeeArrayAdapter(
                    mEmployeeDAO.getByName(this.mEditText.getText().toString())
            );
        }
    }

    @Override
    protected boolean isAllFieldsValide() {
        return true;
    }
}
