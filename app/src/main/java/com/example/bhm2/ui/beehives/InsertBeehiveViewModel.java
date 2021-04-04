package com.example.bhm2.ui.beehives;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class InsertBeehiveViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public InsertBeehiveViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is slideshow fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
