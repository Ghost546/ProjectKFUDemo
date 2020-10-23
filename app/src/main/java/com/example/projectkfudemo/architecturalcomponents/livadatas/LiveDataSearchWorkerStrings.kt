package com.example.projectkfudemo.architecturalcomponents.livadatas

import androidx.lifecycle.MutableLiveData

object LiveDataSearchWorkerStrings: MutableLiveData<MutableList<String>>() {
    var searchWorkerStrings: MutableList<String>?=null

    @Override
    override fun onActive() {
        super.onActive()
    }

    @Override
    override fun onInactive() {
        super.onInactive()
    }
}