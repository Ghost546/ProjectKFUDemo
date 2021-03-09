package com.example.projectkfudemo.architecturalcomponents.models

import com.example.projectkfudemo.architecturalcomponents.viewmodels.ViewModelInterface
import com.example.projectkfudemo.parametrclasses.User
import com.example.projectkfudemo.parametrclasses.forjson.WorkCategoryList
import com.example.projectkfudemo.requests.RequestList

class GetDataWorkCategory(_viewModelInterface: ViewModelInterface): ModelsByRequestToServer {
    override val TAG: String = this.javaClass.simpleName

    var viewModel: ViewModelInterface? = null

    init {
        viewModel = _viewModelInterface
    }

    override var serverRequestsByRx: ServerRequestsByRx? = null

    var workCategoryList: WorkCategoryList? = null

    private fun setWorkCategory(workCategoryList: WorkCategoryList) {
        this.workCategoryList = workCategoryList
        viewModel?.setListsData()
    }

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
        serverRequestsByRx?.setWorkCategoryListByRetrofit()
    }

    override fun setData() {
        serverRequestsByRx?.workCategoryList?.let { setWorkCategory(it) }
    }
}