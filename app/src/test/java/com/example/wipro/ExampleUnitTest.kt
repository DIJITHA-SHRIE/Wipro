package com.example.wipro

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.wipro.Model.DataResponse
import com.example.wipro.Network.mainModule
import com.example.wipro.Repository.DataRepository
import com.example.wipro.ViewModel.CountryViewModel
import io.reactivex.Observer
import org.hamcrest.CoreMatchers
import org.junit.Assert
import org.junit.Test

import org.junit.Before
import org.junit.Rule
import org.koin.dsl.module.applicationContext
import org.koin.standalone.StandAloneContext.loadKoinModules
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
    val viewModel: CountryViewModel by inject()
    val repository: DataRepository by inject()

    @Mock
    lateinit var uiData: Observer<DataResponse>

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun before(){
        MockitoAnnotations.initMocks(this)
        loadKoinModules(listOf(mainModule, applicationContext {  }))
    }

    @Test
    fun testDetail(){
        Assert.assertNotNull(viewModel.canadaResponseData.value)
    }

    @Test
    fun titleTest(){
        Assert.assertThat(viewModel.canadaResponseData.value!!.title,
            CoreMatchers.equalTo("About canada")
        )

    }

}
