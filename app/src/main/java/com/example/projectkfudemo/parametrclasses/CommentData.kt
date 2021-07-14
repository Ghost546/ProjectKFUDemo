package com.example.projectkfudemo.parametrclasses

import android.icu.text.BidiClassifier
import com.example.projectkfudemo.parametrclasses.requests.MyRequest
import java.util.*

data class CommentData(var myRequest: MyRequest, var beginDate: String,
                       var endData: String, var comment: String, var classifier: String,
                       var typeOfActionWithComment: String, var result: String, var summury: String,
                       var recomend: String, var url: String, var status: String,
                       var executor: String, var wid: String, var check: String,
                       var inventoryCard: String, var files:String)
