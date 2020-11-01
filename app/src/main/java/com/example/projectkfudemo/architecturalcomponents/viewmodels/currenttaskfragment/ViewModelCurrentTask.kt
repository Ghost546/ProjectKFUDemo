package com.example.projectkfudemo.architecturalcomponents.viewmodels.currenttaskfragment

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.projectkfudemo.architecturalcomponents.livadatas.LiveDataCurrentTaskRequestList
import com.example.projectkfudemo.architecturalcomponents.livadatas.LiveDataCurrentTaskSelectedPosition
import com.example.projectkfudemo.architecturalcomponents.models.DataRequestListFromServer
import com.example.projectkfudemo.architecturalcomponents.ui.UIList
import com.example.projectkfudemo.architecturalcomponents.viewmodels.ViewModelInterface
import com.example.projectkfudemo.parametrclasses.User
import com.example.projectkfudemo.requests.Request
import com.example.projectkfudemo.requests.RequestList

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
    //объект для взаимодействия с запросами на сервер
    val dataRequestListFromServer:DataRequestListFromServer = DataRequestListFromServer(this)
    //переменная хранящая текущий лист, нужен для того чтобы была незовисимая возможность вернуть отображаемый список после использвания локального поиска
    var requestList: RequestList = RequestList()
    //Значение хранящий текущий текст поисковика
    var searchText: String = ""

    override fun setListsData() {
        liveDataCurrentTaskRequestList.postValue(dataRequestListFromServer.requestList)
        requestList = dataRequestListFromServer.requestList
    }

    fun setObject(user: User) {
        dataRequestListFromServer.setObject(user)
    }

    fun setOnSelectedPosition(position: Int) {
        liveDataCurrentTaskSelectedPosition.postValue(position)
        Log.i(TAG, "!Изменение позиции на: " + position)
    }


    fun setInterface(_uiList: UIList) {
        uiList = _uiList
    }

    //отправка запроса на сервер
    fun sendRequest() {
        Log.i(TAG, "!Отправка запроса на сервер")
        dataRequestListFromServer.sendRequest()
    }

    fun setPosition(position: Int) {
        Log.i(TAG, "!Установка позиции во внутреннем объекте: " + position)
        dataRequestListFromServer.setPosition(position)
    }

    fun setOnChangedSelectedPosition() {
        liveDataCurrentTaskSelectedPosition.value?.let { setPosition(it) }
        sendRequest()
    }



    @Override
    override fun onCleared() {
        super.onCleared()
    }
}