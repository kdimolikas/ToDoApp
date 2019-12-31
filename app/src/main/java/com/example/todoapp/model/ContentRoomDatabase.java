package com.example.todoapp.model;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * An implementation of Room Database.
 * @author KD
 * @version 1.0
 * @see RoomDatabase
 * @since 2019-12
 */
@Database(entities = {ActivityContent.class}, version = 1, exportSchema = false)
public abstract class ContentRoomDatabase extends RoomDatabase {

    public abstract ContentDao contentDao();

    private static volatile ContentRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static ContentRoomDatabase getInstance(final Context context){
        if (INSTANCE == null){
            synchronized (ContentRoomDatabase.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                               ContentRoomDatabase.class, "content_database")
                               .addCallback(sRoomDBCallback)
                               .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDBCallback = new RoomDatabase.Callback(){
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db){
            super.onOpen(db);
        }
    };
}