package com.example.projectkfudemo.architecturalcomponents.viewmodels

import com.example.projectkfudemo.architecturalcomponents.models.ModelsByRequestToServer

interface ViewModelDefaultInterface: ViewModelInterface {
    var modelsByRequestToServer: ModelsByRequestToServer?
}