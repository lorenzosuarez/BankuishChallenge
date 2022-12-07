package com.lsuarez.bankuishchallenge.di

import com.lsuarez.data.api.ItemApi
import com.lsuarez.data.repository.dataSource.ItemRemoteDataSource
import com.lsuarez.data.repository.dataSourceImpl.ItemRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Created by Lorenzo on 12/5/2022.
 */

@Module
@InstallIn(SingletonComponent::class)
object RemoteDataModule {
    @Provides
    fun provideItemsRemoteDataSource(itemApi: ItemApi) : ItemRemoteDataSource =
        ItemRemoteDataSourceImpl(itemApi)
}