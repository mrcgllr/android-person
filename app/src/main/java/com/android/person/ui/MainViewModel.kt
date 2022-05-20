package com.android.person.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.android.person.common.BaseViewModel
import com.android.person.data.repository.PersonRepository
import com.android.person.data.source.FetchResponse
import com.android.person.data.source.Person
import com.android.person.enums.LoadingType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: PersonRepository) :
    BaseViewModel() {

    var nextPage: String? = null

    init {
        fetchPersons()
    }

    private val _personsLiveData: MutableLiveData<List<Person>> = MutableLiveData()
    val personsLiveData: LiveData<List<Person>> = _personsLiveData

    private val _isPersonsEmptyLiveData: MutableLiveData<Boolean> = MutableLiveData(false)
    val isPersonsEmptyLiveData: LiveData<Boolean> = _isPersonsEmptyLiveData

    fun fetchPersons() {
        viewModelScope.launch {
            handleLoading(LoadingType.PROGRESS)
            repository.fetchPerson(nextPage) { fetchResponse, fetchError ->
                handleError(fetchError)
                handleResponse(fetchResponse)
                handleLoading(LoadingType.COMPLETE)
            }
        }
    }

    fun handleResponse(fetchResponse: FetchResponse?) {
        fetchResponse?.apply {
            _personsLiveData.value = people
            _isPersonsEmptyLiveData.value = people.isEmpty()
            nextPage = next
        }
    }

    fun swipeToFetchData() {
        nextPage = null
        fetchPersons()
    }
}