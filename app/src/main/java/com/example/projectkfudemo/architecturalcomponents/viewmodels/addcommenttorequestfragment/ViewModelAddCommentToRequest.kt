package com.example.projectkfudemo.architecturalcomponents.viewmodels.addcommenttorequestfragment

import androidx.lifecycle.ViewModel
import com.example.projectkfudemo.architecturalcomponents.models.GetDataWorkCategory
import com.example.projectkfudemo.architecturalcomponents.viewmodels.ViewModelInterface
import com.example.projectkfudemo.parametrclasses.User
import com.example.projectkfudemo.parametrclasses.forjson.WorkCategoryList
import com.example.projectkfudemo.parametrclasses.requests.Request

class ViewModelAddCommentToRequest:ViewModel(), ViewModelInterface {
    override val TAG = this.javaClass.simpleName

    var workCategory:WorkCategoryList? = null
    override var user: User? = null
    var request: Request? = null

    override fun setObject(user: User) {
        getDataWorkCategory.setObject(user)
    }

    var getDataWorkCategory = GetDataWorkCategory(this)

    override fun changedData() {

    }
}