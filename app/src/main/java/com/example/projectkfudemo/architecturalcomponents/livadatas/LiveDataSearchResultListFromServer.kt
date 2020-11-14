package com.example.projectkfudemo.architecturalcomponents.livadatas

import androidx.lifecycle.MutableLiveData
import com.example.projectkfudemo.requests.RequestList

object LiveDataSearchResultListFromServer: MutableLiveData<RequestList>() {

    @Override
    override fun onActive() {
        super.onActive()
    }

    @Override
    override fun onInactive() {
        super.onInactive()
    }
}