package com.example.todoapp.controller;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import java.lang.reflect.InvocationTargetException;

/**
 * Constructor of {@link ContentViewModel} class.
 * @author KD
 * @version 1.0
 * @since 2019-12
 */
public class ContentViewModelFactory implements ViewModelProvider.Factory {

    private Application app;

    public ContentViewModelFactory(Application anApp){
        this.app = anApp;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        try {
            return modelClass.getConstructor(Application.class)
                             .newInstance(app);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }
}
