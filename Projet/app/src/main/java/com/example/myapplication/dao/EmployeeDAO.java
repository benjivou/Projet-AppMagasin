package com.example.myapplication.dao;

import com.example.myapplication.model.EntityEmployee;

import java.io.IOException;

import static com.example.myapplication.config.ConfigDAO.*;

public class EmployeeDAO extends Dao<EntityEmployee>{

    private static final String path = FOLDER + EMPLOYEE;

    public EmployeeDAO() {
        try {
            this.mFileManager = new FileManager<>(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
