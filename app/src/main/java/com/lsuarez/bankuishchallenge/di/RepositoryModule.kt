package com.lsuarez.bankuishchallenge.di

import com.lsuarez.data.repository.ItemRepositoryImpl
import com.lsuarez.data.repository.dataSource.ItemRemoteDataSource
import com.lsuarez.domain.repository.ItemRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Created by Lorenzo on 12/5/2022.
 */

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideItemsRepository(
        itemRemoteDataSource: ItemRemoteDataSource
    ): ItemRepository =
        ItemRepositoryImpl(itemRemoteDataSource)
}