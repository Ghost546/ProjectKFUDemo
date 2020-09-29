package com.example.projectkfudemo.architecturalcomponents.viewmodels

import androidx.lifecycle.ViewModel
import com.example.projectkfudemo.architecturalcomponents.models.SpinnerDataFromServer
import com.example.projectkfudemo.User
import com.example.projectkfudemo.architecturalcomponents.livadatas.LiveDataSearchDeclarers
import com.example.projectkfudemo.architecturalcomponents.livadatas.LiveDataSearchWorkers

class ViewModelMainActivity(_user: User): ViewModel() {

    var user: User ?= null  //объект для хранения
    var spinnerDataFromServer: SpinnerDataFromServer ?= null //объект отправляющий запросы
    var liveDataSearchDeclarers: LiveDataSearchDeclarers = LiveDataSearchDeclarers() //объект
    var liveDataSearchWorkers: LiveDataSearchWorkers = LiveDataSearchWorkers()

    init {
        user = _user
        spinnerDataFromServer = SpinnerDataFromServer()
    }

    @Override
    override fun onCleared() {
        super.onCleared()
    }

    fun setObjectForRequests() {
        user?.let { spinnerDataFromServer?.setObject(user!!) }
    }

    fun requestOnSetDataAboutSpinners() {//методу достаточно знать что так идёт запрос на получение данных с сервера
        spinnerDataFromServer?.sendRequests()
    }

}