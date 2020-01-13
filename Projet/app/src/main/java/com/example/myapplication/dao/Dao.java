package com.example.myapplication.dao;

import android.app.Service;
import android.content.Context;

import com.example.myapplication.model.SearchebleImplement;

import java.util.ArrayList;

public abstract class Dao <T extends SearchebleImplement> /*extends Service*/ {
     protected FileManager<T> mFileManager ;
     protected String mPath;

     public void add(T t){
          ArrayList<T> list =  this.mFileManager.read();
          list.add(t);
          this.mFileManager.update();
     }

     public ArrayList<T> findByName(String name){
          ArrayList<T> list = new ArrayList<>();
          for( T t : this.mFileManager.read()){
               // find the good device
               if (t.getName().equals(name)){
                    list.add(t);
               }
          }
          return list;
     }

     /**
      *
      * @param t Need a valid element
      */
     public void remove(T t){

          this.mFileManager.read().remove(t);

          this.mFileManager.update();
     }

     /**
      * get the number of element we have in the BDD
      * @return
      */
     public int getSize(){
          return this.mFileManager.read().size();
     }


}
