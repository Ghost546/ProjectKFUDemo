package com.example.projectkfudemo.architecturalcomponents.livadatas

import androidx.lifecycle.LiveData
import com.example.projectkfudemo.parametrclasses.forjson.SearchDeclarer
import com.example.projectkfudemo.parametrclasses.forjson.SearchDeclarerList

class LiveDataSearchDeclarers: LiveData<SearchDeclarerList>() {
    var searchDeclarerStrings:List<String>?=null
    var searchDeclarers:List<SearchDeclarer>?=null



    @Override
    override fun onActive() {
        super.onActive()
    }

    @Override
    override fun onInactive() {
        super.onInactive()
    }
}