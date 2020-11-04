package com.example.projectkfudemo.architecturalcomponents.viewmodels

import android.util.Log
import com.example.projectkfudemo.architecturalcomponents.models.DataRequestListFromServer
import com.example.projectkfudemo.architecturalcomponents.ui.UIList
import com.example.projectkfudemo.parametrclasses.User
import com.example.projectkfudemo.requests.RequestList

interface ViewModelTasksInterface: ViewModelInterface {
    //объект для хранения данных о пользователе
    var user: User?
    //единоразовая переменная для проверки загружался ли экран до этого
    var firstLoad: Boolean
    //постоянная переменная для проверки загружался ли экран до этого
    var alreadyLoaded:Boolean
    //интерфейс для взаимодействия с list в view
    var uiList:UIList?
    //переменная хранящая текущий лист, нужен для того чтобы была незовисимая возможность вернуть отображаемый список после использвания локального поиска
    var requestList:RequestList
    //Значение хранящий текущий текст поисковика
    var searchText: String
    //объект для взаимодействия с запросами на сервер
    val dataRequestListFromServer: DataRequestListFromServer

    fun setObject(user: User) {
        dataRequestListFromServer.setObject(user)
    }

    //На фоне логов, комментарии излишни
    fun setOnSelectedPosition(position: Int) {
        Log.i(TAG, "!Вызов при смене типа отображаемых заявок")
        Log.i(TAG, "!Изменение позиции на: $position (конкретная реализация)")
    }

    fun setInterface(_uiList: UIList) {
        uiList = _uiList
    }

    //отправка запроса на сервер на заявки отдела
    fun sendRequestCurrentTask() {
        Log.i(TAG, "!Отправка запроса на сервер")
        dataRequestListFromServer.sendRequestCurrentTask()
    }

    //отправка запроса на сервер на заявки отдела
    fun sendRequestMyTask() {
        Log.i(TAG, "!Отправка запроса на сервер")
        dataRequestListFromServer.sendRequestMyTask()
    }


    fun setPosition(position: Int) {
        Log.i(TAG, "!Установка позиции во внутреннем объекте: " + position)
        dataRequestListFromServer.setPosition(position)
    }

    fun setOnChangedSelectedPosition() {
        Log.i(TAG, "!Вызов при изменении позиции в LiveData")
    }
}