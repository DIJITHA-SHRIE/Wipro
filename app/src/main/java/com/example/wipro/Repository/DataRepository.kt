package com.example.wipro.Repository

import com.example.wipro.Model.DataResponse
import com.example.wipro.Network.NetworkApi
import retrofit2.Call
import retrofit2.Response
import java.lang.Exception

class DataRepository(val networkApi: NetworkApi) {

    fun fetchStatData(canadaData: CanadaData) {
        networkApi.getCanadaApi().enqueue(object : retrofit2.Callback<DataResponse> {
            override fun onFailure(call: Call<DataResponse>, t: Throwable) {
                canadaData.onFailure(t.localizedMessage)
            }

            override fun onResponse(call: Call<DataResponse>, response: Response<DataResponse>) {
                try {
                    canadaData.onSuccess(response.body() as DataResponse)
                } catch (e: Exception) {
                    canadaData.onToast(e.message.toString())
                }
            }
        })
    }

    interface CanadaData {
        fun onSuccess(data: DataResponse)
        fun onFailure(message: String)
        fun onToast(error: String)

    }
}