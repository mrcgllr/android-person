package com.android.person.data.repository

import com.android.person.data.source.DataSource
import com.android.person.data.source.FetchCompletionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PersonRepositoryImp @Inject constructor(private val dataSource: DataSource) :
    PersonRepository {

    override suspend fun fetchPerson(nextPage: String?, completionHandler: FetchCompletionHandler) {
        withContext(Dispatchers.IO) {
            dataSource.fetch(nextPage) { fetchResponse, fetchError ->
                completionHandler(fetchResponse, fetchError)
            }
        }
    }
}