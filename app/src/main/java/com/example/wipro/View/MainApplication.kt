package com.example.wipro.View

import android.app.Application
import com.example.wipro.Network.mainModule
import org.koin.android.ext.android.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(
            this,
            listOf(mainModule),
            loadPropertiesFromFile = true
        )
    }

}