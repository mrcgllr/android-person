package com.android.person

import android.os.Looper
import com.android.person.data.source.FetchResponse
import com.android.person.ui.MainViewModel
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows

@RunWith(RobolectricTestRunner::class)
class MainViewModelTest {

    private lateinit var viewModel: MainViewModel

    @Before
    fun setup() {
        viewModel = MainViewModel()
    }

    @Test
    fun handleResponseList() {
        Shadows.shadowOf(Looper.getMainLooper()).idle()
        val fetchResponse = FetchResponse(listOf(), "5")
        viewModel.handleResponse(fetchResponse)
        Assert.assertEquals(viewModel.personsLiveData.value, fetchResponse.people)
    }

    @Test
    fun handleResponseNext() {
        Shadows.shadowOf(Looper.getMainLooper()).idle()
        val fetchResponse = FetchResponse(listOf(), "5")
        viewModel.handleResponse(fetchResponse)
        Assert.assertEquals(viewModel.nextPage, fetchResponse.next)
    }
}