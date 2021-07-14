package com.example.projectkfudemo.architecturalcomponents.viewmodels.globalsearchfragment

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.projectkfudemo.architecturalcomponents.livadatas.LiveDataSearchResultListFromServer
import com.example.projectkfudemo.architecturalcomponents.viewmodels.ViewModelInterface
import com.example.projectkfudemo.parametrclasses.User
import com.example.projectkfudemo.parametrclasses.requests.RequestList

class ViewModelGlobalSearchResult: ViewModel(), ViewModelInterface {
    override val tag = this.javaClass.simpleName
    override var user: User? =null

    override fun setObject(user: User) {
        TODO("Not yet implemented")
    }

    val liveDataSearchResultListFromServer = LiveDataSearchResultListFromServer

    override fun changedData() {
        Log.i(tag, "!выполнение setListsData")
    }

    fun setResultList(list: RequestList) {
        Log.i(tag, "!выполнение setResultList")
        Log.i(tag, "!размер list: " + list.requests.size)
        liveDataSearchResultListFromServer.postValue(list)
    }

    fun clearResultList() {
        Log.i(tag, "!запрос на очистку списка поиска")
        Log.i(tag, "!выполнение clearResultList")
        liveDataSearchResultListFromServer.postValue(null)
    }

}