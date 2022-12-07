package com.lsuarez.data.api

import com.lsuarez.data.util.Constant.QUERY_PARAM
import com.lsuarez.data.util.Constant.PER_PAGE
import com.lsuarez.domain.model.RepositoryResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Lorenzo on 12/5/2022.
 */

interface ItemApi {
    @GET("/search/repositories")
    suspend fun getItem(
        @Query(
            "q"
        ) q: String = QUERY_PARAM,
        @Query(
            "page"
        ) page: Int,
        @Query(
            "per_page"
        ) perPage: Int = PER_PAGE,
    ): Response<RepositoryResponse>
}