package com.example.projectkfudemo.architecturalcomponents.viewmodels.currenttaskfragment

import android.os.Bundle
import android.view.LayoutInflater
import androidx.lifecycle.ViewModel
import com.example.projectkfudemo.R
import com.example.projectkfudemo.architecturalcomponents.livadatas.LiveDataCurrentTaskRequestList
import com.example.projectkfudemo.architecturalcomponents.livadatas.LiveDataCurrentTaskSelectedPosition
import com.example.projectkfudemo.architecturalcomponents.models.RequestStateAdapter
import com.example.projectkfudemo.architecturalcomponents.ui.UIList
import com.example.projectkfudemo.architecturalcomponents.ui.currenttask.CurrentTaskFragment
import com.example.projectkfudemo.architecturalcomponents.viewmodels.ViewModelInterface
import com.example.projectkfudemo.parametrclasses.User
import com.example.projectkfudemo.requests.Request
import com.example.projectkfudemo.requests.RequestList
import java.text.FieldPosition

class ViewModelCurrentTask: ViewModel(), ViewModelInterface {
    override val TAG = this.javaClass.simpleName

    var user: User?= null  //объект для хранения
    //основная liveData с list с заявками
    var liveDataCurrentTaskRequestList: LiveDataCurrentTaskRequestList = LiveDataCurrentTaskRequestList
    //единоразовая переменная для проверки загружался ли экран до этого
    var firstLoad: Boolean = true
    //постоянная переменная для проверки загружался ли экран до этого
    var alreadyLoaded = false
    //ключ для объекта хранения в savedInstanceState
    val REQUEST_LIST_SAVING_KEY = "requestListSavingKey"
    //интерфейс для взаимодействия с list в view
    var uiList:UIList?=null
    //выбранная позиция в выпадающем списке
    var liveDataCurrentTaskSelectedPosition = LiveDataCurrentTaskSelectedPosition

    override fun setListsData() {

    }

    fun setOnSelectedPosition(position: Int) {
        liveDataCurrentTaskSelectedPosition.postValue(position)
    }

    fun getRequestList(): MutableList<Request>? {
        return liveDataCurrentTaskRequestList.requestList?.requests
    }

    fun setInterface(_uiList: UIList) {
        uiList = _uiList
    }

    fun setListViewByLoaded(currentTaskFragment: CurrentTaskFragment, savedInstanceState: Bundle, inflater: LayoutInflater) {
        if (savedInstanceState != null) {
            liveDataCurrentTaskRequestList.postValue(savedInstanceState.getSerializable(REQUEST_LIST_SAVING_KEY) as RequestList)
        } else {
            if (alreadyLoaded) {
                alreadyLoaded=false
            } else {
                requestListView = getRequestListView(inflater, selectedItemPosition)
            }
        }

        if (firstLoad) {
            requestListView = getRequestListView(inflater, selectedItemPosition)
            firstLoad = false
        }

        if (savedInstanceState == null && !alreadyLoaded) {
            alreadyLoaded = true
        }
    }

    @Override
    override fun onCleared() {
        super.onCleared()
    }
}