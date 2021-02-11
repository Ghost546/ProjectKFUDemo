package com.example.projectkfudemo.architecturalcomponents.ui

import com.example.projectkfudemo.requests.RequestList

interface DataRequestList {
    fun setRequestList(requestList: RequestList)
    fun getRequestList(): RequestList
}