package com.example.projectkfudemo.architecturalcomponents.viewmodels.requestgeneralviewfragment

import androidx.lifecycle.ViewModel
import com.example.projectkfudemo.architecturalcomponents.ui.ViewModelGet
import com.example.projectkfudemo.architecturalcomponents.viewmodels.ViewModelInterface
import com.example.projectkfudemo.parametrclasses.User
import com.example.projectkfudemo.requests.Request

class ViewModelRequestGeneralView:ViewModel(), ViewModelInterface {
    var user: User? = null

    fun sendAssign() {

    }

    override val TAG: String
        get() = this.javaClass.simpleName

    override fun setListsData() {
        //неправильная логика интерфейсов, бесполезный метод ИМХО
    }


}