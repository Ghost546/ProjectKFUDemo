package com.example.projectkfudemo.architecturalcomponents.livadatas


import androidx.lifecycle.MutableLiveData
import com.example.projectkfudemo.parametrclasses.forjson.SearchDeclarerList

object LiveDataSearchDeclarers: MutableLiveData<SearchDeclarerList>() {

    @Override
    override fun onActive() {
        super.onActive()
    }

    @Override
    override fun onInactive() {
        super.onInactive()
    }
}