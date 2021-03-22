package com.example.projectkfudemo.architecturalcomponents.models

import android.util.Log
import com.example.projectkfudemo.architecturalcomponents.viewmodels.ViewModelInterface
import com.example.projectkfudemo.parametrclasses.User
import com.example.projectkfudemo.parametrclasses.requests.RequestList

//класс отправляет запрос на сервер на поиск
class SearchedDataOnRequestsFromTheServer(_viewModelInterface: ViewModelInterface): StreakToServer {
    override val TAG = this.javaClass.simpleName

    var viewModel = _viewModelInterface

    init {
        Log.i(TAG, "!обект dataOnRequestsFromTheServer создался")
    }

    override var serverRequestsByRx: ServerRequestsByRx ?= null

    var requestListFromServer:RequestList?=null

    private fun setRequestList(requestListFromServer: RequestList) {
        this.requestListFromServer = requestListFromServer
        viewModel.changedListsData()
    }

    @Override
    override fun setObjectByUser(user: User) {
        setObjectByUserAndInterface(this, user)
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

    override fun sendRequest() {
        Log.i(TAG, "!отправил запрос на получение данных для Spinners!")
        serverRequestsByRx?.sendRequestsForRequestOnGlobalSearch()
    }

    override fun setData() {
        if(serverRequestsByRx?.getRequestListFromServer()!=null) {
            Log.i(TAG, "!RequestListStates не пустой!")
            serverRequestsByRx?.requestListFromServer?.let { setRequestList(it) }
            Log.i(TAG, "!Размер массива requestListFromServer: " + requestListFromServer?.requests?.size.toString())
        }
    }
}