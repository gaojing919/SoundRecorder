package com.example.olivier.myapplication;


public interface OnDatabaseChangedListener{
    void onNewDatabaseEntryAdded();
    void onDatabaseEntryRenamed();
}