package com.example.todoapp;

import com.example.todoapp.model.ActivityContent;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class ActivityContentTest {

    private ActivityContent content;
    private static final String CONTENT_TEXT = "Test content";

    @Before
    public void setUp(){
        content = new ActivityContent(0, CONTENT_TEXT, false);
    }

    @Test
    public void testActivityContentIsNotNull(){
        assertNotNull("Object under test is null", content);
    }

    @Test
    public void testActivityContentFields(){
        assertTrue("Id is not defined", content.getId() == 0);
        assertTrue("Content value mismatch",content.getMContent().equals(CONTENT_TEXT));
        assertTrue("IsComplete value mismatch",content.getIsComplete().equals(false));
    }
}
