package com.example.projectkfudemo.architecturalcomponents.viewmodels.addcommenttorequestfragment

import androidx.lifecycle.ViewModel
import com.example.projectkfudemo.architecturalcomponents.models.GetDataWorkCategory
import com.example.projectkfudemo.architecturalcomponents.viewmodels.ViewModelInterface
import com.example.projectkfudemo.parametrclasses.User
import com.example.projectkfudemo.requests.Request
import com.example.projectkfudemo.requests.RequestList

class ViewModelAddCommentToRequest:ViewModel(), ViewModelInterface {
    override val TAG = this.javaClass.simpleName

    var requestList:RequestList? = null
    override var user: User? = null
    var request:Request? = null

    override fun setObject(user: User) {
        TODO("Not yet implemented")
    }

    var getDataWorkCategory = GetDataWorkCategory(this)

    override fun changedData() {
        TODO("Not yet implemented")
    }
}