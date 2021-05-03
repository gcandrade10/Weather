package com.example.weathersample

import com.example.weathersample.utils.CoroutinesTestRule
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever
import retrofit2.Response

@ExperimentalCoroutinesApi
class SearchViewModelTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var coroutinesTestRule = CoroutinesTestRule()

    @Mock
    private lateinit var api: Api

    private lateinit var viewmodel: SearchViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        viewmodel = SearchViewModel(api)
    }

    @Test
    fun `search success should update mainLiveData`() {
        runBlocking {
            whenever(api.search(any())).thenReturn(Response.success(emptyList()))

            viewmodel.search("")

            val value = viewmodel.mainLiveData.getOrAwaitValue()
            Assert.assertEquals(0, value.size)
        }
    }

    @Test
    fun `search fail should update errorLiveData`() {
        runBlocking {
            whenever(api.search(any())).thenReturn(
                Response.error(
                    404,
                    ResponseBody.create(null, "")
                )
            )

            viewmodel.search("")

            val value = viewmodel.errorLiveData.getOrAwaitValue()
            Assert.assertEquals(Unit, value)
        }
    }
}