package com.example.projectkfudemo.architecturalcomponents.models

import android.util.Log
import com.example.projectkfudemo.parametrclasses.User

interface ModelsByRequestToServer {
    val tag: String

    //класс модели
    var serverRequestsByRx: ServerRequestsByRx?

    fun setObjectByUser(user: User) {
        serverRequestsByRx = ServerRequestsByRx(user)
        Log.i(tag, "!spinnerDataFromServer принял user! Конструктор с юзером")
        Log.i(tag, "!в serverRequestsByRx отправил user!")
    }

    fun setObjectByUserAndInterface(modelsByRequestToServer: ModelsByRequestToServer, user: User) {
        serverRequestsByRx = ServerRequestsByRx(modelsByRequestToServer, user)
        Log.i(tag, "!spinnerDataFromServer принял user! Конструктор с интерфейсом и юзером")
        Log.i(tag, "!в serverRequestsByRx отправил user!")
    }

    //отправка запроса в модель из экрана CurrentTask
    fun sendRequestCurrentTask()

    //отправка запроса в модель из экрана MyTask
    fun sendRequestMyTask()

    //отправка запроса в модель, общая
    fun sendRequest()

    //уведомление класса обл изменении данных в модели
    fun setData()
}