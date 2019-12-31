package com.example.todoapp.controller;

import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;
import android.widget.ImageButton;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.todoapp.model.ActivityContent;
import com.example.todoapp.R;

import java.util.Collections;
import java.util.List;

/**
 * Provides binding from the data set to the UI component displayed in {@link RecyclerView}.
 * @author KD
 * @version 1.0
 * @see RecyclerView.Adapter
 * @since 2019-12
 */
public class ActivityContentAdapter extends RecyclerView.Adapter<ActivityContentAdapter.ActivityContentViewHolder> {

    private LayoutInflater mInflater;
    private List<ActivityContent> contents;
    private OnItemClickListener mListener;

    public  interface OnItemClickListener{
        void onDeleteClick(int position);
        void onTextViewClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){mListener = listener;}

    class ActivityContentViewHolder extends RecyclerView.ViewHolder{

        private final CheckedTextView checkedTextView;
        private final ImageButton deleteBtn;

        private ActivityContentViewHolder(View itemView, OnItemClickListener listener){
            super(itemView);
            this.checkedTextView = itemView.findViewById(R.id.checkedTextView);
            this.deleteBtn = itemView.findViewById(R.id.imageBtn_delete);
            checkedTextView.setOnClickListener(v ->{
                if (listener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION){
                        listener.onTextViewClick(position);
                    }
                }

            });
            deleteBtn.setOnClickListener(v ->{
                if (listener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION){
                        listener.onDeleteClick(position);
                    }
                }

            });
        }

        public void bindData(@NonNull ActivityContent content){
            checkedTextView.setText(content.getMContent());
            checkedTextView.setChecked(content.getIsComplete());
            Boolean isChecked = checkedTextView.isChecked();
            if (isChecked){
                checkedTextView.setCheckMarkDrawable(R.drawable.ic_check_circle_lime_24dp);
            }else{
                checkedTextView.setCheckMarkDrawable(null);
            }
        }
    }

    public ActivityContentAdapter(Context context){
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ActivityContentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View itemView = mInflater.inflate(viewType, parent, false);
        return new ActivityContentViewHolder(itemView, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ActivityContentViewHolder viewHolder, int pos){
        if (contents != null){
            ActivityContent content = contents.get(pos);
            viewHolder.bindData(content);
        }else{
            viewHolder.checkedTextView.setText("Nothing to do");
        }
    }

    @Override
    public int getItemViewType(int position){
        return R.layout.recyclerview_item;
    }

    @Override
    public int getItemCount(){
        if (contents != null){
            return contents.size();
        }else{
            return 0;
        }
    }

    public void setContents(List<ActivityContent> contents){
        this.contents = contents;
        notifyDataSetChanged();
    }

    public void removeContent(int position){
        contents.remove(position);
        notifyItemRemoved(position);
    }

    public void updateContentAt(int position, ActivityContent contentClicked) {
        contents.set(position, contentClicked);
        notifyItemChanged(position);
    }

    public void removeAllContents(){
        contents.removeAll(contents);
    }
}