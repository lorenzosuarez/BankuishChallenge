package com.lsuarez.domain.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class RepositoryResponse(
    @SerializedName("incomplete_results")
    val incompleteResults: Boolean,
    @SerializedName("items")
    val items: List<Item>,
    @SerializedName("total_count")
    val totalCount: Int
) : Serializable