package com.example.projectkfudemo.architecturalcomponents.models

import android.util.Log
import com.example.projectkfudemo.User
import com.example.projectkfudemo.architecturalcomponents.viewmodels.mainactivity.MyViewModelMainActivityInterface
import com.example.projectkfudemo.forjson.SearchDeclarer
import com.example.projectkfudemo.forjson.SearchWorkers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SpinnerDataFromServer(_myViewModelMainActivityInterface: MyViewModelMainActivityInterface) {
    private val TAG = this.javaClass.simpleName

    var myViewModelMainActivityInterface = _myViewModelMainActivityInterface

    init {
        Log.i(TAG, "!объект spinnerDataFromServer создался!")
    }

    var serverRequestsByRx: ServerRequestsByRx ?= null

    var searchDeclarers: List<SearchDeclarer> = listOf()
    var searchWorkers: List<SearchWorkers> = listOf()

    fun setObject(user: User) {
        serverRequestsByRx = ServerRequestsByRx(user)
        Log.i(TAG, "!spinnerDataFromServer принял user!")
        Log.i(TAG, "!в serverRequestsByRx отправил user!")
    }

    fun sendRequests() { //метод запрашивает метод на отправку запросов
        serverRequestsByRx?.sendRequestForDataBySpinners()
        Log.i(TAG, "!отправил запрос на получение данных для Spinners")
    }

    fun waitData() {
        GlobalScope.launch {
            while (serverRequestsByRx?.searchDeclarers == null || serverRequestsByRx?.searchWorkers == null) {
                Log.i(TAG, "!Массивы пусты(SpinnerDataFromServer)!")
            }
            delay(5000)
            if (serverRequestsByRx?.searchDeclarers!=null && serverRequestsByRx?.searchWorkers != null) {
                Log.i(TAG, "!Массивы пришли(SpinnerDataFromServer)!")
                if (serverRequestsByRx?.searchDeclarers != null) {
                    Log.i(TAG, "!searchDeclarers пришёл(SpinnerDataFromServer)!")
                    searchDeclarers = serverRequestsByRx?.searchDeclarers!!
                }
                if (serverRequestsByRx?.searchWorkers != null) {
                    Log.i(TAG, "!searchWorkers пришёл(SpinnerDataFromServer)!")
                    searchWorkers = serverRequestsByRx?.searchWorkers!!
                }
                myViewModelMainActivityInterface.setListsData()
            }

        }
    }

}