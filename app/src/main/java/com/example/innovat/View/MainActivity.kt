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
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.innovat.Database.AppDatabase
import com.example.innovat.Database.RoomUser
import com.example.innovat.Model.DataResponse
import com.example.innovat.R
import com.example.innovat.ViewModel.CanadaViewModel
import com.example.innovat.databinding.ActivityMainBinding
import com.google.gson.Gson
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener {


    private lateinit var binding: ActivityMainBinding

    private val canadaViewModel: CanadaViewModel by viewModel()

    private val PREF_NAME = "DATASTORAGE"
    private lateinit var roomUser: DataResponse
    private lateinit var db: AppDatabase


    var gson = Gson()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val actionBar = supportActionBar
        val sharedPref: SharedPreferences =
            this.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

        db = AppDatabase(this)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.setLifecycleOwner(this)

        binding.canadaRecyclerView!!.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL, false
        )
        binding.swipeRefresh.setOnRefreshListener(this)
        binding.swipeRefresh.setColorSchemeResources(
            R.color.colorPrimary,
            android.R.color.holo_green_dark,
            android.R.color.holo_orange_dark,
            android.R.color.holo_blue_dark
        )
        // check the internet connection
        if (isOnline(applicationContext)) {

            canadaViewModel.getCanadaData()
            // binding.progressBar.visibility = View.VISIBLE
            binding.swipeRefresh.isRefreshing = true

            canadaViewModel.canadaResponseData.observe(
                this,
                Observer(function = fun(canadaList: DataResponse?) {
                    canadaList?.let {
                        // binding.progressBar.visibility = View.GONE

                        binding.swipeRefresh.isRefreshing = false
                        actionBar!!.title = canadaList.title

                        // uncomment to store data in sharedpreference
                        /*
                        val editor: SharedPreferences.Editor = sharedPref.edit()

                        var jsonString = gson.toJson(canadaList)
                        editor.putString("OFFLINESTORAGE", jsonString)
                        editor.putString("sample", "sample")
                        editor.apply()
                        editor.commit()*/


                        var dataAdapter: DataAdapter =
                            DataAdapter(canadaList.rows, applicationContext)
                        binding.canadaRecyclerView.adapter = dataAdapter


                        // store data in room

                        if (db != null && db.userDao().getCount() > 0) {
                        db.userDao().updateTodo(canadaList.title,canadaList.rows,1)
                            Log.i("RommUpdateValues", db.userDao().getAll().title)

                    }
                        else{
                            roomUser = DataResponse(0, canadaList.title, canadaList.rows)

                            db.userDao().insertAll(roomUser)

                            Log.i("RommValues", db.userDao().getAll().title)
                        }
                    }
                })
            )

            canadaViewModel.toastError.observe(this, Observer { res ->
                if (res != null) {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(applicationContext, res, Toast.LENGTH_LONG).show()
                    Log.i("RoomErr", res)
                }

            })

        } else {

            binding.progressBar.visibility = View.GONE

            // uncomment to retrive data from sharedpreference

            /*  val json = sharedPref.getString("OFFLINESTORAGE", "")
              if (json.equals("") || json == null) {
                  Toast.makeText(
                      applicationContext,
                      "No Internet Available, please try to connect  once to store offline ",
                      Toast.LENGTH_LONG
                  ).show()


              } else {
                  val dataResponseStr = gson.fromJson(json, DataResponse::class.java)

                  actionBar!!.title = dataResponseStr.title

                  var dataAdapter: DataAdapter = DataAdapter(dataResponseStr.rows, applicationContext)
                  binding.canadaRecyclerView.adapter = dataAdapter
              }*/

            if (db != null && db.userDao().getCount() > 0) {
                Log.i("RoomCount", db.userDao().getCount().toString())

               roomUser = db.userDao().getAll()
                Log.i("getAllRoom",roomUser.title)

                actionBar!!.title = roomUser.title

                var dataAdapter: DataAdapter = DataAdapter(roomUser.rows, applicationContext)
                binding.canadaRecyclerView.adapter = dataAdapter

            } else {
                Log.i("RoomCountError", "Error")
            }

        }

    }

    // call  internet connection
    fun isOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }

    override fun onRefresh() {
        if (isOnline(applicationContext)) {
            canadaViewModel.refreshUsers()
        } else {
            binding.swipeRefresh.isRefreshing = false
        }


    }

}
