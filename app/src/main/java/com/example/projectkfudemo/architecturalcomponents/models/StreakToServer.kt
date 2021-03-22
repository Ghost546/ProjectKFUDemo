package com.example.projectkfudemo.architecturalcomponents.models

import android.util.Log
import com.example.projectkfudemo.parametrclasses.User

interface StreakToServer {
    val TAG: String

    var serverRequestsByRx: ServerRequestsByRx?

    fun setObjectByUser(user: User?) {
        serverRequestsByRx = ServerRequestsByRx(user)
        Log.i(TAG, "!spinnerDataFromServer принял user! Конструктор с юзером")
        Log.i(TAG, "!в serverRequestsByRx отправил user!")
    }

    fun setObjectByUserAndInterface(streakToServer: StreakToServer, user: User) {
        serverRequestsByRx = ServerRequestsByRx(streakToServer, user)
        Log.i(TAG, "!spinnerDataFromServer принял user! Конструктор с интерфейсом и юзером")
        Log.i(TAG, "!в serverRequestsByRx отправил user!")
    }

    fun setObjectByUserAndInterface(streakByRequestToServer: StreakByRequestToServer, user: User) {
        serverRequestsByRx = ServerRequestsByRx(streakByRequestToServer, user)
        Log.i(TAG, "!spinnerDataFromServer принял user! Конструктор с интерфейсом и юзером")
        Log.i(TAG, "!в serverRequestsByRx отправил user!")
    }

    fun setObject(user: User) {
        setObjectByUserAndInterface(this, user)
    }

    fun sendRequest()
    fun setData()
}