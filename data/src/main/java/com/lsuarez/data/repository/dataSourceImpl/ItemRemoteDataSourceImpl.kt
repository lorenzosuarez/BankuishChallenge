package com.lsuarez.data.repository.dataSourceImpl

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.lsuarez.data.api.ItemApi
import com.lsuarez.data.paging.GetRepoPagingSource
import com.lsuarez.data.repository.dataSource.ItemRemoteDataSource
import com.lsuarez.domain.model.Item
import kotlinx.coroutines.flow.Flow

/**
 * Created by Lorenzo on 12/5/2022.
 */

class ItemRemoteDataSourceImpl(private val itemApi: ItemApi) :
    ItemRemoteDataSource {

    override fun getItems(): Flow<PagingData<Item>> = Pager(
        config = PagingConfig(pageSize = 10, enablePlaceholders = false),
        pagingSourceFactory = { GetRepoPagingSource(itemApi) }
    ).flow
}