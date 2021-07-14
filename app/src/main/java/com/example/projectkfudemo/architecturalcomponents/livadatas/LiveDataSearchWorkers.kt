package com.example.projectkfudemo.architecturalcomponents.livadatas

import androidx.lifecycle.MutableLiveData
import com.example.projectkfudemo.parametrclasses.forjson.SearchWorkersList

object LiveDataSearchWorkers: MutableLiveData<SearchWorkersList>(){


    @Override
    override fun onActive() {
        super.onActive()
    }

    @Override
    override fun onInactive() {
        super.onInactive()
    }
}