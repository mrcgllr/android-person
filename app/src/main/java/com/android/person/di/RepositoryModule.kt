package com.android.person.di

import com.android.person.data.repository.PersonRepository
import com.android.person.data.repository.PersonRepositoryImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindPersonRepository(repositoryImp: PersonRepositoryImp): PersonRepository
}