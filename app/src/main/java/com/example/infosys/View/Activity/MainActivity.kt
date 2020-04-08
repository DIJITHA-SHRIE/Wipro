package com.example.infosys.View.Activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.infosys.R
import com.example.infosys.View.Fragment.InnovatFragment


class MainActivity : AppCompatActivity() {
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



