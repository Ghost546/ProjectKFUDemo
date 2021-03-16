package com.example.projectkfudemo.parametrclasses.requests

interface RequestGeneral { //общий только для CurrentRequest и MyRequest
    fun getRequest(): Request
}