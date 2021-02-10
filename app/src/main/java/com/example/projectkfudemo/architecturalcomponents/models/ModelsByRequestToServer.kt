package com.example.projectkfudemo.architecturalcomponents.models

import android.util.Log
import com.example.projectkfudemo.parametrclasses.User
import com.example.projectkfudemo.requests.RequestList

interface ModelsByRequestToServer {
    val TAG: String

    var serverRequestsByRx: ServerRequestsByRx?

    fun setObjectByUser(user: User) {
        serverRequestsByRx = ServerRequestsByRx(user)
        Log.i(TAG, "!spinnerDataFromServer принял user! Конструктор с юзером")
        Log.i(TAG, "!в serverRequestsByRx отправил user!")
    }

    fun setObjectByUserAndInterface(modelsByRequestToServer: ModelsByRequestToServer, user: User) {
        serverRequestsByRx = ServerRequestsByRx(modelsByRequestToServer, user)
        Log.i(TAG, "!spinnerDataFromServer принял user! Конструктор с интерфейсом и юзером")
        Log.i(TAG, "!в serverRequestsByRx отправил user!")
    }

    fun sendRequestCurrentTask()

    fun sendRequestMyTask()



    fun setData()
}