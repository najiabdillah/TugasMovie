package com.tes.assessment.data.remote.response.review


import com.google.gson.annotations.SerializedName

data class ReviewResponse(
    @SerializedName("com")
    val id: Int,
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<Result>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)