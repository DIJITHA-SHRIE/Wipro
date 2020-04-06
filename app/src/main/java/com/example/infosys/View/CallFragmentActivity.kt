package com.example.infosys.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.infosys.R

class CallFragmentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_call_fragment)

        if(savedInstanceState == null){
        val fragment = InnovatFragment()
        supportFragmentManager.beginTransaction().add(R.id.details_fragment, fragment).commit()}

    }
}