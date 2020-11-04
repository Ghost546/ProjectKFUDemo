package com.example.projectkfudemo.architecturalcomponents.livadatas

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.projectkfudemo.requests.Request
import com.example.projectkfudemo.requests.RequestList

object LiveDataSearchResultFromServer: MutableLiveData<RequestList>() {

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