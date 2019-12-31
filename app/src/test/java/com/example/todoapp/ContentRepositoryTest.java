package com.example.todoapp;

import androidx.test.core.app.ApplicationProvider;

import com.example.todoapp.model.ContentRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import static org.junit.Assert.*;


@RunWith(RobolectricTestRunner.class)
public class ContentRepositoryTest {

    private ContentRepository repo;

    @Before
    public void setUp(){
        repo = new ContentRepository(ApplicationProvider.getApplicationContext());
    }

    @Test
    public void testRepositoryIsNotNull(){
        assertNotNull("Object under test is null", repo);
    }

}