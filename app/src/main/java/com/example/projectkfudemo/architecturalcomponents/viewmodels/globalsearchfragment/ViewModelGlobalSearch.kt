package com.example.projectkfudemo.architecturalcomponents.viewmodels.globalsearchfragment

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.projectkfudemo.architecturalcomponents.livadatas.LiveDataSearchResultFromServer
import com.example.projectkfudemo.architecturalcomponents.models.SearchedDataOnRequestsFromTheServer
import com.example.projectkfudemo.architecturalcomponents.ui.globalsearch.GlobalSearchInterface
import com.example.projectkfudemo.architecturalcomponents.viewmodels.ViewModelInterface
import com.example.projectkfudemo.parametrclasses.User

class ViewModelGlobalSearch: ViewModel(), ViewModelInterface {
    override val tag = this.javaClass.simpleName

    override var user: User?= null  //объект для хранения

    var globalSearchInterface: GlobalSearchInterface? = null

    val searchedDataOnRequestsFromTheServer: SearchedDataOnRequestsFromTheServer = SearchedDataOnRequestsFromTheServer(this)

    val liveDataSearchResultFromServer = LiveDataSearchResultFromServer

    @Override
    override fun onCleared() {
        super.onCleared()
    }

    override fun setObject(user: User) {
        TODO("Not yet implemented")
    }

    fun setInterface(_globalSearchInterface: GlobalSearchInterface) {
        globalSearchInterface =_globalSearchInterface
    }

    fun setObjectForRequests() {
        user?.let {
            Log.i(tag, "!из MyViewModelMainActivity отправил user в spinnerDataFromServer!")
            searchedDataOnRequestsFromTheServer.setObjectByUser(user!!)
        }
    }

    fun setParamsForGlobalSearch(declarerFIO: String?, cod: Int?, date1: String?,
                                 date2: String?, regType: Int?, statusId: Int?,
                                 regUserId: Int?, workerId: Int?, text: String?,
                                 techGroup: Int?, office:String?, address: String?,
                                 roomNum:String?) {
        Log.i(tag, "!отправил данные для параметров и вызвал настройку параметров")
        searchedDataOnRequestsFromTheServer.sendParamsForRequestOnGlobalSearch(declarerFIO, cod, date1,
                                                                               date2, regType, statusId,
                                                                               regUserId, workerId, text,
                                                                               techGroup, office, address,
                                                                               roomNum)
    }

    fun sendRequest() {
        searchedDataOnRequestsFromTheServer.sendRequest()
//        searchedDataOnRequestsFromTheServer.waitData()
    }

    fun showNextFragment() {
        Log.i(tag, "!Вызов метода showResultFragment")
        globalSearchInterface?.showResultFragment(searchedDataOnRequestsFromTheServer.requestListFromServer)
    }

    //для вызова через интерфейс из SearchedDataOnRequestsFromTheServer
    override fun changedData() {
        Log.i(tag, "!выполнение setListsData")
        liveDataSearchResultFromServer.postValue(searchedDataOnRequestsFromTheServer.requestListFromServer)
//        showNextFragment()
        Log.i(tag, "!Размер массива requestListFromServer: " + searchedDataOnRequestsFromTheServer.requestListFromServer?.requests?.size.toString())
        Log.i(tag, "!Размер массива requestList в LiveData: " + liveDataSearchResultFromServer.value?.requests?.size.toString())
    }
}