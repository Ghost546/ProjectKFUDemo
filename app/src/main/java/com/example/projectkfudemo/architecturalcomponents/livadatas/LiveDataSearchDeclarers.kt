package com.example.projectkfudemo.architecturalcomponents.livadatas

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.projectkfudemo.parametrclasses.forjson.SearchDeclarer
import com.example.projectkfudemo.parametrclasses.forjson.SearchDeclarerList

object LiveDataSearchDeclarers: MutableLiveData<SearchDeclarerList>() {
    var searchDeclarers:SearchDeclarerList?=null

    @Override
    override fun onActive() {
        super.onActive()
    }

    @Override
    override fun onInactive() {
        super.onInactive()
    }
}