package com.example.projectkfudemo.architecturalcomponents.models

import android.util.Log
import com.example.projectkfudemo.parametrclasses.User
import com.example.projectkfudemo.architecturalcomponents.viewmodels.globalsearchfragment.ViewModelGlobalSearchInterface
import com.example.projectkfudemo.requests.RequestList
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class DataOnRequestsFromTheServer(_viewModelGlobalSearchInterface: ViewModelGlobalSearchInterface): ModelsByRequestToServer {
    override val TAG = this.javaClass.simpleName

    var viewModelGlobalSearchInterface = _viewModelGlobalSearchInterface

    init {
        Log.i(TAG, "!обект dataOnRequestsFromTheServer создался")
    }

    override var serverRequestsByRx: ServerRequestsByRx ?= null

    var requestListFromServer:RequestList?=null


    @Override
    override fun setObject(user: User) {
        super.setObject(user)
    }

    fun setParamsForRequestOnGlobalSearch() {
        serverRequestsByRx?.setParamsGlobalSearchFromVariablesToParamsObject()
    }

    fun sendParamsForRequestOnGlobalSearch(declarerFIO: String, cod: Int, date1: String,
                                           date2: String, regType: Int, statusId: Int,
                                           regUserId: Int, workerId: Int) {
        serverRequestsByRx?.setParamsForRequestOnGlobalSearchToVariables(declarerFIO, cod, date1, date2,
                                                                         regType, statusId, regUserId,
                                                                         workerId)
    }

    override fun sendRequest() {
        serverRequestsByRx?.sendRequestsForRequestOnGlobalSearch()
    }

    override fun waitData() {
        GlobalScope.launch {
            while (serverRequestsByRx?.getRequestListStates()== null) {
                Log.i(TAG, "!Массив пуст(DataOnRequestsFromTheServer)!")
            }
            delay(5000)
            if(serverRequestsByRx?.getRequestListStates()!=null) {

            }
            viewModelGlobalSearchInterface.setRequestList()
        }
    }
}