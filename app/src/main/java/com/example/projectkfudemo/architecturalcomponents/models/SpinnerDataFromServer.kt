package com.example.projectkfudemo.architecturalcomponents.models

import android.util.Log
import com.example.projectkfudemo.parametrclasses.User
import com.example.projectkfudemo.architecturalcomponents.viewmodels.ViewModelInterface
import com.example.projectkfudemo.parametrclasses.forjson.SearchDeclarerList
import com.example.projectkfudemo.parametrclasses.forjson.SearchWorkersList
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SpinnerDataFromServer(_viewModelInterface: ViewModelInterface): ModelsByRequestToServer {
    override val TAG = this.javaClass.simpleName

    var viewModelMainActivityInterface = _viewModelInterface

    init {
        Log.i(TAG, "!объект spinnerDataFromServer создался!")
    }

    override var serverRequestsByRx: ServerRequestsByRx ?= null

    var searchDeclarers: SearchDeclarerList? = null
    var searchWorkers: SearchWorkersList? = null

    @Override
    override fun setObject(user: User) {
        super.setObject(user)
    }

    override fun sendRequest() { //метод запрашивает метод на отправку запросов
        Log.i(TAG, "!отправил запрос на получение данных для Spinners")
        serverRequestsByRx?.sendRequestForDataBySpinners()
    }

    override fun setData() {

    }

    fun waitData() {
        GlobalScope.launch {
            Log.i(TAG, "!Массивы пусты(SpinnerDataFromServer)!")
            while (serverRequestsByRx?.searchDeclarers == null || serverRequestsByRx?.searchWorkers == null) {

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
                viewModelMainActivityInterface.setListsData()
            }

        }
    }

}