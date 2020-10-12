package com.example.projectkfudemo.architecturalcomponents.livadatas

import androidx.lifecycle.LiveData
import com.example.projectkfudemo.parametrclasses.forjson.SearchWorkers
import com.example.projectkfudemo.parametrclasses.forjson.SearchWorkersList

class LiveDataSearchWorkers: LiveData<SearchWorkersList>(){
    var searchWorkerStrings:List<String>?=null
    var searchWorkers:List<SearchWorkers>?=null

    @Override
    override fun onActive() {
        super.onActive()
    }

    @Override
    override fun onInactive() {
        super.onInactive()
    }
}