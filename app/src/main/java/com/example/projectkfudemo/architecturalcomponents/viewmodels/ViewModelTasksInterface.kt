package com.example.projectkfudemo.architecturalcomponents.viewmodels

import android.util.Log
import com.example.projectkfudemo.architecturalcomponents.models.DataRequestListFromServer
import com.example.projectkfudemo.architecturalcomponents.ui.ListVisibilityInterface
import com.example.projectkfudemo.parametrclasses.User
import com.example.projectkfudemo.requests.RequestList

interface ViewModelTasksInterface: ViewModelInterface {
    //единоразовая переменная для проверки загружался ли экран до этого
    var firstLoad: Boolean
    //постоянная переменная для проверки загружался ли экран до этого
    var alreadyLoaded:Boolean
    //значение хранит текущую категорию заявок
    var category: Int
    //переменная хранящая текущий лист, нужен для того чтобы была незовисимая возможность вернуть отображаемый список после использвания локального поиска
    var requestList:RequestList
    //Значение хранящий текущий текст поисковика
    var searchText: String
    //объект для взаимодействия с запросами на сервер
    val dataRequestListFromServer: DataRequestListFromServer

    override fun setObject(user: User) {
        dataRequestListFromServer.setObject(user)
    }

    //читать логи, комментарии излишни
    fun setOnSelectedPosition(position: Int) {
        Log.i(TAG, "!Вызов при смене типа отображаемых заявок")
        Log.i(TAG, "!Изменение позиции на: $position (конкретная реализация)")
    }

    //отправка запроса на сервер на заявки отдела
    fun sendRequestCurrentTask() {
        Log.i(TAG, "!Отправка запроса на сервер")
        dataRequestListFromServer.sendRequestCurrentTask()
    }

    //отправка запроса на сервер на заявки пользователя
    fun sendRequestMyTask() {
        Log.i(TAG, "!Отправка запроса на сервер")
        dataRequestListFromServer.sendRequestMyTask()
    }


    fun setPosition(position: Int) {
        Log.i(TAG, "!Установка позиции во внутреннем объекте: $position")
        dataRequestListFromServer.setPosition(position)
    }

    fun setOnChangedSelectedPosition() {
        Log.i(TAG, "!Вызов при изменении позиции в LiveDataSelectedPosition")
    }
}