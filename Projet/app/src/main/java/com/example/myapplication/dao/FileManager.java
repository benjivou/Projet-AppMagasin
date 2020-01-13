package com.example.myapplication.dao;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;



public class FileManager<T>  {
    private static final String TAG = "FileManager";
    private final static GsonBuilder gsonBuilder = new GsonBuilder();
    private final static Gson gson = gsonBuilder.create();

    private Type mType;
    private String path;
    private ArrayList<T> list;

    public FileManager(String path) throws IOException {
        int intChar;

        this.list = new ArrayList<>(); // store the List of Object

        this.mType = new TypeToken<ArrayList<T>>(){}.getType();
        this.path = path;
        init();

    }


    /**
     * @brief Deserialize all elements from the file
     */
    public void init() throws IOException {
        /*
        Step 1 : Open the file
         */
        int intChar;
        ArrayList<T> list;
        String content="";
        File yourFile = new File(this.path);

        /* Step 2: read the number of elements in the file*/
        FileInputStream inFile = null;
        if(yourFile.exists()) {
            try {
                inFile = new FileInputStream(yourFile);

            /*
        Step 2 : Get by char the file content
            */

                while ((intChar = inFile.read()) != -1) {

                    content += (char) intChar;
                }

                try {
                    inFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

        /*
        Step 3 : Deserialize & open the access
         */


                Log.d(TAG, "init: \"in the file : " + content);
                this.list = gson.fromJson(content, this.mType);


            } catch (FileNotFoundException e) {
                Log.d(TAG, "init: (\"File U search was " + yourFile.toString());
                e.printStackTrace();
            }
        }
        else
        {
            // do nothing
        }




    }

    /**
     * return to the other program the list of all elements
     * @return
     */
    public ArrayList<T> read(){
        return this.list;
    }

    /**
     * update the Db
     */
    public void update(){
        File myFile = null;
        FileWriter outFile = null;

        /*
        Serialize
         */

        try {

            // write the contents on mySettings to the file

            gson.toJson(list, outFile);                         // save
            outFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public String toString() {
        return "FileManager{" +
                "mType=" + mType +
                ", path='" + path + '\'' +
                ", list=" + list +
                '}';
    }
}
