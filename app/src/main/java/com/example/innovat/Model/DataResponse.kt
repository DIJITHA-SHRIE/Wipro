package com.example.innovat.Model

import java.io.Serializable

data class DataResponse (
    var title:String,
    var rows:ArrayList<RowResponse>
): Serializable
