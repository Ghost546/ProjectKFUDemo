package com.example.projectkfudemo.architecturalcomponents.models

import android.util.Log
import com.example.projectkfudemo.parametrclasses.User
import com.example.projectkfudemo.architecturalcomponents.viewmodels.ViewModelInterface
import com.example.projectkfudemo.parametrclasses.forjson.SearchDeclarerList
import com.example.projectkfudemo.parametrclasses.forjson.SearchWorkersList

class SpinnerDataFromServer(_viewModelInterface: ViewModelInterface): ModelsByRequestToServer {
    override val TAG = this.javaClass.simpleName

    var viewModelMainActivityInterface = _viewModelInterface

    init {
        Log.i(TAG, "!объект spinnerDataFromServer создался!")
    }

    override var serverRequestsByRx: ServerRequestsByRx ?= null

    var searchDeclarers: SearchDeclarerList? = null
    var searchWorkers: SearchWorkersList? = null

    fun setObject(user: User) {
        setObjectByUserAndInterface(this, user)
    }

    override fun sendRequestCurrentTask() {
        //код не нужен
    }

    override fun sendRequestMyTask() {
        //код не нужен
    }

    override fun sendRequest() {    //метод запрашивает метод на отправку запросов
        Log.i(TAG, "!отправил запрос на получение данных для Spinners")
        serverRequestsByRx?.sendRequestForDataBySpinners()
    }

    override fun setData() {
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
            viewModelMainActivityInterface.changedData()
        }
    }

}