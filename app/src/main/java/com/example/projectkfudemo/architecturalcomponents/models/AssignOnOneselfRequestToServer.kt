package com.example.projectkfudemo.architecturalcomponents.models

import com.example.projectkfudemo.architecturalcomponents.viewmodels.ViewModelInterface
import com.example.projectkfudemo.parametrclasses.User

//класс прослойка отправляет запрос на сервер "назначить на себя"
class AssignOnOneselfRequestToServer (_viewModelInterface: ViewModelInterface):ModelsByRequestToServer {
    override val tag: String
        get() = this.javaClass.simpleName

    val viewModelInterface = _viewModelInterface

    var textFromServer: String? = null

    fun setText(text: String) {
        textFromServer = text
        viewModelInterface.changedData()
    }

    override var serverRequestsByRx: ServerRequestsByRx? = null

    fun setObject(user: User) {
        setObjectByUserAndInterface(this, user)
    }


    override fun sendRequestCurrentTask() {
        TODO("Not yet implemented")
    }

    override fun sendRequestMyTask() {
        TODO("Not yet implemented")
    }

    override fun sendRequest() {
        serverRequestsByRx?.setAssignOnOneselfRequestToServer()
    }

    override fun setData() {
        serverRequestsByRx?.textAnswerFromServerByAssignOnOneself?.let { setText(it) }
    }
}