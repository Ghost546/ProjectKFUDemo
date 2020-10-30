package com.example.projectkfudemo.architecturalcomponents.viewmodels.globalsearchfragment

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.projectkfudemo.architecturalcomponents.livadatas.LiveDataSearchResultFromServer
import com.example.projectkfudemo.architecturalcomponents.models.DataOnRequestsFromTheServer
import com.example.projectkfudemo.architecturalcomponents.ui.globalsearch.GlobalSearchInterface
import com.example.projectkfudemo.architecturalcomponents.viewmodels.ViewModelInterface
import com.example.projectkfudemo.parametrclasses.User
import com.example.projectkfudemo.requests.Request

class ViewModelGlobalSearch: ViewModel(), ViewModelInterface {
    override val TAG = this.javaClass.simpleName

    var user: User?= null  //объект для хранения
    val requestsList:List<Request> = listOf()

    var globalSearchInterface: GlobalSearchInterface? = null

    val dataOnRequestsFromTheServer: DataOnRequestsFromTheServer = DataOnRequestsFromTheServer(this)

    val liveDataSearchResultFromServer: MutableLiveData<List<Request>> = LiveDataSearchResultFromServer

    var states: List<Request>? = listOf()

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
            dataOnRequestsFromTheServer.setObject(user!!)
        }
    }

    fun setParamsForGlobalSearch(declarerFIO: String?, cod: Int?, date1: String?,
                                 date2: String?, regType: Int?, statusId: Int?,
                                 regUserId: Int?, workerId: Int?) {
        Log.i(TAG, "!отправил данные для параметров и вызвал настройку параметров")
        dataOnRequestsFromTheServer.sendParamsForRequestOnGlobalSearch(declarerFIO, cod, date1,
                date2, regType, statusId,
                regUserId, workerId)
        dataOnRequestsFromTheServer.setParamsForRequestOnGlobalSearch()
    }

    fun sendRequest() {
        dataOnRequestsFromTheServer.sendRequest()
        dataOnRequestsFromTheServer.waitData()
    }

    fun showNextFragment() {
        Log.i(TAG, "!Вызов метода showResultFragment")
        globalSearchInterface?.showResultFragment(dataOnRequestsFromTheServer.requestListFromServer)
    }

    //для вызова через интерфейс из dataOnRequestsFromTheServer
    override fun setListsData() {
        Log.i(TAG, "!Вызов setRequestList")
        liveDataSearchResultFromServer.postValue(dataOnRequestsFromTheServer.requestListFromServer)
//        showNextFragment()
        Log.i(TAG, "!Размер массива requestListFromServer: " + dataOnRequestsFromTheServer.requestListFromServer?.size.toString())
        Log.i(TAG, "!Размер массива requestList в LiveData: " + LiveDataSearchResultFromServer.requestList?.size.toString())
    }
}