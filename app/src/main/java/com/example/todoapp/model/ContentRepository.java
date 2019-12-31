package com.example.todoapp.model;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import java.util.List;

/**
 * Provides access to {@link ContentDao} class.
 * @author KD
 * @version 1.0
 * @since 2019-12
 */
public class ContentRepository {

    private ContentDao aContentDao;
    private LiveData<List<ActivityContent>> allContent;

    public ContentRepository(@NonNull Application app){
        ContentRoomDatabase database = ContentRoomDatabase.getInstance(app.getApplicationContext());
        aContentDao = database.contentDao();
        allContent = aContentDao.getToDoListContents();
    }

    public LiveData<List<ActivityContent>> getAllContent(){
        return allContent;
    }

    public void insertContent(ActivityContent activityContent){
        ContentRoomDatabase.databaseWriteExecutor.execute(()->{
            aContentDao.insertContent(activityContent);
        });
    }

    public void deleteAllContent(){
        ContentRoomDatabase.databaseWriteExecutor.execute(()->{
            aContentDao.deleteAll();
        });
    }

    public void deleteRecordWithId(int id){
        ContentRoomDatabase.databaseWriteExecutor.execute((()->{
            aContentDao.deleteRecordWithId(id);
        }));
    }

    public void updateCompleteStatus(int recordId, Boolean status){
        ContentRoomDatabase.databaseWriteExecutor.execute((()->{
            aContentDao.updateCompleteStatus(recordId, status);
        }));
    }
}