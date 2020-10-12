package com.example.projectkfudemo.architecturalcomponents.ui.globalsearch

import com.example.projectkfudemo.requests.RequestList

interface GlobalSearchInterface {
    fun showResultFragment(requestList: RequestList?)
}