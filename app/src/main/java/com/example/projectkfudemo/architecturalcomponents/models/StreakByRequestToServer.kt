package com.example.projectkfudemo.architecturalcomponents.models

import android.util.Log
import com.example.projectkfudemo.parametrclasses.User

interface StreakByRequestToServer:StreakToServer {

    fun sendRequestCurrentTask()

    fun sendRequestMyTask()


}