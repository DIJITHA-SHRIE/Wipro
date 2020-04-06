package com.example.infosys.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.infosys.Repository.DataRepository

class ViewModelFactory(   // We need to create instances of below dependencies to create instance of ViewModelFactory
    private val repository: DataRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {  // but this method is called by ViewModelProvider only if ViewModel wasn't already created
        return CanadaViewModel(repository) as T
    }
}