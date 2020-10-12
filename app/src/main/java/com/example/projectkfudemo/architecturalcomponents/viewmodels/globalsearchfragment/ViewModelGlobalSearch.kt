package com.example.projectkfudemo.architecturalcomponents.viewmodels.globalsearchfragment

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.projectkfudemo.architecturalcomponents.livadatas.LiveDataSearchResultFromServer
import com.example.projectkfudemo.architecturalcomponents.models.DataOnRequestsFromTheServer
import com.example.projectkfudemo.parametrclasses.User
import com.example.projectkfudemo.requests.Request


class ViewModelGlobalSearch: ViewModel(), ViewModelGlobalSearchInterface {
    private val TAG = this.javaClass.simpleName

    var user: User?= null  //объект для хранения
    val requestsList:List<Request> = listOf()

    val dataOnRequestsFromTheServer: DataOnRequestsFromTheServer = DataOnRequestsFromTheServer(this)

    val liveDataSearchResultFromServer = LiveDataSearchResultFromServer()


    @Override
    override fun onCleared() {
        super.onCleared()
    }

    fun setObjectForRequests() {
        user?.let {
            Log.i(TAG, "!из MyViewModelMainActivity отправил user в spinnerDataFromServer!")
            dataOnRequestsFromTheServer.setObject(user!!)
        }
    }

    fun setParamsForGlobalSearch(declarerFIO: String, cod: Int, date1: String,
                                 date2: String, regType: Int, statusId: Int,
                                 regUserId: Int, workerId: Int) {
        dataOnRequestsFromTheServer.sendParamsForRequestOnGlobalSearch(declarerFIO, cod, date1,
                date2, regType, statusId,
                regUserId, workerId)
        dataOnRequestsFromTheServer.setParamsForRequestOnGlobalSearch()
    }

    fun sendRequest() {
        dataOnRequestsFromTheServer.sendRequest()
        dataOnRequestsFromTheServer.waitData()
    }

    //для вызова через интерфейс из dataOnRequestsFromTheServer
    override fun setRequestList() {
        liveDataSearchResultFromServer.requestList = dataOnRequestsFromTheServer.requestListFromServer
    }
}