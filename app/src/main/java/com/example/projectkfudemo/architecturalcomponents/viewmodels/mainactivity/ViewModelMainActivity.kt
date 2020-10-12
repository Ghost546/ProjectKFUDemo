package com.example.projectkfudemo.architecturalcomponents.viewmodels.mainactivity

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.projectkfudemo.parametrclasses.User
import com.example.projectkfudemo.architecturalcomponents.livadatas.LiveDataSearchDeclarers
import com.example.projectkfudemo.architecturalcomponents.livadatas.LiveDataSearchWorkers
import com.example.projectkfudemo.architecturalcomponents.models.SpinnerDataFromServer

class ViewModelMainActivity: ViewModel(), ViewModelMainActivityInterface {
    private val TAG = this.javaClass.simpleName

    var user: User?= null  //объект для хранения
    var spinnerDataFromServer: SpinnerDataFromServer = SpinnerDataFromServer(this) //объект отправляющий запросы
    var liveDataSearchDeclarers: LiveDataSearchDeclarers = LiveDataSearchDeclarers() //объект
    var liveDataSearchWorkers: LiveDataSearchWorkers = LiveDataSearchWorkers()

    @Override
    override fun onCleared() {
        super.onCleared()
    }

    fun setObjectForRequests() {
        user?.let {
            Log.i(TAG, "!из MyViewModelMainActivity отправил user в spinnerDataFromServer!")
            spinnerDataFromServer.setObject(user!!)
        }
    }

    fun requestOnSetDataAboutSpinners() {//методу достаточно знать что так идёт запрос на получение данных с сервера
        spinnerDataFromServer.sendRequest()
        spinnerDataFromServer.waitData()
    }

    override fun setListsData() {
        Log.i(TAG, "!Массивы пришли!")
        liveDataSearchDeclarers.searchDeclarers = spinnerDataFromServer.searchDeclarers
        liveDataSearchDeclarers.searchDeclarerStrings = ArrayList()
        for (i in spinnerDataFromServer.searchDeclarers.indices) {
            (liveDataSearchDeclarers.searchDeclarerStrings as ArrayList<String>).add(i, spinnerDataFromServer.searchDeclarers[i].name)
        }
        (liveDataSearchDeclarers.searchDeclarerStrings as ArrayList<String>).add(0, " ")
        liveDataSearchWorkers.searchWorkers = spinnerDataFromServer.searchWorkers
        liveDataSearchWorkers.searchWorkerStrings = ArrayList()
        for (i in spinnerDataFromServer.searchWorkers.indices) {
            (liveDataSearchWorkers.searchWorkerStrings as ArrayList<String>).add(i, spinnerDataFromServer.searchWorkers[i].name)
        }
        (liveDataSearchWorkers.searchWorkerStrings as ArrayList<String>).add(0, " ")
    }

}