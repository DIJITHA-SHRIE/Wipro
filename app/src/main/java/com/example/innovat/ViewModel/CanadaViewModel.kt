package com.example.innovat.ViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.innovat.Model.DataResponse
import com.example.innovat.Repository.DataRepository
import org.koin.standalone.KoinComponent
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.lifecycle.LiveData


class CanadaViewModel(val dataRepository: DataRepository) : ViewModel(), KoinComponent {


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
                Log.i("ThrowableData", message)
                //REQUEST FAILED
            }

            override fun onToast(error: String) {
                toastError.value = error
            }
        })


    }


    fun callUsers() {
        canadaResponseData =  MutableLiveData<DataResponse>()
        toastError = MutableLiveData<String>()
        if(canadaResponseData.value == null){

        getCanadaData()}
        else{
            val oldValue = canadaResponseData.value
            canadaResponseData.value = oldValue
        }

    }

    fun refreshUser(){
        getCanadaData()
    }

    fun getCanadaDetailsOrie(): MutableLiveData<DataResponse> {

        return canadaResponseData

    }

}