package com.example.innovat.ViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.innovat.Model.DataResponse
import com.example.innovat.Repository.DataRepository
import org.koin.standalone.KoinComponent

class CanadaViewModel(val dataRepository: DataRepository): ViewModel(), KoinComponent {

    var canadaResponseData = MutableLiveData<DataResponse>()

    var toastError = MutableLiveData<String>()


    init {
        canadaResponseData.value
        toastError.value
    }


    fun getCanadaData() {


        dataRepository.fetchStatData(object : DataRepository.CanadaData {
            override fun onSuccess(data: DataResponse) {

                canadaResponseData.value = data

            }

            override fun onFailure(message: String) {
                Log.i("ThrowableData", message)
                //REQUEST FAILED
            }

            override fun onToast(error: String) {
                toastError.value = error
            }
        })


    }

}