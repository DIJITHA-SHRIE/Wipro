package com.example.infosys.View

import android.content.Context
import android.net.ConnectivityManager
import android.net.ConnectivityManager.NetworkCallback
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import com.example.infosys.R


class CallFragmentActivity : AppCompatActivity() {

    var isConnected: Boolean = true
    var monitoringConnectivity: Boolean = true


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_call_fragment)

        if (savedInstanceState == null) {
            val fragment = InnovatFragment()
            supportFragmentManager.beginTransaction().replace(R.id.details_fragment, fragment)
                .commit()
        }

    }


}



