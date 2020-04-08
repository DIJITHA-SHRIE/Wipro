package com.example.infosys.ViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.infosys.Model.DataResponse
import com.example.infosys.Repository.DataRepository
import org.koin.standalone.KoinComponent


class CountryViewModel(val dataRepository: DataRepository) : ViewModel(), KoinComponent {
    lateinit var canadaResponseData: MutableLiveData<DataResponse>
    lateinit var toastError: MutableLiveData<String>

    init {
        callUsers()
    }

        fun getCanadaData() {
            dataRepository.fetchStatData(object : DataRepository.CanadaData {
                override fun onSuccess(data: DataResponse) {
                    canadaResponseData.value = data
                }
                override fun onFailure(message: String) {

                }
                override fun onToast(error: String) {
                    toastError.value = error
                }
            })
        }

        fun callUsers() {
            canadaResponseData = MutableLiveData<DataResponse>()
            toastError = MutableLiveData<String>()
            if (canadaResponseData.value == null) {

                getCanadaData()
            } else {
                val oldValue = canadaResponseData.value
                canadaResponseData.value = oldValue
            }
        }

        fun refreshUser() {
            getCanadaData()
        }

        fun getCanadaDetailsOrie(): MutableLiveData<DataResponse> {
            return canadaResponseData
        }

}