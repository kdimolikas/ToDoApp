package com.example.todoapp;


import androidx.test.core.app.ApplicationProvider;
import com.example.todoapp.controller.ActivityContentAdapter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.robolectric.RobolectricTestRunner;


import static org.junit.Assert.*;

@RunWith(RobolectricTestRunner.class)
public class ActivityContentAdapterTest {

    private ActivityContentAdapter contentAdapter;

    @Before
    public void setUp() {
        contentAdapter = new ActivityContentAdapter(ApplicationProvider.getApplicationContext());
    }

    @Test
    public void adapterIsNotNull(){
        assertNotNull("Adapter is null", contentAdapter);
    }
}
