package com.example.projectkfudemo.architecturalcomponents.ui.currenttask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CurrentTaskViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public CurrentTaskViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This current task fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}