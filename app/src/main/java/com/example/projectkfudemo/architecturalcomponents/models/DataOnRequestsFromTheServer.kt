package com.example.projectkfudemo.architecturalcomponents.models

import android.util.Log
import com.example.projectkfudemo.parametrclasses.User
import com.example.projectkfudemo.architecturalcomponents.viewmodels.globalsearchfragment.ViewModelGlobalSearchInterface
import com.example.projectkfudemo.requests.Request
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

    var requestListFromServer:List<Request>?=null


    @Override
    override fun setObject(user: User) {
        super.setObject(user)
    }

    fun setParamsForRequestOnGlobalSearch() {
        Log.i(TAG, "!вызвал настройку параметров!")
        serverRequestsByRx?.setParamsGlobalSearchFromVariablesToParamsObject()
    }

    fun sendParamsForRequestOnGlobalSearch(declarerFIO: String?, cod: Int?, date1: String?,
                                           date2: String?, regType: Int?, statusId: Int?,
                                           regUserId: Int?, workerId: Int?) {
        Log.i(TAG, "!отправил параметры для отправки запроса!")
        serverRequestsByRx?.setParamsForRequestOnGlobalSearchToVariables(declarerFIO, cod, date1, date2,
                                                                         regType, statusId, regUserId,
                                                                         workerId)
    }

    override fun sendRequest() {
        Log.i(TAG, "!отправил запрос на получение данных для Spinners!")
        serverRequestsByRx?.sendRequestsForRequestOnGlobalSearch()
    }

    override fun waitData() {
        GlobalScope.launch {
            Log.i(TAG, "!Массив пуст(DataOnRequestsFromTheServer)!")
            while (serverRequestsByRx?.getRequestListFromServer()== null) {

            }
            if(serverRequestsByRx?.getRequestListFromServer()!=null) Log.i(TAG, "!Массив получен(DataOnRequestsFromTheServer)!")
            delay(5000)
            if(serverRequestsByRx?.getRequestListFromServer()!=null) {
                Log.i(TAG, "!RequestListStates не пустой!")
                requestListFromServer = serverRequestsByRx?.requestListFromServer
                Log.i(TAG, "!Размер массива requestListFromServer: " + requestListFromServer?.size.toString())
            }
            Log.i(TAG, "!вызов viewModelGlobalSearchInterface.setRequestList()!")
            viewModelGlobalSearchInterface.setRequestList()
        }
    }
}