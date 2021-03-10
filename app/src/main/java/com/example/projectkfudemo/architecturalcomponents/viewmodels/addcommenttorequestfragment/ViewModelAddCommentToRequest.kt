package com.example.projectkfudemo.architecturalcomponents.viewmodels.addcommenttorequestfragment

import androidx.lifecycle.ViewModel
import com.example.projectkfudemo.architecturalcomponents.models.GetDataWorkCategory
import com.example.projectkfudemo.architecturalcomponents.viewmodels.ViewModelInterface
import com.example.projectkfudemo.parametrclasses.User

class ViewModelAddCommentToRequest:ViewModel(), ViewModelInterface {
    override val TAG = this.javaClass.simpleName

    var user: User? = null

    var getDataWorkCategory = GetDataWorkCategory(this)

    override fun setListsData() {
        TODO("Not yet implemented")
    }
}