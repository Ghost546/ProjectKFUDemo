package com.example.projectkfudemo.architecturalcomponents.viewmodels

import com.example.projectkfudemo.parametrclasses.User

interface ViewModelInterface {
    val tag: String
    //объект для хранения данных о пользователе
    var user: User?
    //отправляет параметр пользователь в прослойку
    fun setObject(user: User) {

    }
    //Идея метода, в том что почти во всех VM есть список с данными который выводится на экран, есть
    //прослойка между VM и M, прослойка обращается к этому методу, чем оповещает о том что в
    //прослойке появились данные, VM не нужно знать как прослойка получает данные из модели.
    //VM достаточно получить данные из прослойки
    fun changedData()
}