package com.example.infosys.Network

import com.example.infosys.Model.DataResponse
import retrofit2.Call
import retrofit2.http.GET

interface NetworkApi {
    @GET("/s/2iodh4vg0eortkl/facts.json")
    fun getCanadaApi(): Call<DataResponse>
}