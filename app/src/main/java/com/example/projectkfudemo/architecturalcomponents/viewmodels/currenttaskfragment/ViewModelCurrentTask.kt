   package com.example.projectkfudemo.architecturalcomponents.viewmodels.currenttaskfragment

import androidx.lifecycle.ViewModel
import com.example.projectkfudemo.architecturalcomponents.livadatas.LiveDataCurrentTaskRequestList
import com.example.projectkfudemo.architecturalcomponents.livadatas.LiveDataCurrentTaskSelectedPosition
import com.example.projectkfudemo.architecturalcomponents.models.DataRequestListFromServer
import com.example.projectkfudemo.architecturalcomponents.viewmodels.ViewModelTasksInterface
import com.example.projectkfudemo.parametrclasses.User
import com.example.projectkfudemo.parametrclasses.requests.RequestList

class ViewModelCurrentTask: ViewModel(), ViewModelTasksInterface {
    override val TAG = this.javaClass.simpleName

    //CII
    override var user:User? = null
    //CII
    override var firstLoad = true
    //CII
    override var alreadyLoaded = false
    //CII
    override var category = 0
    //CII
    override var requestList: RequestList = RequestList()
    //CII
    override var searchText: String = ""
    //CII
    override val dataRequestListFromServer = DataRequestListFromServer(this)
    //основная liveData с list с заявками
    var liveDataCurrentTaskRequestList = LiveDataCurrentTaskRequestList
    //тип заявки текущего отображения
    var liveDataCurrentTaskSelectedPosition = LiveDataCurrentTaskSelectedPosition

    override fun changedData() {
        liveDataCurrentTaskRequestList.postValue(dataRequestListFromServer.requestList)
        requestList = dataRequestListFromServer.requestList
    }

    //CII
    override fun setOnSelectedPosition(position: Int) {
        super.setOnSelectedPosition(position)
        liveDataCurrentTaskSelectedPosition.postValue(position)
    }

    override fun setOnChangedSelectedPosition() {
        super.setOnChangedSelectedPosition()
        liveDataCurrentTaskSelectedPosition.value?.let { setPosition(it) }
        liveDataCurrentTaskSelectedPosition.value?.let { category = it }
        sendRequestCurrentTask()
    }

    @Override
    override fun onCleared() {
        super.onCleared()
    }
}