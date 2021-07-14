package com.example.projectkfudemo.architecturalcomponents.viewmodels.requestgeneralviewfragment

import androidx.lifecycle.ViewModel
import com.example.projectkfudemo.architecturalcomponents.livadatas.LiveDataLogAboutAssignOnOneself
import com.example.projectkfudemo.architecturalcomponents.models.AssignOnOneselfRequestToServer
import com.example.projectkfudemo.architecturalcomponents.viewmodels.ViewModelInterface
import com.example.projectkfudemo.parametrclasses.User
import com.example.projectkfudemo.parametrclasses.requests.Request

class ViewModelRequestGeneralView:ViewModel(), ViewModelInterface {
    override val tag: String
        get() = this.javaClass.simpleName

    override var user: User? = null
    var request: Request? = null

    val liveDataLogAboutAssignOnOneself = LiveDataLogAboutAssignOnOneself

    val assignOnOneselfRequestToServer = AssignOnOneselfRequestToServer(this)

    override fun setObject(user: User) {
        assignOnOneselfRequestToServer.setObject(user)
    }

    private fun sendUserToStreak() {
        user?.let { setObject(user = it) }
    }

    fun setOnAction() {
        sendAssign()
    }

    private fun sendAssign() {
        sendUserToStreak()
        assignOnOneselfRequestToServer.sendRequest()
    }

    override fun changedData() {
        assignOnOneselfRequestToServer.textFromServer.let { liveDataLogAboutAssignOnOneself.postValue(it) }
    }

}