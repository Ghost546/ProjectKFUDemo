package com.example.projectkfudemo.viewmodels

import android.os.Bundle
import androidx.lifecycle.ViewModel
import com.example.projectkfudemo.SpinnerDataFromServer
import com.example.projectkfudemo.User

class ViewModelMainActivity(user: User): ViewModel() {


    init {

    }

    @Override
    override fun onCleared() {
        super.onCleared()
    }

    fun requestOnGetDataAboutSpinners() {
        var spinnerDataFromServer:SpinnerDataFromServer = SpinnerDataFromServer()
    }

}