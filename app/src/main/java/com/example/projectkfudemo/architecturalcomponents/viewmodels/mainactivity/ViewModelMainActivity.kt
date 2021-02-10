package com.example.projectkfudemo.architecturalcomponents.viewmodels.mainactivity

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.projectkfudemo.parametrclasses.User
import com.example.projectkfudemo.architecturalcomponents.livadatas.LiveDataSearchDeclarers
import com.example.projectkfudemo.architecturalcomponents.livadatas.LiveDataSearchDeclarersStrings
import com.example.projectkfudemo.architecturalcomponents.livadatas.LiveDataSearchWorkerStrings
import com.example.projectkfudemo.architecturalcomponents.livadatas.LiveDataSearchWorkers
import com.example.projectkfudemo.architecturalcomponents.models.SpinnerDataFromServer
import com.example.projectkfudemo.architecturalcomponents.ui.globalsearch.GlobalSearchInterface
import com.example.projectkfudemo.architecturalcomponents.viewmodels.ViewModelInterface

class ViewModelMainActivity: ViewModel(), ViewModelInterface {
    override val TAG = this.javaClass.simpleName

    var user: User?= null  //объект для хранения
    var spinnerDataFromServer: SpinnerDataFromServer = SpinnerDataFromServer(this) //объект отправляющий запросы
    var liveDataSearchDeclarers: LiveDataSearchDeclarers = LiveDataSearchDeclarers //объект
    var liveDataSearchWorkers: LiveDataSearchWorkers = LiveDataSearchWorkers
    var liveDataSearchDeclarerString: LiveDataSearchDeclarersStrings = LiveDataSearchDeclarersStrings
    var liveDataSearchWorkerString: LiveDataSearchWorkerStrings = LiveDataSearchWorkerStrings
    var globalSearchInterfaceInMain: GlobalSearchInterface? = null

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


    fun requestOnSetDataAboutSpinners() {   //методу достаточно знать что так идёт запрос на получение данных с сервера
        spinnerDataFromServer.sendRequestCurrentTask()
//        spinnerDataFromServer.waitData()
    }

    fun setGlobalSearchInterface(_globalSearchInterface: GlobalSearchInterface) {
        globalSearchInterfaceInMain = _globalSearchInterface
    }

    override fun setListsData() {
        Log.i(TAG, "!Массивы пришли!")
        liveDataSearchDeclarers.postValue(spinnerDataFromServer.searchDeclarers)
        var mutableList = mutableListOf<String>()
        for (i in spinnerDataFromServer.searchDeclarers?.declarersList?.indices!!) {
            (mutableList as ArrayList<String>).add(i, spinnerDataFromServer.searchDeclarers!!.declarersList[i].name)
        }
        (mutableList as ArrayList<String>).add(0, " ")
        liveDataSearchDeclarerString.postValue(mutableList)

        mutableList = mutableListOf()

        liveDataSearchWorkers.postValue(spinnerDataFromServer.searchWorkers)
        for (i in spinnerDataFromServer.searchWorkers?.workersList?.indices!!) {
            (mutableList as ArrayList<String>).add(i, spinnerDataFromServer.searchWorkers!!.workersList[i].name)
        }
        (mutableList as ArrayList<String>).add(0, " ")
        liveDataSearchWorkerString.postValue(mutableList)
    }

}