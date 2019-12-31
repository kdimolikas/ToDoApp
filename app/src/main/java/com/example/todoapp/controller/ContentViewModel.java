package com.example.todoapp.controller;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.todoapp.model.ActivityContent;
import com.example.todoapp.model.ContentRepository;

import java.util.List;

/**
 * Provides data to UI components and survives layout changes.
 * @author KD
 * @version 1.0
 * @see AndroidViewModel
 * @since 2019-12
 */
public class ContentViewModel extends AndroidViewModel {

    private ContentRepository repository=null;
    private LiveData<List<ActivityContent>> allContent=null;

    public ContentViewModel(Application app){
        super(app);
        repository = new ContentRepository(app);
        allContent = repository.getAllContent();
    }

    public LiveData<List<ActivityContent>> getAllContent(){
        return allContent;
    }

    public void insertContent(ActivityContent activityContent){
        repository.insertContent(activityContent);
    }

    public void deleteAllContent(){
        repository.deleteAllContent();
    }

    public void deleteRecordWithId(int anId){repository.deleteRecordWithId(anId);}

    public void updateCompleteStatus(int recordId, Boolean status){repository.updateCompleteStatus(recordId, status);}
}