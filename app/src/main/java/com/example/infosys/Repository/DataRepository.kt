package com.example.infosys.Repository

import com.example.infosys.Model.DataResponse
import com.example.infosys.Network.NetworkApi
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
                    //canadaData.onToast("Url Not Found!! please try after sometime")
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