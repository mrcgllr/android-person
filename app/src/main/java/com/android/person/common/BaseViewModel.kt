package com.android.person.common

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.person.data.source.FetchError
import com.android.person.enums.LoadingType

abstract class BaseViewModel : ViewModel() {

    private val _progressLiveData: MutableLiveData<LoadingType> = MutableLiveData()
    val progressLiveData: LiveData<LoadingType> = _progressLiveData

    private val _errorLiveData: MutableLiveData<String> = MutableLiveData()
    val errorLiveData: LiveData<String> = _errorLiveData

    fun handleError(fetchError: FetchError?) {
        fetchError?.let {
            _errorLiveData.value = it.errorDescription
        }
    }

    fun handleLoading(type: LoadingType) {
        _progressLiveData.value = type
    }
}