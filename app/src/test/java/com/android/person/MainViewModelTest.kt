package com.android.person

import android.os.Looper
import com.android.person.data.repository.PersonRepository
import com.android.person.data.source.FetchResponse
import com.android.person.ui.MainViewModel
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.kotlin.mock
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows

@RunWith(RobolectricTestRunner::class)
class MainViewModelTest {

    private lateinit var viewModel: MainViewModel
    private lateinit var repository: PersonRepository

    @Before
    fun setup() {
        repository = mock()
        viewModel = MainViewModel(repository)
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

    @Test
    fun handleResponseListEmpty() {
        Shadows.shadowOf(Looper.getMainLooper()).idle()
        val fetchResponse = FetchResponse(listOf(), "5")
        viewModel.handleResponse(fetchResponse)
        Assert.assertEquals(viewModel.isPersonsEmptyLiveData.value, true)
    }
}