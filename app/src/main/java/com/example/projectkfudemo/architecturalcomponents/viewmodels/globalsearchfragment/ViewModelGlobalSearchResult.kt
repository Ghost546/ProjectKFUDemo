package com.example.projectkfudemo.architecturalcomponents.viewmodels.globalsearchfragment

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.projectkfudemo.architecturalcomponents.livadatas.LiveDataSearchResultListFromServer
import com.example.projectkfudemo.architecturalcomponents.viewmodels.ViewModelInterface
import com.example.projectkfudemo.requests.RequestList

class ViewModelGlobalSearchResult: ViewModel(), ViewModelInterface {
    override val TAG = this.javaClass.simpleName

    val liveDataSearchResultListFromServer = LiveDataSearchResultListFromServer

    override fun setListsData() {
        Log.i(TAG, "!выполнение setListsData")
    }

    fun setResultList(list: RequestList) {
        Log.i(TAG, "!выполнение setResultList")
        Log.i(TAG, "!размер list: " + list.requests.size)
        liveDataSearchResultListFromServer.postValue(list)
    }

    fun clearResultList() {
        liveDataSearchResultListFromServer.value = null
    }

}