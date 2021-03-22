package com.example.projectkfudemo.architecturalcomponents.models

import android.util.Log
import com.example.projectkfudemo.architecturalcomponents.viewmodels.ViewModelInterface
import com.example.projectkfudemo.parametrclasses.User
import com.example.projectkfudemo.requests.RequestList

class SearchedDataOnRequestsFromTheServer(_viewModelInterface: ViewModelInterface): ModelsByRequestToServer {
    override val TAG = this.javaClass.simpleName

    var viewModelInterface = _viewModelInterface

    init {
        Log.i(TAG, "!обект dataOnRequestsFromTheServer создался")
    }

    override var serverRequestsByRx: ServerRequestsByRx ?= null

    var requestListFromServer:RequestList?=null

    private fun setRequestList(requestListFromServer: RequestList) {
        this.requestListFromServer = requestListFromServer
        viewModelInterface.changedData()
    }

    @Override
    override fun setObjectByUser(user: User) {
        setObjectByUserAndInterface(this, user)
    }

    override fun sendRequest() {
        Log.i(TAG, "!отправил запрос на получение данных для Spinners!")
        serverRequestsByRx?.sendRequestsForRequestOnGlobalSearch()
    }

    fun sendParamsForRequestOnGlobalSearch(declarerFIO: String?, cod: Int?, date1: String?,
                                           date2: String?, regType: Int?, statusId: Int?,
                                           regUserId: Int?, workerId: Int?, text: String?,
                                           techGroup: Int?, office:String?, address: String?,
                                           roomNum:String?) {
        Log.i(TAG, "!отправил параметры для отправки запроса!")
        serverRequestsByRx?.setParamsForRequestOnGlobalSearchToVariables(declarerFIO, cod, date1, date2,
                                                                         regType, statusId, regUserId,
                                                                         workerId, text, techGroup,
                                                                         office, address, roomNum)
    }

    override fun sendRequestCurrentTask() {

    }

    override fun sendRequestMyTask() {
        
    }

    override fun setData() {
        if(serverRequestsByRx?.getRequestListFromServer()!=null) {
            Log.i(TAG, "!RequestListStates не пустой!")
            serverRequestsByRx?.requestListFromServer?.let { setRequestList(it) }
            Log.i(TAG, "!Размер массива requestListFromServer: " + requestListFromServer?.requests?.size.toString())
            viewModelInterface.changedData()
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