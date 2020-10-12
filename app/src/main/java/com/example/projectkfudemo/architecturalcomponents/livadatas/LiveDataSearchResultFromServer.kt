package com.example.projectkfudemo.architecturalcomponents.livadatas

import androidx.lifecycle.LiveData
import com.example.projectkfudemo.requests.RequestList

class LiveDataSearchResultFromServer: LiveData<RequestList>() {

    var requestList:RequestList ?= null

    @Override
    override fun onActive() {
        super.onActive()
    }

    @Override
    override fun onInactive() {
        super.onInactive()
    }
}