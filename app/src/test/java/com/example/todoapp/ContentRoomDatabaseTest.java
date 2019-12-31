package com.example.todoapp;

import androidx.test.core.app.ApplicationProvider;
import com.example.todoapp.model.ContentRoomDatabase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import static org.junit.Assert.*;

@RunWith(RobolectricTestRunner.class)
public class ContentRoomDatabaseTest {

    private ContentRoomDatabase database;

    @Before
    public void setUp(){
        database = ContentRoomDatabase.getInstance(ApplicationProvider.getApplicationContext());
    }

    @Test
    public void testDatabaseIsNotNull(){
        assertNotNull("Object under test is null", database);
    }
}