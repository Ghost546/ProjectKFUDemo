package com.example.projectkfudemo.requests

interface RequestGeneral { //общий только для CurrentRequest и MyRequest
    fun getRequest(): Request
}