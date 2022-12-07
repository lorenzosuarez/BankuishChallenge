package com.lsuarez.data.repository.dataSource

import androidx.paging.PagingData
import com.lsuarez.domain.model.Item
import kotlinx.coroutines.flow.Flow

/**
 * Created by Lorenzo on 12/5/2022.
 */

interface ItemRemoteDataSource {
    fun getItems(): Flow<PagingData<Item>>
}