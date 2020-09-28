package com.example.projectkfudemo.architecturalcomponents.viewmodels

import androidx.lifecycle.ViewModel
import com.example.projectkfudemo.architecturalcomponents.models.SpinnerDataFromServer
import com.example.projectkfudemo.User

class ViewModelMainActivity(_user: User): ViewModel() {

    var user: User ?= null

    init {
        user = _user
    }


    @Override
    override fun onCleared() {
        super.onCleared()
    }

    fun requestOnGetDataAboutSpinners() {
        var spinnerDataFromServer: SpinnerDataFromServer = SpinnerDataFromServer()
    }

}