package com.android.person.data.repository

import com.android.person.data.source.FetchCompletionHandler

interface PersonRepository {

    suspend fun fetchPerson(nextPage: String?, completionHandler: FetchCompletionHandler)
}