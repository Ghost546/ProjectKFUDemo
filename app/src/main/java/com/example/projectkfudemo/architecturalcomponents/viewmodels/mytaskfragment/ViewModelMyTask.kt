package com.example.projectkfudemo.architecturalcomponents.viewmodels.mytaskfragment

import androidx.lifecycle.ViewModel
import com.example.projectkfudemo.architecturalcomponents.livadatas.LiveDataMyTaskRequestList
import com.example.projectkfudemo.architecturalcomponents.livadatas.LiveDataMyTaskSelectedPosition
import com.example.projectkfudemo.architecturalcomponents.models.DataRequestListFromServer
import com.example.projectkfudemo.architecturalcomponents.ui.UIList
import com.example.projectkfudemo.architecturalcomponents.viewmodels.ViewModelTasksInterface
import com.example.projectkfudemo.parametrclasses.User
import com.example.projectkfudemo.requests.RequestList

class ViewModelMyTask: ViewModel(), ViewModelTasksInterface {
    override val TAG: String = this.javaClass.simpleName

    //CII
    override var user:User? = null
    //CII
    override var firstLoad: Boolean = true
    //CII
    override var alreadyLoaded: Boolean = false
    //CII
    override var uiList: UIList? = null
    //CII
    override var requestList: RequestList = RequestList()
    //CII
    override var searchText: String = ""
    //CII
    override val dataRequestListFromServer = DataRequestListFromServer(this)
    //основная liveData с list с заявками
    var liveDataMyTaskRequestList = LiveDataMyTaskRequestList
    //тип заявки текущего отображения
    var liveDataMyTaskSelectedPosition = LiveDataMyTaskSelectedPosition

    override fun setListsData() {
        liveDataMyTaskRequestList.postValue(dataRequestListFromServer.requestList)
        requestList = dataRequestListFromServer.requestList
    }

    override fun setOnSelectedPosition(position: Int) {
        super.setOnSelectedPosition(position)
        liveDataMyTaskSelectedPosition.postValue(position)
    }

    override fun setOnChangedSelectedPosition() {
        super.setOnChangedSelectedPosition()
        liveDataMyTaskSelectedPosition.value?.let { setPosition(it) }
        sendRequestCurrentTask()
    }

    @Override
    override fun onCleared() {
        super.onCleared()
    }
}