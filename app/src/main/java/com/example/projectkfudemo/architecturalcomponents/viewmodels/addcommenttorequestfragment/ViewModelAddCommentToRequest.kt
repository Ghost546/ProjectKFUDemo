package com.example.projectkfudemo.architecturalcomponents.viewmodels.addcommenttorequestfragment

import androidx.lifecycle.ViewModel
import com.example.projectkfudemo.architecturalcomponents.models.AssignOnOneselfRequestToServer
import com.example.projectkfudemo.architecturalcomponents.models.GetDataWorkCategory
import com.example.projectkfudemo.architecturalcomponents.models.StreakToServer
import com.example.projectkfudemo.architecturalcomponents.viewmodels.ViewModelDefaultInterface
import com.example.projectkfudemo.parametrclasses.User
import com.example.projectkfudemo.parametrclasses.requests.RequestList

class ViewModelAddCommentToRequest:ViewModel(), ViewModelDefaultInterface {
    override val TAG = this.javaClass.simpleName

    var requestList: RequestList? = null
    var user: User? = null

    override var streakToServer: StreakToServer? = AssignOnOneselfRequestToServer(this)

    var getDataWorkCategory = GetDataWorkCategory(this)



    override fun changedListsData() {

    }
}