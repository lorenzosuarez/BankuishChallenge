package com.lsuarez.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.lsuarez.data.api.ItemApi
import com.lsuarez.domain.model.Item

/**
 * Created by Lorenzo on 12/5/2022.
 */

@OptIn(ExperimentalPagingApi::class)
class ItemRemoteMediator(private val itemApi: ItemApi) :
    RemoteMediator<Int, Item>() {

    /*private val itemDao = itemDB.itemDao()

    override suspend fun load(loadType: LoadType, state: PagingState<Int, Item>): MediatorResult {
        return try {
            val page = when(loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> 1
                LoadType.APPEND -> 1
            }
            val response = itemApi.getItem(page = page, q = QUERY_PARAM, perPage = PER_PAGE)
            var endOfPaginationReached = false

            if (response.isSuccessful) {
                val responseData = response.body()
                endOfPaginationReached = responseData == null
                responseData?.let {
                    itemDB.withTransaction {
                        if (loadType == LoadType.REFRESH) { itemDao.deleteAllItems() }
                        var prevPage: Int?
                        var nextPage: Int

                        itemDao.addItems(items = responseData.items)
                    }
                }
            }
            MediatorResult.Success(endOfPaginationReached = false)
        } catch (e: Exception) {
            return MediatorResult.Error(e)
        }
    }*/
    override suspend fun load(loadType: LoadType, state: PagingState<Int, Item>): MediatorResult {
       return  MediatorResult.Success(false)
    }
}
