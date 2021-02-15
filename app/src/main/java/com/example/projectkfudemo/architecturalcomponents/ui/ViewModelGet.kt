package com.example.projectkfudemo.architecturalcomponents.ui

import androidx.lifecycle.ViewModel
import com.example.projectkfudemo.architecturalcomponents.viewmodels.ViewModelTasksInterface

interface ViewModelGet {
    fun getViewModel(): ViewModelTasksInterface
}