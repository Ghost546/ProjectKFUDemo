package com.example.projectkfudemo.architecturalcomponents.viewmodels

interface ViewModelInterface {
    val TAG: String
    //Идея метода, почти во всех VM есть список с данными который выводится на экран, есть прослойка
    //между VM и M, прослойка обращается к этому методу, чем оповещает о том что в прослойке
    //появились данные, VM не нужно знать как прослойка получает данные из модели.
    fun setListsData()
}