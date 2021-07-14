package com.example.projectkfudemo.architecturalcomponents.models

import android.util.Log
import com.example.projectkfudemo.architecturalcomponents.viewmodels.ViewModelInterface
import com.example.projectkfudemo.parametrclasses.User
import com.example.projectkfudemo.parametrclasses.requests.RequestList

//класс отправляет запрос на сервер на поиск
class SearchedDataOnRequestsFromTheServer(_viewModelInterface: ViewModelInterface): ModelsByRequestToServer {
    override val tag = this.javaClass.simpleName

    var viewModelInterface = _viewModelInterface

    init {
        Log.i(tag, "!обект dataOnRequestsFromTheServer создался")
    }

    override var serverRequestsByRx: ServerRequestsByRx ?= null

    var requestListFromServer: RequestList?=null

    private fun setRequestList(requestListFromServer: RequestList) {
        this.requestListFromServer = requestListFromServer
        viewModelInterface.changedData()
    }

    @Override
    override fun setObjectByUser(user: User) {
        setObjectByUserAndInterface(this, user)
    }

    override fun sendRequest() {
        Log.i(tag, "!отправил запрос на получение данных для Spinners!")
        serverRequestsByRx?.sendRequestsForRequestOnGlobalSearch()
    }

    fun sendParamsForRequestOnGlobalSearch(declarerFIO: String?, cod: Int?, date1: String?,
                                           date2: String?, regType: Int?, statusId: Int?,
                                           regUserId: Int?, workerId: Int?, text: String?,
                                           techGroup: Int?, office:String?, address: String?,
                                           roomNum:String?) {
        Log.i(tag, "!отправил параметры для отправки запроса!")
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
            Log.i(tag, "!RequestListStates не пустой!")
            serverRequestsByRx?.requestListFromServer?.let { setRequestList(it) }
            Log.i(tag, "!Размер массива requestListFromServer: " + requestListFromServer?.requests?.size.toString())
            viewModelInterface.changedData()
        }
    }
}