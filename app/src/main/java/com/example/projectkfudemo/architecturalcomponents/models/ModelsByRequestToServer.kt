package com.example.projectkfudemo.architecturalcomponents.models

import android.util.Log
import com.example.projectkfudemo.parametrclasses.User

interface ModelsByRequestToServer {
    val TAG: String

    var serverRequestsByRx: ServerRequestsByRx?

    fun setObject(user: User) {
        serverRequestsByRx = ServerRequestsByRx(user)
        Log.i(TAG, "!spinnerDataFromServer принял user!")
        Log.i(TAG, "!в serverRequestsByRx отправил user!")
    }

    fun sendRequest()

    fun waitData() {

    }
}