package com.example.innovat.View

import android.content.Context
import android.content.SharedPreferences
import android.net.ConnectivityManager
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
import com.google.gson.Gson
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding

    private val canadaViewModel: CanadaViewModel by viewModel()

    private val PREF_NAME = "DATASTORAGE"



    var  gson = Gson()




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val actionBar = supportActionBar
        val sharedPref:SharedPreferences = this.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)


        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.setLifecycleOwner(this)

        binding.canadaRecyclerView!!.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL, false
        )

        if(isOnline(applicationContext)){

        canadaViewModel.getCanadaData()
        binding.progressBar.visibility = View.VISIBLE

        canadaViewModel.canadaResponseData.observe(
            this,
            Observer(function = fun(canadaList: DataResponse?) {
                canadaList?.let {
                    binding.progressBar.visibility = View.GONE
                    actionBar!!.title = canadaList.title


                    val editor: SharedPreferences.Editor = sharedPref.edit()

                    var jsonString = gson.toJson(canadaList)
                    editor.putString("OFFLINESTORAGE", jsonString)
                    editor.putString("sample","sample")
                    editor.apply()
                    editor.commit()


                    var dataAdapter: DataAdapter = DataAdapter(canadaList.rows, applicationContext)
                    binding.canadaRecyclerView.adapter = dataAdapter


                }
            })
        )

        canadaViewModel.toastError.observe(this, Observer { res ->
            if (res != null) {
                binding.progressBar.visibility = View.GONE
                Toast.makeText(applicationContext, res, Toast.LENGTH_LONG).show()
            }

        })

    }

        else{

            binding.progressBar.visibility = View.GONE

            val json = sharedPref.getString("OFFLINESTORAGE", "")
            if(json.equals("") || json == null  ){
                Toast.makeText(applicationContext,"No Internet Available, please try to connect  once to store offline ",Toast.LENGTH_LONG).show()
            }
            else {
                val dataResponseStr = gson.fromJson(json, DataResponse::class.java)

                actionBar!!.title = dataResponseStr.title

                var dataAdapter: DataAdapter = DataAdapter(dataResponseStr.rows, applicationContext)
                binding.canadaRecyclerView.adapter = dataAdapter
            }


        }

    }

    fun isOnline(context: Context):Boolean{
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }

}
