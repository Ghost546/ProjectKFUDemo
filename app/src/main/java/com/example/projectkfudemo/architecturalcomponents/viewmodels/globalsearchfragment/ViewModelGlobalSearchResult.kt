package com.example.projectkfudemo.architecturalcomponents.viewmodels.globalsearchfragment

import androidx.lifecycle.ViewModel
import com.example.projectkfudemo.architecturalcomponents.livadatas.LiveDataSearchResultListFromServer
import com.example.projectkfudemo.architecturalcomponents.viewmodels.ViewModelInterface
import com.example.projectkfudemo.requests.Request
import com.example.projectkfudemo.requests.RequestList

class ViewModelGlobalSearchResult: ViewModel(), ViewModelInterface {
    override val TAG = this.javaClass.simpleName

    val liveDataSearchResultListFromServer = LiveDataSearchResultListFromServer

    override fun setListsData() {
        TODO("Not yet implemented")
    }

    fun setResultList(list: RequestList) {
        liveDataSearchResultListFromServer.postValue(list)
    }

    fun clearResultList() {
        liveDataSearchResultListFromServer.postValue(null)
    }

}