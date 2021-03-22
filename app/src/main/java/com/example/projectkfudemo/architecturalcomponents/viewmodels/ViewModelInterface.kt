package com.example.projectkfudemo.architecturalcomponents.viewmodels

import com.example.projectkfudemo.architecturalcomponents.models.StreakToServer
import com.example.projectkfudemo.architecturalcomponents.ui.DataRequestList
import com.example.projectkfudemo.architecturalcomponents.ui.DataUser

interface ViewModelInterface {
    val TAG: String
    //Идея метода, в том что почти во всех VM есть список с данными который выводится на экран, есть
    //прослойка между VM и M, прослойка обращается к этому методу, чем оповещает о том что в
    //прослойке появились данные, VM не нужно знать как прослойка получает данные из модели.
    //VM достаточно получить данные из прослойки
    fun changedListsData()
}