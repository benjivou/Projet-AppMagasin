package com.example.myapplication.model;

public interface Searchable<T> {
    public String getName();
    public T getUniqueKey();
}
