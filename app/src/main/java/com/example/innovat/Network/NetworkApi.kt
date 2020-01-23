package com.example.innovat.Network

import com.example.innovat.Model.DataResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NetworkApi {
    @GET("/s/2iodh4vg0eortkl/facts.json")
    fun getCanadaApi(): Call<DataResponse>
}