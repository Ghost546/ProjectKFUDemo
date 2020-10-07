package com.example.projectkfudemo.architecturalcomponents.models

import com.example.projectkfudemo.User
import com.example.projectkfudemo.forjson.SearchDeclarer
import com.example.projectkfudemo.forjson.SearchWorkers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SpinnerDataFromServer {
    var serverRequestsByRx: ServerRequestsByRx ?= null

    lateinit var searchDeclarers: List<SearchDeclarer>
    lateinit var searchWorkers: List<SearchWorkers>

    fun setObject(user: User) {
        serverRequestsByRx = ServerRequestsByRx(user)
    }

    fun sendRequests() { //метод запрашивает метод на отправку запросов
        serverRequestsByRx?.sendRequestForDataBySpinners()
    }

    fun setArraysForSpinner() {

    }

    fun waitData() {
        GlobalScope.launch {
            while (serverRequestsByRx?.searchDeclarers == null && serverRequestsByRx?.searchWorkers == null) {
                println("Массивы пусты")
            }
            if (serverRequestsByRx?.searchDeclarers!=null && serverRequestsByRx?.searchWorkers != null) {
                searchDeclarers = serverRequestsByRx?.searchDeclarers!!
                searchWorkers = serverRequestsByRx?.searchWorkers!!
            }
        }
    }

}