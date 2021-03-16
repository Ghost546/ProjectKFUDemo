package com.example.projectkfudemo.architecturalcomponents.viewmodels.globalsearchfragment

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.projectkfudemo.architecturalcomponents.livadatas.LiveDataSearchResultListFromServer
import com.example.projectkfudemo.architecturalcomponents.viewmodels.ViewModelInterface
import com.example.projectkfudemo.parametrclasses.requests.RequestList

class ViewModelGlobalSearchResult: ViewModel(), ViewModelInterface {
    override val TAG = this.javaClass.simpleName

    val liveDataSearchResultListFromServer = LiveDataSearchResultListFromServer

    override fun changedListsData() {
        Log.i(TAG, "!выполнение setListsData")
    }

    fun setResultList(list: RequestList) {
        Log.i(TAG, "!выполнение setResultList")
        Log.i(TAG, "!размер list: " + list.requests.size)
        liveDataSearchResultListFromServer.postValue(list)
    }

    fun clearResultList() {
        Log.i(TAG, "!запрос на очистку списка поиска")
        Log.i(TAG, "!выполнение clearResultList")
        liveDataSearchResultListFromServer.postValue(null)
    }

}