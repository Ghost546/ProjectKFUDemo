package com.example.projectkfudemo.architecturalcomponents.viewmodels.globalsearchfragment

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.projectkfudemo.architecturalcomponents.livadatas.LiveDataSearchResultFromServer
import com.example.projectkfudemo.architecturalcomponents.models.SearchedDataOnRequestsFromTheServer
import com.example.projectkfudemo.architecturalcomponents.ui.globalsearch.GlobalSearchInterface
import com.example.projectkfudemo.architecturalcomponents.viewmodels.ViewModelInterface
import com.example.projectkfudemo.parametrclasses.User
import com.example.projectkfudemo.requests.Request

class ViewModelGlobalSearch: ViewModel(), ViewModelInterface {
    override val TAG = this.javaClass.simpleName

    var user: User?= null  //объект для хранения

    var globalSearchInterface: GlobalSearchInterface? = null

    val searchedDataOnRequestsFromTheServer: SearchedDataOnRequestsFromTheServer = SearchedDataOnRequestsFromTheServer(this)

    val liveDataSearchResultFromServer = LiveDataSearchResultFromServer

    @Override
    override fun onCleared() {
        super.onCleared()
    }

    fun setInterface(_globalSearchInterface: GlobalSearchInterface) {
        globalSearchInterface =_globalSearchInterface
    }

    fun setObjectForRequests() {
        user?.let {
            Log.i(TAG, "!из MyViewModelMainActivity отправил user в spinnerDataFromServer!")
            searchedDataOnRequestsFromTheServer.setObjectByUser(user!!)
        }
    }

    fun setParamsForGlobalSearch(declarerFIO: String?, cod: Int?, date1: String?,
                                 date2: String?, regType: Int?, statusId: Int?,
                                 regUserId: Int?, workerId: Int?) {
        Log.i(TAG, "!отправил данные для параметров и вызвал настройку параметров")
        searchedDataOnRequestsFromTheServer.sendParamsForRequestOnGlobalSearch(declarerFIO, cod, date1,
                date2, regType, statusId,
                regUserId, workerId)
        searchedDataOnRequestsFromTheServer.setParamsForRequestOnGlobalSearch()
    }

    fun sendRequest() {
        searchedDataOnRequestsFromTheServer.sendRequestCurrentTask()
        searchedDataOnRequestsFromTheServer.waitData()
    }

    fun showNextFragment() {
        Log.i(TAG, "!Вызов метода showResultFragment")
        globalSearchInterface?.showResultFragment(searchedDataOnRequestsFromTheServer.requestListFromServer)
    }

    //для вызова через интерфейс из dataOnRequestsFromTheServer
    override fun setListsData() {
        Log.i(TAG, "!Вызов setRequestList")
        liveDataSearchResultFromServer.postValue(searchedDataOnRequestsFromTheServer.requestListFromServer)
//        showNextFragment()
        Log.i(TAG, "!Размер массива requestListFromServer: " + searchedDataOnRequestsFromTheServer.requestListFromServer?.requests?.size.toString())
        Log.i(TAG, "!Размер массива requestList в LiveData: " + LiveDataSearchResultFromServer.requestList?.size.toString())
    }
}