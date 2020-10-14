package com.example.projectkfudemo.architecturalcomponents.livadatas

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.projectkfudemo.requests.Request

class LiveDataSearchResultFromServer: MutableLiveData<List<Request>>() {

    companion object{
        val temp = LiveDataSearchResultFromServer()
    }

    var requestList:List<Request> ?= null

    @Override
    override fun onActive() {
        super.onActive()
    }

    @Override
    override fun onInactive() {
        super.onInactive()
    }
}