package com.example.projectkfudemo.architecturalcomponents.livadatas

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.projectkfudemo.parametrclasses.forjson.SearchWorkers
import com.example.projectkfudemo.parametrclasses.forjson.SearchWorkersList

object LiveDataSearchWorkers: MutableLiveData<SearchWorkersList>(){

    var searchWorkers:SearchWorkersList?=null

    @Override
    override fun onActive() {
        super.onActive()
    }

    @Override
    override fun onInactive() {
        super.onInactive()
    }
}