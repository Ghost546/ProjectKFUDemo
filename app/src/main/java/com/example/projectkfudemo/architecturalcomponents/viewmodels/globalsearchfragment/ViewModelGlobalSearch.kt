package com.example.projectkfudemo.architecturalcomponents.viewmodels.globalsearchfragment

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.projectkfudemo.architecturalcomponents.livadatas.LiveDataSearchResultFromServer
import com.example.projectkfudemo.architecturalcomponents.models.SearchedDataOnRequestsFromTheServer
import com.example.projectkfudemo.architecturalcomponents.models.StreakToServer
import com.example.projectkfudemo.architecturalcomponents.ui.globalsearch.GlobalSearchInterface
import com.example.projectkfudemo.architecturalcomponents.viewmodels.ViewModelDefaultInterface
import com.example.projectkfudemo.architecturalcomponents.viewmodels.ViewModelInterface
import com.example.projectkfudemo.parametrclasses.User

class ViewModelGlobalSearch: ViewModel(), ViewModelDefaultInterface {
    override val TAG = this.javaClass.simpleName

    var user: User?= null  //объект для хранения

    var globalSearchInterface: GlobalSearchInterface? = null

    override var streakToServer: StreakToServer = SearchedDataOnRequestsFromTheServer(this)

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
            streakToServer.setObjectByUser(user!!)
        }
    }

    fun setParamsForGlobalSearch(declarerFIO: String?, cod: Int?, date1: String?,
                                 date2: String?, regType: Int?, statusId: Int?,
                                 regUserId: Int?, workerId: Int?, text: String?,
                                 techGroup: Int?, office:String?, address: String?,
                                 roomNum:String?) {
        Log.i(TAG, "!отправил данные для параметров и вызвал настройку параметров")
        streakToServer.sendParamsForRequestOnGlobalSearch(declarerFIO, cod, date1,
                                                                               date2, regType, statusId,
                                                                               regUserId, workerId, text,
                                                                               techGroup, office, address,
                                                                               roomNum)
    }

    fun sendRequest() {
        streakToServer.sendRequest()
//        searchedDataOnRequestsFromTheServer.waitData()
    }

    fun showNextFragment() {
        Log.i(TAG, "!Вызов метода showResultFragment")
        globalSearchInterface?.showResultFragment(streakToServer.requestListFromServer)
    }

    //для вызова через интерфейс из SearchedDataOnRequestsFromTheServer
    override fun changedListsData() {
        Log.i(TAG, "!выполнение setListsData")
        liveDataSearchResultFromServer.postValue(streakToServer.requestListFromServer)
//        showNextFragment()
        Log.i(TAG, "!Размер массива requestListFromServer: " + streakToServer.requestListFromServer?.requests?.size.toString())
        Log.i(TAG, "!Размер массива requestList в LiveData: " + liveDataSearchResultFromServer.value?.requests?.size.toString())
    }
}