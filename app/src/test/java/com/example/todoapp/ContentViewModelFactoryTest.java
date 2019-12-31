package com.example.todoapp;

import android.app.Application;
import androidx.test.core.app.ApplicationProvider;
import com.example.todoapp.controller.ContentViewModel;
import com.example.todoapp.controller.ContentViewModelFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import static org.junit.Assert.*;

@RunWith(RobolectricTestRunner.class)
public class ContentViewModelFactoryTest {

    private ContentViewModelFactory viewModelFactory;

    @Before
    public void setUp() throws InstantiationException, IllegalAccessException{
        viewModelFactory = new ContentViewModelFactory(ApplicationProvider.getApplicationContext());
        assertNotNull("ContentViewModelFactory is null", viewModelFactory);
    }

    @Test
    public void testCreate_ViewModel() {
        assertTrue("Not creating the right object",
                    viewModelFactory.create(ContentViewModel.class) instanceof ContentViewModel);
    }
}