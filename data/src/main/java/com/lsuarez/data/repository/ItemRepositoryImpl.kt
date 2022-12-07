package com.lsuarez.data.repository

import androidx.paging.PagingData
import com.lsuarez.data.repository.dataSource.ItemRemoteDataSource
import com.lsuarez.domain.model.Item
import com.lsuarez.domain.repository.ItemRepository
import kotlinx.coroutines.flow.Flow

/**
 * Created by Lorenzo on 12/5/2022.
 */

class ItemRepositoryImpl(
    private val itemRemoteDataSource: ItemRemoteDataSource
) : ItemRepository {
    override fun getItems(): Flow<PagingData<Item>> =
        itemRemoteDataSource.getItems()
}