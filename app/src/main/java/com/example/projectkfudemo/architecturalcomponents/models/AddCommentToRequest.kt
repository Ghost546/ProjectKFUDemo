package com.example.projectkfudemo.architecturalcomponents.models

import com.example.projectkfudemo.architecturalcomponents.viewmodels.ViewModelInterface

class AddCommentToRequest(_viewModelInterface:ViewModelInterface): ModelsByRequestToServer {
    override val TAG: String
        get() = TODO("Not yet implemented")
    override var serverRequestsByRx: ServerRequestsByRx?
        get() = TODO("Not yet implemented")
        set(value) {}

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