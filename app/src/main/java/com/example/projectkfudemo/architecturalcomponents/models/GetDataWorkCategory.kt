package com.example.projectkfudemo.architecturalcomponents.models

import com.example.projectkfudemo.architecturalcomponents.viewmodels.ViewModelInterface
import com.example.projectkfudemo.parametrclasses.User
import com.example.projectkfudemo.parametrclasses.forjson.WorkCategoryList

//класс нужен чтобы получать список "Категория работ"
class GetDataWorkCategory(_viewModelInterface: ViewModelInterface): StreakToServer {
    override val TAG: String = this.javaClass.simpleName

    //интерфейс для взаимодействия с viewmodel которая создала объект по данному классу
    var viewModel: ViewModelInterface? = null

    init {
        viewModel = _viewModelInterface     //установка интерфейса в объект
    }

    //объект содержит модель из которого прилетют данные
    override var serverRequestsByRx: ServerRequestsByRx? = null

    //Лист, содержащий данные из модели
    private var workCategoryList: WorkCategoryList? = null

    //метод устанавливает значение в листе и оповещает viewmodel
    private fun setWorkCategory(workCategoryList: WorkCategoryList) {
        this.workCategoryList = workCategoryList
        viewModel?.changedListsData()
    }




    override fun sendRequest() {
        serverRequestsByRx?.setWorkCategoryListByRetrofit()
    }

    override fun setData() {
        serverRequestsByRx?.workCategoryList?.let { setWorkCategory(it) }
    }
}