package com.example.myapplication.controller.util.button.listActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;

import com.example.myapplication.config.ConfigFront;
import com.example.myapplication.controller.PopUp.ShowEmployeePopUp;
import com.example.myapplication.controller.PopUp.ShowProductPopUp;
import com.example.myapplication.dao.ArticleDAO;
import com.example.myapplication.model.EntityArticle;
import com.example.myapplication.model.EntityEmployee;

import java.util.ArrayList;

public abstract class ControlOnglet extends ButtonOnglet implements AdapterView.OnItemClickListener {

    private static final String TAG = "ControlOnglet";

    protected ListView mListOf;

    private ArrayAdapter<EntityEmployee> mEmployeeArrayAdapter;
    private ArrayAdapter<EntityArticle> mArticleArrayAdapter;

    private ArrayList<EntityArticle> mArticleList;
    private ArrayList<EntityEmployee> mEmployeeList;

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
    protected void onStart() {
        super.onStart();
        mListOf.setOnItemClickListener(this);
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
        this.mArticleList = mArticleDAO.getAll();
        setArticleArrayAdapter(  this.mArticleList);
    }
    public void refreshEmployeeList(){
        // request all employee
        this.mEmployeeList = mCurrentUser.getAllEmployee();
        setEmployeeArrayAdapter( this.mEmployeeList);


    }

    public void refreshAll(){
        Log.d(TAG, "refreshAll: U refresh");

        if (isModeProductIsClicked())
            refreshProductList();
        else
            refreshEmployeeList();

    }

    public void setEmployeeArrayAdapter(ArrayList<EntityEmployee>  employeeArrayList) {
        mEmployeeArrayAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                employeeArrayList
        );

        mListOf.setAdapter(mEmployeeArrayAdapter);
        mListOf.setOnItemClickListener(this);
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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.d(TAG, "onItemClick: list clicked");
        // depending of the type of the current list
        if(isModeProductIsClicked()){
            EntityArticle entityArticle = this.mArticleList.get(position);

            ShowProductPopUp pop =  new ShowProductPopUp(
                this.getEntityEmployee(),
                    this,
                    entityArticle);
            pop.show();

        }
        else{
            EntityEmployee entityEmployee = this.mEmployeeList.get(position);
            Log.d(TAG, "onItemClick: u select "+ entityEmployee.getIdEmployee());
            ShowEmployeePopUp pop = new ShowEmployeePopUp(
                    this.getEntityEmployee(),
                    this,
                    entityEmployee
                    );

            pop.show();
        }
    }
}
