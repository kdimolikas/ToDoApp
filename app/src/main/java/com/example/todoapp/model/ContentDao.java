package com.example.todoapp.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import java.util.List;

/**
 * Provides the access methods for the database.
 * @author KD
 * @version 1.0
 * @since 2019-12
 */
@Dao
public abstract class ContentDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public abstract void insertContent(ActivityContent activityContent);

    @Query("DELETE FROM content_table")
    public abstract void deleteAll();

    @Query("DELETE FROM content_table WHERE id = :id")
    public abstract void deleteRecordWithId(int id);

    @Query("SELECT * from content_table")
    public abstract LiveData<List<ActivityContent>> getToDoListContents();

    @Query("UPDATE content_table SET isComplete = :completeStatus WHERE id = :id")
    public abstract void updateCompleteStatus(int id, Boolean completeStatus);
}