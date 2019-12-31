package com.example.todoapp;

import android.content.Context;
import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.Observer;
import androidx.room.Room;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import com.example.todoapp.model.ActivityContent;
import com.example.todoapp.model.ContentDao;
import com.example.todoapp.model.ContentRoomDatabase;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.hamcrest.Matchers.empty;
import static org.mockito.Mockito.verify;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@RunWith(AndroidJUnit4.class)
public class ContentDaoTest {

    @Rule
    public TestRule testRule = new InstantTaskExecutorRule();

    private static final String CONTENT_TEXT = "Test content";
    private ContentDao dao;
    private ContentRoomDatabase db;

    @Mock
    private Observer<List<ActivityContent>> mockObserver;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        db = Room.inMemoryDatabaseBuilder(context, ContentRoomDatabase.class)
                .allowMainThreadQueries()
                .build();
        dao = db.contentDao();

    }

    @After
    public void tearDown(){
        db.close();
    }

    @Test
    public void testInsertContent(){
        //given
        ActivityContent insertTestContent = new ActivityContent(0, CONTENT_TEXT, false);
        dao.getToDoListContents().observeForever(mockObserver);
        //when
        dao.insertContent(insertTestContent);
        //then
        verify(mockObserver).onChanged(Collections.singletonList(insertTestContent));
    }

    @Test
    public void testDeleteAll(){
        //given
        ActivityContent testContent = new ActivityContent(0, CONTENT_TEXT, false);
        dao.getToDoListContents().observeForever(mockObserver);
        dao.insertContent(testContent);
        //when
        dao.deleteAll();
        //then
        assertThat(dao.getToDoListContents().getValue(), is(empty()));
    }

    @Test
    public void testDeleteRecordWithId(){
        //given
        ActivityContent testContent = new ActivityContent(1, CONTENT_TEXT, false);
        dao.getToDoListContents().observeForever(mockObserver);
        dao.insertContent(testContent);
        //when
        dao.deleteRecordWithId(1);
        //then
        assertThat(dao.getToDoListContents().getValue(), is(empty()));
    }

    @Test
    public void testGetToDoListContents(){
        //given
        ActivityContent testContent = new ActivityContent(1, CONTENT_TEXT, false);
        dao.insertContent(testContent);
        //then
        assertThat(Objects.requireNonNull(dao.getToDoListContents().getValue()).size(), is(1));
    }

    @Test
    public void testUpdateCompleteStatus(){
        //given
        ActivityContent testContent = new ActivityContent(1, CONTENT_TEXT, false);
        //when
        dao.updateCompleteStatus(1, true);
        //then
        assertThat(testContent.getIsComplete(), is(true));
    }
}