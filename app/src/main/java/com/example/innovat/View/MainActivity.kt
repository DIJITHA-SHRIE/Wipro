package com.example.innovat.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.innovat.Model.DataResponse
import com.example.innovat.R
import com.example.innovat.ViewModel.CanadaViewModel
import com.example.innovat.databinding.ActivityMainBinding
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding

    private val canadaViewModel: CanadaViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.setLifecycleOwner(this)

        binding.canadaRecyclerView!!.layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.VERTICAL,false)



        canadaViewModel.getCanadaData()
        binding.progressBar.visibility = View.VISIBLE

        canadaViewModel.canadaResponseData.observe(this, Observer(function = fun(canadaList:  DataResponse?) {
            canadaList?.let {
                binding.progressBar.visibility = View.GONE

            }
        }))

        canadaViewModel.toastError.observe(this, Observer { res->
            if(res!=null){
                binding.progressBar.visibility = View.GONE
                Toast.makeText(applicationContext,res, Toast.LENGTH_LONG).show()
            }

        })



    }
}
