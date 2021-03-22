package com.example.projectkfudemo.architecturalcomponents.livadatas

import androidx.lifecycle.MutableLiveData

object LiveDataLogAboutAssignOnOneself: MutableLiveData<String>() {
    @Override
    override fun onActive() {
        super.onActive()
    }

    @Override
    override fun onInactive() {
        super.onInactive()
    }
}