package com.example.myapplication.controller.util.button.listActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;

import com.example.myapplication.config.ConfigFront;
import com.example.myapplication.dao.ArticleDAO;
import com.example.myapplication.dao.EmployeeDAO;
import com.example.myapplication.model.EntityArticle;
import com.example.myapplication.model.EntityEmployee;

import java.util.ArrayList;

public abstract class ControlOnglet extends ButtonOnglet{

    private static final String TAG = "ControlOnglet";

    protected ListView mListOf;

    private ArrayAdapter<EntityEmployee> mEmployeeArrayAdapter;
    private ArrayAdapter<EntityArticle> mArticleArrayAdapter;

    private ArticleDAO mArticleDAO;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mArticleDAO = new ArticleDAO(
                ConfigFront.SYSTEM_ROLE,
                ConfigFront.SYSTEM_AISLE,
                this);
    }

    @Override
    protected void onOngletEmployee() {
        refreshEmployeeList();
    }

    @Override
    protected void onOngletProduct() {
        refreshProductList();
    }

    public void refreshProductList(){
        // request all article
        setArticleArrayAdapter(  mArticleDAO.getAll());


    }
    public void refreshEmployeeList(){
        // request all employee
        setEmployeeArrayAdapter( mCurrentUser.getAllEmployee());


    }

    public void refreshAll(){
        Log.d(TAG, "refreshAll: U refresh");
        refreshEmployeeList();
        refreshProductList();
    }

    public void setEmployeeArrayAdapter(ArrayList<EntityEmployee>  employeeArrayList) {
        mEmployeeArrayAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                employeeArrayList
        );

        mListOf.setAdapter(mEmployeeArrayAdapter);
    }

    public void setArticleArrayAdapter(ArrayList<EntityArticle> articleArrayList) {
        mArticleArrayAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                articleArrayList
        );
        // display
        mListOf.setAdapter(mArticleArrayAdapter);


    }
}
