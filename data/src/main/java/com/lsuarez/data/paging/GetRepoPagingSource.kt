package com.lsuarez.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.lsuarez.data.api.ApiResponse
import com.lsuarez.data.api.ItemApi
import com.lsuarez.data.api.parseResponse
import com.lsuarez.data.util.Constant.INITIAL_PAGE
import com.lsuarez.domain.model.Item
import retrofit2.HttpException
import java.io.IOException

/**
 * Created by Lorenzo on 12/5/2022.
 */

class GetRepoPagingSource(
    private val itemApi: ItemApi
) : PagingSource<Int, Item>() {

    override fun getRefreshKey(state: PagingState<Int, Item>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Item> {
        return try {
            val page = params.key ?: INITIAL_PAGE
            return when (val result = itemApi
                .getItem(page = page)
                .parseResponse()
            ) {
                is ApiResponse.Success -> {
                    LoadResult.Page(
                        data = result.value.items,
                        prevKey = if (page == INITIAL_PAGE) null else page - 1,
                        nextKey = if (result.value.items.isEmpty()) null else page + 1
                    )
                }
                is ApiResponse.Failure -> LoadResult.Error(Throwable("error code ${result.statusCode}"))
            }
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }
}