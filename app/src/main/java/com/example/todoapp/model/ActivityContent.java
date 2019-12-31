package com.example.todoapp.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Models the table of the database used to store TO-DO tasks.
 * @author KD
 * @version 1.0
 * @since 2019-12
 */
@Entity(tableName = "content_table")
public class ActivityContent {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    @ColumnInfo(name = "content")
    private String mContent;

    @ColumnInfo(name="isComplete")
    private Boolean isComplete;

    public ActivityContent(int id, @NonNull String mContent, Boolean complete){
        this.id = id;
        this.mContent = mContent;
        this.isComplete = complete;
    }

    public  String getMContent(){
        return this.mContent;
    }

    public int getId(){
        return this.id;
    }

    public Boolean getIsComplete(){return this.isComplete;}
}