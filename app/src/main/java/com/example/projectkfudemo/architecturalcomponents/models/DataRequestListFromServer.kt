package com.example.projectkfudemo.architecturalcomponents.models

import android.util.Log
import com.example.projectkfudemo.architecturalcomponents.viewmodels.ViewModelInterface
import com.example.projectkfudemo.parametrclasses.User
import com.example.projectkfudemo.requests.RequestList


class DataRequestListFromServer(_viewModelInterface: ViewModelInterface): ModelsByRequestToServer {
    override val TAG = this.javaClass.simpleName

    override var serverRequestsByRx: ServerRequestsByRx? = null

    var viewModelInterface = _viewModelInterface

    var requestList: RequestList = RequestList()

    fun setObject(user: User) {
        setObjectByUserAndInterface(this, user)
    }

    fun setPosition(position: Int?) {
        position?.let { serverRequestsByRx?.setPosition(it) }
        if (position==null) {
            Log.i(TAG, "!Позиция не установилась")
        }
    }

    override fun sendRequestCurrentTask() {
        Log.i(TAG, "!Отправка запроса на сервер")
        serverRequestsByRx?.setRequestListByCurrentTask()
    }

    override fun sendRequestMyTask() {
        Log.i(TAG, "!Отправка запроса на сервер")
        serverRequestsByRx?.setRequestListByMyTask()
    }

    override fun setData() {
        requestList = serverRequestsByRx!!.requestListStates
        Log.i(TAG, "!Установка requestList, её размер: " + requestList.requests.size)
        viewModelInterface.setListsData()
        Log.i(TAG, "!Установка list во ViewModel")
    }

}