package com.example.myapplication.dao;

import android.content.Context;
import android.util.Log;

import com.example.myapplication.model.EntityAisle;
import com.example.myapplication.model.EntityArticle;
import com.example.myapplication.model.EntityEmployee;

/**
 * The goal is to Manage the permission of user
 * when he access to the DB
 */
public abstract class ManagerDAO {


    private static final String TAG = "ManagerDAO";

    protected RoleDAO mCurrentRole;
    protected EntityAisle mCurrentAisle;
    protected Context mExecutionContext;

    public ManagerDAO(RoleDAO mCurrentRole, EntityAisle mCurrentAisle, Context mExecutionContext) {
        this.mCurrentRole = mCurrentRole;
        this.mCurrentAisle = mCurrentAisle;
        this.mExecutionContext = mExecutionContext;
    }



    /**
     * Say what product the client can see
     * @return the String of SQL constrint of the user
     */
    protected boolean modifyArticlesRules(EntityArticle article){
        boolean canI = false;

        // admin rule
        if(this.mCurrentRole.equals(RoleDAO.ADMIN)){
            canI = true;
        }
        // normal employee rules
        if(this.mCurrentRole.equals(RoleDAO.USER)){
            // verify the Aisle
            canI = mCurrentAisle.getName().equals(article.getEntityAisle().getName());
        }

        Log.d(TAG, "modifyArticlesRules: the user can modify :" + article.getName()  + " , " + canI);
        return canI;
    }

}
