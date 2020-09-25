package com.example.projectkfudemo.architecturalcomponents.viewmodels

import androidx.lifecycle.ViewModel
import com.example.projectkfudemo.architecturalcomponents.models.SpinnerDataFromServer
import com.example.projectkfudemo.User

class ViewModelMainActivity(user: User): ViewModel() {


    init {

    }

    @Override
    override fun onCleared() {
        super.onCleared()
    }

    fun requestOnGetDataAboutSpinners() {
        var spinnerDataFromServer: SpinnerDataFromServer = SpinnerDataFromServer()
    }

}