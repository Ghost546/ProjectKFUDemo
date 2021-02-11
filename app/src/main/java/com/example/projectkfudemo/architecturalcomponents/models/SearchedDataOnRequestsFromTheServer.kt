package com.example.projectkfudemo.architecturalcomponents.models

import android.util.Log
import com.example.projectkfudemo.architecturalcomponents.viewmodels.ViewModelInterface
import com.example.projectkfudemo.parametrclasses.User
import com.example.projectkfudemo.requests.Request
import com.example.projectkfudemo.requests.RequestList
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SearchedDataOnRequestsFromTheServer(_viewModelInterface: ViewModelInterface): ModelsByRequestToServer {
    override val TAG = this.javaClass.simpleName

    var viewModelInterface = _viewModelInterface

    init {
        Log.i(TAG, "!обект dataOnRequestsFromTheServer создался")
    }

    override var serverRequestsByRx: ServerRequestsByRx ?= null

    var requestListFromServer:RequestList?=null


    @Override
    override fun setObjectByUser(user: User) {
        setObjectByUserAndInterface(this, user)
    }

    fun setParamsForRequestOnGlobalSearch() {
        Log.i(TAG, "!вызвал настройку параметров!")
        serverRequestsByRx?.setParamsGlobalSearchFromVariablesToParamsObject()
    }

    fun sendParamsForRequestOnGlobalSearch(declarerFIO: String?, cod: Int?, date1: String?,
                                           date2: String?, regType: Int?, statusId: Int?,
                                           regUserId: Int?, workerId: Int?, text: String?) {
        Log.i(TAG, "!отправил параметры для отправки запроса!")
        serverRequestsByRx?.setParamsForRequestOnGlobalSearchToVariables(declarerFIO, cod, date1, date2,
                                                                         regType, statusId, regUserId,
                                                                         workerId, text)
    }

    override fun sendRequestCurrentTask() {
        Log.i(TAG, "!отправил запрос на получение данных для Spinners!")
        serverRequestsByRx?.sendRequestsForRequestOnGlobalSearch()
    }

    override fun sendRequestMyTask() {
        
    }

    override fun setData() {
        if(serverRequestsByRx?.getRequestListFromServer()!=null) {
            Log.i(TAG, "!RequestListStates не пустой!")
            requestListFromServer = serverRequestsByRx?.getRequestListFromServer()
            Log.i(TAG, "!Размер массива requestListFromServer: " + requestListFromServer?.requests?.size.toString())
            viewModelInterface.setListsData()
        }
    }

//    fun waitData() {
//        GlobalScope.launch {
//            Log.i(TAG, "!Массив пуст(DataOnRequestsFromTheServer)!")
//            while (serverRequestsByRx?.getRequestListFromServer()== null) {
//
//            }
//            if(serverRequestsByRx?.getRequestListFromServer()!=null) Log.i(TAG, "!Массив получен(DataOnRequestsFromTheServer)!")
//            delay(1000)
//            if(serverRequestsByRx?.getRequestListFromServer()!=null) {
//                Log.i(TAG, "!RequestListStates не пустой!")
//                requestListFromServer = serverRequestsByRx?.getRequestListFromServer()
//                Log.i(TAG, "!Размер массива requestListFromServer: " + requestListFromServer?.requests?.size.toString())
//            }
//            Log.i(TAG, "!вызов viewModelGlobalSearchInterface.setRequestList()!")
//            viewModelInterface.setListsData()
//        }
//    }
}