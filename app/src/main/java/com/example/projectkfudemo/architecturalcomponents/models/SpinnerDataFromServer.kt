package com.example.projectkfudemo.architecturalcomponents.models

import com.example.projectkfudemo.User

class SpinnerDataFromServer {
    var serverRequestsByRx: ServerRequestsByRx ?= null

    fun setObject(user: User) {
        serverRequestsByRx = ServerRequestsByRx(user)
    }

    fun sendRequests() {
        serverRequestsByRx?.sendRequestForDataBySpinners()
    }

    fun setArraysForSpinner() {

    }


}