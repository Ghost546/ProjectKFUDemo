package com.example.projectkfudemo.architecturalcomponents.models

import com.example.projectkfudemo.architecturalcomponents.viewmodels.ViewModelInterface

//класс прослойка отправляет запрос на сервер "назначить на себя"
class AssignOnOneselfRequestToServer (_viewModelInterface: ViewModelInterface):ModelsByRequestToServer {
    override val TAG: String
        get() = this.javaClass.simpleName

    override var serverRequestsByRx: ServerRequestsByRx? = null

    override fun sendRequestCurrentTask() {
        TODO("Not yet implemented")
    }

    override fun sendRequestMyTask() {
        TODO("Not yet implemented")
    }

    override fun sendRequest() {
        TODO("Not yet implemented")
    }

    override fun setData() {
        TODO("Not yet implemented")
    }
}