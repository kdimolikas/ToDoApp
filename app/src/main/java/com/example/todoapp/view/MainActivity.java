package com.example.todoapp.view;

import android.content.Intent;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.todoapp.*;
import com.example.todoapp.controller.ActivityContentAdapter;
import com.example.todoapp.controller.ContentViewModel;
import com.example.todoapp.controller.ContentViewModelFactory;
import com.example.todoapp.model.ActivityContent;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


import java.util.List;
import java.util.Objects;

/**
 * The main UI of this app.
 * @author KD
 * @version 1.0
 * @since 2019-12
 */
public class MainActivity extends AppCompatActivity {

    public static final int ADD_CONTENT_ACTIVITY_REQUEST_CODE = 1;
    private ContentViewModel mViewModel;
    private ContentViewModelFactory viewModelFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        final ActivityContentAdapter contentAdapter = new ActivityContentAdapter(this);
        recyclerView.setAdapter(contentAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        viewModelFactory = new ContentViewModelFactory(this.getApplication());
        mViewModel = new ViewModelProvider(this, viewModelFactory).get(ContentViewModel.class);

        mViewModel.getAllContent().observe(this, new Observer<List<ActivityContent>>() {
                    @Override
                    public void onChanged(List<ActivityContent> activityContents) {
                        //Update the cached copy of contents in the adapter
                        contentAdapter.setContents(activityContents);
                    }
        });

        contentAdapter.setOnItemClickListener(new ActivityContentAdapter.OnItemClickListener() {
            @Override
            public void onDeleteClick(int position) {
                ActivityContent content2Remove = Objects.requireNonNull(mViewModel.getAllContent().getValue()).get(position);
                int contentId = content2Remove.getId();
                mViewModel.deleteRecordWithId(contentId);
                contentAdapter.removeContent(position);
            }

            @Override
            public void onTextViewClick(int position) {
                ActivityContent contentClicked = Objects.requireNonNull(mViewModel.getAllContent().getValue()).get(position);
                int contentId = contentClicked.getId();
                Boolean status = contentClicked.getIsComplete();
                mViewModel.updateCompleteStatus(contentId, !status);
                contentAdapter.updateContentAt(position, contentClicked);
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, AddContentActivity.class);
            startActivityForResult(intent, ADD_CONTENT_ACTIVITY_REQUEST_CODE);
        });

        final Button deleteAllBtn = findViewById(R.id.button_deleteAll);
        deleteAllBtn.setOnClickListener(view -> {
            if (contentAdapter.getItemCount() > 0) {
                mViewModel.deleteAllContent();
                contentAdapter.removeAllContents();
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == ADD_CONTENT_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK){
            ActivityContent content = new ActivityContent(0, data.getStringExtra(AddContentActivity.EXTRA_REPLY), false);
            mViewModel.insertContent(content);
        }else{
            Toast.makeText(
                    getApplicationContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG
            );
        }
    }
}