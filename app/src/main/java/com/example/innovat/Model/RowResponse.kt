package com.example.innovat.Model

import java.io.Serializable

data class RowResponse(
    var title: String,
    var description: String,
    var imageHref: String
) : Serializable