package com.lsuarez.domain.repository

import androidx.paging.PagingData
import com.lsuarez.domain.model.Item
import kotlinx.coroutines.flow.Flow


/**
 * Created by Lorenzo on 12/5/2022.
 */

interface ItemRepository {
    fun getItems(): Flow<PagingData<Item>>
}