package com.example.projectkfudemo.architecturalcomponents.livadatas

import androidx.lifecycle.MutableLiveData

object LiveDataSearchDeclarersStrings: MutableLiveData<MutableList<String>>() {
    var searchDeclarersStrings: MutableList<String>? = null
    
    @Override
    override fun onActive() {
        super.onActive()
    }

    @Override
    override fun onInactive() {
        super.onInactive()
    }
}