package com.example.todoapp.view;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.todoapp.R;

/**
 * The UI for adding new TO-DO tasks.
 * @author KD
 * @version 1.0
 * @since 2019-12
 */
public class AddContentActivity extends AppCompatActivity {

    public final static String EXTRA_REPLY = "com.example.android.wordlistsql.REPLY";

    private EditText mEditContentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_content);
        mEditContentView = findViewById(R.id.edit_content);

        final Button saveButton = findViewById(R.id.button_save);
        saveButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(mEditContentView.getText().toString())){
                    setResult(RESULT_CANCELED, replyIntent);
                }else{
                    String content = mEditContentView.getText().toString();
                    replyIntent.putExtra(EXTRA_REPLY, content);
                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });
    }
}
