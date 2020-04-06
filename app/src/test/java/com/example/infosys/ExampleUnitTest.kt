package com.example.infosys

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.infosys.Model.DataResponse
import com.example.infosys.Network.mainModule
import com.example.infosys.Repository.DataRepository
import com.example.infosys.ViewModel.CanadaViewModel
import io.reactivex.Observer
import org.junit.Assert
import org.junit.Test

import org.junit.Before
import org.junit.Rule
import org.koin.dsl.module.applicationContext
import org.koin.standalone.StandAloneContext.startKoin
import org.koin.standalone.inject
import org.koin.test.KoinTest
import org.mockito.Mock
import org.mockito.MockitoAnnotations

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest: KoinTest {



    val viewModel: CanadaViewModel by inject()
    val repository: DataRepository by inject()

    @Mock
    lateinit var uiData: Observer<DataResponse>

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun before(){
        MockitoAnnotations.initMocks(this)
        startKoin(listOf(mainModule, applicationContext {  }))
    }

    @Test
    fun testDetail(){
        Assert.assertNotNull(viewModel.canadaResponseData.value)

    }



}