package com.tes.assessment.data.remote.response.trailer


import com.google.gson.annotations.SerializedName

data class TrailerResponse(
    @SerializedName("com")
    val id: Int,
    @SerializedName("results")
    val results: List<Result>
)