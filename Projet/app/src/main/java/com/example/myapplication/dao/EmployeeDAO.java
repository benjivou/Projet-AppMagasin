package com.example.myapplication.dao;

import android.content.Context;

import com.example.myapplication.model.EntityEmployee;

import java.io.IOException;

import static com.example.myapplication.config.ConfigDAO.*;

public class EmployeeDAO extends Dao<EntityEmployee>{



    public EmployeeDAO() {
        this.mPath = EMPLOYEE;
        try {
            this.mFileManager = new FileManager<>(this.mPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
