package com.example.wipro.Network

import com.example.wipro.Model.DataResponse
import retrofit2.Call
import retrofit2.http.GET

interface NetworkApi {
    @GET("/s/2iodh4vg0eortkl/facts.json")
    fun getCanadaApi(): Call<DataResponse>
}