package com.example.projectkfudemo.architecturalcomponents.viewmodels.requestgeneralviewfragment

import androidx.lifecycle.ViewModel
import com.example.projectkfudemo.architecturalcomponents.models.AssignOnOneselfRequestToServer
import com.example.projectkfudemo.architecturalcomponents.models.StreakToServer
import com.example.projectkfudemo.architecturalcomponents.viewmodels.ViewModelInterface
import com.example.projectkfudemo.parametrclasses.User
import com.example.projectkfudemo.parametrclasses.requests.Request

class ViewModelRequestGeneralView:ViewModel(), ViewModelInterface {
    override val TAG: String
        get() = this.javaClass.simpleName

    var user: User? = null
    var request: Request? = null
    override var streakToServer: StreakToServer? = AssignOnOneselfRequestToServer(this)

    //метод отправляет информацию о пользователе и заявку которую нужно назначить на себя
    fun sendAssign() {

    }

    override fun changedListsData() {
        //неправильная логика интерфейсов, бесполезный метод ИМХО
    }


}